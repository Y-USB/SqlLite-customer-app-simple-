package com.example.yusub_x.finalwork;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class database extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "customers.db";
    private static final String TABLE_NAME = "customers";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_SURNAME = "surname";
    private static final String COLUMN_FATHER_NAME = "father";
    private static final String COLUMN_AGE = "age";
    private static final String COLUMN_GENDER = "gender";
    private static final String COLUMN_EDUCATION = "education";
    private static final String COLUMN_OPERATOR = "operator";
    private static final String COLUMN_NUMBER = "number";
    private static final String[] COLUMNS = {COLUMN_ID,COLUMN_NAME,COLUMN_SURNAME,COLUMN_FATHER_NAME,
            COLUMN_AGE,COLUMN_GENDER,COLUMN_EDUCATION,COLUMN_OPERATOR,COLUMN_NUMBER};
    public database(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + "(" +
                COLUMN_ID + " integer primary key autoincrement, " +
                COLUMN_NAME +" text, " +
                COLUMN_SURNAME + " text, " +
                COLUMN_FATHER_NAME +" text, " +
                COLUMN_AGE +" integer, " +
                COLUMN_GENDER +" text, " +
                COLUMN_EDUCATION +" text, " +
                COLUMN_OPERATOR +" text, " +
                COLUMN_NUMBER +" text " +
                ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_NAME);
        onCreate(db);
    }

    public void addCustomer(model model){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME,model.getName());
        contentValues.put(COLUMN_SURNAME,model.getSurname());
        contentValues.put(COLUMN_FATHER_NAME,model.getFather());
        contentValues.put(COLUMN_AGE,model.getAge());
        contentValues.put(COLUMN_GENDER,model.getGender());
        contentValues.put(COLUMN_EDUCATION,model.getEducation());
        contentValues.put(COLUMN_OPERATOR,model.getOperator());
        contentValues.put(COLUMN_NUMBER,model.getNumber());
        db.insert(TABLE_NAME,null,contentValues);
        db.close();
    }

    public List<model> getAllCustomers(){
        String query = "select * from " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        model model;
        List<model>  listler = new ArrayList<model>();
        if(cursor.moveToFirst()){
            while (!cursor.isAfterLast()==true){
                model = new model();
                model.setUserID(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
                model.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
                model.setSurname(cursor.getString(cursor.getColumnIndex(COLUMN_SURNAME)));
                model.setFather(cursor.getString(cursor.getColumnIndex(COLUMN_FATHER_NAME)));
                model.setAge(cursor.getInt(cursor.getColumnIndex(COLUMN_AGE)));
                model.setGender(cursor.getString(cursor.getColumnIndex(COLUMN_GENDER)));
                model.setEducation(cursor.getString(cursor.getColumnIndex(COLUMN_EDUCATION)));
                model.setOperator(cursor.getString(cursor.getColumnIndex(COLUMN_OPERATOR)));
                model.setNumber(cursor.getString(cursor.getColumnIndex(COLUMN_NUMBER)));
                listler.add(model);
                cursor.moveToNext();
            }
        }
        return listler;
    }

    public model getCustomer(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME,COLUMNS,COLUMN_ID +" =? ",new String[]{String.valueOf(id)},
                null,null,null);
        if(cursor !=null)
            cursor.moveToFirst();
        model model = new model();
        model.setUserID(cursor.getInt(0));
        model.setName(cursor.getString(1));
        model.setSurname(cursor.getString(2));
        model.setFather(cursor.getString(3));
        model.setAge(cursor.getInt(4));
        model.setGender(cursor.getString(5));
        model.setEducation(cursor.getString(6));
        model.setOperator(cursor.getString(7));
        model.setNumber(cursor.getString(8));
        return model;
    }

    public void deleteCustomer(model model){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME,COLUMN_ID +" = ?",new String[]{String.valueOf(model.getUserID())});
        db.close();
    }

  /*
    public int updateUsers(istifadeciler istifadeciler){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_USER_NAME,istifadeciler.getAdUser());
        contentValues.put(COLUMN_USER_SURNAME,istifadeciler.getSoyadUser());
        int i = db.update(TABLE_NAME,contentValues,COLUMN_ID +" = ?",new String[]{String.valueOf(istifadeciler.getIdUser())});
        db.close();
        return i;
    }
   */
  public int updateCustomer(model model) {
      SQLiteDatabase db = this.getWritableDatabase();
      ContentValues contentValues = new ContentValues();

      contentValues.put(COLUMN_NAME,model.getName());
      contentValues.put(COLUMN_SURNAME,model.getSurname());
      contentValues.put(COLUMN_FATHER_NAME,model.getFather());
      contentValues.put(COLUMN_AGE,model.getAge());
      contentValues.put(COLUMN_GENDER,model.getGender());
      contentValues.put(COLUMN_EDUCATION,model.getEducation());
      contentValues.put(COLUMN_OPERATOR,model.getOperator());
      contentValues.put(COLUMN_NUMBER,model.getNumber());

      int i = db.update(TABLE_NAME,contentValues,COLUMN_ID +" =? ",new String[]{String.valueOf(model.getUserID())});
      return i;
  }
}
