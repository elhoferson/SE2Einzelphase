package at.alehofer.se2einzelphase.threads.std;

import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import at.alehofer.se2einzelphase.threads.AbstractOutputThread;

public class SocketOutputThread extends AbstractOutputThread {

    private static final String HOST = "http://se2-isys.aau.at/";
    private static final int PORT = 53212;

    public SocketOutputThread (String matrikelNumber) {
        super(matrikelNumber);
    }

    @Override
    public String calculate(String matrikelNumber) {
        String result = null;
        try {
            Socket client = new Socket(HOST, PORT);
            DataOutputStream sendToServer = new DataOutputStream(client.getOutputStream());
            sendToServer.writeBytes(matrikelNumber);

            BufferedReader resultReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            result = resultReader.readLine();

            client.close();

        } catch (IOException e) {
            Log.e("SOCKET", "error calling socket " + HOST + ":" + PORT, e);
        }
        return result;
    }
}
