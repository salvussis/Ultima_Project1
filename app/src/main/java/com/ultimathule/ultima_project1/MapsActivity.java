package com.ultimathule.ultima_project1;

import android.location.Address;
import android.location.Geocoder;
import android.media.ExifInterface;
import android.os.Environment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    static final String TAG = "AppTest";
    private GoogleMap mMap;
    ExifInterface exif;
    Geocoder mGeocoder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        getExifInfo();


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

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
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

                        //緯度と経度を一緒に表示させる（geododerは別々なので使わない）
                        //  String info = String.format("%f, %f", latlng[0], latlng[1]);

                        //緯度経度確認用
                        Log.d(TAG, "Exif_info ①: " + latlng[0] + "," + latlng[1]);
                        Log.d(TAG, "Exif_info ②: " + latlng[0]);
                        Log.d(TAG, "Exif_info ③: " + latlng[1]);

                      /*
                      ③Geocoderのプログラミング
                       */
                        //可変長のStringクラス
                        StringBuilder builder = new StringBuilder();
                        try {
                            //Localeクラス:(Public Constructors)は"Geocoder(Context context, Locale locale)"
                            // getDefault()メソッド以外に”ENGLISH”等にすると表示の言語を変えられる
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


                          /*　＊＊＊＊ここを別クラスにする＊＊＊＊
                          ④部分一致検索プログラミング（都道府県を選別する）
                          */
                            //String tokyo = "東京都";
                            //String saitama = "埼玉県";
                            final String TOKYO = "Tokyo";
                            final String SAITAMA = "Saitama Prefecture";
                            String ken = builder.toString();

                            //tokyo.indexOf(ken)では動作しないので注意
                            if(ken.indexOf(TOKYO) != -1){

                                Log.d(TAG, "住所（東京）：　" + builder.toString());

                            } else  if(ken.indexOf(SAITAMA) != -1){

                                Log.d(TAG, "住所（埼玉）：　" + builder.toString());

                            } else {

                                Log.d(TAG, "住所（？？？）：　" + builder.toString());
                            }


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


}
