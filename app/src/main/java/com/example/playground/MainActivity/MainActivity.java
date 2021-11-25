package com.example.playground.MainActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.playground.MainActivity.BluetoothSendData.BluetoothSendDataActivity;
import com.example.playground.MainActivity.ImageView.ImageViewActivity;
import com.example.playground.MainActivity.RetrofitGet.RetrofitGetActivity;
import com.example.playground.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rv;
    private ArrayList<MainActivityDataInstance> buttons = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv = findViewById(R.id.mainactivity_rv);
        fillButtons();
        setUpRecyclerView(rv);
    }

    public void setUpRecyclerView(RecyclerView rv) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        RecyclerView.Adapter adapter = new MainActivityAdapter(buttons,this);
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(adapter);
    }

    private void fillButtons () {
        Intent imageViewIntent = new Intent(this, ImageViewActivity.class);
        Intent sendBluetoothDataIntent = new Intent(this, BluetoothSendDataActivity.class);
//        Intent AWSIoTIntent = new Intent(this, AWSIoTActivity.class);
        Intent retrofitGetActivity = new Intent(this, RetrofitGetActivity.class);

        buttons.add(new MainActivityDataInstance("Image View",R.drawable.ic_image,imageViewIntent));
        buttons.add(new MainActivityDataInstance("Send Data via Bluetooth",R.drawable.ic_baseline_send_ble,sendBluetoothDataIntent));
        buttons.add(new MainActivityDataInstance("Retrofit Get", R.drawable.ic_wifi,retrofitGetActivity));

//        buttons.add(new MainActivityDataInstance("AWS IoT",R.drawable.ic_baseline_developer_board_24,AWSIoTIntent));
    }
}