package com.isoft;


import java.text.DecimalFormat;
import java.util.Arrays;

public class DrawGraph {
    static double increasingDelta = 0;
    static double[] maxMin = new double[2];
    static String[][] rowsAndColumns = new String[10][100];
    static double[] graphData = new double[25];
    static int c = 0;

    public static void addValuesToTheGraph(double percentage, int column, boolean dotted) {
        double q = (percentage - maxMin[1]) * 10/increasingDelta;
        percentage = Math.round(q/10.0)*10;
        final DecimalFormat df = new DecimalFormat("0.00");
        for (int i = 0; i < 10; i++) {
            double scaleBR = maxMin[1] + (i + 1) * increasingDelta;
            String scale = df.format(scaleBR);
            scale = ValueConverter.addFollowingZero(scale);
            if(!dotted) {
                if (column != 0) {
                    if ((i + 1) * 10 > percentage) {
                        rowsAndColumns[i][column] = " ";
                    } else {
                        rowsAndColumns[i][column] = "█";
                        //▄█
                    }
                } else {
                    String spaceNumbers = "";
                    for (int j = 0; j < (String.valueOf(maxMin[0]).length() - scale.length()); j++) {
                        //System.out.println(String.valueOf(scale).length());
                        spaceNumbers = spaceNumbers + " ";
                    }
                    //System.out.println(spaceNumbers + scale);
                    if ((i + 1) * 10 > percentage) {
                        rowsAndColumns[i][column] = spaceNumbers + scale + " - |  ";
                    } else {
                        rowsAndColumns[i][column] = spaceNumbers + scale + " - | █";
                        //▄█
                    }
                }
            }
            else {
                if (column != 0) {
                    if ((i + 1) * 10 == percentage) {
                        rowsAndColumns[i][column] = "●";
                    } else {
                        rowsAndColumns[i][column] = " ";
                        //▄█
                    }
                } else {
                    String spaceNumbers = "";
                    for (int j = 0; j < (String.valueOf(maxMin[0]).length() - scale.length()); j++) {
                        //System.out.println(String.valueOf(maxMin[0]).length() - String.valueOf(scale).length());
                        spaceNumbers = spaceNumbers + " ";
                    }
                    //System.out.println(spaceNumbers + scale);
                    if ((i + 1) * 10 == percentage) {
                        rowsAndColumns[i][column] = spaceNumbers + scale + " - | ●";
                    } else {
                        rowsAndColumns[i][column] = spaceNumbers + scale + " - |  ";
                        //▄█
                    }
                }
            }
        }
    }

    public static void drawGraph(double[] array, boolean dotted) {
        String spacesBase = "";
        double[] sortedArray = new double[array.length];
        for (int i = 0; i < array.length; i++) {
            sortedArray[i] = array[i];
        }
        Arrays.sort(sortedArray);
        maxMin[0] = sortedArray[sortedArray.length - 1];
        maxMin[1] = sortedArray[0];
        for(int i = 0; i< (ValueConverter.addFollowingZero(String.valueOf(maxMin[0])).length()-ValueConverter.addFollowingZero(String.valueOf(maxMin[1])).length()); i++){
            spacesBase = spacesBase + " ";
        }

        increasingDelta = (maxMin[0] - maxMin[1]) / 10;
        //System.out.println(increasingDelta);
        for (int i = 0; i < array.length; i++) {
            addValuesToTheGraph(array[i], i, dotted);
        }
        for (int i = 9; i >= 0; i--) {
            for (int j = 0; j < array.length; j++) {
                System.out.print(rowsAndColumns[i][j] + " ");
            }
            System.out.println();
        }
        System.out.print(spacesBase + ValueConverter.addFollowingZero(String.valueOf(maxMin[1])) + " - |");
        for (int i = 0; i < array.length; i++) {
            System.out.print("--");
        }
        System.out.println();
        System.out.println();
    }

    public static double[] setGraphData(double Data) {
        if (c < 25) {
            //System.out.println(Data);
            graphData[c] = Data;
            //System.out.println(graphData[c]);
            c++;
        }
        else{
            c =0;
        }

        return graphData;
    }
}
