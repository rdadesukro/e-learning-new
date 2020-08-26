package com.example.e_learning_penjas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.e_learning_penjas.model.Soal;

import java.util.ArrayList;
import java.util.List;

public class mapel_7 extends SQLiteOpenHelper {
    final static String DB_NAME = "db_7";

    public mapel_7(Context context) {
        super(context, DB_NAME, null, 1);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS tbl_soal1(id INTEGER PRIMARY KEY AUTOINCREMENT, soal TEXT, pil_a TEXT, pil_b TEXT, pil_c TEXT, jwban INTEGER, img BLOB)";
        db.execSQL(sql);

        ContentValues values = new ContentValues();
        values.put("soal", "Loncat kangkang di atas peti lompat termasuk ke dalam olahraga ....");
        values.put("pil_a", "Senam ketangkasan tanpa alat");
        values.put("pil_b", "Senam aerobik");
        values.put("pil_c", "Senam irama");
        values.put("jwban", "2");
        //  values.put("img", R.drawable.sapi);
        db.insert("tbl_soal1", "soal", values);

        values.put("soal", "Alat bantu yang biasa digunakan dalam loncat kangkang adalah ....");
        values.put("pil_a", " Kuda-kuda pelana");
        values.put("pil_b", "Kuda-kuda lompat");
        values.put("pil_c", "Palang sejajar");
        values.put("jwban", "1");
        //  values.put("img", R.drawable.hidup);
        db.insert("tbl_soal1", "soal", values);

        values.put("soal", "Tiga tahap dalam melakukan loncat kangkang adalah ....");
        values.put("pil_a", "Pemanasan, gerakan inti, dan pendinginan");
        values.put("pil_b", "Lari cepat, tolakan, dan gerakan saat melayang di udara");
        values.put("pil_c", "Tolakan, melewatkan kaki di atas peti lompat, dan pendaratan");
        values.put("jwban", "2");
        //  values.put("img", R.drawable.iakan);
        db.insert("tbl_soal1", "soal", values);

        values.put("soal", "Langkah pertama dalam melakukan tolakan pada loncat kangkang adalah ....");
        values.put("pil_a", "Mengambil awalan untuk ancang-ancang");
        values.put("pil_b", "Pandangan ke depan");
        values.put("pil_c", "Melakukan tolakan kaki");
        values.put("jwban", "0");
        //  values.put("img", R.drawable.ramb);
        db.insert("tbl_soal1", "soal", values);

        values.put("soal", "Gerakan tahap melewatkan kaki di atas peti lompat pada loncat kangkang adalah ....");
        values.put("pil_a", "Melakukan tolakan kak");
        values.put("pil_b", "Kedua tangan menahan berat badan");
        values.put("pil_c", "Kedua tangan menahan berat badan");
        values.put("jwban", "2");
        // values.put("img", R.drawable.ayam);
        db.insert("tbl_soal1", "soal", values);

        values.put("soal", "Kesalahan yang bisa terjadi saat melakukan loncat kangkang adalah ...");
        values.put("pil_a", "Saat membuka kaki sangat lebar");
        values.put("pil_b", "Saat melayang di udara, badan agak membungkuk");
        values.put("pil_c", "Saat menumpu di atas peti, tangan kurang melakukan tolakan");
        values.put("jwban", "2");
        //  values.put("img", R.drawable.tebu);
        db.insert("tbl_soal1", "soal", values);

        values.put("soal", "Sikap badan pada saat melakukan pendaratan gerakan loncat kangkang adalah …");
        values.put("pil_a", "Berdiri tegak");
        values.put("pil_b", "Jongkok");
        values.put("pil_c", "Condong ke depan dengan menekuk lutut");
        values.put("jwban", "2");
        //  values.put("img", R.drawable.kelapa);
        db.insert("tbl_soal1", "soal", values);

        values.put("soal", "Berikut yang bukan merupakan teknik dasar loncat kangkang …");
        values.put("pil_a", "Pinggul diangkat tinggi");
        values.put("pil_b", "Menekuk panggul saat tangan menyentuh peti lompat");
        values.put("pil_c", "Sikap jongkokr");
        values.put("jwban", "2");
        //  values.put("img", R.drawable.tikus);
        db.insert("tbl_soal1", "soal", values);


        values.put("soal",  "Jarak antara papan tolakan dengan awalan pada loncat kangkang adalah …");
        values.put("pil_a", "5-10 meter");
        values.put("pil_b", "10-15 meter");
        values.put("pil_c", "5-20 m1eter");
        values.put("jwban", "0");
        // values.put("img", R.drawable.padi);
        db.insert("tbl_soal1", "soal", values);

        values.put("soal", "Loncat kangkang dilakukan oleh pesenam dengan diawali gerakan ……");
        values.put("pil_a", "Melayang");
        values.put("pil_b", "Mendarat");
        values.put("pil_c", "Menolak");
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