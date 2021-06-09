package com.example.simplecalculator;

import java.util.Arrays;

public class Calculator {
    private final static int MAXLEN = 300;
    private final static int MAXCHARS = 14;
    private final static int MAXCHARSland = 30;
    char [] line = new char[MAXLEN];
    int cursor = 0;

    public Calculator() {
        cursor = 0;
        setValue(0);
    }

    public String getString(boolean isLand) {
        return isLand
                ?
                String.valueOf(Arrays.copyOfRange(line, cursor - MAXCHARSland < 0 ? 0 : cursor - MAXCHARSland, cursor))
                :
                String.valueOf(Arrays.copyOfRange(line, cursor - MAXCHARS < 0 ? 0 : cursor - MAXCHARS, cursor));
    }

    public void setValue(double value) {
        line = new char[MAXLEN];

        String str;

        if (value % 1 == 0) {
            str = String.valueOf((int) value);
        } else {
            str = String.valueOf(value);
        }

        for (int i = 0; i < str.length(); i++) {
            line[i] = str.charAt(i);
        }

        cursor = str.length();
    }

    public double getResult() throws Exception {
        return calculate(line);
    }

    private static int findOp(String line, Operator [] operator) {
        int index = line.lastIndexOf("+");
        if (index >= 0) {
            operator[0] = Operator.SUM;
            return index;
        }

        index = line.lastIndexOf("-");
        if (index > 0 && !"*/+=".contains(String.valueOf(line.charAt(index - 1)))) {
            operator[0] = Operator.SUB;
            return index;
        }

        index = line.lastIndexOf("*");
        if (index >= 0) {
            operator[0] = Operator.MULT;
            return index;
        }

        index = line.lastIndexOf("/");
        if (index >= 0) {
            operator[0] = Operator.DIV;
            return index;
        }

        index = line.lastIndexOf("^");
        if (index >= 0) {
            operator[0] = Operator.EXP;
            return index;
        }

        index = line.lastIndexOf("-");
        if (index >= 0) {
            operator[0] = Operator.INVERSE;
            return index;
        }

        index = line.lastIndexOf("s");
        if (index >= 0) {
            operator[0] = Operator.SIN;
            return index;
        }

        index = line.lastIndexOf("c");
        if (index >= 0) {
            operator[0] = Operator.COS;
            return index;
        }

        index = line.lastIndexOf("t");
        if (index >= 0) {
            operator[0] = Operator.TG;
            return index;
        }

        index = line.lastIndexOf("g");
        if (index >= 0) {
            operator[0] = Operator.CTG;
            return index;
        }

        index = line.lastIndexOf("r");
        if (index >= 0) {
            operator[0] = Operator.DTR;
            return index;
        }

        index = line.lastIndexOf("d");
        if (index >= 0) {
            operator[0] = Operator.RTD;
            return index;
        }

        return -1;
    }

    private static void trigOk(int index, char[] line) throws Exception {
        if (index != 0 && !"/*-+^".contains(String.valueOf(line[index - 1])))
            throw new Exception("numtrignum");
    }

    private static double calculate(char [] line) throws Exception {
        Operator[] operator = {Operator.EMPTY};
        int index = findOp(String.valueOf(line), operator);

        if (operator[0] == Operator.EMPTY) {
            return line.length == 0 ? 0 : Double.parseDouble(String.valueOf(line).replace(',', '.'));
        }

        double left = calculate(Arrays.copyOf(line, index));
        double right = calculate(Arrays.copyOfRange(line, index + 1, MAXLEN));

        switch (operator[0]) {
            case SUM:
                return left + right;
            case SUB:
                return left - right;
            case MULT:
                return left * right;
            case DIV:
                return left / right;
            case INVERSE:
                return -right;
            case SIN:
                trigOk(index, line);
                return Math.sin(right);
            case COS:
                trigOk(index, line);
                return Math.cos(right);
            case TG:
                trigOk(index, line);
                return Math.tan(right);
            case CTG:
                trigOk(index, line);
                return 1 / Math.tan(right);
            case EXP:
                return Math.pow(left, right);
            case RTD:
                trigOk(index, line);
                return Math.toDegrees(right);
            case DTR:
                trigOk(index, line);
                return Math.toRadians(right);
        }

        return 0;
    }

    public void add(char value) {
        if ("1234567890-sctgrd".contains(String.valueOf(value))) {
            int lastSym = -1;
            for (int i = MAXLEN - 1; i >= 0; i--) {
                if ("/*-+^,".contains(String.valueOf(line[i]))) {
                    lastSym = i;
                    break;
                }
            }

            if (line[lastSym + 1] == '0') {
                cursor = lastSym + 1;
            }
        }


        if (cursor < MAXLEN) {
            line[cursor] = value;
            cursor += 1;
        }
    }

    public void del() {
        if (cursor == 1) {
            cursor = 0;
            setValue(0);
        } else {
            line = Arrays.copyOf(Arrays.copyOf(line, cursor - 1), MAXLEN);
            cursor--;
        }
    }
}
