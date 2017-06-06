package com.example.falcon.timetable.TatCaCongViec;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.falcon.timetable.DanhSachCongViec.DanhSachCongViecAdapter;
import com.example.falcon.timetable.DanhSachCongViec.ItemCongViecInfo;
import com.example.falcon.timetable.R;

import java.util.List;

/**
 * Created by Optimus on 06/06/17.
 */

public class TatCaCongViecAdapter extends RecyclerView.Adapter<TatCaCongViecAdapter.MyViewHolder> {

    private List<ItemTatCaCongViecInfo> itemTatCaCongViecInfos;
    private Context context;

    public TatCaCongViecAdapter(List<ItemTatCaCongViecInfo> itemTatCaCongViecInfos) {
        this.itemTatCaCongViecInfos = itemTatCaCongViecInfos;

    }

    @Override
    public TatCaCongViecAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_congviec, parent, false);
        context = parent.getContext();
        return new TatCaCongViecAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TatCaCongViecAdapter.MyViewHolder holder, int position) {
        ItemTatCaCongViecInfo itemTatCaCongViecInfo = itemTatCaCongViecInfos.get(position);
        holder.time.setText(itemTatCaCongViecInfo.getTime());
    }


    @Override
    public int getItemCount() {
        return itemTatCaCongViecInfos.size();
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
        }

        @Override
        public void onClick(View v) {
        }
    }
}
