package ift604.testclient;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import commun.Message;


public class MatchDetails extends IntentService {
    public static final String OUT_message="matchsList";
    public static final String IN_message="numMatch";
    Socket client;
    final int SERVERPORT = 2015;
    final String SERVER_IP = "192.168.2.12";
    ObjectOutputStream oos;
    ObjectInputStream ois;
    Integer requestNumber = 0;

    public MatchDetails() {
        super("MatchDetails");
    }

    private synchronized Integer getNextRequestNumber(){
        Integer nextNumber = requestNumber;
        requestNumber++;
        return nextNumber;
    }

    public synchronized Message executeRequest(Message m) throws IOException, ClassNotFoundException{
        m.setNumero(getNextRequestNumber());
        oos.writeObject(m);
        oos.flush();

        Message reponse = (Message)ois.readObject();
        return reponse;
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            Message reponse=null;
            try{
                client = new Socket(SERVER_IP, SERVERPORT); // connect to the server
                oos = new ObjectOutputStream(client.getOutputStream());
                ois = new ObjectInputStream(client.getInputStream());
                Message m = new Message(Message.Method.DetailsMatch);
                m.addArgument(intent.getIntExtra(IN_message,0));
                reponse=executeRequest(m);
            }catch (IOException|ClassNotFoundException e){
                e.printStackTrace();
            }
            Intent broadcastIntent = new Intent();
            broadcastIntent.setAction(matchResume.ResponseReceiver2.ACTION_RESP);
            broadcastIntent.addCategory(Intent.CATEGORY_DEFAULT);
            broadcastIntent.putExtra(OUT_message, reponse);
            sendBroadcast(broadcastIntent);

        }
    }


}
