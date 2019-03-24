import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static java.nio.charset.StandardCharsets.UTF_8;

public class Vector {
    public double letters[] = new double[26];
    public double points[] = new double[26];
    public int classifiedValue;
    public int trueValue;
    public String language;

    public Vector(String language) {
        this.language = language;
    }

    public Vector(){}

    public void createVector(String line) {
        line = line.toLowerCase();
        for (int letter = 0; letter < line.length(); ++letter) {
            if (line.charAt(letter) >= 'a'  && line.charAt(letter) <= 'z') {
                ++letters[line.charAt(letter) - 'a'];
            }
        }
    }

    /*public void normalize() {
        double magnitude = 0;
        for (int i = 0; i < points.length; ++i) {
            magnitude += Math.pow(points[i], 2);
        }
        magnitude = Math.sqrt(magnitude);

        for (int i = 0; i < points.length; ++i) {
            points[i] /= magnitude;
        }
    }*/

    public void readData(File file) {
        if (!file.exists() || !file.isFile()) {
            System.out.println("This file doesn't exist or it isn't file at all");
            System.exit(-1);
        }
        try {
            BufferedReader readData = Files.newBufferedReader(Paths.get(file.toURI()), UTF_8);
            String line = readData.readLine();
            do {
                createVector(line);
                line = readData.readLine();
            }   while (line != null);
            readData.close();
        }
        catch (IOException e) {
            System.out.println("Impossible to read data!");
            System.exit(-2);
        }
        calculateProportion();
    }

    public void calculateProportion() {
        double totalLetters = 0;
        for (double letter : letters) {
            totalLetters += letter;
        }
        System.out.println(language + " " + totalLetters + " input");
        for (int i = 0; i < letters.length; ++i) {
            points[i] = letters[i] / totalLetters;
        }
    }

    public void showLetters(){
        char letter = 'A';
        for (int i = 0; i < letters.length ; ++i) {
            System.out.print(letter + "." + (int) letters[i] + " ");
            if (i % 3 == 0) System.out.println();
            ++letter;
        }
        System.out.println();
    }

    @Override
    public String toString(){
        StringBuilder allPoints = new StringBuilder("[");
        for (int i = 0; i < points.length - 1; ++i) {
            allPoints.append(points[i]).append(", ");
        }
        allPoints.append(points[points.length - 1]).append("]");
        allPoints.append(" - ").append(trueValue);
        return allPoints.toString();
    }
}
