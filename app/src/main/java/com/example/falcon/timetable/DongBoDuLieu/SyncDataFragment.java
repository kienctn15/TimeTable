package com.example.falcon.timetable.DongBoDuLieu;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.falcon.timetable.Config.Config;
import com.example.falcon.timetable.DBHandler.DBHandler;
import com.example.falcon.timetable.DanhSachCongViec.CongViec;
import com.example.falcon.timetable.Login_Register.UserSessionManager;
import com.example.falcon.timetable.R;
import com.github.nkzawa.emitter.Emitter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Admin on 6/13/2017.
 */

public class SyncDataFragment extends Fragment {
    View myView;
    Button btn_save, btn_get;
    DBHandler db;
    private ProgressDialog progressDialog;
    Config config;
    UserSessionManager userSessionManager;


    private Emitter.Listener onSave = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    JSONObject jsonObject = (JSONObject) args[0];
                    String data = null;
                    try {
                        data = jsonObject.getString("result1");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    if (data == "true") {
                        HideDialog();
                        Toast.makeText(getActivity(), "Save Complete", Toast.LENGTH_SHORT).show();


                    } else {
                        Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
                        HideDialog();
                    }
                }
            });
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.fragment_syncdata, container, false);

        db = new DBHandler(getActivity());
        config = new Config();
        config.Connect();
        config.mSocket.on("result_save", onSave);

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setCancelable(false);
        btn_save = (Button) myView.findViewById(R.id.btn_save);
        btn_get = (Button) myView.findViewById(R.id.btn_get);

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressDialog.setMessage("Sign up....");
                ShowDialog();

                List<CongViec> list_congviec = new ArrayList<CongViec>();
                list_congviec = db.get_all_congviec();

                userSessionManager = new UserSessionManager(getActivity());

                HashMap<String, String> user = userSessionManager.getUserDetails();
                String username = user.get(UserSessionManager.KEY_USERNAME);
                if (!list_congviec.isEmpty()) {
                    for (int i = 0; i < list_congviec.size(); i++) {
                        JSONObject obj = new JSONObject();
                        try {
                            obj.put("username", username);
                            obj.put("title",list_congviec.get(i).getTitle());
                            obj.put("address",list_congviec.get(i).getAddress());
                            obj.put("date",list_congviec.get(i).getDate());
                            obj.put("timestart",list_congviec.get(i).getTime_start());
                            obj.put("timeend",list_congviec.get(i).getTime_end());
                            obj.put("description",list_congviec.get(i).getNote());

                            config.mSocket.emit("save_time_table",obj.toString());

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }


                } else {
                    HideDialog();
                    Toast.makeText(getActivity(), "DB Empty!", Toast.LENGTH_SHORT).show();
                }

            }
        });


        return myView;
    }

    private void ShowDialog() {
        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }
    }

    private void HideDialog() {
        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }
}
