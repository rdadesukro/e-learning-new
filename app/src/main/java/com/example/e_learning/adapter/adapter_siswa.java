package com.example.e_learning.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.e_learning.R;
import com.example.e_learning.menu_detail_siswa;
import com.example.e_learning.model.model_siswa.ResultItem_siswa;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;


public class adapter_siswa extends RecyclerView.Adapter<adapter_siswa.HolderData> {
    private static CountDownTimer countDownTimer;

    private List<ResultItem_siswa> mList ;
    private Context ctx;
    String status_laporan,status_baru;
    String timer;
    public adapter_siswa(Context ctx, List<ResultItem_siswa> mList)
    {
        this.ctx = ctx;
        this.mList = mList;
    }
    @Override
    public HolderData onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.model_siswa,parent, false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(final HolderData holder, int position) {
        //  Toast.makeText(ctx, waktu, Toast.LENGTH_SHORT).show();
        final ResultItem_siswa dm = mList.get(position);
        String waktu;




        holder.nama.setText(dm.getNama());
        holder.txt_nis.setText(dm.getNis());
        holder.txt_kelas.setText("Kelas "+dm.getNama_kelas());






        Glide.with(ctx)
                .load("http://192.168.43.48/penjas/images/profil/"+dm.getFoto())
                .apply(new RequestOptions()
                        .fitCenter()
                        .error(R.drawable.siswa))
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
        @BindView(R.id.txt_kelas) TextView txt_kelas;
        @BindView(R.id.img_profil) ImageView bg;

        ResultItem_siswa dm;




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
            Intent goInput = new Intent(ctx, menu_detail_siswa.class);
            goInput.putExtra("nama", dm.getNama());
            goInput.putExtra("nis", dm.getNis());
            goInput.putExtra("alamat", dm.getAlamat());
            goInput.putExtra("tempat", dm.getTempatLahir());
            goInput.putExtra("tgl", dm.getTglLahir());
            goInput.putExtra("jk", dm.getJk());
            goInput.putExtra("no", dm.getNoHp());
            goInput.putExtra("foto", dm.getFoto());
            goInput.putExtra("kelas", dm.getNama_kelas());
            ctx.startActivity(goInput);
        }

        public void click_desc_track(){
//            Intent goInput = new Intent(ctx, menu_detail_laporan_pelayanan.class);
//            goInput.putExtra("id_rgister", dm.getIdRegis());
//            goInput.putExtra("nama", dm.getNama());
//            ctx.startActivity(goInput);
        }


    }



}
