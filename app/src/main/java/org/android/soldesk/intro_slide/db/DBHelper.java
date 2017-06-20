package org.android.soldesk.intro_slide.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import org.android.soldesk.intro_slide.vo.Word;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    private Context context;

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context = context;
    }

    /*
    * DB가 존재하지 않을 때, 딱 한번 실행된다
    * DB를 생성하는 역할
    * @param db
    * */
    @Override
    public void onCreate(SQLiteDatabase db) {

        StringBuffer sb = new StringBuffer();
        sb.append(" CREATE TABLE WORD_TABLE ( ");
        sb.append(" WID INTEGER PRIMARY KEY AUTOINCREMENT, ");
        sb.append(" NAME TEXT, ");
        sb.append(" FNAME INTEGER, ");
        sb.append(" SNAME INTEGER, ");
        sb.append(" WORDCHECK INTEGER, ");
        sb.append(" CHAP INTEGER, ");
        sb.append(" CLEAR INTEGER, ");
        sb.append(" LANGUAGE INTEGER);");

        // SQL 실행
        db.execSQL(sb.toString());

        /*Toast.makeText(context, "DB 생성 완료", Toast.LENGTH_SHORT).show();*/
    }

    /*
    * Application의 버전이 올라가 Table 구조가 변경되었을 때 실행
    * @param db
    * @param oldVersion
    * @param newVersion
    * */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table WORD_TABLE");
        onCreate(db);

        Toast.makeText(context, "Version 올라감", Toast.LENGTH_SHORT).show();
    }

    public List<Word> select() {
        List<Word> list = new ArrayList<Word>();

        StringBuffer sb = new StringBuffer();
        sb.append(" SELECT * FROM WORD_TABLE ");
        SQLiteDatabase db = getReadableDatabase();

        Cursor c = db.rawQuery(sb.toString(), null);


        while (c.moveToNext()) {
            Word w = new Word();
            w.setName(c.getString(1));
            w.setFname(c.getInt(2));
            w.setSname(c.getInt(3));
            w.setClear(c.getInt(6));

            list.add(w);
            Log.i("word", w.toString());
        }
        db.close();
        return list;
    }

    // db 최신화를 위해 인위적으로 drop 메소드 부여
    public void drop() {

        getWritableDatabase().execSQL("drop table WORD_TABLE");

        /*Toast.makeText(context, "DB 삭제 완료", Toast.LENGTH_SHORT).show();*/
    }

    // db 최신화를 위해 인위적으로 create 메소드 부여
    public void create() {
        StringBuffer sb = new StringBuffer();
        sb.append(" CREATE TABLE WORD_TABLE ( ");
        sb.append(" WID INTEGER PRIMARY KEY AUTOINCREMENT, ");
        sb.append(" NAME TEXT, ");
        sb.append(" FNAME INTEGER, ");
        sb.append(" SNAME INTEGER, ");
        sb.append(" WORDCHECK INTEGER, ");
        sb.append(" CHAP INTEGER, ");
        sb.append(" CLEAR INTEGER, ");
        sb.append(" LANGUAGE INTEGER);");

        // SQL 실행
        getWritableDatabase().execSQL(sb.toString());
        /*Toast.makeText(context, "DB 생성 완료", Toast.LENGTH_SHORT).show();*/
    }

    public void insert(Word w) {

        SQLiteDatabase db = getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put("name", w.getName());
        values.put("fname", w.getFname());
        values.put("sname", w.getSname());
        values.put("wordcheck", 0);
        values.put("chap", w.getChap());
        values.put("clear", 0);
        values.put("language", w.getLanguage());
        db.insert("WORD_TABLE", null, values);

        db.close();
    }

    public List<Word> chapselect(int i, int j) {
        List<Word> list = new ArrayList<Word>();
        StringBuffer sb = new StringBuffer();
        sb.append(" SELECT * FROM WORD_TABLE WHERE CHAP = ? and LANGUAGE = ?");
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sb.toString(), new String[]{Integer.toString(i), Integer.toString(j)});
        while (c.moveToNext()) {
            Word w = new Word();
            w.setName(c.getString(1));
            w.setFname(c.getInt(2));
            w.setSname(c.getInt(3));
            w.setWordcheck(c.getInt(4));
            w.setClear(c.getInt(6));
            list.add(w);
            Log.i("word", w.toString());
        }
        db.close();
        return list;
    }

    public void wordUpdate(Word w) {
        if (w.getWordcheck() == 1) {
            getWritableDatabase().execSQL("UPDATE WORD_TABLE SET WORDCHECK=" + 0 + " WHERE NAME='" + w.getName() + "';");
        } else {
            getWritableDatabase().execSQL("UPDATE WORD_TABLE SET WORDCHECK=" + 1 + " WHERE NAME='" + w.getName() + "';");
        }
        getWritableDatabase().close();


    }

    public ArrayList<Word> wordselect(int i) {
        ArrayList<Word> list = new ArrayList<Word>();
        StringBuffer sb = new StringBuffer();
        sb.append(" SELECT * FROM WORD_TABLE WHERE WORDCHECK = ? AND LANGUAGE = ?");
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sb.toString(), new String[]{Integer.toString(1), Integer.toString(i)});
        while (c.moveToNext()) {
            Word w = new Word();
            w.setName(c.getString(1));
            w.setFname(c.getInt(2));
            w.setSname(c.getInt(3));
            w.setWordcheck(c.getInt(4));
            w.setClear(c.getInt(6));
            list.add(w);
            Log.i("word", w.toString());
        }
        db.close();
        return list;
    }

    public void wordDelete(Word word) {
        getWritableDatabase().execSQL("UPDATE WORD_TABLE SET WORDCHECK=" + 0 + " WHERE NAME='" + word.getName() + "';");
    }

    public void clearUpdate(Word word) {
        if (word.getClear() == 1) {
            getWritableDatabase().execSQL("UPDATE WORD_TABLE SET CLEAR=" + 0 + " WHERE NAME='" + word.getName() + "';");
        } else {
            getWritableDatabase().execSQL("UPDATE WORD_TABLE SET CLEAR=" + 1 + " WHERE NAME='" + word.getName() + "';");
        }
        getWritableDatabase().close();
    }

    public List<Word> proSelect(int i) {
        List<Word> list = new ArrayList<Word>();
        StringBuffer sb = new StringBuffer();
        sb.append(" SELECT * FROM WORD_TABLE WHERE CLEAR = ? and LANGUAGE = ?");
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sb.toString(), new String[]{Integer.toString(1), Integer.toString(i)});
        Log.i("word", "--------------------------------------------------------");
        while (c.moveToNext()) {
            Word w = new Word();
            w.setName(c.getString(1));
            w.setFname(c.getInt(2));
            w.setSname(c.getInt(3));
            w.setWordcheck(c.getInt(4));
            w.setClear(c.getInt(6));
            list.add(w);
            Log.i("word", w.toString() + "---------------------");
        }
        db.close();

        return list;
    }

    public List<Word> gameselect(int i) {
        List<Word> list = new ArrayList<Word>();
        StringBuffer sb = new StringBuffer();
        sb.append(" SELECT * FROM WORD_TABLE WHERE LANGUAGE = ?");
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sb.toString(), new String[]{Integer.toString(i)});
        while (c.moveToNext()) {
            Word w = new Word();
            w.setName(c.getString(1));
            w.setFname(c.getInt(2));
            w.setSname(c.getInt(3));
            w.setWordcheck(c.getInt(4));
            w.setClear(c.getInt(6));
            list.add(w);
            Log.i("word", w.toString() + "---------------------");
        }
        db.close();
        return list;
    }
}
