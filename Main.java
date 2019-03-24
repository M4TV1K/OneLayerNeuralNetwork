
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static final File TRAIN_ENGLISH = new File("E:\\Programming\\AI\\OneLayerNeuralNetwork\\English\\train.txt");
    public static final File TEST_ENGLISH = new File("E:\\Programming\\AI\\OneLayerNeuralNetwork\\English\\test.txt");
    public static final File TRAIN_GERMAN = new File("E:\\Programming\\AI\\OneLayerNeuralNetwork\\German\\train.txt");
    public static final File TEST_GERMAN = new File("E:\\Programming\\AI\\OneLayerNeuralNetwork\\German\\test.txt");
    public static final File TRAIN_SPANISH = new File("E:\\Programming\\AI\\OneLayerNeuralNetwork\\Spanish\\train.txt");
    public static final File TEST_SPANISH = new File("E:\\Programming\\AI\\OneLayerNeuralNetwork\\Spanish\\test.txt");

    public static void main(String[] args){
        System.out.println("Hello language explorer!");
        //Perceptron.learnRate();

        Vector englishVector = new Vector("English");
        englishVector.readData(TRAIN_ENGLISH);
        Perceptron englishPerceptron = new Perceptron(englishVector);

        Vector germanVector = new Vector("German");
        germanVector.readData(TRAIN_GERMAN);
        Perceptron germanPerceptron = new Perceptron(germanVector);

        Vector spanishVector = new Vector("Spanish");
        spanishVector.readData(TRAIN_SPANISH);
        Perceptron spanishPerceptron = new Perceptron(spanishVector);


        ArrayList<Perceptron> perceptrons = new ArrayList<>();
        perceptrons.add(englishPerceptron);
        perceptrons.add(germanPerceptron);
        perceptrons.add(spanishPerceptron);

        NeuralNetwork neuralLanguageNetwork = new NeuralNetwork();
        neuralLanguageNetwork.addPerceptrons(perceptrons);

        System.out.println("Training neural network...");
        int attempts = 0;
        int count = 0;
        while (true) {
            neuralLanguageNetwork.trainLanguageClassifier(englishVector);
            neuralLanguageNetwork.trainLanguageClassifier(germanVector);
            neuralLanguageNetwork.trainLanguageClassifier(spanishVector);
            int totalTraining = neuralLanguageNetwork.calculateTotalTrainings();
            if (totalTraining == attempts) break;
            attempts = totalTraining;
            ++count;
        }

        for (Perceptron perceptron : perceptrons) {
            //perceptron.weight.normalize();
            System.out.println(perceptron + " threshold and points: ");
            System.out.println(Arrays.toString(perceptron.weight.points));
        }

        System.out.println("Amount of training: " + count);

        System.out.println("\nTesting neural network...");
        Vector englishTest = new Vector("English");
        englishTest.readData(TEST_ENGLISH);
        neuralLanguageNetwork.classifyLanguage(englishTest);

        Vector germanTest = new Vector("German");
        germanTest.readData(TEST_GERMAN);
        neuralLanguageNetwork.classifyLanguage(germanTest);

        Vector spanishTest = new Vector("Spanish");
        spanishTest.readData(TEST_SPANISH);
        neuralLanguageNetwork.classifyLanguage(spanishTest);

        while (true) {
            System.out.println("\nEnter any text using latin letters:");
            String myText = Perceptron.SCAN.nextLine();
            neuralLanguageNetwork.classifyTextLanguage(myText);
        }

    }
}
