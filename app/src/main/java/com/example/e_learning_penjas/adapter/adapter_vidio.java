package com.example.e_learning_penjas.adapter;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.e_learning_penjas.R;
//import com.example.e_learning_penjas.menu_detail_vidio;
import com.example.e_learning_penjas.model.vidio.ResultItem_vidio;


import java.util.List;

import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;


public class adapter_vidio extends RecyclerView.Adapter<adapter_vidio.HolderData> {
    private static CountDownTimer countDownTimer;

    private List<ResultItem_vidio> mList ;
    private Context ctx;
    String status_laporan,status_baru;
    String timer;
    public adapter_vidio(Context ctx, List<ResultItem_vidio> mList)
    {
        this.ctx = ctx;
        this.mList = mList;
    }
    @Override
    public HolderData onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.model_vidio,parent, false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(final HolderData holder, int position) {
        //  Toast.makeText(ctx, waktu, Toast.LENGTH_SHORT).show();
        final ResultItem_vidio dm = mList.get(position);
        String waktu;




        holder.nama.setText(dm.getNama());






        Glide.with(ctx)
                .load("http://192.168.43.48/penjas/images/vidio/"+dm.getFoto())
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
        @BindView(R.id.bg_foto) ImageView bg;

        ResultItem_vidio dm;




        public HolderData (View v)
        {
            super(v);
            ButterKnife.bind(this, itemView);
            nama.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent webIntent = new Intent(Intent.ACTION_VIEW,
                            Uri.parse(dm.getLink()));
                    try {
                        ctx.startActivity(webIntent);
                    } catch (ActivityNotFoundException ex) {
                    }
//                    Intent goInput = new Intent(ctx, menu_detail_vidio.class);
//                     goInput.putExtra("nama", dm.getNama());
//                    goInput.putExtra("link", dm.getLink());
//                    ctx.startActivity(goInput);
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
