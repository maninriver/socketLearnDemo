package com.example.feng.sockettest;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private EditText show;
    String line = "No data";
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        show = (EditText) findViewById(R.id.show);
        new socketThread().start();


        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                show.setText("来自服务器的数据： " + line);

            }
        };
        Message message = Message.obtain();
        message.what = 1;
        handler.sendMessage(message);

    }


    public class socketThread extends Thread {

        @Override
        public void run() {
            Log.e("derek", "runing");
            clientSocket();


        }
    }


    public String clientSocket() {

        BufferedReader br;
        try {
            Log.e("derek", "Socket 。。。");
            Socket socket = new Socket("192.168.0.104", 9000);
            socket.setSoTimeout(50000);
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            line = br.readLine();
            Log.e("derek", "line"+line);
            br.close();
            socket.close();
        } catch (UnknownHostException unkonw) {
            Log.e(TAG, "UnknownHostException ---来自服务器的数据");
            unkonw.printStackTrace();
        }catch (SocketException socketexception){

            socketexception.printStackTrace();
        }

        catch (IOException e) {
            Log.e("IOException", "来自服务器的数据");
            e.printStackTrace();
        }
        return line;

    }
}
