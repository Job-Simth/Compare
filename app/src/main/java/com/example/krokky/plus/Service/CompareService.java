package com.example.krokky.plus.Service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class CompareService extends Service {
    private MyBinder myBinder;

    @Override
    public void onCreate() {
        super.onCreate();
        myBinder = new MyBinder();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return myBinder;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    @Override
    public boolean onUnbind(Intent intent) {
        Log.i("Msg", "onUnbind run!");
        return super.onUnbind(intent);
    }

    public class MyBinder extends Binder {
        public int compare(int Num1, int Num2) {
            return Num1 - Num2 > 0 ? Num1 : Num2;
        }
        public int compare(String Num1, String Num2) {
            return Integer.valueOf(Num1) - Integer.valueOf(Num2) > 0 ? Integer.valueOf(Num1) : Integer.valueOf(Num2);
        }
    }
}
