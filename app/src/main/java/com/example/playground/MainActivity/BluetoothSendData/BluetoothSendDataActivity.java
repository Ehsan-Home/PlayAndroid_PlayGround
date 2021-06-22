package com.example.playground.MainActivity.BluetoothSendData;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.playground.R;

import java.util.Arrays;

import static android.bluetooth.BluetoothGatt.GATT_SUCCESS;

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
                // we can filter based on MAC address & name
                final ScanFilter scanFilter = new ScanFilter.Builder()
                        .setDeviceAddress("7E:D0:38:3C:D0:8B")
                        .setDeviceName(null)
//                        Best way to filter is based on UUID that we have
//                        .setServiceUuid()
                        .build();

//                Set up scan mode : LOW_LATENCY
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
            String address = result.getDevice().getAddress();
            String nameText = " name :  " + (name == null ? "Unnamed" : name);
            String addressText = ", address : " + address;
            Log.d("ble_scan",nameText + addressText);
//            Probably the best place to stop the scan
            bluetoothLeScanner.stopScan(scanCallBack);
            result.getDevice().connectGatt(getApplicationContext(),false,bluetoothGattCallback);


            super.onScanResult(callbackType, result);
        }

        @Override
        public void onScanFailed(int errorCode) {
            super.onScanFailed(errorCode);
            Log.e("scan_error", String.valueOf(errorCode));
        }
    };

    private BluetoothGattCallback bluetoothGattCallback = new BluetoothGattCallback() {
        @Override
        public void onConnectionStateChange(BluetoothGatt gatt, int status, int newState) {
            if (status == GATT_SUCCESS)
            {
                gatt.discoverServices();
            }
            else {
                Log.d("ble_scan", "error in onConnectionChange");
                gatt.close();
                gatt = null;
            }

            super.onConnectionStateChange(gatt, status, newState);
        }

        @Override
        public void onServicesDiscovered(BluetoothGatt gatt, int status) {
            BluetoothGattService bluetoothGattService = gatt.getService(null);
            BluetoothGattCharacteristic bluetoothGattCharacteristic = bluetoothGattService.getCharacteristic(null);
            gatt.readCharacteristic(bluetoothGattCharacteristic);
            super.onServicesDiscovered(gatt, status);
        }

        @Override
        public void onCharacteristicRead(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
            final String value = characteristic.getStringValue(0);
            Log.d("ble_result",value);
            BluetoothGattService bluetoothGattService = gatt.getService(null);
//            readNextCharacteristic(gatt,characteristic);
            super.onCharacteristicRead(gatt, characteristic, status);
        }

        @Override
        public void onCharacteristicWrite(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
            super.onCharacteristicWrite(gatt, characteristic, status);
        }
    };
}
