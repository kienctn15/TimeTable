package com.example.falcon.timetable.TatCaCongViec;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.falcon.timetable.DanhSachCongViec.CongViec;
import com.example.falcon.timetable.DanhSachCongViec.DanhSachCongViecAdapter;
import com.example.falcon.timetable.DanhSachCongViec.ItemCongViecInfo;
import com.example.falcon.timetable.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Optimus on 06/06/17.
 */

public class TatCaCongViecAdapter extends RecyclerView.Adapter<TatCaCongViecAdapter.MyViewHolder> {

    private List<CongViec> list_congviec;
    private Context context;

    public TatCaCongViecAdapter(List<CongViec> congviec, Context context) {
        this.context=context;
        this.list_congviec=congviec;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.item_tatcacongviec, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TatCaCongViecAdapter.MyViewHolder holder, int position) {
        CongViec congViec = list_congviec.get(position);
        holder.title.setText(congViec.getTitle());
        holder.time.setText(congViec.getTime_start().toString());
        holder.address.setText(congViec.getAddress());
        holder.description.setText(congViec.getNote());



    }


    @Override
    public int getItemCount() {
        return list_congviec.size();
    }




    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView time, title, address, description;
        public ImageButton edit, delete;

        public MyViewHolder(View v) {
            super(v);
            time = (TextView) v.findViewById(R.id.item_tatcacongviec_Time);
            title = (TextView) v.findViewById(R.id.item_tatcacongviec_Title);
            address = (TextView) v.findViewById(R.id.item_tatcacongviec_Address);
            description = (TextView) v.findViewById(R.id.item_tatcacongviec_Description);
            edit = (ImageButton) v.findViewById(R.id.item_tatcacongviec_Edit);
            delete = (ImageButton) v.findViewById(R.id.item_tatcacongviec_Delete);

            v.setOnClickListener(this);
            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println("---------------------" + getPosition());
                }
            });
        }

        @Override
        public void onClick(View v) {
            System.out.println("++++++++++++++ "+ getPosition());
            AppCompatActivity activity = (AppCompatActivity) v.getContext();

            ChiTietCongViecFragment myFragment = new ChiTietCongViecFragment();
            Bundle bundle = new Bundle();
            bundle.putString("id", String.valueOf(list_congviec.get(getPosition()).getId()));
            bundle.putString("title",list_congviec.get(getPosition()).getTitle());
            bundle.putString("address",list_congviec.get(getPosition()).getAddress());
            bundle.putString("date",list_congviec.get(getPosition()).getDate());
            bundle.putString("time_start",list_congviec.get(getPosition()).getTime_start());
            bundle.putString("time_end",list_congviec.get(getPosition()).getTime_end());
            bundle.putString("description",list_congviec.get(getPosition()).getNote());

            myFragment.setArguments(bundle);

            activity.getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, myFragment).addToBackStack(null).commit();

        }
    }
}
