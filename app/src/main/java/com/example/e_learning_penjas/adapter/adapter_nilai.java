package com.example.e_learning_penjas.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.e_learning_penjas.R;
import com.example.e_learning_penjas.model.model_nilai.ResultItem_nilai;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class adapter_nilai extends RecyclerView.Adapter<adapter_nilai.HolderData> {
    private static CountDownTimer countDownTimer;

    private List<ResultItem_nilai> mList ;
    private Context ctx;
    String status_laporan,status_baru;
    String timer;
    public adapter_nilai(Context ctx, List<ResultItem_nilai> mList)
    {
        this.ctx = ctx;
        this.mList = mList;
    }
    @Override
    public HolderData onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.model_niali,parent, false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(final HolderData holder, int position) {
        //  Toast.makeText(ctx, waktu, Toast.LENGTH_SHORT).show();
        final ResultItem_nilai dm = mList.get(position);
        String waktu;




        holder.nama.setText(dm.getNama());
        holder.txt_nis.setText(dm.getAmbilNis());
        holder.txt_nilai.setText(dm.getNilai());
        holder.txt_quiz.setText("Quiz "+dm.getQuiz());






        Glide.with(ctx)
                .load("http://192.168.1.71/penjas/images/profil/"+dm.getFoto())
                .apply(new RequestOptions()
                        .fitCenter()
                        .error(R.drawable.bg))
                .into(holder.bg);

        holder.dm = dm;



    }


    @Override
    public int getItemCount() {
        return mList.size();
    }


    class HolderData extends  RecyclerView.ViewHolder{

        @BindView(R.id.txt_nama) TextView nama;

        @BindView(R.id.txt_nis) TextView txt_nis;
        @BindView(R.id.txt_nilai) TextView txt_nilai;
        @BindView(R.id.txt_quiz) TextView txt_quiz;


        @BindView(R.id.img_profil) ImageView bg;

        ResultItem_nilai dm;




        public HolderData (View v)
        {
            super(v);
            ButterKnife.bind(this, itemView);
            nama.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                 //   click_desc_data();
                }
            });
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                  // / Toast.makeText(ctx, "ID"+dm.getIdRegis(), Toast.LENGTH_SHORT).show();
                    click_desc_data();
                }
            });
        }

        public void click_desc_data(){
//            Intent goInput = new Intent(ctx, menu_detail_laporan_pelayanan.class);
//            goInput.putExtra("id_rgister", dm.getIdRegis());
//            goInput.putExtra("nama", dm.getNama());
//            ctx.startActivity(goInput);
        }

        public void click_desc_track(){
//            Intent goInput = new Intent(ctx, menu_detail_laporan_pelayanan.class);
//            goInput.putExtra("id_rgister", dm.getIdRegis());
//            goInput.putExtra("nama", dm.getNama());
//            ctx.startActivity(goInput);
        }


    }



}
