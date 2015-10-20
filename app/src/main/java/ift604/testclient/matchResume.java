package ift604.testclient;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.widget.TextView;



import commun.Match;
import commun.Message;


public class matchResume extends AppCompatActivity {

    ResponseReceiver2 receiver2 = new ResponseReceiver2();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_resume);

        Intent intent = getIntent();
        int numeroMatch =intent.getIntExtra("numeroMatch", 0);


        Intent detailsMatchIntent=new Intent(this,MatchDetails.class);

       if(numeroMatch==1){
           detailsMatchIntent.putExtra(MatchDetails.IN_message, 1);
        } else if (numeroMatch==2){
           detailsMatchIntent.putExtra(MatchDetails.IN_message, 2);
        }

        IntentFilter filter = new IntentFilter(ResponseReceiver2.ACTION_RESP);
        filter.addCategory(Intent.CATEGORY_DEFAULT);
        registerReceiver(receiver2, filter);

        startService(detailsMatchIntent);




    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        if (receiver2!=null) {
            unregisterReceiver(receiver2);
            receiver2=null;
        }
    }
    @Override
    public void onBackPressed(){
        super.onBackPressed();
        this.finish();
    }


    public class ResponseReceiver2 extends BroadcastReceiver {
        public static final String ACTION_RESP =
                "ift604.testclient.intent.action.MESSAGE2_PROCESSED";

        @Override
        public void onReceive(Context context, Intent intent) {
            TextView resume = (TextView) findViewById(R.id.matchresume);
            TextView periode=(TextView)findViewById(R.id.periode);
            TextView chrono=(TextView)findViewById(R.id.chrono);
            TextView score=(TextView)findViewById(R.id.score);
            TextView scoreurA=(TextView)findViewById(R.id.scoreurA);
            TextView scoreurB=(TextView)findViewById(R.id.scoreurB);
            TextView penaliteA=(TextView)findViewById(R.id.penaliteA);
            TextView penaliteB=(TextView)findViewById(R.id.penaliteB);

            Message reponse = (Message) intent.getSerializableExtra(Connection.OUT_message);
            if (reponse==null){
                resume.setText("Pas de résumé à afficher");
            } else{
                Match match = (Match) reponse.getArgument().get(0);
                resume.setText("Résumé du match : ");
                periode.setText("Periode : "+match.getPeriode());
                chrono.setText("Chrono : "+match.getChronometre());
                score.setText("Score : "+match.getCompteursA().size() + "-" + match.getCompteursB().size());
            }
        }
    }

}

