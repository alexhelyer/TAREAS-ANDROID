package com.alex.helyer.search;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "naat";

    public static final String TABLE_NAME = "empleados";


    public static final String ID = "_id";
    public static final String COLUMN_NOMBRE = "nombre";
    public static final String COLUMN_PUESTO = "puesto";
    public static final String COLUMN_CORREO = "correo";

    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME
            + "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_NOMBRE + " TEXT,"
            + COLUMN_PUESTO + " TEXT,"
            + COLUMN_CORREO + " TEXT)";

    public DBHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLE);

        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NOMBRE, "Alejandro Martinez");
        cv.put(COLUMN_PUESTO, "Desarroldador Android");
        cv.put(COLUMN_CORREO,"alex@naat.com");

        db.insert(TABLE_NAME,COLUMN_NOMBRE,cv);

        cv.put(COLUMN_NOMBRE, "Diego Montero");
        cv.put(COLUMN_PUESTO, "Desarrolador Android");
        cv.put(COLUMN_CORREO,"diego@naat.com");

        db.insert(TABLE_NAME,COLUMN_NOMBRE,cv);

        cv.put(COLUMN_NOMBRE, "Pablo corona");
        cv.put(COLUMN_PUESTO, "Desarroldador Java");
        cv.put(COLUMN_CORREO,"pabloc@naat.com");

        db.insert(TABLE_NAME,COLUMN_NOMBRE,cv);

        cv.put(COLUMN_NOMBRE, "Pablo Soto");
        cv.put(COLUMN_PUESTO, "Desarroldador Web");
        cv.put(COLUMN_CORREO,"pablos@naat.com");

        db.insert(TABLE_NAME,COLUMN_NOMBRE,cv);

        cv.put(COLUMN_NOMBRE, "Gabriel Zempoalteca");
        cv.put(COLUMN_PUESTO, "Desarroldador iOS");
        cv.put(COLUMN_CORREO,"gabriel@naat.com");

        db.insert(TABLE_NAME,COLUMN_NOMBRE,cv);

        cv.put(COLUMN_NOMBRE, "Cesar Jesús");
        cv.put(COLUMN_PUESTO, "Desarroldador Java");
        cv.put(COLUMN_CORREO,"cesar@naat.com");

        db.insert(TABLE_NAME,COLUMN_NOMBRE,cv);



    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NOMBRE, "Hector Gibran");
        cv.put(COLUMN_PUESTO, "Desarroldador Android");
        cv.put(COLUMN_CORREO,"hector@naat.com");

        db.insert(TABLE_NAME,COLUMN_NOMBRE,cv);

    }
}