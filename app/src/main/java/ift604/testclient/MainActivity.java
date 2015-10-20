package ift604.testclient;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.HashMap;
import java.util.Map;

import commun.Message;

public class MainActivity extends AppCompatActivity {

    ResponseReceiver receiver = new ResponseReceiver();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent connectionIntent = new Intent(this,Connection.class);
        startService(connectionIntent);

        IntentFilter filter = new IntentFilter(ResponseReceiver.ACTION_RESP);
        filter.addCategory(Intent.CATEGORY_DEFAULT);
        registerReceiver(receiver, filter);

        Button button1=(Button)findViewById(R.id.match1);
        Button button2=(Button)findViewById(R.id.match2);

        button1.setOnClickListener(matchListener);
        button2.setOnClickListener(matchListener);



    }
    @Override
    protected void onStop(){
        super.onStop();
        if(receiver!=null){
            unregisterReceiver(receiver);
            receiver=null;
        }
    }

    public class ResponseReceiver extends BroadcastReceiver {
        public static final String ACTION_RESP =
                "ift604.testclient.intent.action.MESSAGE_PROCESSED";

        @Override
        public void onReceive(Context context, Intent intent) {
            int i=0;
            Button button1=(Button)findViewById(R.id.match1);
            Button button2=(Button)findViewById(R.id.match2);
            Message reponse = (Message) intent.getSerializableExtra(Connection.OUT_message);
                HashMap<Integer, String> matchs = (HashMap<Integer, String>) reponse.getArgument().get(0);
                for (Map.Entry<Integer, String> entry : matchs.entrySet()) {
                    if (i==0){
                        button1.setText("Match no " + entry.getKey() + " : " + entry.getValue());
                        i++;
                    } else if (i==1){
                        button2.setText("Match no " + entry.getKey() + " : " + entry.getValue());
                    }
                }




        }
    }

    View.OnClickListener matchListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent=new Intent(MainActivity.this, matchResume.class);
            switch (v.getId()){
                case R.id.match1:
                    intent.putExtra("numeroMatch",1);
                    break;
                case R.id.match2:
                    intent.putExtra("numeroMatch", 2);
                    break;
            }
            startActivityForResult(intent, 0);



        }
    };


}


