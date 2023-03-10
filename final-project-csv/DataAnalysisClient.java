/*
 * This client class is used to read in a .csv file and plot its data onto a graphical display.
 *
 * To include all .jar files in lib and .lift folders, here are the
 * operating-system specific compile and execution commands:
 *
 * For Mac and Linux:
 * Compile: javac-introcs -cp ".:lib/*:.lift/*" DataAnalysisClient.java
 * Execute: java-introcs -cp ".:lib/*:.lift/*" DataAnalysisClient FILEPATH
 *
 * For Windows:
 * Compile: javac-introcs -cp ".;lib/*;.lift/*" DataAnalysisClient.java
 * Execute: java-introcs -cp ".;lib/*;.lift/*" DataAnalysisClient FILEPATH
 *
 * Example (on Mac/Linux, replace ":" with ";" for Windows):
 * java-introcs -cp ".:lib/*:.lift/*" DataAnalysisClient data/database_file.csv
 */

public class DataAnalysisClient {
    public static void main(String[] args) {
        // Get .csv file path from command line.
        String filePath = args[0];

        // Parse .csv file and save as 2d array.
        String[][] dataset = CSVReader.parseCSV(filePath);

        // TODO: Delete/modify the sample code and replace with your own code!
        // Sample code to print out the contents of dataset.
        DataAnalysisFunctions.plot(dataset, filePath);
    }
}
