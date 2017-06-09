package com.example.falcon.timetable.DanhSachCongViec;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.falcon.timetable.DBHandler.DBHandler;
import com.example.falcon.timetable.R;
import com.example.falcon.timetable.TatCaCongViec.ChiTietCongViecFragment;

import java.util.List;

/**
 * Created by Optimus on 06/06/17.
 */

public class DanhSachCongViecAdapter extends RecyclerView.Adapter<DanhSachCongViecAdapter.MyViewHolder> {

    private List<CongViec> itemCongViecs;
    private Context context;

    public DanhSachCongViecAdapter(List<CongViec> itemCongViecs, Context context) {
        this.itemCongViecs = itemCongViecs;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_congviec, parent, false);
        context = parent.getContext();
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(DanhSachCongViecAdapter.MyViewHolder holder, int position) {
        CongViec congViec = itemCongViecs.get(position);
        holder.title.setText(congViec.getTitle());
        holder.address.setText(congViec.getAddress());
        holder.date.setText(congViec.getDate());
        holder.time.setText(congViec.getTime_start().toString());
        holder.description.setText(congViec.getNote());
    }


    @Override
    public int getItemCount() {
        return itemCongViecs.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView time, title, address, description, date;
        public ImageButton delete;

        public MyViewHolder(View v) {
            super(v);
            time = (TextView) v.findViewById(R.id.item_congviec_Time);
            title = (TextView) v.findViewById(R.id.item_congviec_Title);
            date = (TextView)v.findViewById(R.id.item_congviec_Date);
            address = (TextView) v.findViewById(R.id.item_congviec_Address);
            description = (TextView) v.findViewById(R.id.item_congviec_Description);
            delete = (ImageButton) v.findViewById(R.id.item_congviec_Delete);

            v.setOnClickListener(this);
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                    builder1.setMessage("Bạn có chắc chắn muốn xóa không ?");
                    builder1.setCancelable(true);

                    builder1.setPositiveButton(
                            "Yes",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {

                                    DBHandler db = new DBHandler(context);
                                    db.delete_congviec(itemCongViecs.get(getPosition()).getId());
                                    itemCongViecs.remove(getPosition());
                                    notifyItemRemoved(getPosition());


                                }
                            });

                    builder1.setNegativeButton(
                            "No",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });

                    AlertDialog alert11 = builder1.create();
                    alert11.show();
                }
            });
        }

        @Override
        public void onClick(View v) {
            AppCompatActivity activity = (AppCompatActivity) v.getContext();
            ChiTietCongViecFragment myFragment = new ChiTietCongViecFragment();
            Bundle bundle = new Bundle();
            bundle.putString("id", String.valueOf(itemCongViecs.get(getPosition()).getId()));
            bundle.putString("title", itemCongViecs.get(getPosition()).getTitle());
            bundle.putString("address", itemCongViecs.get(getPosition()).getAddress());
            bundle.putString("date", itemCongViecs.get(getPosition()).getDate());
            bundle.putString("time_start", itemCongViecs.get(getPosition()).getTime_start());
            bundle.putString("time_end", itemCongViecs.get(getPosition()).getTime_end());
            bundle.putString("description", itemCongViecs.get(getPosition()).getNote());

            myFragment.setArguments(bundle);

            activity.getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, myFragment).addToBackStack(null).commit();
        }
    }
}
