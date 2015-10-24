package com.ultimathule.ultima_project1;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.media.ExifInterface;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    static final String TAG = "AppTest";
    private static GoogleMap mMap;
    private static ExifInterface exif;
    private static Geocoder mGeocoder;
    private MySQLiteHelper mySQLiteHelper;

    //marker用
    Marker marker;
    private LatLng idoKeido;//static?
    private ArrayList<String> arrayList = new ArrayList<>();//static?
    static LatLng TOKYO = new LatLng(35.691563, 139.785568);//カメラの位置


    Button goToSecondActivity;//Intent用

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //Ⅰ DBに初期データ登録
        mySQLiteHelper = new MySQLiteHelper(MapsActivity.this);
        mySQLiteHelper.getWritableDatabase();

        //Ⅱ Exif情報読み込みメソッド呼び出し
        getExifInfo();//jpgファイル、exif情報取得のメソッド

        //Ⅳ フラグ１の県を取り出すメソッド呼び出し
        mySQLiteHelper.readFlg();


        goToSecondActivity = (Button)findViewById(R.id.button);
        goToSecondActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
                startActivity(intent);
            }
        });



    }

    public void getExifInfo() {
        //①以下の方法だと/storage/emulated/0/DCIMまで取得できる
        File file = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
        String direc = file.getAbsolutePath();
        Log.d(TAG, "Path: " + direc);//pathを確認

        //関数searchFilesを呼び出し、
        //①target_dirにあるjpgファイルを取得する。
        searchFiles(direc + "/Camera/", ".*..jpg", false);
    }


    //ディレクリ内のファイル(ここではjpgファイル)をすべて読み込む
    public ArrayList<String> searchFiles(String dir_path, String expr, boolean search_subdir) {
        final File dir = new File(dir_path);

        ArrayList<String> find_files = new ArrayList<>();
        final File[] files = dir.listFiles();

        if (null != files) {
            for (int i = 0; i < files.length; ++i) {
                //pathとjpg情報確認用
                Log.d(TAG, "AbsolutePath: " + files[i].toString());

                //②exif
                try {

                    exif = new ExifInterface(files[i].toString());
                    float[] latlng = new float[2];

                    //exif情報を確認。trueなら情報を表示する
                    if (exif.getLatLong(latlng) == true) {


                        //緯度経度確認用
                        Log.d(TAG, "Exif_info ①: " + latlng[0] + "," + latlng[1]);
//                        Log.d(TAG, "Exif_info ②: " + latlng[0]);
//                        Log.d(TAG, "Exif_info ③: " + latlng[1]);

                        //markersを表示するため
                        //緯度と経度を一緒に表示させる方法
                        String infoIchi = String.format("%f, %f", latlng[0], latlng[1]);
                        //ArrayListに全ての写真の位置情報を入れる
                        arrayList.add(infoIchi);
                        Log.d(TAG, "arrayList: " + arrayList);

                      /*
                      ③Geocoderのプログラミング
                       */
                        //可変長のStringクラス
                        StringBuilder builder = new StringBuilder();
                        try {
                            //Localeクラス:(Public Constructors)は"Geocoder(Context context, Locale locale)"
                            // getDefault()メソッド以外に”ENGLISH”等にzすると表示の言語を変えられる
                            //mGeocoder = new Geocoder(getApplicationContext(), Locale.JAPANESE);
                            mGeocoder = new Geocoder(getApplicationContext(), Locale.ENGLISH);//英語で住所を表示

                            //Geocoderクラスのメソッド 住所を取得（第一、第二引数はdouble型。第三引数は取得するアドレスの最大数。1でいい）
                            List<Address> addresses = mGeocoder.getFromLocation(latlng[0], latlng[1], 1);
                            for(Address addr : addresses){
                                int index = addr.getMaxAddressLineIndex();
                                for(int j = 1; j <= index; ++j){
                                    builder.append(addr.getAddressLine(j));
                                }
                            }
                            //Geocoder確認用。住所が確認できる
                            Log.d(TAG, "geocoder: " + builder.toString());

                            //Ⅲ 写真のあった県のフラグを１にする
                            //String型に一度、住所を格納
                            String ken = builder.toString();
                            Log.d(TAG, "ken: " + ken);
                            //メソッド呼び出し
                            bubunIchi(ken);


                        } catch (IOException e){
                            e.printStackTrace();
                        }
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
        return find_files;

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        setMarker();
    }

    public void setMarker() {
        //③ArrayListに入っている写真のデータ数分、ループさせる
        for(int i = 0; i < arrayList.size(); i++) {
            String kakunou = arrayList.get(i);//配列の要素を０から順に一度格納する
            Log.d(TAG, "Marker arrayList(要素): " + kakunou) ;

             String[] wakeru = kakunou.split(",");//LatLngの引数にするため緯度・経度を分けなければいけない
            double lat = Double.parseDouble(wakeru[0]);//緯度
            double lng = Double.parseDouble(wakeru[1]);//経度

            idoKeido = new LatLng(lat, lng);//LatLngクラス
            marker = mMap.addMarker(new MarkerOptions()
            .position(idoKeido));

            Log.d(TAG, "Marker arrayList: " + arrayList.toString()) ;

        }

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(TOKYO, 10));
        Log.d(TAG, "7") ;

    }

    /*Ⅲ
     写真のあった県のフラグを１にするメソッド呼び出し
     部分一致検索プログラミング（都道府県を選別する）
     */
    public void bubunIchi(String ken) {
        //final String TOKYO = "Tokyo";
        final String HOKKAIDO = "Hokkaido Prefecture";
        final String AOMORI = "Aomori Prefecture";
        final String IWATE = "Iwate Prefecture";
        final String MIYAGI = "Miyagi Prefecture";
        final String AKITA = "Akita Prefecture";
        final String YAMAGATA = "Yamagata Prefecture";
        final String FUKUSHIMA = "Fukushima Prefecture";
        final String IBARAKI = "Ibaraki Prefecture";
        final String TOCHIGI = "Tochigi Prefecture";
        final String GUNMA = "Gunma Prefecture";
        final String SAITAMA = "Saitama Prefecture";
        final String CHIBA = "Chiba Prefecture";
        final String TOKYO = "Tokyo";
        final String KANAGAWA = "Kanagawa Prefecture";
        final String YAMANASHI = "Yamanashi Prefecture";
        final String NIIGATA = "Niigata Prefecture";
        final String TOYAMA = "Toyama Prefecture";
        final String ISHIKAWA = "Ishikawa Prefecture";
        final String FUKUI = "Fukui Prefecture";
        final String NAGANO = "Nagano Prefecture";
        final String GIFU = "Gifu Prefecture";
        final String SHIZUOKA = "Shizuoka Prefecture";
        final String AICHI = "Aichi Prefecture";
        final String MIE = "Mie Prefecture";
        final String SHIGA = "Shiga Prefecture";
        final String KYOTO = "Kyoto Prefecture";
        final String OSAKA = "Osaka Prefecture";
        final String HYOGO = "Hyogo Prefecture";
        final String NARA = "Nara Prefecture";
        final String WAKAYAMA = "Wakayama Prefecture";
        final String TOTTORI = "Tottori Prefecture";
        final String SHIMANE = "Shimane Prefecture";
        final String OKAYAMA = "Okayama Prefecture";
        final String HIROSHIMA = "Hiroshima Prefecture";
        final String YAMAGUCHI = "Yamaguchi Prefecture";
        final String TOKUSHIMA = "Tokushima Prefecture";
        final String KAGAWA = "Kagawa Prefecture";
        final String EHIME = "Ehime Prefecture";
        final String KOCHI = "Kochi Prefecture";
        final String FUKUOKA = "Fukuoka Prefecture";
        final String SAGA = "Saga Prefecture";
        final String NAGASAKI = "Nagasaki Prefecture";
        final String KUMAMOTO = "Kumamoto Prefecture";
        final String OITA = "Oita Prefecture";
        final String MIYAZAKI = "Miyazaki Prefecture";
        final String KAGOSHIMA = "Kagoshima Prefecture";
        final String OKINAWA = "Okinawa Prefecture";

        Log.d(TAG, "ken(bubunIchi): " + ken);
        String state;

        //1.containsメソッドで部分一致検索
        if(ken.contains(HOKKAIDO)){
            state = HOKKAIDO;
            mySQLiteHelper.updateFlg(state);

        }else if(ken.contains(AOMORI)){
            state = AOMORI;
            mySQLiteHelper.updateFlg(state);

        }else if(ken.contains(IWATE)){
            state = IWATE;
            mySQLiteHelper.updateFlg(state);

        }else if(ken.contains(MIYAGI)){
            state = MIYAGI;
            mySQLiteHelper.updateFlg(state);

        }else if(ken.contains(AKITA)){
            state = AKITA;
            mySQLiteHelper.updateFlg(state);

        }else if(ken.contains(YAMAGATA)){
            state = YAMAGATA;
            mySQLiteHelper.updateFlg(state);

        }else if(ken.contains(FUKUSHIMA)){
            state = FUKUSHIMA;
            mySQLiteHelper.updateFlg(state);

        }else if(ken.contains(IBARAKI)){
            state = IBARAKI;
            mySQLiteHelper.updateFlg(state);

        }else if(ken.contains(TOCHIGI)){
            state = TOCHIGI;
            mySQLiteHelper.updateFlg(state);

        }else if(ken.contains(GUNMA)){
            state = GUNMA;
            mySQLiteHelper.updateFlg(state);

        }else if(ken.contains(SAITAMA)){
            state = SAITAMA;
            mySQLiteHelper.updateFlg(state);

        }else if(ken.contains(CHIBA)){
            state = CHIBA;
            mySQLiteHelper.updateFlg(state);

        }else if(ken.contains(TOKYO)){
            //2.一致したら、Tokyoを引数にしてupdateFlgメソッドを呼ぶ
            state = TOKYO;
            mySQLiteHelper.updateFlg(state);
            Log.d(TAG, "選別用(東京)：　" + state.toString());
            Log.d(TAG, "選別用(東京)" + ken);

        }else if(ken.contains(KANAGAWA)){
            state = KANAGAWA;
            mySQLiteHelper.updateFlg(state);

        }else if(ken.contains(YAMANASHI)){
            state = YAMANASHI;
            mySQLiteHelper.updateFlg(state);

        }else if(ken.contains(NIIGATA)){
            state = NIIGATA;
            mySQLiteHelper.updateFlg(state);

        }else if(ken.contains(TOYAMA)){
            state = TOYAMA;
            mySQLiteHelper.updateFlg(state);

        }else if(ken.contains(ISHIKAWA)){
            state = ISHIKAWA;
            mySQLiteHelper.updateFlg(state);

        }else if(ken.contains(FUKUI)){
            state = FUKUI;
            mySQLiteHelper.updateFlg(state);

        }else if(ken.contains(NAGANO)){
            state = NAGANO;
            mySQLiteHelper.updateFlg(state);

        }else if(ken.contains(GIFU)){
            state = GIFU;
            mySQLiteHelper.updateFlg(state);

        }else if(ken.contains(SHIZUOKA)){
            state = SHIZUOKA;
            mySQLiteHelper.updateFlg(state);

        }else if(ken.contains(AICHI)){
            state = AICHI;
            mySQLiteHelper.updateFlg(state);

        }else if(ken.contains(MIE)){
            state = MIE;
            mySQLiteHelper.updateFlg(state);

        }else if(ken.contains(SHIGA)){
            state = SHIGA;
            mySQLiteHelper.updateFlg(state);

        }else if(ken.contains(KYOTO)){
            state = KYOTO;
            mySQLiteHelper.updateFlg(state);

        }else if(ken.contains(OSAKA)){
            state = OSAKA;
            mySQLiteHelper.updateFlg(state);

        }else if(ken.contains(HYOGO)){
            state = HYOGO;
            mySQLiteHelper.updateFlg(state);

        }else if(ken.contains(NARA)){
            state = NARA;
            mySQLiteHelper.updateFlg(state);

        }else if(ken.contains(WAKAYAMA)){
            state = WAKAYAMA;
            mySQLiteHelper.updateFlg(state);

        }else if(ken.contains(TOTTORI)){
            state = TOTTORI;
            mySQLiteHelper.updateFlg(state);

        }else if(ken.contains(SHIMANE)){
            state = SHIMANE;
            mySQLiteHelper.updateFlg(state);

        }else if(ken.contains(OKAYAMA)){
            state = OKAYAMA;
            mySQLiteHelper.updateFlg(state);

        }else if(ken.contains(HIROSHIMA)){
            state = HIROSHIMA;
            mySQLiteHelper.updateFlg(state);

        }else if(ken.contains(YAMAGUCHI)){
            state = YAMAGUCHI;
            mySQLiteHelper.updateFlg(state);

        }else if(ken.contains(TOKUSHIMA)){
            state = TOKUSHIMA;
            mySQLiteHelper.updateFlg(state);

        }else if(ken.contains(KAGAWA)){
            state = KAGAWA;
            mySQLiteHelper.updateFlg(state);

        }else if(ken.contains(EHIME)){
            state = EHIME;
            mySQLiteHelper.updateFlg(state);

        }else if(ken.contains(KOCHI)){
            state = KOCHI;
            mySQLiteHelper.updateFlg(state);

        }else if(ken.contains(FUKUOKA)){
            state = FUKUOKA;
            mySQLiteHelper.updateFlg(state);

        }else if(ken.contains(SAGA)){
            state = SAGA;
            mySQLiteHelper.updateFlg(state);

        }else if(ken.contains(NAGASAKI)){
            state = NAGASAKI;
            mySQLiteHelper.updateFlg(state);

        }else if(ken.contains(KUMAMOTO)){
            state = KUMAMOTO;
            mySQLiteHelper.updateFlg(state);

        }else if(ken.contains(OITA)){
            state = OITA;
            mySQLiteHelper.updateFlg(state);

        }else if(ken.contains(MIYAZAKI)){
            state = MIYAZAKI;
            mySQLiteHelper.updateFlg(state);

        }else if(ken.contains(KAGOSHIMA)){
            state = KAGOSHIMA;
            mySQLiteHelper.updateFlg(state);

        }else if(ken.contains(OKINAWA)){
            state = OKINAWA;
            mySQLiteHelper.updateFlg(state);

        }


    }


}
