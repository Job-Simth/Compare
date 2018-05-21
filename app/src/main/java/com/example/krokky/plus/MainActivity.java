package com.example.krokky.plus;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.krokky.plus.Service.CompareService;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText ed;
    private EditText ed2;
    private Button bt;
    private CompareService.MyBinder myBinder;
    private ServiceConnection myServiceConnection;
    private Intent myService;
    private boolean isBind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myService = new Intent(MainActivity.this, CompareService.class);
        myServiceConnection = new MyServiceConnection();

        //绑定一个服务，以便调用服务内的方法
        isBind = getApplicationContext().bindService(myService,myServiceConnection, Service.BIND_AUTO_CREATE);

        ed = findViewById(R.id.editText);
        ed2 = findViewById(R.id.editText2);
        bt = findViewById(R.id.button);
        bt.setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        //退出Activity时要解除绑定
        if (isBind && myServiceConnection != null){
            getApplicationContext().unbindService(myServiceConnection);
        }
        super.onDestroy();
    }

    class MyServiceConnection implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            myBinder = (CompareService.MyBinder) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                Toast.makeText(
                        MainActivity.this,
                        "较大的数是" + myBinder.compare(ed.getText().toString(),
                                ed2.getText().toString()), Toast.LENGTH_LONG).show();
                break;
            default:
                break;
        }
    }
}
