package com.ultimathule.ultima_project1;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by nobuyukiizumi on 15/10/19.
 */
public class MySQLiteHelper extends SQLiteOpenHelper {
    static final String TAG = "AppTest";
    private static TohDohFuKen tohDohFuKen;

    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "AppDB";
    // Books table name
    private static final String TABLE_NAME = "prefectures";

    // Books Table Columns names
    private static final String ID = "id";
    private static final String STATE = "state";
    private static final String COMPLETE_FLG = "complete_flg";

//    private static final String[] COLUMNS = {ID,STATE,COMPLETE_FLG};

    //初期データ登録用のArrayList
    ArrayList<Integer> number = new ArrayList<>();
    ArrayList<String> kenmei = new ArrayList<>();


    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        Log.d(TAG, "Before creating new table!");

        // SQL statement to create book table
        String CREATE_BOOK_TABLE = "CREATE TABLE IF NOT EXISTS "
                + TABLE_NAME + " ("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + STATE + " TEXT, "
                + COMPLETE_FLG + " INTEGER)";

        // create books table
        db.execSQL(CREATE_BOOK_TABLE);

        Log.d(TAG, "make table: " + CREATE_BOOK_TABLE.toString());

        //①初期データ登録用　
        number.add(1);
        number.add(2);
        number.add(3);

        kenmei.add("Tokyo");
        kenmei.add("Saitama Prefecture");
        kenmei.add("Miyagi Prefecture");

        for(int i = 0; i < number.size(); i++) {

            //ここで、"execSQL" を実行すると初期データ登録ができる。
            String INSERT_INITIAL_DATA = "INSERT INTO "
                    + TABLE_NAME + " ("
                    + ID + ", "
                    + STATE + ", "
                    + COMPLETE_FLG + ") "
                    + "VALUES (" + number.get(i) + ", '" + kenmei.get(i) + "', 0)";
            db.execSQL(INSERT_INITIAL_DATA);
            //①はここまで・・・・・・・

            Log.d(TAG, "After creating and inserting new table!");
            Log.d(TAG, "insert SQL文: " + INSERT_INITIAL_DATA.toString());
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older books table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        // create fresh books table
        this.onCreate(db);

        Log.d(TAG, "After upgrading the table!");

    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onDowngrade(db, oldVersion, newVersion);
    }


    //フラグを１にするメソッド
    public void updateFlg(String state) {
        //順番を守ること！
        //①
        SQLiteDatabase db = this.getWritableDatabase();
        //②
        String UPDATE_FLG = "UPDATE "
                + TABLE_NAME + " SET "
                + COMPLETE_FLG + "=1"
                + " WHERE " + STATE + "='"
                + state + "'; ";
        // update prefectures set complete_flg=1 where state='Tokyo';
        Log.d(TAG, "update SQL文: " + UPDATE_FLG.toString());

        //③
        db.execSQL(UPDATE_FLG);

        //クローズ処理

    }

    //フラグ１の県を抜き出すメソッド
    public void readFlg() {
        // 1. get reference to readable DB
        SQLiteDatabase db = this.getReadableDatabase();

        String sql = "SELECT * FROM "
                + TABLE_NAME + " WHERE "
                + COMPLETE_FLG + "='1';";

        Log.d(TAG, "readFlg SQL文: " + sql.toString());

        Cursor cursor = db.rawQuery(sql.toString(), null);

        // 3. if we got results get the first one
        if (cursor.moveToFirst()) {
            do {
                Log.d(TAG, "回数: ");

                //  SetterGetterクラスは使っていないので下記のコードとともに削除予定
//                // 4. build book object
//                SetterGetter setterGetter = new SetterGetter();
//                //setter & getter
//                setterGetter.setId(Integer.parseInt(cursor.getString(0)));
//                setterGetter.setState(cursor.getString(1));
//                setterGetter.setComplete(cursor.getInt(2));
//                Log.d(TAG, "getBook: " + setterGetter.toString());

                //フラグが１の県名の "ID" を取り出す
                String column_id = cursor.getString(0);
//                String column_state = cursor.getString(1);
//                String column_flg = cursor.getString(2);
                Log.d(TAG, "flg_state: " + column_id);

                //ここで、"flg_state"を引数にしてboundaryのメソッドを呼び出す
                tohDohFuKen = new TohDohFuKen();
                tohDohFuKen.switchTest(column_id);

                //クローズ処理

            } while (cursor.moveToNext());

        }

    }

}

