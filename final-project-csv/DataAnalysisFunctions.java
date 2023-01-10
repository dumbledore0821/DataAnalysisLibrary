import java.awt.Color;

public class DataAnalysisFunctions {

    // Helper function that generates an array with relevant values
    private static double[] importantValues(String[][] dataset) {
        double xMin = Double.POSITIVE_INFINITY;
        double xMax = Double.NEGATIVE_INFINITY;
        double yMin = Double.POSITIVE_INFINITY;
        double yMax = Double.NEGATIVE_INFINITY;
        int rowNum = dataset.length;

        // Iterating through the dataset array
        for (int i = 1; i < rowNum; i++) {
            double xVal = Double.parseDouble(dataset[i][0]);
            double yVal = Double.parseDouble(dataset[i][1]);
            // Changing the corresponding values based on conditions
            if (xVal < xMin) {
                xMin = xVal;
            }
            if (xVal > xMax) {
                xMax = xVal;
            }
            if (yVal < yMin) {
                yMin = yVal;
            }
            if (yVal > yMax) {
                yMax = yVal;
            }
        }

        double xDist = xMax - xMin;
        double yDist = yMax - yMin;
        double scaleMin = Math.min(xMin - 0.6 * xDist, yMin - 0.6 * yDist);
        double scaleMax = Math.max(xMax + 0.6 * xDist, yMax + 0.6 * yDist);

        // Returning results in an array
        double[] result = {
                roundToTwo(xMin), roundToTwo(xMax), roundToTwo(yMin),
                roundToTwo(yMax), roundToTwo(xDist), roundToTwo(yDist),
                roundToTwo(scaleMin), roundToTwo(scaleMax)
        };
        return result;
    }

    // Computing the edges of the dataset
    private static double[] findEdges(String[][] dataset) {
        // Extracting the important values of a dataset
        double[] impoVals = importantValues(dataset);
        double xMin = impoVals[0];
        double xMax = impoVals[1];
        double yMin = impoVals[2];
        double yMax = impoVals[3];
        double xDist = impoVals[4];
        double yDist = impoVals[5];

        // Max and min values of the grid
        double gridMax = Math.max(xMax, yMax);
        double gridMin = Math.min(xMin, yMin);

        // Computing the edges of the coordinate lines based on
        // which quadrants the data values are in

        // Finding the left and right edges used to draw the x coordinate line
        double leftEdge, rightEdge;
        if (xMin >= 0 && xMax >= 0) {
            leftEdge = 0;
            rightEdge = gridMax + 0.15 * xDist;
        }
        else if (xMin <= 0 && xMax <= 0) {
            leftEdge = gridMin - 0.15 * xDist;
            rightEdge = 0;
        }
        else {
            leftEdge = gridMin - 0.15 * xDist;
            rightEdge = gridMax + 0.15 * xDist;
        }

        // Finding the top and bottom edges used to draw the y coordinate line
        double topEdge, bottomEdge;
        if (yMin >= 0 && yMax >= 0) {
            bottomEdge = 0;
            topEdge = gridMax + 0.15 * yDist;
        }
        else if (yMin <= 0 && yMax <= 0) {
            bottomEdge = gridMin - 0.15 * yDist;
            topEdge = 0;
        }
        else {
            bottomEdge = gridMin - 0.15 * yDist;
            topEdge = gridMax + 0.15 * yDist;
        }

        // Storing the results into an array
        double[] results = { leftEdge, rightEdge, bottomEdge, topEdge };
        return results;
    }

    // Rounding a number to two decimal places
    private static double roundToTwo(double num) {
        return Math.round(num * 100.0) / 100.0;
    }

    // Removing all instances of '_' from a string
    private static String cutDashes(String string) {
        StringBuilder sb = new StringBuilder(string);
        for (int i = 0; i < string.length(); i++) {
            if (sb.substring(i, i + 1).equals("_")) {
                sb.replace(i, i + 1, " ");
            }
        }
        return sb.toString();
    }

    // Removing irrelevant parts of the title
    private static String cleanTitle(String title) {
        // Deleting 'data/' part of the title
        StringBuilder sb = new StringBuilder(cutDashes(title));
        sb.replace(0, 5, "");
        // Deleting '.csv' part of the title
        sb.replace(sb.length() - 4, sb.length(), "");
        return sb.toString();
    }

    // Capitalize the first letter of every word
    private static String capitalize(String string) {
        string = cutDashes(string);
        // Capitalizing very first letter
        string = string.substring(0, 1).toUpperCase() + string.substring(1);
        for (int i = 0; i < string.length() - 2; i++) {
            // Finding the first letter of a word
            if (string.charAt(i) == ' ') {
                // Capitalizing the first letter
                string = string.substring(0, i) + string.charAt(i)
                        + string.substring(i + 1, i + 2).toUpperCase()
                        + string.substring(i + 2);
            }
        }
        return string;
    }

    // Used to plot a dataset using StdDraw
    public static void plotData(String[][] dataset, String title) {
        // Extracting necessary values from dataset
        double[] impoVals = importantValues(dataset);
        double xMin = impoVals[0];
        double xMax = impoVals[1];
        double yMin = impoVals[2];
        double yMax = impoVals[3];
        int rowNum = dataset.length;
        String xLabel = capitalize(dataset[0][0]);
        String yLabel = capitalize(dataset[0][1]);

        // Finding a proper scale for the plot based on min and max values
        double scaleMin = impoVals[6];
        double scaleMax = impoVals[7];
        double scale = scaleMax - scaleMin;
        StdDraw.setScale(scaleMin, scaleMax);
        StdDraw.setTitle("Plot of " + xLabel + " vs " + yLabel);

        // Drawing a background for the plot
        StdDraw.setPenColor(StdDraw.PRINCETON_ORANGE);
        StdDraw.filledSquare(0, 0, 1000);

        // Drawing the x coordinate line and edges
        double diagonal = Math.sqrt(2) / 2 * scale * 0.01;
        StdDraw.setPenColor(Color.BLACK);
        StdDraw.setPenRadius(0.004);

        // Extracting the values for edges
        double[] edges = findEdges(dataset);
        double leftEdge = edges[0];
        double rightEdge = edges[1];
        double bottomEdge = edges[2];
        double topEdge = edges[3];

        // Drawing the x coordinate line
        if (xMin >= 0 && xMax >= 0) {
            StdDraw.line(rightEdge, 0, rightEdge - diagonal, -diagonal);
            StdDraw.line(rightEdge, 0, rightEdge - diagonal, diagonal);
        }
        else if (xMin <= 0 && xMax <= 0) {
            StdDraw.line(leftEdge, 0, leftEdge + diagonal, -diagonal);
            StdDraw.line(leftEdge, 0, leftEdge + diagonal, diagonal);
        }
        else {
            StdDraw.line(rightEdge, 0, rightEdge - diagonal, -diagonal);
            StdDraw.line(rightEdge, 0, rightEdge - diagonal, diagonal);
            StdDraw.line(leftEdge, 0, leftEdge + diagonal, -diagonal);
            StdDraw.line(leftEdge, 0, leftEdge + diagonal, diagonal);
        }
        StdDraw.line(leftEdge, 0, rightEdge, 0);

        // Drawing the y coordinate line
        if (yMin >= 0 && yMax >= 0) {
            StdDraw.line(0, topEdge, -diagonal, topEdge - diagonal);
            StdDraw.line(0, topEdge, diagonal, topEdge - diagonal);
        }
        else if (yMin <= 0 && yMax <= 0) {
            StdDraw.line(0, bottomEdge, -diagonal, bottomEdge + diagonal);
            StdDraw.line(0, bottomEdge, diagonal, bottomEdge + diagonal);
        }
        else {
            StdDraw.line(0, topEdge, -diagonal, topEdge - diagonal);
            StdDraw.line(0, topEdge, diagonal, topEdge - diagonal);
            StdDraw.line(0, bottomEdge, -diagonal, bottomEdge + diagonal);
            StdDraw.line(0, bottomEdge, diagonal, bottomEdge + diagonal);
        }
        StdDraw.line(0, topEdge, 0, bottomEdge);

        // Writing the labels onto the screen
        StdDraw.text((leftEdge + rightEdge) / 2, bottomEdge - scale * 0.035, xLabel);
        StdDraw.text(leftEdge - scale * 0.035, (topEdge + bottomEdge) / 2, yLabel, 90);

        // Drawing circles that corresponds to the data points
        for (int i = 1; i < rowNum; i++) {
            double xVal = Double.parseDouble(dataset[i][0]);
            double yVal = Double.parseDouble(dataset[i][1]);
            StdDraw.filledCircle(xVal, yVal, scale * 0.005);
        }

        // Displaying the title
        StdDraw.text((leftEdge + rightEdge) / 2, topEdge + scale * 0.15, cleanTitle(title));
    }

    // Finding the mean of an array
    private static double mean(double[] data) {
        double mean = 0;
        for (int i = 0; i < data.length; i++) {
            mean += data[i];
        }
        return roundToTwo(mean / data.length);
    }

    // Finding the median of an array
    private static double median(double[] data) {
        double median;
        if (data.length % 2 == 0) {
            median = (data[data.length / 2 - 1] + data[data.length / 2]) / 2;
        }
        else {
            median = data[data.length / 2];

        }
        return roundToTwo(median);
    }

    // Finding the standard deviation of an array
    private static double std(double[] data) {
        double std = 0;
        double mean = mean(data);
        for (int i = 0; i < data.length; i++) {
            std += Math.pow(data[i] - mean, 2);
        }
        return roundToTwo(std / data.length);
    }

    // Printing the descriptive statistics onto the graph
    public static void printStats(String[][] dataset) {
        // Extracting values from a dataset
        int rowNum = dataset.length;
        double[] xVals = new double[dataset.length - 1];
        double[] yVals = new double[dataset.length - 1];
        for (int i = 1; i < rowNum; i++) {
            xVals[i - 1] = Double.parseDouble(dataset[i][0]);
            yVals[i - 1] = Double.parseDouble(dataset[i][1]);
        }

        // Computing means
        double xMean = mean(xVals);
        double yMean = mean(yVals);

        // Computing medians
        double xMedian = median(xVals);
        double yMedian = median(yVals);

        // Computing standard deviations
        double xStd = std(xVals);
        double yStd = std(yVals);

        // Computing mins and maxs
        double[] impoVals = importantValues(dataset);
        double xMin = impoVals[0];

        // Extracting the edges
        double[] edges = findEdges(dataset);
        double leftEdge = edges[0];
        double bottomEdge = edges[2];
        double scaleMin = impoVals[6];
        double scaleMax = impoVals[7];
        double scale = scaleMax - scaleMin;

        // Array of the values that needs to be printed
        String[] stringValues = {
                "x Mean", "y Mean", "x Median",
                "y Median", "x SD", "y SD"
        };
        double[] values = { xMean, yMean, xMedian, yMedian, xStd, yStd };

        // Establishing the initial values
        double initialScale;
        if (xMin > 0) {
            initialScale = -0.275;
        }
        else {
            initialScale = -0.200;
        }

        // Printing the descriptive values onto the graph
        for (int i = 0; i < values.length; i++) {
            StdDraw.text(leftEdge + scale * (initialScale + (i + 1) * 0.145),
                         bottomEdge - scale * 0.1,
                         stringValues[i]);
            StdDraw.text(leftEdge + scale * (initialScale + (i + 1) * 0.145),
                         bottomEdge - scale * 0.15,
                         String.valueOf(values[i]));
        }
    }

    // Returns the predicted y value given the model and x value
    private static double linearModel(double x, double m, double b) {
        return m * x + b;
    }

    // Creating and drawing a linear regression for the dataset
    public static void regressionLine(String[][] dataset) {
        // Extracting the values from the dataset
        int rowNum = dataset.length;
        double[] xVals = new double[dataset.length - 1];
        double[] yVals = new double[dataset.length - 1];
        for (int i = 1; i < rowNum; i++) {
            xVals[i - 1] = Double.parseDouble(dataset[i][0]);
            yVals[i - 1] = Double.parseDouble(dataset[i][1]);
        }

        // Evaluating the needed values for the regression line
        double sumX = 0;
        double sumY = 0;
        double sumXX = 0;
        double sumXY = 0;

        // Computing the values
        for (int i = 0; i < xVals.length; i++) {
            sumX += xVals[i];
            sumY += yVals[i];
            sumXX += Math.pow(xVals[i], 2);
            sumXY += xVals[i] * yVals[i];
        }

        // Calculating the slope and intercept of the linear model
        int n = xVals.length;
        double m = roundToTwo((n * sumXY - sumX * sumY) / (n * sumXX - sumX * sumX));
        double b = roundToTwo(sumY / n - sumX * m / n);

        // Drawing the model based on xMin and xMax
        double[] impoVals = importantValues(dataset);
        double xMin = impoVals[0];
        double xMax = impoVals[1];
        StdDraw.setPenColor(Color.red);
        StdDraw.line(xMin, linearModel(xMin, m, b), xMax, linearModel(xMax, m, b));

        // Creating the string that represents linear equation
        String equation = "y = ";
        if (b > 0) {
            equation += m + "x + " + b;
        }
        else {
            equation += m + "x - " + Math.abs(b);
        }

        // Writing the equation onto the graph
        double[] edges = findEdges(dataset);
        double leftEdge = edges[0];
        double rightEdge = edges[1];
        double bottomEdge = edges[2];
        double scale = impoVals[7] - impoVals[6];
        if (xMin < 0) {
            StdDraw.text(leftEdge - scale * 0.01, bottomEdge,
                         equation);
        }
        else if (xMax >= 0) {
            StdDraw.text(rightEdge + scale * 0.01, bottomEdge - scale * 0.04,
                         equation);
        }
    }

    public static void plot(String[][] dataset, String title) {
        plotData(dataset, title);
        printStats(dataset);
        regressionLine(dataset);
    }

    // Creating tester plots
    public static void main(String[] args) {
        String[][] generalLinear = CSVReader.parseCSV("data/General_Linear.csv");
        plot(generalLinear, "data/General_Linear.csv");
        StdDraw.pause(3000);

        String[][] njBoosters = CSVReader.parseCSV("data/New_Jersey_COVID_Boosters.csv");
        plot(njBoosters, "data/New_Jersey_COVID_Boosters.csv");
        StdDraw.pause(3000);

        String[][] caBoosters = CSVReader.parseCSV("data/California_COVID_Boosters.csv");
        plot(caBoosters, "data/California_COVID_Boosters.csv");
    }
}
