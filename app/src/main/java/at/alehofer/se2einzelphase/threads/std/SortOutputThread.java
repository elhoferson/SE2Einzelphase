package at.alehofer.se2einzelphase.threads.std;

import android.widget.TextView;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import at.alehofer.se2einzelphase.threads.AbstractOutputThread;

public class SortOutputThread extends AbstractOutputThread {


    public SortOutputThread(String matrikelNumber, TextView outputView) {
        super(matrikelNumber, outputView);
    }

    @Override
    public String calculate(String matrikelNumber) {
        List<Integer> oddNumbers = new ArrayList<>();
        List<Integer> evenNumbers = new ArrayList<>();
        for (char digit : matrikelNumber.toCharArray()) {
            int number = Character.getNumericValue(digit);
            if (number % 2 == 0) {
                evenNumbers.add(number);
            } else {
                oddNumbers.add(number);
            }
        }
        Collections.sort(oddNumbers);
        Collections.sort(evenNumbers);
        return StringUtils.join(evenNumbers,"")+ StringUtils.join(oddNumbers,"");
    }
}
