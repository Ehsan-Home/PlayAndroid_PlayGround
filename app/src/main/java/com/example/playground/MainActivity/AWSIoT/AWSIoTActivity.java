package com.example.playground.MainActivity.AWSIoT;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.mobileconnectors.iot.AWSIotMqttLastWillAndTestament;
import com.amazonaws.mobileconnectors.iot.AWSIotMqttManager;
import com.amazonaws.mobileconnectors.iot.AWSIotMqttQos;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.iot.AWSIotClient;
import com.example.playground.R;

import java.util.UUID;

public class AWSIoTActivity extends AppCompatActivity {
//    The endpoint of AWS IoT
    public static final String CUSTOMER_SPECIFIC_ENDPOINT = "CHANGE_ME";
//    Pool ID for cognito which needs to be unauthenticated pool
//    AWS permissions
    public static final String COGNITO_POOL_ID = "CHANGE_ME";
//    Region of AWS IoT
    public static final Regions MY_REGION = Regions.CA_CENTRAL_1;

    private CognitoCachingCredentialsProvider credentialsProvider;
    private AWSIotMqttManager mqttManager;
    private AWSIotClient mIotAndroidClient;

    private String clientId;

    private TextView clientIdTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_awsiot);

        clientIdTextView = findViewById(R.id.clientIdText);

        clientId = UUID.randomUUID().toString();
        clientIdTextView.setText("Client id : " + clientId);

        credentialsProvider = new CognitoCachingCredentialsProvider(
                getApplicationContext(),
                COGNITO_POOL_ID,
                MY_REGION
        );

        Region region = Region.getRegion(MY_REGION);

        mqttManager = new AWSIotMqttManager(clientId,CUSTOMER_SPECIFIC_ENDPOINT);
        mqttManager.setKeepAlive(10);
        AWSIotMqttLastWillAndTestament lwt = new AWSIotMqttLastWillAndTestament("my/lwt/topic",
                "Android device lost connection", AWSIotMqttQos.QOS0);
        mqttManager.setMqttLastWillAndTestament(lwt);

        mIotAndroidClient = new AWSIotClient(credentialsProvider);
        mIotAndroidClient.setRegion(region);




    }
}
