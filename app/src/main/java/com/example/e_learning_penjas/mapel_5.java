package com.example.e_learning_penjas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.e_learning_penjas.model.Soal;

import java.util.ArrayList;
import java.util.List;

public class mapel_5 extends SQLiteOpenHelper {
    final static String DB_NAME = "db_5";

    public mapel_5(Context context) {
        super(context, DB_NAME, null, 1);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS tbl_soal1(id INTEGER PRIMARY KEY AUTOINCREMENT, soal TEXT, pil_a TEXT, pil_b TEXT, pil_c TEXT, jwban INTEGER, img BLOB)";
        db.execSQL(sql);

        ContentValues values = new ContentValues();
        values.put("soal", "gerak maju dengan melangkah tanpa adanya hubungan terputus dengan tanah disebut......");
        values.put("pil_a", "Jalan santai");
        values.put("pil_b", "Jalan cepat");
        values.put("pil_c", "Lari");
        values.put("jwban", "1");
        //  values.put("img", R.drawable.sapi);
        db.insert("tbl_soal1", "soal", values);

        values.put("soal", "melakukan tarikan kaki belakang ke depan adalah tahap jalan cepat tahap.........");
        values.put("pil_a", "Tahap pertama");
        values.put("pil_b", "Tahap kedua");
        values.put("pil_c", "Tahap relaksasi");
        values.put("jwban", "1");
        //  values.put("img", R.drawable.hidup);
        db.insert("tbl_soal1", "soal", values);

        values.put("soal", "tahap antara tahap awal ketika melangkahkan kaki ke depan dan ketika akan melakukan tarikan kaki belakang disebut tahap......");
        values.put("pil_a", "Tahap pertama");
        values.put("pil_b", "Tahap kedua");
        values.put("pil_c", "Tahap relaksasi");
        values.put("jwban", "2");
        //  values.put("img", R.drawable.iakan);
        db.insert("tbl_soal1", "soal", values);

        values.put("soal", "Cara memasuki garis finish yang banyak dilakukan oleh atlit lari jarak pendek adalah\n");
        values.put("pil_a", "Menjatuhkan salah satu kaki tepat digaris finish");
        values.put("pil_b", "Lari terus tanpa merubah kecepatan hingga melewati garis finish");
        values.put("pil_c", "Kepala melihat garis finish");
        values.put("jwban", "1");
        //  values.put("img", R.drawable.ramb);
        db.insert("tbl_soal1", "soal", values);

        values.put("soal", "Aba-aba lari jarak pendek adalah....");
        values.put("pil_a", "Siap – Ya");
        values.put("pil_b", "Bersedia – Awas - Ya");
        values.put("pil_c", "Bersedia- Siap – Ya");
        values.put("jwban", "2");
        // values.put("img", R.drawable.ayam);
        db.insert("tbl_soal1", "soal", values);

        values.put("soal", "Sikap kepalan pada saat melakukan start jongkok adalah");
        values.put("pil_a", "Dikepalkan");
        values.put("pil_b", "Seluruh telapak menyentuh tanah");
        values.put("pil_c", "Ibu jari dan ke empat jari tangan membentuk huruf V terbalik");
        values.put("jwban", "2");
        //  values.put("img", R.drawable.tebu);
        db.insert("tbl_soal1", "soal", values);

        values.put("soal", "Yang dimaksud dengan chrauching start adalah");
        values.put("pil_a", "Start berdiri");
        values.put("pil_b", "Start pendek");
        values.put("pil_c", "Start jongkok");
        values.put("jwban", "2");
        //  values.put("img", R.drawable.kelapa);
        db.insert("tbl_soal1", "soal", values);

        values.put("soal", "Gaya dan kedudukan badan ketika berada di udara dan di atas mistar adalah pengertian dari ...\n");
        values.put("pil_a", "Awalan");
        values.put("pil_b", "Tolakan");
        values.put("pil_c", "Melayang");
        values.put("jwban", "2");
        //  values.put("img", R.drawable.tikus);
        db.insert("tbl_soal1", "soal", values);


        values.put("soal",  "Berikut ini yang bukan merupakan tahap gaya lompatan adalah...");
        values.put("pil_a", "Awalan");
        values.put("pil_b", "Tolakan");
        values.put("pil_c", "Lompatan");
        values.put("jwban", "2");
        // values.put("img", R.drawable.padi);
        db.insert("tbl_soal1", "soal", values);

        values.put("soal", "Pada gaya Fosbury Flop prtama kali yang mendarat adalah bagian....");
        values.put("pil_a", "Punggung dan bagian belakang kepala");
        values.put("pil_b", "Kaki kiri dan kanan");
        values.put("pil_c", "Kedua telapak tangan");
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