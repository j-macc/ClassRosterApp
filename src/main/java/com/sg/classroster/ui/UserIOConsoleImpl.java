package com.sg.classroster.ui;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UserIOConsoleImpl implements UserIO {

    Scanner input = new Scanner(System.in);

    private void castHandler(String type) {
        System.out.printf("that number can't be represented as %1$s!\n", type);
        input.nextLine();
    }

    @Override
    public void print(String message) {
        System.out.println(message);
    }

    @Override
    public String readString(String prompt) {
        System.out.println(prompt);
        return input.nextLine();
    }

    @Override
    public double readDouble(String prompt) {
        System.out.println(prompt);
        try {
            return Double.parseDouble(input.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Please enter a number only!");
            return readDouble(prompt);
        }
    }

    @Override
    public double readDouble(String prompt, double min, double max) {
        double x = readDouble(prompt);
        while (x < min || x > max) {
            System.out.printf("Please enter a number between %1$.0f and %2$.0f only!%n", min, max);
            x = readDouble(prompt);
        }
        return x;
    }

    @Override
    public float readFloat(String prompt) {
        try {
            return (float) readDouble(prompt);
        } catch (NumberFormatException e) {
            castHandler("float");
            return readFloat(prompt);
        }
    }

    @Override
    public float readFloat(String prompt, float min, float max) {
        try {
            return (float) readDouble(prompt, min, max);
        } catch (NumberFormatException e) {
            castHandler("float");
            return readFloat(prompt, min, max);
        }
    }

    @Override
    public int readInt(String prompt) {

        // note - rounds down if given non-integers

        try {
            return (int) readDouble(prompt);
        } catch (NumberFormatException e) {
            castHandler("int");
            return readInt(prompt);
        }

    }

    @Override
    public int readInt(String prompt, int min, int max) {
        try {
            return (int) readDouble(prompt, min, max);
        } catch (NumberFormatException e) {
            castHandler("int");
            return readInt(prompt, min, max);
        }
    }

    @Override
    public long readLong(String prompt) {
        try {
            return (long) readDouble(prompt);
        } catch (InputMismatchException e) {
            castHandler("long");
            return readLong(prompt);
        }
    }

    @Override
    public long readLong(String prompt, long min, long max) {
        try {
            return (long) readDouble(prompt, min, max);
        } catch (NumberFormatException e) {
            castHandler("long");
            return readLong(prompt, min, max);
        }
    }

}

