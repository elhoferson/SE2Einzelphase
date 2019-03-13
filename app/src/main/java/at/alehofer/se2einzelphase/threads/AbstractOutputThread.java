package at.alehofer.se2einzelphase.threads;

import android.widget.TextView;

public abstract class AbstractOutputThread extends Thread {

    private String matrikelNumber;
    private TextView outputView;

    public AbstractOutputThread (String matrikelNumber, TextView outputView) {
        this.matrikelNumber = matrikelNumber;
        this.outputView = outputView;
    }

    @Override
    public void run() {
        String result = calculate(matrikelNumber);
        outputView.setText(result);
    }

    public abstract String calculate(String matrikelNumber);
}
