package serverLib.reader;

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
    public void printErr(String mes) {
        System.err.println(mes);
    }
}
