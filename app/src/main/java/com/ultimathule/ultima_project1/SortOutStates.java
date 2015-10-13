package com.ultimathule.ultima_project1;

import android.util.Log;

/**
 * Created by nobuyukiizumi on 15/10/13.
 * ④部分一致検索プログラミング（都道府県を選別する）
 */
public class SortOutStates {
    static final String TAG = "AppTest";
    String ken;

    public static void sortOut(String ken) {
        //
        final String TOKYO = "Tokyo";
        final String SAITAMA = "Saitama Prefecture";

        //tokyo.indexOf(ken)では動作しないので注意
        if(ken.contains(TOKYO)){

            Log.d(TAG, "住所（東京）：　" + ken.toString());

        } else  if(ken.contains(SAITAMA)){

            Log.d(TAG, "住所（埼玉）：　" + ken.toString());

        } else {

            Log.d(TAG, "住所（？？？）：　" + ken.toString());
        }
    }
}
