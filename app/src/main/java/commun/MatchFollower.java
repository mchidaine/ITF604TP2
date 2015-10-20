package commun;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import ift604.testclient.MainActivity;

/**
 * Created by MarieChidaine on 18/10/2015.
 */
/*public class MatchFollower implements Runnable{
    Integer matchId;
    Match match;
    MainActivity.ClientConnection cc;

    public MatchFollower(Integer matchId, MainActivity.ClientConnection cc) {
        this.matchId = matchId;
        this.cc = cc;
    }

    public void run() {
        Socket client;
        ObjectOutputStream oos;
        ObjectInputStream ois;
        final int SERVERPORT = 2015;
        final String SERVER_IP = "192.168.2.12";
        Integer requestNumber = 0;
        while (true) {
            Message m = new Message(Message.Method.DetailsMatch);
            m.addArgument(matchId);
            // Results
            Message reponse;
            try {
                client = new Socket(SERVER_IP, SERVERPORT); // connect to the server
                oos = new ObjectOutputStream(client.getOutputStream());
                ois = new ObjectInputStream(client.getInputStream());
//                m.setNumero(cc.getNextRequestNumber());
                oos.writeObject(m);
                oos.flush();

                reponse = (Message) ois.readObject();
                //reponse = cc.executeRequest(m);
                match = (Match) reponse.getArgument().get(0);

                // Debug
                Match match = (Match) reponse.getArgument().get(0);
                System.out.println("Periode : " + match.getPeriode());
                System.out.println("Chrono : " + match.getChronometre());
                System.out.println("Score : " + match.getCompteursA().size() + "-" + match.getCompteursB().size());
                for (String s : match.getCompteursA())
                    System.out.println("Scoreur equipe A : " + s);
                for (String s : match.getCompteursB())
                    System.out.println("Scoreur equipe B : " + s);
                for (Penalite p : match.getPenalitesA())
                    System.out.println("Penalite equipe A : " + p.joueur + " | "
                            + (match.getChronometre() - p.chronometreLiberation));
                for (Penalite p : match.getPenalitesB())
                    System.out.println("Penalite equipe B : " + p.joueur + " | "
                            + (match.getChronometre() - p.chronometreLiberation));
            } catch (ClassNotFoundException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }

            // Sleep
            try {
                Thread.sleep(5000);//2 * 60000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

}*/
