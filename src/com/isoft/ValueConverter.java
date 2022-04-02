package com.isoft;

public class ValueConverter {
    public static Double toDouble(String s, boolean hasDecimals) {
        if(hasDecimals) {
            String[] q = s.split("\\.");
            char[] c = q[0].toCharArray();
            String str = "";
            for (char l : c) {
                try {
                    Integer.parseInt(String.valueOf(l));
                    str = str + l;

                } catch (Exception ignored) {
                }
            }

            return Double.parseDouble(str+"."+q[1]);
        }
        else{
            char[] c = s.toCharArray();
            String str = "";
            for (char l : c) {
                try {
                    Integer.parseInt(String.valueOf(l));
                    str = str + l;
                } catch (Exception ignored) {}
            }
            return Double.parseDouble(str);
        }

    }
    public static String addFollowingZero(String s){
        //System.out.println(s);

        try {
            String[] q = s.split("\\.");
            if (q[1].length() != 2) {
                return q[0] + "." + q[1] + "0";
            } else {
                return q[0] + "." + q[1];
            }
        }
        catch (Exception e){
            return s + ".00";

        }
    }
    public static double round(double num, double multipleOf) {
        return Math.floor((num +  multipleOf / 2) / multipleOf) * multipleOf;
    }
}