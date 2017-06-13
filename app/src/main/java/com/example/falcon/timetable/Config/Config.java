package com.example.falcon.timetable.Config;


import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;


public class Config {
    public Socket mSocket;

    public Config() {
        {
            try {
                mSocket = IO.socket("http://192.168.137.183:3000/");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void Connect() {
        mSocket.connect();
    }


}

