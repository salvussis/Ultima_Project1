package com.ultimathule.ultima_project1;

import android.util.Log;

/**
 * Created by nobuyukiizumi on 15/10/19.
 * フラグ１の県を調べ、boundaryメソッド呼び出すためのクラス
 *
 * 現在、使用していないので最後に削除予定
 */
public class TohDohFuKen {

    static final String TAG = "AppTest";
    int num;

    void switchTest(String state) {
        num = Integer.parseInt(state);
        Log.d(TAG, "st: " + num);

        switch (num) {
            case 1: Log.d(TAG, "method_test:東京"); break;

            case 2: Log.d(TAG, "method_test:埼玉" ); break;

            case 3: Log.d(TAG, "method_test:宮城" ); break;
        }
//        if(st == 1){
//            Log.d(TAG, "method_test:東京" );
//        } else if (st == 2) {
//            Log.d(TAG, "method_test:千葉" );
//        } else if (st == 3){
//            Log.d(TAG, "method_test:宮城" );
//        } else {
//            Log.d(TAG, "method_test:他" );
//        }


    }
}

