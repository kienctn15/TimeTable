package com.example.falcon.timetable.DanhSachCongViec;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.falcon.timetable.R;

import java.util.List;

/**
 * Created by Optimus on 06/06/17.
 */

public class DanhSachCongViecAdapter extends RecyclerView.Adapter<DanhSachCongViecAdapter.MyViewHolder> {

    private List<ItemCongViecInfo> itemCongViecInfos;
    private Context context;

    public DanhSachCongViecAdapter(List<ItemCongViecInfo> itemCongViecInfos) {
        this.itemCongViecInfos = itemCongViecInfos;

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
        ItemCongViecInfo itemCongViecInfo = itemCongViecInfos.get(position);
        holder.time.setText(itemCongViecInfo.getTime());
    }


    @Override
    public int getItemCount() {
        return itemCongViecInfos.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView time, title, address, description;
        public ImageButton edit, delete;

        public MyViewHolder(View v) {
            super(v);
            time = (TextView) v.findViewById(R.id.item_congviec_Time);
            title = (TextView) v.findViewById(R.id.item_congviec_Title);
            address = (TextView) v.findViewById(R.id.item_congviec_Address);
            description = (TextView) v.findViewById(R.id.item_congviec_Description);
            edit = (ImageButton) v.findViewById(R.id.item_congviec_Edit);
            delete = (ImageButton) v.findViewById(R.id.item_congviec_Delete);
        }

        @Override
        public void onClick(View v) {
        }
    }
}
