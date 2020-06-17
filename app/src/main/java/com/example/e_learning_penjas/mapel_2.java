package com.example.e_learning_penjas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.e_learning_penjas.model.Soal;

import java.util.ArrayList;
import java.util.List;

public class mapel_2 extends SQLiteOpenHelper {
    final static String DB_NAME = "db_bio";

    public mapel_2(Context context) {
        super(context, DB_NAME, null, 1);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS tbl_soal1(id INTEGER PRIMARY KEY AUTOINCREMENT, soal TEXT, pil_a TEXT, pil_b TEXT, pil_c TEXT, jwban INTEGER, img BLOB)";
        db.execSQL(sql);

        ContentValues values = new ContentValues();
        values.put("soal", "Orang yang menciptakan permainan bola basket adalah...");
        values.put("pil_a", "M. Luther");
        values.put("pil_b", "James Nasmith");
        values.put("pil_c", "Hasley. T");
        values.put("jwban", "1");
        //  values.put("img", R.drawable.sapi);
        db.insert("tbl_soal1", "soal", values);

        values.put("soal", "Berapakah jumlah pemain bola basket...");
        values.put("pil_a", "5 Orang Pemain dan 5 Orang Cadangan dalam satu regu");
        values.put("pil_b", "6 Orang dalam satu regu");
        values.put("pil_c", "11 Orang dalam dua regu");
        values.put("jwban", "0");
        //  values.put("img", R.drawable.hidup);
        db.insert("tbl_soal1", "soal", values);

        values.put("soal", "Menggiring dalam permainan bola basket berarti...");
        values.put("pil_a", "Shooting");
        values.put("pil_b", "Jagling");
        values.put("pil_c", "Dribbling");
        values.put("jwban", "2");
        //  values.put("img", R.drawable.iakan);
        db.insert("tbl_soal1", "soal", values);

        values.put("soal", "Teknik yang mendorong badan ke belakang saat melakukan shoot adalah...");
        values.put("pil_a", "Fade Away");
        values.put("pil_b", "Hook Shoot");
        values.put("pil_c", "Jump Shoot");
        values.put("jwban", "0");
        //  values.put("img", R.drawable.ramb);
        db.insert("tbl_soal1", "soal", values);

        values.put("soal", ". .....teknik yang sangat efektif bila pemain dijaga oleh orang yang lebih tinggi daripada pemain");
        values.put("pil_a", "Refrigeran");
        values.put("pil_b", "Dryer");
        values.put("pil_c", "Evaporator");
        values.put("jwban", "2");
        // values.put("img", R.drawable.ayam);
        db.insert("tbl_soal1", "soal", values);

        values.put("soal", "Teknik menembak yang sering dilakukan pemain dari luar daerah bersyarat lawan dalam bola basket adalah...");
        values.put("pil_a", "Tembak Kaiton");
        values.put("pil_b", "Tembakan dari samping");
        values.put("pil_c", "Tembakan langsung ke tangan");
        values.put("jwban", "1");
        //  values.put("img", R.drawable.tebu);
        db.insert("tbl_soal1", "soal", values);

        values.put("soal", "Sistem pertahanan dalam permainan bola basket dimana setiap pemain selalu berusaha mendekati lawan satu-satu, dari pertanyaan tersebut jawaban yang benar adalah...");
        values.put("pil_a", "Pressing defence");
        values.put("pil_b", "Zone defence");
        values.put("pil_c", "Man to man defence");
        values.put("jwban", "2");
        //  values.put("img", R.drawable.kelapa);
        db.insert("tbl_soal1", "soal", values);

        values.put("soal", "Permainan bola basket termasuk permainan…");
        values.put("pil_a", "Sedang");
        values.put("pil_b", "Renda");
        values.put("pil_c", "Tinggi");
        values.put("jwban", "2");
        //  values.put("img", R.drawable.tikus);
        db.insert("tbl_soal1", "soal", values);


        values.put("soal",  "Proses pendinginan refrigerant pada kondensor melalui…..?");
        values.put("pil_a", "Gabungan antara air dan udara ");
        values.put("pil_b", "pendinginan air");
        values.put("pil_c", "Pendinginan udara");
        values.put("jwban", "0");
        // values.put("img", R.drawable.padi);
        db.insert("tbl_soal1", "soal", values);

        values.put("soal", "Kaca pengintai pada receiver berfungsi untuk….?");
        values.put("pil_a", "Melihat refrigeran");
        values.put("pil_b", "Melihat jumlah refrigerant");
        values.put("pil_c", "Melihat gelembung udarah yang ada pada refrigerant");
        values.put("jwban", "0");
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