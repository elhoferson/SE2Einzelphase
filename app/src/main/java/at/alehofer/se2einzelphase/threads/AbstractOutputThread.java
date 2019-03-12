package at.alehofer.se2einzelphase.threads;

public abstract class AbstractOutputThread extends Thread {

    private String matrikelNumber;
    private String result;

    public AbstractOutputThread (String matrikelNumber) {
        this.matrikelNumber = matrikelNumber;
    }

    @Override
    public void run() {
        result = calculate(matrikelNumber);
    }

    public abstract String calculate(String matrikelNumber);

    public String getResult() {
        return result;
    }
}
