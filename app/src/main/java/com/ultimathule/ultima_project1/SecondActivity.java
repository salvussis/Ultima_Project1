package com.ultimathule.ultima_project1;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolygonOptions;

public class SecondActivity extends FragmentActivity implements OnMapReadyCallback {
    static final String TAG = "AppTest";
    private GoogleMap mMap;
    MySQLiteHelper mySQLiteHelper;

    Button goToMainActivity;
        static LatLng TOKYO = new LatLng(35.691563, 139.785568);//カメラの位置

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map2);
        mapFragment.getMapAsync(this);

        goToMainActivity = (Button)findViewById(R.id.button2);
        goToMainActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
                startActivity(intent);
            }
        });
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

        showBoundary();

            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(TOKYO, 5));
                // Add a marker in Sydney and move the camera
//                LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

        //フラグが１の県をswitch文で各メソッドを呼び出す
    public void showBoundary() {

        Log.d(TAG, "SecondActivity:: " + mySQLiteHelper.boundaryArray);

       for(int i = 0; i < mySQLiteHelper.boundaryArray.size(); i++) {
           int boundaryId = Integer.parseInt(mySQLiteHelper.boundaryArray.get(i));

           Log.d(TAG, "SecondActivity2:: " + boundaryId);

           switch (boundaryId) {
               case 1:  break;
               case 2:  break;
               case 3:  break;
               case 4: Log.d(TAG, "method_test:宮城"); break;
               case 5:  break;
               case 6:  break;
               case 7:  break;
               case 8:  break;
               case 9:  break;
               case 10:  break;
               case 11: Log.d(TAG, "method_test:埼玉"); break;
               case 12:  break;
               case 13: Log.d(TAG, "method_test:東京"); tokyoBoundary(); break;
               case 14:  break;
               case 15:  break;
               case 16:  break;
               case 17:  break;
               case 18:  break;
               case 19:  break;
               case 20:  break;
               case 21:  break;
               case 22:  break;
               case 23:  break;
               case 24:  break;
               case 25:  break;
               case 26:  break;
               case 27:  break;
               case 28:  break;
               case 29:  break;
               case 30:  break;
               case 31:  break;
               case 32:  break;
               case 33:  break;
               case 34:  break;
               case 35:  break;
               case 36:  break;
               case 37:  break;
               case 38:  break;
               case 39:  break;
               case 40:  break;
               case 41:  break;
               case 42:  break;
               case 43:  break;
               case 44:  break;
               case 45:  break;
               case 46:  break;
               case 47:  break;
           }

       }
    }

    public void tokyoBoundary() {

            //ポリゴンを描画(練習　宮城県①）if文で呼び出す？
            PolygonOptions rectOptions = new PolygonOptions()
                    .add(new LatLng(38.923000393000123, 141.64527428500006),
                            new LatLng(38.872626044000086, 141.66879316499998),
                            new LatLng(38.851955471000068, 141.67359459700018),
                            new LatLng(38.864325262000122, 141.65211022200006),
                            new LatLng(38.869086005000113, 141.64926191499998),
                            new LatLng(38.882635809000178, 141.64568118600002),
                            new LatLng(38.888495184000121, 141.64210045700017),
                            new LatLng(38.891099351000108, 141.63672936300011),
                            new LatLng(38.889715887000037, 141.63152103000019),
                            new LatLng(38.886908270000092, 141.62525475400017),
                            new LatLng(38.885443427000112, 141.61784915500007),
                            new LatLng(38.885199286000088, 141.59864342500012),
                            new LatLng(38.882228908000073, 141.58855228000007),
                            new LatLng(38.873114325000174, 141.58448326900012),
                            new LatLng(38.854437567, 141.58375084700006),
                            new LatLng(38.835109768000066, 141.586192254),
                            new LatLng(38.826076565000065, 141.58936608200005),
                            new LatLng(38.819240627000127, 141.58708743600019),
                            new LatLng(38.806586005000085, 141.57325280000006),
                            new LatLng(38.780829169000029, 141.53052819100017),
                            new LatLng(38.768133856000176, 141.52165774800002),
                            new LatLng(38.763332424000097, 141.53199303500006),
                            new LatLng(38.756252346000124, 141.53882897200006),
                            new LatLng(38.740790106000091, 141.54908287900017),
                            new LatLng(38.72882721600017, 141.56706790500002),
                            new LatLng(38.718654690000122, 141.57260175900015),
                            new LatLng(38.703558661000116, 141.56641686300006),
                            new LatLng(38.699530341000141, 141.55974368600013),
                            new LatLng(38.70258209800015, 141.55445397200006),
                            new LatLng(38.707017320000105, 141.54908287900017),
                            new LatLng(38.707261460000055, 141.54281660200004),
                            new LatLng(38.704087632, 141.53785241000017),
                            new LatLng(38.689927476000079, 141.52540123800006),
                            new LatLng(38.684271552000084, 141.51587975400017),
                            new LatLng(38.677557684000035, 141.49105879000004),
                            new LatLng(38.672552802, 141.480723504),
                            new LatLng(38.66136302299999, 141.46306399800019),
                            new LatLng(38.655462958000143, 141.46314537900017),
                            new LatLng(38.645209052000112, 141.47388756600017),
                            new LatLng(38.640855210000112, 141.48519941500015),
                            new LatLng(38.638413804, 141.53223717500006),
                            new LatLng(38.632025458000086, 141.53516686300011),
                            new LatLng(38.617905992000047, 141.52808678500006),
                            new LatLng(38.603827216000084, 141.51775149800002),
                            new LatLng(38.597398179, 141.51140384200019),
                            new LatLng(38.569525458000143, 141.46705162900017),
                            new LatLng(38.557318427000055, 141.48308353000007),
                            new LatLng(38.541652736000074, 141.49903405000018),
                            new LatLng(38.534816799000069, 141.51465905000023),
                            new LatLng(38.549017645000092, 141.52914472699999),
                            new LatLng(38.533433335, 141.54224694100017),
                            new LatLng(38.518622137000122, 141.54761803500006),
                            new LatLng(38.50214264500012, 141.54712975400005),
                            new LatLng(38.481390692000119, 141.54281660200004),
                            new LatLng(38.484076239000032, 141.53174889400012),
                            new LatLng(38.489691473, 141.52418053500006),
                            new LatLng(38.497951565, 141.51872806100019),
                            new LatLng(38.508042710000055, 141.51482181100013),
                            new LatLng(38.470445054000081, 141.50066165500019),
                            new LatLng(38.460882880000085, 141.49496504000004),
                            new LatLng(38.45791250200007, 141.50074303500017),
                            new LatLng(38.454657294000029, 141.50326582099999),
                            new LatLng(38.450995184000149, 141.50489342500012),
                            new LatLng(38.446600653000175, 141.50798587300019),
                            new LatLng(38.440619208000058, 141.48292076900012),
                            new LatLng(38.435736395, 141.47144616000006),
                            new LatLng(38.429510809000035, 141.47046959700018),
                            new LatLng(38.418443101000022, 141.47722415500013),
                            new LatLng(38.409735419000114, 141.476817254),
                            new LatLng(38.40155670800003, 141.47754967500023),
                            new LatLng(38.392035223, 141.487559441),
                            new LatLng(38.399603583000058, 141.49903405000018),
                            new LatLng(38.402329820000134, 141.51840254),
                            new LatLng(38.398179429, 141.53760826900023),
                            new LatLng(38.385158596000011, 141.54908287900017),
                            new LatLng(38.389837958000115, 141.53419030000023),
                            new LatLng(38.390529690000065, 141.52035566500004),
                            new LatLng(38.385158596000011, 141.50904381600006),
                            new LatLng(38.371527411000059, 141.50123131600006),
                            new LatLng(38.359930731000034, 141.52523847699999),
                            new LatLng(38.339544989000061, 141.52930748800017),
                            new LatLng(38.317938544000143, 141.52979576900023),
                            new LatLng(38.302639065, 141.54281660200004),
                            new LatLng(38.295070705000157, 141.537364129),
                            new LatLng(38.267808335000055, 141.52540123800006),
                            new LatLng(38.270453192000147, 141.51856530000023),
                            new LatLng(38.292385158000073, 141.49122155000006),
                            new LatLng(38.295599677000112, 141.484629754),
                            new LatLng(38.296087958000115, 141.46998131600017),
                            new LatLng(38.299221096000096, 141.46338951900012),
                            new LatLng(38.306341864000146, 141.45997155000012),
                            new LatLng(38.312404690000122, 141.46338951900012),
                            new LatLng(38.317816473000093, 141.46753991000006),
                            new LatLng(38.323065497000172, 141.46705162900017),
                            new LatLng(38.330877997000144, 141.457774285),
                            new LatLng(38.343573309000035, 141.42554772200018),
                            new LatLng(38.350246486000074, 141.43083743600008),
                            new LatLng(38.371527411000059, 141.439707879),
                            new LatLng(38.380438544000086, 141.42807050899998),
                            new LatLng(38.380682684000035, 141.41944420700011),
                            new LatLng(38.377915757000082, 141.41049238400015),
                            new LatLng(38.377671617000132, 141.39812259200014),
                            new LatLng(38.380845445000105, 141.38624108200005),
                            new LatLng(38.385443427000141, 141.37614993600019),
                            new LatLng(38.40224844000015, 141.35377037900017),
                            new LatLng(38.409002997000087, 141.33318118600008),
                            new LatLng(38.407904364000146, 141.30445397200012),
                            new LatLng(38.394110419000029, 141.22950280000023),
                            new LatLng(38.38580963700015, 141.202403191),
                            new LatLng(38.37116120000006, 141.18091881600017),
                            new LatLng(38.347316799000154, 141.17212975400005),
                            new LatLng(38.324367580000072, 141.17497806100008),
                            new LatLng(38.322821356000034, 141.16968834700018),
                            new LatLng(38.323065497000172, 141.15211022200006),
                            new LatLng(38.329494533000073, 141.14771569100017),
                            new LatLng(38.371527411000059, 141.13184655000023),
                            new LatLng(38.366522528000118, 141.12322024800002),
                            new LatLng(38.36465078300013, 141.11459394600004),
                            new LatLng(38.366156317, 141.10596764400023),
                            new LatLng(38.371527411000059, 141.09782962300019),
                            new LatLng(38.360907294000114, 141.07943769600016),
                            new LatLng(38.346625067000119, 141.06470787900017),
                            new LatLng(38.32916901200015, 141.05420983200011),
                            new LatLng(38.309475002000013, 141.04867597699999),
                            new LatLng(38.313706773000135, 141.05836022200006),
                            new LatLng(38.314886786000059, 141.06959069100006),
                            new LatLng(38.311590887000037, 141.07911217500023),
                            new LatLng(38.302639065, 141.08350670700023),
                            new LatLng(38.29490794499999, 141.08057701900012),
                            new LatLng(38.289536851000136, 141.07252037900017),
                            new LatLng(38.284735419000143, 141.06226647200006),
                            new LatLng(38.27871328300013, 141.05274498800023),
                            new LatLng(38.244859117000161, 141.01734459700006),
                            new LatLng(38.207017320000134, 140.98796634200002),
                            new LatLng(38.170477606000176, 140.96680748800023),
                            new LatLng(38.049383856000119, 140.92579186300023),
                            new LatLng(38.007228908000158, 140.91863040500019),
                            new LatLng(37.965155341000113, 140.91976972700004),
                            new LatLng(37.891750393000095, 140.93262780000023),
                            new LatLng(37.889804989206212, 140.93319259500842),
                            new LatLng(37.889780585305701, 140.92672651571607),
                            new LatLng(37.887713527757015, 140.8778406112551),
                            new LatLng(37.883036811199887, 140.86466312140604),
                            new LatLng(37.87603465377633, 140.85489627534272),
                            new LatLng(37.867508042961774, 140.85272586500676),
                            new LatLng(37.84735423411054, 140.8552063337053),
                            new LatLng(37.838104152884412, 140.8552063337053),
                            new LatLng(37.810043849943796, 140.85272586500676),
                            new LatLng(37.798778388012138, 140.8496252786837),
                            new LatLng(37.790897732344476, 140.84125369660094),
                            new LatLng(37.786376044518775, 140.82492394358511),
                            new LatLng(37.785962633368754, 140.77805341982949),
                            new LatLng(37.783740546189264, 140.77510786403658),
                            new LatLng(37.775420640949747, 140.78461632678201),
                            new LatLng(37.770382188287286, 140.7789835958163),
                            new LatLng(37.77020132113411, 140.76410078272485),
                            new LatLng(37.776867580874168, 140.73304324804744),
                            new LatLng(37.798545844015464, 140.69469933510621),
                            new LatLng(37.807511705301053, 140.68730960495432),
                            new LatLng(37.815676580909454, 140.68281375465099),
                            new LatLng(37.823893134260757, 140.68105675726346),
                            new LatLng(37.846992498904754, 140.68296878518143),
                            new LatLng(37.859911607234906, 140.67940311086491),
                            new LatLng(37.872365628470973, 140.66958458885765),
                            new LatLng(37.882907619990604, 140.65160119034263),
                            new LatLng(37.886524970251187, 140.63191247038415),
                            new LatLng(37.894224757866326, 140.61439415896328),
                            new LatLng(37.903268134416876, 140.57491336535955),
                            new LatLng(37.893191229991217, 140.51739749639725),
                            new LatLng(37.89313955314789, 140.50835411984681),
                            new LatLng(37.894948229177473, 140.49796715705864),
                            new LatLng(37.903423163148503, 140.48499637278442),
                            new LatLng(37.925178941554961, 140.46882164939947),
                            new LatLng(37.935643418708722, 140.45765954025561),
                            new LatLng(37.943007311338263, 140.44324181515762),
                            new LatLng(37.95463450847582, 140.40458784385382),
                            new LatLng(37.953652656544861, 140.39032514928616),
                            new LatLng(37.946211249549449, 140.35291141233191),
                            new LatLng(37.945849514343664, 140.32061364240568),
                            new LatLng(37.964246324008641, 140.26955732580998),
                            new LatLng(38.030185451897111, 140.27705040874932),
                            new LatLng(38.045119940932594, 140.28232140540851),
                            new LatLng(38.056592109338411, 140.29503380816365),
                            new LatLng(38.061656399523216, 140.30857303321889),
                            new LatLng(38.065067044208732, 140.34484988771217),
                            new LatLng(38.068632716726526, 140.3606628767902),
                            new LatLng(38.078709622051363, 140.3788013040369),
                            new LatLng(38.091163642388224, 140.39652632103301),
                            new LatLng(38.114418035763677, 140.41388960192367),
                            new LatLng(38.139455268545717, 140.44070966961553),
                            new LatLng(38.152141831979989, 140.45078657404113),
                            new LatLng(38.175137843836424, 140.45905480423576),
                            new LatLng(38.190795803283763, 140.46215538965956),
                            new LatLng(38.208210761017781, 140.46308556474699),
                            new LatLng(38.244849352515658, 140.46138024330358),
                            new LatLng(38.267819525950458, 140.46820153177524),
                            new LatLng(38.281746323733799, 140.47435102757774),
                            new LatLng(38.29518219690091, 140.48277428560485),
                            new LatLng(38.31872081201594, 140.50308312408688),
                            new LatLng(38.333112697792743, 140.5119714701074),
                            new LatLng(38.357710680103864, 140.52251346162697),
                            new LatLng(38.379388943245161, 140.54313235937079),
                            new LatLng(38.385796821466172, 140.55227908691037),
                            new LatLng(38.39478852027402, 140.55972049390576),
                            new LatLng(38.429540921376471, 140.57263960223591),
                            new LatLng(38.452898668438863, 140.58504194572927),
                            new LatLng(38.465714423082218, 140.58793582557783),
                            new LatLng(38.480028795392641, 140.58204471219366),
                            new LatLng(38.488658758994589, 140.5740348653168),
                            new LatLng(38.496513577139936, 140.56519819613996),
                            new LatLng(38.508089098333144, 140.55837690586955),
                            new LatLng(38.524367174505571, 140.55491458613918),
                            new LatLng(38.567775376732314, 140.55429446761562),
                            new LatLng(38.584647732107115, 140.54886844312412),
                            new LatLng(38.607385362444589, 140.53791303865577),
                            new LatLng(38.619167589212864, 140.53460574765714),
                            new LatLng(38.631182359078551, 140.53439904208219),
                            new LatLng(38.642137763546955, 140.54111697776628),
                            new LatLng(38.644049791464639, 140.55212405817875),
                            new LatLng(38.641750189919279, 140.56798872320113),
                            new LatLng(38.643274645108832, 140.58194136030548),
                            new LatLng(38.649553331221185, 140.59108808784495),
                            new LatLng(38.659785265277733, 140.59899458193431),
                            new LatLng(38.671955063875046, 140.60364546096937),
                            new LatLng(38.686372788973031, 140.60597090093646),
                            new LatLng(38.710402330503214, 140.60457563695613),
                            new LatLng(38.732313136741922, 140.60674604729221),
                            new LatLng(38.736395575895031, 140.60891645672908),
                            new LatLng(38.7474026563075, 140.6163578637244),
                            new LatLng(38.7555675319161, 140.6182182147987),
                            new LatLng(38.765437729867315, 140.61604780536183),
                            new LatLng(38.790242417753305, 140.59511885015482),
                            new LatLng(38.822230130216397, 140.5771354507406),
                            new LatLng(38.85752513277869, 140.54266727137761),
                            new LatLng(38.881709703040329, 140.53290042531435),
                            new LatLng(38.873699856163356, 140.5440625353576),
                            new LatLng(38.871529445827278, 140.5580668484063),
                            new LatLng(38.87202037224246, 140.57760053873395),
                            new LatLng(38.889331977189087, 140.64198937391043),
                            new LatLng(38.916798000926931, 140.69562951109302),
                            new LatLng(38.928115138802653, 140.71040896959821),
                            new LatLng(38.936951808878874, 140.72549848916393),
                            new LatLng(38.943463039887448, 140.73428348239688),
                            new LatLng(38.951731269182645, 140.75944990458933),
                            new LatLng(38.946589463732707, 140.81608727440826),
                            new LatLng(38.91405914980983, 140.90703779485833),
                            new LatLng(38.880417791847648, 140.97638756743217),
                            new LatLng(38.875198472931217, 140.99266564270525),
                            new LatLng(38.872924709807549, 141.00527469267283),
                            new LatLng(38.873234768170008, 141.07410770130949),
                            new LatLng(38.869462389177968, 141.11260664298206),
                            new LatLng(38.863648790159615, 141.1288847191544),
                            new LatLng(38.857111720729321, 141.13699791881893),
                            new LatLng(38.852745063434, 141.13622277156358),
                            new LatLng(38.849411933114155, 141.13296715740844),
                            new LatLng(38.839800117581333, 141.11772261001059),
                            new LatLng(38.831376857755785, 141.11245161425055),
                            new LatLng(38.822385158947853, 141.11353681896915),
                            new LatLng(38.798769029467067, 141.13343224540179),
                            new LatLng(38.7862116563429, 141.14614464725778),
                            new LatLng(38.773860988793601, 141.18087120993832),
                            new LatLng(38.753784695207528, 141.21776818295533),
                            new LatLng(38.754172267935829, 141.22841352816172),
                            new LatLng(38.758280545510772, 141.23647505098293),
                            new LatLng(38.772465724813415, 141.25135786407409),
                            new LatLng(38.778408515040994, 141.26065962124457),
                            new LatLng(38.790526637694242, 141.28908165939114),
                            new LatLng(38.796805324705971, 141.2974532405747),
                            new LatLng(38.80435008448886, 141.30318932522712),
                            new LatLng(38.81003449229793, 141.30939049697398),
                            new LatLng(38.81303172493422, 141.32070763574868),
                            new LatLng(38.808716743582906, 141.33404015522885),
                            new LatLng(38.804763494739504, 141.35543419842944),
                            new LatLng(38.785436509986894, 141.40669721880161),
                            new LatLng(38.788588772254016, 141.41677412322716),
                            new LatLng(38.809414373774374, 141.44328413255678),
                            new LatLng(38.816287339988762, 141.44886518757838),
                            new LatLng(38.824090481290753, 141.45057050992116),
                            new LatLng(38.832668768948466, 141.44995039319602),
                            new LatLng(38.853184312106364, 141.45144900906465),
                            new LatLng(38.863674628581336, 141.45010542192782),
                            new LatLng(38.872769680176546, 141.45072553865262),
                            new LatLng(38.881968085458723, 141.45496300743682),
                            new LatLng(38.88987457954822, 141.46209435427104),
                            new LatLng(38.897005927281711, 141.47031090672309),
                            new LatLng(38.904576523687652, 141.47697716736232),
                            new LatLng(38.91137197553617, 141.48131798713544),
                            new LatLng(38.921242174386762, 141.48395348456577),
                            new LatLng(38.932404283530943, 141.48395348456577),
                            new LatLng(38.947442125353817, 141.48235151590976),
                            new LatLng(38.963616847839305, 141.48235151590976),
                            new LatLng(38.97700104416289, 141.48581383743877),
                            new LatLng(38.986767890226005, 141.49945641528129),
                            new LatLng(38.989610094130583, 141.51480431456756),
                            new LatLng(38.990152696489716, 141.5399707376595),
                            new LatLng(38.98568268550757, 141.56772098223749),
                            new LatLng(38.976303412172754, 141.59536787312888),
                            new LatLng(38.969559638066769, 141.63366011012593),
                            new LatLng(38.967503417366501, 141.64030353084499),
                            new LatLng(38.965196031000161, 141.64055423300013),
                            new LatLng(38.923000393000123, 141.64527428500006))

                    .strokeColor(Color.GREEN)//外枠線の色を変更（デフォルトは黒）
                    .strokeWidth(1)//外枠線の太さを調整（デフォルトは10）
                    .fillColor(Color.argb(127, 0, 63, 255));//ポリゴンの中の色を変更（デフォルトは無色 argbの第一引数は透明度）


            mMap.addPolygon(rectOptions);
    }
}
