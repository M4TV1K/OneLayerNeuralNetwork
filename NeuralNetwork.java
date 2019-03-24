
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
        //toClassify.normalize();
        int selector[] = new int[perceptrons.size()];
        for (int i = 0; i < perceptrons.size(); ++i) {
            perceptrons.get(i).checkLanguage(toClassify);
            selector[i] = toClassify.classifiedValue;
        }

        //SELECTOR
        int count = 0;
        for (int select : selector) {
            count += select;
        }
        if (count == 0) System.out.println("Neural network wasn't able to define language:(");
        else if (count != 1) System.out.println("Neural network has defined more than one language!");
        else {
            for (int select = 0; select < selector.length; ++select) {
                if (selector[select] == 1) {
                    System.out.println(toClassify.language + " text " +
                            "was classified as: " + perceptrons.get(select).language);
                    break;
                }
            }
        }
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
