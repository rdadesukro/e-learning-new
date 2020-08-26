package com.example.e_learning_penjas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.e_learning_penjas.model.Soal;

import java.util.ArrayList;
import java.util.List;

public class mapel_3 extends SQLiteOpenHelper {
    final static String DB_NAME = "db_3";

    public mapel_3(Context context) {
        super(context, DB_NAME, null, 1);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS tbl_soal1(id INTEGER PRIMARY KEY AUTOINCREMENT, soal TEXT, pil_a TEXT, pil_b TEXT, pil_c TEXT, jwban INTEGER, img BLOB)";
        db.execSQL(sql);

        ContentValues values = new ContentValues();
        values.put("soal", "Permainan sepak bola berasal dari negara");
        values.put("pil_a", "Amerika");
        values.put("pil_b", "Inggris");
        values.put("pil_c", "Brasil");
        values.put("jwban", "1");
        //  values.put("img", R.drawable.sapi);
        db.insert("tbl_soal1", "soal", values);

        values.put("soal", "Tinggi gawang dalam permainan seopak bola adalah ....");
        values.put("pil_a", "2,20 meter");
        values.put("pil_b", " 2,22 mter");
        values.put("pil_c", "2,44 meter");
        values.put("jwban", "2");
        //  values.put("img", R.drawable.hidup);
        db.insert("tbl_soal1", "soal", values);

        values.put("soal", "Lingkaran tengah pada lapangan sepak bola berukuran ....");
        values.put("pil_a", "9,10 meter");
        values.put("pil_b", "9,20 meter");
        values.put("pil_c", "9,15 meter");
        values.put("jwban", "2");
        //  values.put("img", R.drawable.iakan);
        db.insert("tbl_soal1", "soal", values);

        values.put("soal", "Lama permainan sepak bola adalah ....");
        values.put("pil_a", "2 x 30 menit");
        values.put("pil_b", "2 x 35 menit");
        values.put("pil_c", "2 x 45 menit");
        values.put("jwban", "2");
        //  values.put("img", R.drawable.ramb);
        db.insert("tbl_soal1", "soal", values);

        values.put("soal", "Berikut nama lain sepak bola adalah ....");
        values.put("pil_a", "seccor");
        values.put("pil_b", "soccer");
        values.put("pil_c", "coach");
        values.put("jwban", "1");
        // values.put("img", R.drawable.ayam);
        db.insert("tbl_soal1", "soal", values);

        values.put("soal", "Induk organisasi sepak bola internasional adalah ....");
        values.put("pil_a", "FIFA");
        values.put("pil_b", "FIBA");
        values.put("pil_c", "FIHA");
        values.put("jwban", "0");
        //  values.put("img", R.drawable.tebu);
        db.insert("tbl_soal1", "soal", values);

        values.put("soal", "FIFA terbentuk sejak tahun ....");
        values.put("pil_a", "1940");
        values.put("pil_b", "1904");
        values.put("pil_c", "1934");
        values.put("jwban", "1");
        //  values.put("img", R.drawable.kelapa);
        db.insert("tbl_soal1", "soal", values);

        values.put("soal", "Jumlah pemain sepak bola dalam satu regu adalah ....");
        values.put("pil_a", "9 orang");
        values.put("pil_b", "10 orang");
        values.put("pil_c", "11 orang");
        values.put("jwban", "2");
        //  values.put("img", R.drawable.tikus);
        db.insert("tbl_soal1", "soal", values);


        values.put("soal",  "Lebar lapangan sepak bola adalah ....");
        values.put("pil_a", "64 meter – 75 meter");
        values.put("pil_b", "5 meter – 74 meter");
        values.put("pil_c", "66 meter – 75 meter");
        values.put("jwban", "0");
        // values.put("img", R.drawable.padi);
        db.insert("tbl_soal1", "soal", values);

        values.put("soal", "Jika bola keluar melewati garis samping lapangan pada permainan sepak bola disebut ....");
        values.put("pil_a", "free kick");
        values.put("pil_b", "hand ball");
        values.put("pil_c", "out ball");
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