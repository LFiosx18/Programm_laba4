package client;

import clientLib.command.Command;
import clientLib.command.EScript;
import serialPack.Request;
import serialPack.Response;
import serialPack.user.User;
import user.AuthReg;

import java.io.IOException;
import java.io.StreamCorruptedException;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static final String GREEN = "\u001B[35m";
    public static final String RESET = "\u001B[0m";
    private Command command;
    private Serialize serialize;
    private AuthReg authReg;
    private User user;

    public Client() {
        this.command = new Command();
        this.serialize = new Serialize();
        this.user = new User();
        this.authReg = new AuthReg(this.user);
    }


    public void run() throws IOException  {
        DatagramSocket socket = new DatagramSocket();
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
            while (true) {
                Response response = new Response();
                while (!response.isAuthorized()) {
                    authReg.userAuthReg();
                    Request authReq = new Request(user);
                    buffer = serialize.requestSerial(authReq);
                    DatagramPacket packetToServer = new DatagramPacket(buffer, buffer.length, address, servicePort);
                    socket.send(packetToServer);
                    response = readAuthoriz(socket);
                }
                try {
                    while (true) {
                        String mes = write();
                        user.setAuthorization(true);

                        if (mes.equals("out")) {
                            System.out.println("Выход из рабочего профиля..");
                            break;
                        }

                        if (mes.equals("execute_script")) {
                            EScript script = new EScript(socket, user);
                            script.start();
                        }
                        else {
                            Request object = command.toObject(mes);
                            if (object==null) {
                                System.err.println("Команда не распознана :(");
                                continue;
                            }
                            object.setUser(user);
                            System.out.println(object.getUser().getUsername());
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
    }

    public void request1(String mes, InetAddress address, int servicePort, DatagramSocket socket) throws IOException {
        long time = System.currentTimeMillis();
        do {
            byte[] buf = mes.getBytes();
            DatagramPacket packetToServer = new DatagramPacket(buf, buf.length, address, servicePort);
            socket.send(packetToServer);
            if (System.currentTimeMillis() - time > 10000) {
                System.out.println("Превышен лимит времени ожидания, завершение программы..");
                System.exit(0);
            }

        }
        while (!readFromServer(socket));
    }

    public void sendToServer() {

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

    public Response readAuthoriz (DatagramSocket socket) {
        try {
            byte[] buff = new byte[65535];
            DatagramPacket packetFromServer = new DatagramPacket(buff, buff.length);
            socket.setSoTimeout(5000);
            socket.receive(packetFromServer);
            Response response = serialize.desirial(packetFromServer.getData(), packetFromServer.getLength());
            System.out.println(GREEN +"\nОтвет сервера:\n" +RESET+ response.getBody());
            return response;
        }
        catch (SocketTimeoutException | SocketException e) {
            System.out.println("Сервер не отвечает, попробуйте позже");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
