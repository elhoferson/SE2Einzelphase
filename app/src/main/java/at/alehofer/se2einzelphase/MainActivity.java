package at.alehofer.se2einzelphase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    private static final String HOST = "se2-isys.aau.at";
    private static final int PORT = 53212;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("TASK" ,"My Task is nr.: " + (11816488 % 7));
    }


    public void onClick(View v) {
        setResult("");
        String result = null;
        String matrikelNumber = getMatrikelNumber();
        if (StringUtils.isBlank(matrikelNumber)) {
            Log.i("NUMBER", "no number typed in");
            return;
        }

         switch(v.getId()) {
            case R.id.sendButton:
                result = sendToServer(matrikelNumber);
                break;
            case R.id.calculateButton:
                result = calculateResult(matrikelNumber);
                break;
        }

        setResult(result);
    }

    private String sendToServer(String matrikelNumber) {
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

    private String calculateResult(String matrikelNumber) {
        String result = matrikelNumber;
        return result;
    }

    private String getMatrikelNumber() {
        EditText matrikelNumberInput = (EditText) findViewById(R.id.matrikelnumberInput);
        return matrikelNumberInput.getText().toString();
    }

    private void setResult(String result) {
        if (StringUtils.isNotBlank(result)) {
            TextView serverAnswerOutput = (TextView) findViewById(R.id.serverAnswerOutput);
            serverAnswerOutput.setText(result);
        } else {
            Log.e("RESULT", "no result was given");
        }
    }


}
