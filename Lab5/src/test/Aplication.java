package test;

import collection.CollectionMng;
import commands.Commander;
import file.FileWorker;
import reader.Scan;

import java.io.File;
import java.io.IOException;
import java.util.Locale;

/**
 * Основной класс чтения комманд
 */
public class Aplication {
    CollectionMng collectionMng;
    Scan scan;
    Commander commander;
    FileWorker fileWorker;

    /**
     * Проверка на наличие файла, из которого должна загружаться коллекция
     * @param inputFile - имя проверяемого файла
     */
    public void start(String inputFile) {
        collectionMng = new CollectionMng();
        scan = new Scan();
        commander = new Commander(collectionMng);
        fileWorker = new FileWorker(collectionMng);
        try{
            File file = new File(inputFile);
            if (!file.canWrite() || !file.isFile() || file.isDirectory()) throw new IOException();
            fileWorker.fromXmlToObject();
        } catch (IOException e) {
            scan.printErr("Такого файла не существует, либо недостаточно прав доступа :(");
            System.exit(0);
        }
        process();
    }

    /**
     * Начало работы "полезной" программы
     * Чтение комманд и передача их обработки классу "Commander"
     */
    public void process() {
        scan.print("Программа запущена, для справки введите 'help'");
        if (collectionMng.getSize()==0) {
            scan.print("Коллекция пуста, добавьте элемент коллекции 'add'");
        } else
            scan.print("Коллекция загружена!");
        while (true) {
            scan.print("\nВедите команду:");
            String command = scan.readLine().toLowerCase(Locale.ROOT);
            commander.define(command);
        }
    }
}
