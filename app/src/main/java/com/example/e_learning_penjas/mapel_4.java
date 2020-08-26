package com.example.e_learning_penjas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.e_learning_penjas.model.Soal;

import java.util.ArrayList;
import java.util.List;

public class mapel_4 extends SQLiteOpenHelper {
    final static String DB_NAME = "db_4";

    public mapel_4(Context context) {
        super(context, DB_NAME, null, 1);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS tbl_soal1(id INTEGER PRIMARY KEY AUTOINCREMENT, soal TEXT, pil_a TEXT, pil_b TEXT, pil_c TEXT, jwban INTEGER, img BLOB)";
        db.execSQL(sql);

        ContentValues values = new ContentValues();
        values.put("soal", "Nama lain permainan tenis meja ialah ...");
        values.put("pil_a", "softball");
        values.put("pil_b", "ping pong");
        values.put("pil_c", "football");
        values.put("jwban", "1");
        //  values.put("img", R.drawable.sapi);
        db.insert("tbl_soal1", "soal", values);

        values.put("soal", "Panjang meja tenis meja adalah ...");
        values.put("pil_a", "2,58 m");
        values.put("pil_b", "2,32 m");
        values.put("pil_c", "2,74 m");
        values.put("jwban", "2");
        //  values.put("img", R.drawable.hidup);
        db.insert("tbl_soal1", "soal", values);

        values.put("soal", "Tinggi meja dari lantai lapangan adalah ...");
        values.put("pil_a", "76 cm");
        values.put("pil_b", "87 cm");
        values.put("pil_c", "89 cm");
        values.put("jwban", "0");
        //  values.put("img", R.drawable.iakan);
        db.insert("tbl_soal1", "soal", values);

        values.put("soal", "Alat pemukul dalam tenis meja dikenal dengan nama ...");
        values.put("pil_a", "glove");
        values.put("pil_b", "bet");
        values.put("pil_c", "bat");
        values.put("jwban", "1");
        //  values.put("img", R.drawable.ramb);
        db.insert("tbl_soal1", "soal", values);

        values.put("soal", "Panjang net pada lapangan tenis meja ialah ...");
        values.put("pil_a", "1,56 m");
        values.put("pil_b", "1,65 m");
        values.put("pil_c", "1,83 m");
        values.put("jwban", "2");
        // values.put("img", R.drawable.ayam);
        db.insert("tbl_soal1", "soal", values);

        values.put("soal", "Induk organisasi tenis meja internasional adalah ...");
        values.put("pil_a", "FIFA");
        values.put("pil_b", "IPSI");
        values.put("pil_c", "ITTF");
        values.put("jwban", "1");
        //  values.put("img", R.drawable.tebu);
        db.insert("tbl_soal1", "soal", values);

        values.put("soal", "Berikut ini yang bukan teknik memegang bet adalah ...");
        values.put("pil_a", "shakehands grip");
        values.put("pil_b", "penholder grip");
        values.put("pil_c", "cut grip");
        values.put("jwban", "2");
        //  values.put("img", R.drawable.kelapa);
        db.insert("tbl_soal1", "soal", values);

        values.put("soal", "Posisi jari manis dan kelingking yang benar dalam pegangan penholder ...");
        values.put("pil_a", "di belakang bet");
        values.put("pil_b", "di permukaan samping bet");
        values.put("pil_c", "di permukaan pinggir bet");
        values.put("jwban", "0");
        //  values.put("img", R.drawable.tikus);
        db.insert("tbl_soal1", "soal", values);


        values.put("soal",  "Posisi jari telunjuk yang benar dalam teknik pegangan shakeholder adalah ...");
        values.put("pil_a", "di permukaan samping bet");
        values.put("pil_b", "di permukaan samping bet");
        values.put("pil_c", "di belakang bet");
        values.put("jwban", "1");
        // values.put("img", R.drawable.padi);
        db.insert("tbl_soal1", "soal", values);

        values.put("soal", "Teknik memukul bola dengan gerakan menghentikan atau membendung bola dengan sikap bet tertutup pada tenis meja dinamakan ...");
        values.put("pil_a", "chop");
        values.put("pil_b", "drive");
        values.put("pil_c", "block");
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