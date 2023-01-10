public class DataAnalysisFunctions {
    private static double[] minMax(String[][] dataset) {
        double xMin = Double.POSITIVE_INFINITY;
        double xMax = Double.NEGATIVE_INFINITY;
        double yMin = Double.POSITIVE_INFINITY;
        double yMax = Double.NEGATIVE_INFINITY;
        int rowNum = dataset.length;
        for (int i = 1; i < rowNum; i++) {
            double xVal = Double.parseDouble(dataset[i][0]);
            double yVal = Double.parseDouble(dataset[i][1]);
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
        double[] result = { xMin, xMax, yMin, yMax };
        return result;
    }

    public static void plotData(String[][] dataset) {
        double[] extremeVals = minMax(dataset);
        double xMin = extremeVals[0];
        double xMax = extremeVals[1];
        double yMin = extremeVals[2];
        double yMax = extremeVals[3];
        double xDist = xMax - xMin;
        double yDist = yMax - yMin;
        int rowNum = dataset.length;
        String xLabel = dataset[0][0];
        String yLabel = dataset[0][1];
        for (int i = 1; i < rowNum; i++) {
            double xVal = Double.parseDouble(dataset[i][0]);
            double yVal = Double.parseDouble(dataset[i][1]);
            StdDraw.filledCircle(xVal, yVal, 0.01);
        }
        StdDraw.setXscale(xMin - 0.5 * xDist, xMax + 0.5 * xDist);
        StdDraw.setXscale(yMin - 0.5 * yDist, yMax + 0.5 * yDist);
        StdDraw.show();
    }

    public static void main(String[] args) {


    }
}
