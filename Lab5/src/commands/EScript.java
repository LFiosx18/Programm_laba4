package commands;

import collection.CollectionMng;
import reader.Scan;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class EScript {
    private CollectionMng collectionMng;
    Scan scan = new Scan();
    public EScript(CollectionMng collectionMng) {
        this.collectionMng = collectionMng;
    }

    public void script(String fileName) {
        while (fileName=="") {
            scan.printErr("Не указано имя файла скрипта, введите имя файла:");
            fileName = scan.readLine();
        }
        try {
            File script = new File(fileName);
            if (!script.canRead() || !script.isFile() || script.isDirectory()) throw new IOException();
            FileInputStream fileInputStream = new FileInputStream(fileName);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            Scanner scanner = new Scanner(inputStreamReader);
            Commander commander = new Commander(collectionMng);
            while (scanner.hasNext()) {
                commander.define(scanner.nextLine());
            }
        } catch (IOException e) {
            scan.printErr("Файл скрипта е найден, либо недостаточно прав доступа :(");
        }
    }
}
