package at.alehofer.se2einzelphase.threads.std;

import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import at.alehofer.se2einzelphase.threads.AbstractOutputThread;

public class SocketOutputThread extends AbstractOutputThread {

    private static final String TAG_SOCKET = "SOCKET";
    private static final String HOST = "se2-isys.aau.at";
    private static final int PORT = 53212;

    public SocketOutputThread (String matrikelNumber, TextView outputView) {
        super(matrikelNumber, outputView);
    }

    @Override
    public String calculate(String matrikelNumber) {
        String result = null;
        Socket client = null;
        try {
            client = new Socket(HOST, PORT);
            DataOutputStream sendToServer = new DataOutputStream(client.getOutputStream());
            sendToServer.writeBytes(matrikelNumber+"\n");

            BufferedReader resultReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            result = resultReader.readLine();

        } catch (IOException e) {
            Log.e(TAG_SOCKET, "error calling socket " + HOST + ":" + PORT, e);
        } finally {
            if (client != null) {
                try {
                    client.close();
                } catch (IOException e) {
                    Log.w(TAG_SOCKET, "error closing socket " + HOST + ":" + PORT, e);
                }
            }
        }
        return result;
    }
}
