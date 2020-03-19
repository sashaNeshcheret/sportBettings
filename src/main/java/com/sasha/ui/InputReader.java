package com.sasha.ui;

import java.util.Scanner;

public class InputReader {
    private Scanner scanner;

    public InputReader(Scanner scanner){
        this.scanner = scanner;
    }

    public String getNextLine(){
        return scanner.nextLine();
    }
}
