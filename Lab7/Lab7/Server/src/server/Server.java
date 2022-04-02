package server;

import database.DataBaseMng;
import serialPack.Request;
import serialPack.Response;
import serverLib.CollectionMng;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.sql.SQLException;

public class Server {
    private DataBaseMng dataBaseMng;
    private Serialize serialize;
    private Processing processing;
    private CollectionMng collectionMng;
    private static byte[] buffer = new byte[65535];


    public Server() throws SQLException {
        this.serialize = new Serialize();
        this.dataBaseMng = new DataBaseMng();
    }

    public void run() throws IOException {
        this.collectionMng = new CollectionMng();
        this.processing = new Processing(collectionMng);

        dataBaseMng.load(this.collectionMng);

        DatagramSocket datSocket = new DatagramSocket(8080);
        System.out.println("Сервер готов к работе!");


        while (true) {
            DatagramPacket inputPack = new DatagramPacket(buffer, buffer.length);
            System.out.println("Ожидание запроса..");
            datSocket.receive(inputPack);

            toClient(inputPack, datSocket, inputPack.getAddress(), inputPack.getPort());

            System.out.println("Запрос обработан, ответ отправлен!");
        }
    }

    public void toClient(DatagramPacket fromClient, DatagramSocket socket, InetAddress adress, int port) throws IOException {
        Response response = new Response();
        Request request = serialize.deserial(fromClient.getData(), fromClient.getLength());
        if (request.getUser().getRegistration()) {
            if (dataBaseMng.addUserToDataBase(request.getUser().getUsername(), request.getUser().getPassword())) {
                response.setBody("Пользователь зарегистрирован, необходима авторизация");
            }
            else {
                response.setBody("Пользователь с таким именем уже существует");
            }
        }
        else if (!request.getUser().getAuthorization()){
            String authoriz = dataBaseMng.authorize(request.getUser().getUsername(), request.getUser().getPassword());
            response.setBody(authoriz);
            if (authoriz.equals("Авторазиция прошла успешно! Для выхода из рабочего профиля введите 'out'"))
                response.setAuthorized(true);
        }
        else {
            System.out.println(request.getUser().getUsername());
            response = processing.process(request, dataBaseMng);
        }
        if (collectionMng.getSize()!=0)
            response.setBody(response.getBody() + "\nКоллекция пуста, добавьте первый элемент!");
        byte[] buffer2 = serialize.responseSerial(response);
        DatagramPacket packetToClient = new DatagramPacket(buffer2, buffer2.length, adress, port);
        socket.send(packetToClient);
        if (request.getCommand()!=null && request.getCommand().equals("exit"))
            System.exit(0);
    }
}
