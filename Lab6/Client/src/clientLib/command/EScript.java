package clientLib.command;

import client.Serialize;
import serialPack.Request;
import serialPack.Response;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.Scanner;

public class EScript {
    public static final String GREEN = "\u001B[35m";
    public static final String RESET = "\u001B[0m";
    private DatagramSocket socket;
    private Serialize serialize = new Serialize();
    Scanner scanner = new Scanner(System.in);

    public EScript(DatagramSocket socket) {
        this.socket = socket;
    }

    public void start() throws UnknownHostException {
        InetAddress address = Inet4Address.getByName("localhost");
        int servicePort = 8080;
        String fileName = fileName();
        byte[] buffer;
        try {
            File script = new File(fileName);
            if (!script.canRead() || !script.isFile() || script.isDirectory()) throw new IOException();
            FileInputStream fileInputStream = new FileInputStream(fileName);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            Scanner scan = new Scanner(inputStreamReader);
            Command command = new Command();
            Request object = new Request();
            while (scan.hasNext()) {
                String mes = scan.nextLine();
                object = command.toObject(mes);
                if (object==null) {
                    System.err.println("Команда " + mes + " не распознана :(");
                    continue;
                }
                buffer = serialize.requestSerial(object);
                DatagramPacket packetToServer = new DatagramPacket(buffer, buffer.length, address, servicePort);
                socket.send(packetToServer);
                readFromServer(socket);
                if (mes.equals("exit")) {
                    System.out.println("Программа завершает работу");
                    System.exit(0);
                }
            }
        } catch (IOException e) {
            System.out.println("Файл скрипта не найден, либо недостаточно прав доступа :(");
        }
    }

    public String fileName() {
        String infile = "";
        while (infile.equals("")){
            System.out.println("Введите имя файла скрипта:");
            infile = scanner.nextLine();
        }
        return ("C:\\Users\\Lis\\Desktop\\University\\Lab_6\\Lab6\\Client\\src\\"+infile);
    }

    public void readFromServer(DatagramSocket socket) throws IOException {
        try {
            byte[] buff = new byte[65535];
            DatagramPacket packetFromServer = new DatagramPacket(buff, buff.length);
            socket.setSoTimeout(5000);
            socket.receive(packetFromServer);
            Response response = serialize.desirial(packetFromServer.getData(), packetFromServer.getLength());
            System.out.println(GREEN +"\nОтвет сервера:\n" +RESET+ response.getBody());
        }
        catch (SocketTimeoutException e) {
            System.out.println("Сервер не отвечает, попробуйте позже");
        }
    }
}
