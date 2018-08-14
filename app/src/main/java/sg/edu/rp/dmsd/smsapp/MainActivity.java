package sg.edu.rp.dmsd.smsapp;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    Button btnSend;
    EditText etTo;
    EditText etMsg;
    BroadcastReceiver br = new MsgReceiver();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkPermission();
        btnSend = findViewById(R.id.buttonSend);
        etTo = findViewById(R.id.editTextToName);
        etMsg = findViewById(R.id.editTextMsg);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String to = etTo.getText().toString();
                String message =  etMsg.getText().toString();
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(to,null,message,null,null);
            }
        });

    }

    private void checkPermission() {
        int permissionSendSMS = ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS);
        int permissionRecvSMS = ContextCompat.checkSelfPermission(this,Manifest.permission.RECEIVE_SMS);
        if(permissionSendSMS != PackageManager.PERMISSION_GRANTED
                && permissionRecvSMS != PackageManager.PERMISSION_GRANTED ){
            String[] permissionNeeded = new String[]{Manifest.permission.SEND_SMS,Manifest.permission.RECEIVE_SMS};
            ActivityCompat.requestPermissions(this,permissionNeeded,1);
        }
    }


}
