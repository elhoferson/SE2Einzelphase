package at.alehofer.se2einzelphase.threads;

import android.os.AsyncTask;
import android.widget.TextView;

public abstract class AbstractOutputThread extends AsyncTask<String, Void, String> {

    private String matrikelNumber;
    private TextView outputView;

    public AbstractOutputThread (String matrikelNumber, TextView outputView) {
        this.matrikelNumber = matrikelNumber;
        this.outputView = outputView;
    }


    public abstract String calculate(String matrikelNumber);

    @Override
    protected String doInBackground(String... strings) {
        return calculate(matrikelNumber);
    }

    @Override
    protected void onPostExecute(String s) {
        outputView.setText(s);
    }
}
