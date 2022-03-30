package serverLib.commands;

import serverLib.CollectionMng;

public class Info {
    private CollectionMng collectionMng;
    public Info(CollectionMng collectionMng){
        this.collectionMng = collectionMng;
    }

    public String getInfo() {
        return ("Дата инициализации: " + collectionMng.getTime() + "\n" +
                "Количество элементов: " + collectionMng.getSize());
    }
}
