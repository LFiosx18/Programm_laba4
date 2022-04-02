package client;

import serialPack.Request;
import serialPack.Response;

import java.io.*;
import java.util.Arrays;

public class Serialize {

    public byte[] requestSerial(Request request) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(out);
            objectOutputStream.writeObject(request);
            return out.toByteArray();
        }
        catch (Exception e) {
            System.err.println("Ошибка в сериализации");
        }
        return null;
    }

    public Response desirial(byte[] array, int size) {
        byte[] mass = new byte[size];
        System.arraycopy(array, 0, mass, 0, size);
        Arrays.fill(array, (byte) 0);

        ByteArrayInputStream input = new ByteArrayInputStream(mass);
        try (ObjectInputStream objectInputStream = new ObjectInputStream(input)) {
            Object obj = objectInputStream.readObject();

            return (Response) obj;
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error " + Arrays.toString(e.getStackTrace()));
        }
        return null;
    }
}
