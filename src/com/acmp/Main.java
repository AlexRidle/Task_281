package com.acmp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {

    private static byte coinFlipCount;
    private static byte tailCountToWin;
    private static long result;

    public static void main(String[] args) {
        try {
            readFile();
            calculateResult();
            writeFile();
        } catch (IOException | TaskException e) {
            e.printStackTrace();
        }
    }

    private static void readFile() throws IOException, TaskException {
        File file = new File("INPUT.txt");
        Scanner inputFile = new Scanner(file);
        coinFlipCount = inputFile.nextByte();
        tailCountToWin = inputFile.nextByte();
        if (!(1<coinFlipCount || coinFlipCount <= 20 || 0 <= tailCountToWin || tailCountToWin <= coinFlipCount)) {
            throw new TaskException("Please, check INPUT.txt file. 1 < N <= 20 and 0 <= M <= N");
        }
    }

    private static void calculateResult(){
        result = 0;
        for (byte i = tailCountToWin; i <= coinFlipCount; i++) {
            result += (getFactorial(coinFlipCount)) / (getFactorial(coinFlipCount - i) * getFactorial(i));
        }
    }

    private static long getFactorial(int a) {
        long res = 1;
        for (int i = 1; i <= a; i++) {
            res *= i;
        }
        return res;
    }

    private static void writeFile() {
        File file = new File("OUTPUT.txt");
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(file);
            writer.println(result);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            writer.flush();
            writer.close();
        }
    }
}

class TaskException extends Exception {

    public TaskException(String message) {
        super(message);
    }

}