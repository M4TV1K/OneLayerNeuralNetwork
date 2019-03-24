
import java.util.Scanner;

public class Perceptron {
    public static double alpha = 0.55;
    public String language;
    public double threshold = 0.3;
    public Vector weight = new Vector();
    public int training = 0;

    public final static Scanner SCAN = new Scanner(System.in);

    public Perceptron(Vector languageVector){
        for (int point = 0; point < languageVector.points.length; ++point) {
            weight.points[point] = threshold;
        }
        language = languageVector.language;
    }


    public void checkLanguage(Vector vector) {
        double result = 0.0;
        for (int i = 0; i < vector.points.length; ++i) {
            result += vector.points[i] * weight.points[i];
        }
        if (result >= threshold) vector.classifiedValue = 1;
        else vector.classifiedValue = 0;
    }

    public void trainPerceptron(Vector vector) {
        int difference = vector.trueValue - vector.classifiedValue;
        if (difference == 0) return;
        Vector newWeight = new Vector();
        for (int i = 0; i < vector.points.length; ++i) {
            newWeight.points[i] = weight.points[i] + (difference * alpha * vector.points[i]);
        }
        weight = newWeight;
        threshold += difference * alpha * -1;
        ++training;
    }

    public static void learnRate(){
        System.out.println("Please, enter learning rate!");
        double learnRate;
        while(true) {
            try {
                learnRate = Double.parseDouble(SCAN.next());
                if (learnRate <= 0 || learnRate >= 1) throw new NumberFormatException();
                break;
            } catch (NumberFormatException e) {
                System.out.println("Number should be bigger than 0 and smaller than 1");
            }
        }
        alpha = learnRate;
    }

    @Override
    public String toString() {
        return language + " " + threshold;
    }
}
