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

import at.alehofer.se2einzelphase.threads.AbstractOutputThread;
import at.alehofer.se2einzelphase.threads.std.SocketOutputThread;
import at.alehofer.se2einzelphase.threads.std.SortOutputThread;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("TASK" ,"My Task is nr.: " + (11816488 % 7));
        Log.i("TASK", "Do it");
    }


    public void onClick(View v) {
        setResult("");
        String matrikelNumber = getMatrikelNumber();
        if (StringUtils.isBlank(matrikelNumber)) {
            Log.i("NUMBER", "no number typed in");
            return;
        }
        AbstractOutputThread t = null;
         switch(v.getId()) {
            case R.id.sendButton:
                t = new SocketOutputThread(matrikelNumber);
                break;
            case R.id.calculateButton:
                t = new SortOutputThread(matrikelNumber);
                break;
        }
        t.start();
        try {
            t.join();
            setResult(t.getResult());
        } catch (InterruptedException e) {
            Log.e("THREAD", "thread interrupted", e);
        }
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
