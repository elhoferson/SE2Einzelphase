package at.alehofer.se2einzelphase;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.apache.commons.lang3.StringUtils;

import at.alehofer.se2einzelphase.threads.AbstractOutputThread;
import at.alehofer.se2einzelphase.threads.std.SocketOutputThread;
import at.alehofer.se2einzelphase.threads.std.SortOutputThread;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // calculate task to do
        // tasks:
        //0 Jede zweite Ziffer der Matrikelnummer durch Ascii Character ersetzen, wobei  1 = a, 2 = b, …
        //1 Quersumme der Matrikelnummer bilden und anschließend als Binärzahl darstellen
        //2 Ziffern der Größe nach sortieren, Primzahlen werden gestrichen
        //3 Nur jene Ziffern ausgeben, die Primzahlen sind
        //4 Alternierende Quersumme bilden und ausgeben ob diese gerade oder ungerade ist.
        //5 Matrikelnummer sortieren wobei zuerst alle geraden dann alle ungeraden Ziffern gereiht sind
        //6 Multiplizieren Sie die Indizes der Matrikelnummer an deren Position gerade
        //  Ziffern stehen und geben Sie das Produkt aus
        Log.i("TASK" ,"My Task is nr.: " + (11816488 % 7));
        Log.i("TASK", "Do it");
    }


    public void onClick(View v) {
        TextView serverAnswerOutput =  findViewById(R.id.serverAnswerOutput);
        serverAnswerOutput.clearComposingText();
        String matrikelNumber = getMatrikelNumber();
        if (StringUtils.isBlank(matrikelNumber)) {
            serverAnswerOutput.setText(getString(R.string.matrikelnumber_warn));
            return;
        }

        AbstractOutputThread t = null;
         switch(v.getId()) {
            case R.id.sendButton:
                t = new SocketOutputThread(matrikelNumber, serverAnswerOutput);
                break;
            case R.id.calculateButton:
                t = new SortOutputThread(matrikelNumber, serverAnswerOutput);
                break;
        }

        if (t != null) {
            t.start();
        }
    }

    private String getMatrikelNumber() {
        EditText matrikelNumberInput = findViewById(R.id.matrikelnumberInput);
        return matrikelNumberInput.getText().toString();
    }


}
