package commands;

import collection.CollectionMng;

public class Info {
    private CollectionMng collectionMng;
    public Info(CollectionMng collectionMng){
        this.collectionMng = collectionMng;
    }

    public void getInfo() {
        System.out.println("Дата инициализации: " +collectionMng.getTime());
        System.out.println("Количество элементов: " +collectionMng.getSize());
    }
}
