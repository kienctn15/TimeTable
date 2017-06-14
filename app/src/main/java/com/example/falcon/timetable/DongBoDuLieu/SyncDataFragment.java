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
import com.github.nkzawa.socketio.client.Socket;

import org.json.JSONArray;
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
    private Socket mSocket;
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
                        HideDialog();
                        Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }
    };

    private Emitter.Listener onSaveUpdate = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    JSONObject jsonObject = (JSONObject) args[0];
                    String data = null;

                    try {
                        data = jsonObject.getString("result2");


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    if (data == "true") {
                        HideDialog();
                        Toast.makeText(getActivity(), "Save Complete", Toast.LENGTH_SHORT).show();
                    } else {
                        HideDialog();
                        Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }
    };

    private Emitter.Listener onresult_getAllCongViecByUsername = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    JSONObject jsonObject = (JSONObject) args[0];
                    String data = null;

                    try {
                        data = jsonObject.getString("userList");
                        JSONArray arr = new JSONArray(data);


                        for(int i=0; i<arr.length(); i++){
                            JSONObject obj = arr.getJSONObject(i);
                            CongViec congViec = new CongViec();
                            congViec.setId(Integer.parseInt(obj.getString("idcv")));
                            congViec.setTitle(obj.getString("title"));
                            congViec.setDate(obj.getString("date"));
                            congViec.setTime_start(obj.getString("timestart"));
                            congViec.setTime_end(obj.getString("timeend"));
                            congViec.setNote(obj.getString("description"));

                            if(db.check_congviec(congViec)){
                                db.update_congviec(congViec);
                            }else{
                                db.insert_table_congviec(congViec);
                            }

                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    if (data.toString().length()>=0) {
                        HideDialog();
                        Toast.makeText(getActivity(), "Get Complete", Toast.LENGTH_SHORT).show();

                    } else {
                        HideDialog();
                        Toast.makeText(getActivity(), "Empty", Toast.LENGTH_SHORT).show();
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
        mSocket = config.mSocket;
        mSocket.connect();
        mSocket.on("result_save", onSave);
        mSocket.on("result_save_update", onSaveUpdate);
        mSocket.on("result_getAllCongViecByUsername",onresult_getAllCongViecByUsername);
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setCancelable(false);
        btn_save = (Button) myView.findViewById(R.id.btn_save);
        btn_get = (Button) myView.findViewById(R.id.btn_get);

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressDialog.setMessage("Sync....");
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
                            obj.put("idcv", String.valueOf(list_congviec.get(i).getId()));
                            obj.put("title", list_congviec.get(i).getTitle());
                            obj.put("address", list_congviec.get(i).getAddress());
                            obj.put("date", list_congviec.get(i).getDate());
                            obj.put("timestart", list_congviec.get(i).getTime_start());
                            obj.put("timeend", list_congviec.get(i).getTime_end());
                            obj.put("description", list_congviec.get(i).getNote());

                            mSocket.emit("save_time_table", obj);
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

        btn_get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressDialog.setMessage("Getting....");
                ShowDialog();

                userSessionManager = new UserSessionManager(getActivity());

                HashMap<String, String> user = userSessionManager.getUserDetails();
                String username = user.get(UserSessionManager.KEY_USERNAME);

                mSocket.emit("getAllCongViecByUsername", username);


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
