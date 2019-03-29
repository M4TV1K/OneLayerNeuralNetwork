
import java.util.ArrayList;
import java.util.List;

public class NeuralNetwork {
    public ArrayList<Perceptron> perceptrons = new ArrayList<>();

    public void addPerceptrons(List<Perceptron> perceptrons){
        this.perceptrons.addAll(perceptrons);
    }

    public void trainLanguageClassifier(Vector textVector) {
        for (Perceptron perceptron : perceptrons) {
            if (textVector.language.equals(perceptron.language)) textVector.trueValue = 1;
            else textVector.trueValue = 0;
            perceptron.checkLanguage(textVector);
            perceptron.trainPerceptron(textVector);
        }
    }

    public void classifyLanguage(Vector toClassify) {
        double results[] = new double[perceptrons.size()];
        for (int i = 0; i < perceptrons.size(); ++i) {
            results[i] = perceptrons.get(i).classifyTextLanguage(toClassify);
        }

        for (int i = 0; i < results.length; ++i) {
            System.out.println(results[i] + " - " + perceptrons.get(i).language);
        }

        double max = results[0];
        int maxIndex = 0;
        for (int i = 1; i < results.length; ++i) {
            if (results[i] > max) {
                max = results[i];
                maxIndex = i;
            }
        }
        System.out.println("The language of " + toClassify.language + " text was " +
                "classified as " + perceptrons.get(maxIndex).language + "\n");
    }

    public void classifyTextLanguage(String text) {
        Vector toClassify = new Vector("(need classification)");
        toClassify.createVector(text);
        toClassify.calculateProportion();
        classifyLanguage(toClassify);
    }

    public int calculateTotalTrainings() {
        int total = 0;
        for (Perceptron perceptron : perceptrons) {
            total += perceptron.training;
        }
        return total;
    }

}
