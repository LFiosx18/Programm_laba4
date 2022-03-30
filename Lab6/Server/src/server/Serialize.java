package server;

import serialPack.Request;
import serialPack.Response;

import java.io.*;

public class Serialize {

    public byte[] responseSerial(Response response) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(out);
            objectOutputStream.writeObject(response);
            return out.toByteArray();
        }
        catch (Exception e) {
            System.err.println("Ошибка в сериализации ответа");
        }
        return null;
    }

    public Request deserial(byte[] array, int size) {
        byte[] mass = new byte[size];
        System.arraycopy(array, 0, mass, 0, size);

        ByteArrayInputStream input = new ByteArrayInputStream(mass);
        try (ObjectInputStream objectInputStream = new ObjectInputStream(input)) {
            Object obj = objectInputStream.readObject();

            return (Request) obj;
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Ошибка в десериализации запроса");
        }
        return null;
    }


}
