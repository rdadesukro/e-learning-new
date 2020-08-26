package com.example.e_learning_penjas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.e_learning_penjas.model.Soal;

import java.util.ArrayList;
import java.util.List;

public class mapel_6 extends SQLiteOpenHelper {
    final static String DB_NAME = "db_6";

    public mapel_6(Context context) {
        super(context, DB_NAME, null, 1);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS tbl_soal1(id INTEGER PRIMARY KEY AUTOINCREMENT, soal TEXT, pil_a TEXT, pil_b TEXT, pil_c TEXT, jwban INTEGER, img BLOB)";
        db.execSQL(sql);

        ContentValues values = new ContentValues();
        values.put("soal", "Pencak silat adalah budaya asli dari ....");
        values.put("pil_a", "singapura");
        values.put("pil_b", "vietnam");
        values.put("pil_c", "indonesia");
        values.put("jwban", "2");
        //  values.put("img", R.drawable.sapi);
        db.insert("tbl_soal1", "soal", values);

        values.put("soal", "Induk pencak silat di indonesia adalah ....");
        values.put("pil_a", "PSSI");
        values.put("pil_b", "IPSI");
        values.put("pil_c", "PBVSI");
        values.put("jwban", "1");
        //  values.put("img", R.drawable.hidup)
        db.insert("tbl_soal1", "soal", values);

        values.put("soal", "Banyak orang meyakini pencak silat diciptakan oleh orang ....");
        values.put("pil_a", "korea");
        values.put("pil_b", "melayu");
        values.put("pil_c", "jepang");
        values.put("jwban", "1");
        //  values.put("img", R.drawable.iakan);
        db.insert("tbl_soal1", "soal", values);

        values.put("soal", "Yang bukan termasuk kategori dalam pencak silat adalah ....");
        values.put("pil_a", "kategori tunggal");
        values.put("pil_b", "kategori regu");
        values.put("pil_c", "kategori campuran");
        values.put("jwban", "2");
        //  values.put("img", R.drawable.ramb);
        db.insert("tbl_soal1", "soal", values);

        values.put("soal", "Berikut ini yang bukan termasuk teknik dasar pencak silat ialah ....");
        values.put("pil_a", "pukulan");
        values.put("pil_b", "kuncian");
        values.put("pil_c", "sundulan");
        values.put("jwban", "2");
        // values.put("img", R.drawable.ayam);
        db.insert("tbl_soal1", "soal", values);

        values.put("soal", "Posisi menapak kaki untuk memperkokoh posisi tubuh tersebut ....");
        values.put("pil_a", "pukulan");
        values.put("pil_b", "elakan");
        values.put("pil_c", "kuda-kuda");
        values.put("jwban", "2");
        //  values.put("img", R.drawable.tebu);
        db.insert("tbl_soal1", "soal", values);

        values.put("soal", "Tangkapan yang baik didahului dengan gerakan ....");
        values.put("pil_a", "kuncian");
        values.put("pil_b", "kuda-kuda");
        values.put("pil_c", " elakan");
        values.put("jwban", "0");
        //  values.put("img", R.drawable.kelapa);
        db.insert("tbl_soal1", "soal", values);

        values.put("soal", "Melepaskan diri dari tangkapan lawan disebut ....");
        values.put("pil_a", "elakan");
        values.put("pil_b", "menangkis");
        values.put("pil_c", "lepasan");
        values.put("jwban", "2");
        //  values.put("img", R.drawable.tikus);
        db.insert("tbl_soal1", "soal", values);


        values.put("soal",  "Menghindari dari teknik serangan lawan disebut ....");
        values.put("pil_a", "elakan");
        values.put("pil_b", "menangkis");
        values.put("pil_c", "kuncian");
        values.put("jwban", "0");
        // values.put("img", R.drawable.padi);
        db.insert("tbl_soal1", "soal", values);

        values.put("soal", "Tangkapan sempurna yang membuat lawan tidak berdaya disebut .....");
        values.put("pil_a", "elakan");
        values.put("pil_b", "tangkapan");
        values.put("pil_c", "kuncian");
        values.put("jwban", "2");
        //  values.put("img", R.drawable.burung);
        db.insert("tbl_soal1", "soal", values);

        String sql2 = "CREATE TABLE IF NOT EXISTS tbl_gambar(id INTEGER PRIMARY KEY AUTOINCREMENT, nama TEXT, img BLOB)";
        db.execSQL(sql2);

        ContentValues v = new ContentValues();
        v.put("nama", "Manfaat hewan");
        // values.put("img", R.drawable.sapi);
        db.insert("tbl_gambar1", "nama", v);

        v.put("nama", "Manfaat hewan");
        //   values.put("img", R.drawable.burung);
        db.insert("tbl_gambar1", "nama", v);

    }

    public List<Soal> getSoal() {
        List<Soal> listSoal = new ArrayList<Soal>();
        String query = "select * from tbl_soal1";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                Soal s = new Soal();
                s.setSoal(cursor.getString(1));
                s.setPil_a(cursor.getString(2));
                s.setPil_b(cursor.getString(3));
                s.setPil_c(cursor.getString(4));
                s.setJwban(cursor.getInt(5));
                s.setGambar(cursor.getInt(6));
                listSoal.add(s);
            } while (cursor.moveToNext());
        }
        return listSoal;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS tbl_soal1");
        db.execSQL("DROP TABLE IF EXISTS tbl_gambar1");
    }
}