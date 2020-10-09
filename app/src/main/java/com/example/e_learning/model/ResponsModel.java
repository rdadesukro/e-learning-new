package com.example.e_learning.model;

import com.example.e_learning.model.model_siswa.DataModel;

import java.util.List;

/**
 * Created by hakiki95 on 4/16/2017.
 */

public class ResponsModel {

    String kode, pesan;
    List<DataModel> result;

    public List<DataModel> getResult() {
        return result;
    }

    public void setResult(List<DataModel> result) {
        this.result = result;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }
}
