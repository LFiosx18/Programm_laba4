package clientLib.reader;

import java.util.Scanner;

/**
 * Класс для считывания пользовательского ввода,
 * вывода сообщений и ошибок
 */
public class Scan {
    Scanner scanner;

    public Scan() {
        this.scanner = new Scanner(System.in);
    }

    public String readLine() {
        return scanner.nextLine();
    }
    public void printErr(String mes) {
        System.err.println(mes);
    }
    public void print(String mes) {
        System.out.println(mes);
    }
}
