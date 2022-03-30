package client;

import clientLib.command.Command;
import clientLib.command.EScript;
import serialPack.Request;
import serialPack.Response;

import java.io.IOException;
import java.io.StreamCorruptedException;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static final String GREEN = "\u001B[35m";
    public static final String RESET = "\u001B[0m";
    private Serialize serialize;

    public Client() {
        this.serialize = new Serialize();
    }


    public void run() throws IOException  {
        Scanner scanner = new Scanner(System.in);
        InetAddress address = Inet4Address.getByName("localhost");
        int servicePort = 8080;
        byte[] buffer;

        System.out.println("Подключиться к серверу? (yes)");
        String mes1 = scanner.nextLine();
        if (!mes1.equals("yes")) {
            System.out.println("Программа завершает работу..");
            System.exit(0);
        }
        else {
            request1(mes1, address, servicePort);
            try {
                while (true) {
                    DatagramSocket socket = new DatagramSocket();
                    Command command = new Command();
                    Serialize serialize = new Serialize();
                    String mes = write();

                    if (mes.equals("execute_script")) {
                            EScript script = new EScript(socket);
                            script.start();
                    }
                    else {
                        Request object = command.toObject(mes);
                        if (object==null) {
                            System.err.println("Команда не распознана :(");
                            continue;
                        }
                        buffer = serialize.requestSerial(object);
                        if (buffer!=null) {
                            DatagramPacket packetToServer = new DatagramPacket(buffer, buffer.length, address, servicePort);
                            socket.send(packetToServer);
                            readFromServer(socket);
                            if (mes.equals("exit")) {
                                System.out.println("Программа завершает работу..");
                                socket.close();
                                System.exit(0);
                            }
                        }
                    }
                }
            } catch (SocketException e) {
                System.err.println("Нет подключения по данному порту!");
            } catch (IllegalArgumentException e2) {
                System.err.println("Нет соединения с сервером!");
            } catch (StreamCorruptedException e3) {
                System.err.println("Нет соединения с сервером!");
            }
        }
    }

    public void request1(String mes, InetAddress address, int servicePort) throws IOException {
        DatagramSocket socket;
        long time = System.currentTimeMillis();
        do {
            byte[] buf = mes.getBytes();
            socket = new DatagramSocket();
            DatagramPacket packetToServer = new DatagramPacket(buf, buf.length, address, servicePort);
            socket.send(packetToServer);
            if (System.currentTimeMillis() - time > 10000) {
                System.out.println("Превышен лимит времени ожидания, завершение программы..");
                System.exit(0);
            }

        }
        while (!readFromServer(socket));
    }

    public static String write() throws IOException {
        Scanner scanner = new Scanner(System.in);
        String command = "";
        while (command.equals("")) {
            System.out.print("\nВведите команду (для справки 'help'):\n");
            command = scanner.nextLine().trim();
            if (command.equals("")) {
                System.out.println("Вы ничего не ввели..");
            }
        }
        return command;
    }

    public boolean readFromServer(DatagramSocket socket) throws IOException {
        try {
            byte[] buff = new byte[65535];
            DatagramPacket packetFromServer = new DatagramPacket(buff, buff.length);
            socket.setSoTimeout(5000);
            socket.receive(packetFromServer);
            Response response = serialize.desirial(packetFromServer.getData(), packetFromServer.getLength());
            System.out.println(GREEN +"\nОтвет сервера:\n" +RESET+ response.getBody());
            return true;
        }
        catch (SocketTimeoutException e) {
            System.out.println("Сервер не отвечает, попробуйте позже");
            return false;
        }
    }
}
