/*
 * Library for parsing csv files.
 *
 * Dependencies: org.apache.commons.csv
 */

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class CSVReader {
    /*
     * Given a path to a .csv file, return a 2d array of Strings where
     * each row in the 2d array represents a row in the .csv file and
     * each column in the array represents a column in the .csv file.
     *
     * Assumes that there are the same number of columns in each row
     * of the .csv file.
     */
    public static String[][] parseCSV(String path) {
        // Read in data from .csv file.
        Reader in;
        CSVParser parser;
        List<CSVRecord> list = null;
        try {
            in = new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8);
            parser = new CSVParser(in, CSVFormat.DEFAULT);
            list = parser.getRecords();
        }
        catch (IOException e) {
            System.out.println("Invalid filename: " + path);
        }

        // Prepare to store dataset in 2d array.
        int numRows;
        int numCols;
        if (list != null) {
            numRows = list.size();
            numCols = list.get(0).size();
        }
        else {
            throw new RuntimeException("Parser did not find any contents in "
                                               + ".csv file. Double-check the "
                                               + ".csv file path and contents.");
        }
        String[][] dataset = new String[numRows][numCols];

        // Store each element of data in the 2d array.
        for (int r = 0; r < numRows; r++) {
            CSVRecord row = list.get(r);
            for (int c = 0; c < numCols; c++)
                dataset[r][c] = row.get(c);
        }

        return dataset;
    }
}
