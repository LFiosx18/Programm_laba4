package server;

import serialPack.Request;
import serialPack.Response;
import serverLib.CollectionMng;
import serverLib.file.FileWorker;

import java.io.File;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Server {
    private Serialize serialize;
    private Processing processing;
    private CollectionMng collectionMng;
    private FileWorker fileWorker;
    private String infile;
    private static byte[] buffer = new byte[65535];


    public Server(String infile){
        this.infile = infile;
    }

    public void run() throws IOException {
        this.collectionMng = new CollectionMng();
        this.processing = new Processing(collectionMng);
        String mes;

        try {
            File file = new File(infile);
            if (!file.canWrite() || !file.isFile() || file.isDirectory()) throw new IOException();
            fileWorker = new FileWorker(collectionMng);
            mes = fileWorker.fromXmlToObject();
        } catch (IOException e) {
            mes = "Такого файла не существует, либо недостаточно прав доступа :(";
            System.exit(0);
        }

        DatagramSocket datSocket = new DatagramSocket(8080);
        System.out.println("Сервер готов к работе!");
        toClient1(datSocket, mes);

        while (true) {
            DatagramPacket inputPack = new DatagramPacket(buffer, buffer.length);
            System.out.println("Ожидание запроса..");
            datSocket.receive(inputPack);

            toClient(inputPack, datSocket, inputPack.getAddress(), inputPack.getPort());

            System.out.println("Запрос обработан, ответ отправлен!");
        }
    }

    public void toClient1(DatagramSocket datSocket, String mes) throws IOException {
        DatagramPacket inputPack = new DatagramPacket(buffer, buffer.length);
        System.out.println("Ожидание подключения..");
        datSocket.receive(inputPack);
        Response response = new Response();
        Serialize serialize = new Serialize();

        if (mes.charAt(0) != 'E') {
            if (collectionMng.getSize()==0)
                mes = "Коллекция пуста, добавьте первый элемент";
            response.setBody(mes);
            byte[] buffer2 = serialize.responseSerial(response);
            DatagramPacket packetToClient = new DatagramPacket(buffer2, buffer2.length, inputPack.getAddress(), inputPack.getPort());
            datSocket.send(packetToClient);
            return;
        }
        response.setBody(mes + "\nСервер завершает работу..");
        byte[] buffer2 = serialize.responseSerial(response);
        DatagramPacket packetToClient = new DatagramPacket(buffer2, buffer2.length, inputPack.getAddress(), inputPack.getPort());
        datSocket.send(packetToClient);
        System.exit(0);
    }

    public void toClient(DatagramPacket fromClient, DatagramSocket socket, InetAddress adress, int port) throws IOException {
        Serialize serialize = new Serialize();
        Request request = serialize.deserial(fromClient.getData(), fromClient.getLength());

        System.out.println(request);
        Response response = new Response();

        response = processing.process(request);
        byte[] buffer2 = serialize.responseSerial(response);
        DatagramPacket packetToClient = new DatagramPacket(buffer2, buffer2.length, adress, port);
        socket.send(packetToClient);
        if (request.getCommand().equals("exit"))
            System.exit(0);
    }
}
