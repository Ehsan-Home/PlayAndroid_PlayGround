package com.example.playground.MainActivity.BluetoothSendData;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.playground.R;

import java.util.Arrays;
import java.util.UUID;

public class BluetoothSendDataActivity extends AppCompatActivity {
    private BluetoothAdapter bluetoothAdapter;
    private BluetoothLeScanner bluetoothLeScanner;
    private BluetoothGatt bluetoothGatt;
//    private static final UUID UUID_Service = UUID.fromString("19fc95c0-c111–11e3–9904–0002a5d5c51b");
//    final UUID UUID_characteristic = UUID.fromString("21fac9e0-c111–11e3–9246–0002a5d5c51b");

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_bluetooth_data);

        BluetoothManager bluetoothManager = (BluetoothManager)getSystemService(BLUETOOTH_SERVICE);
        bluetoothAdapter = bluetoothManager.getAdapter();
        startScan();
    }

    private void startScan() {
        if (bluetoothAdapter == null || !bluetoothAdapter.isEnabled())
        {
            Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableIntent,1);
        }
        else {
            bluetoothLeScanner = bluetoothAdapter.getBluetoothLeScanner();
            if (bluetoothLeScanner != null) {
                final ScanFilter scanFilter = new ScanFilter.Builder().build();
                ScanSettings scanSettings =new ScanSettings.Builder().setScanMode(ScanSettings.SCAN_MODE_LOW_LATENCY).build();
                bluetoothLeScanner.startScan(Arrays.asList(scanFilter),scanSettings,scanCallBack);
            }
        }
    }

    private ScanCallback scanCallBack = new ScanCallback() {
        @Override
        public void onScanResult(int callbackType, ScanResult result) {
            if(bluetoothLeScanner != null) {
                bluetoothLeScanner.stopScan(scanCallBack);
            }
            String name = result.getDevice().getName();
            Log.d("ble_scan",name == null ? "unnamed": name);

            super.onScanResult(callbackType, result);
        }
    };
}
