package serverLib.commands;

import serverLib.CollectionMng;

public class Clear {
    private CollectionMng collectionMng;
    public Clear(CollectionMng collectionMng) {
        this.collectionMng = collectionMng;
    }

    public String clearCollection() {
        collectionMng.clear();
        return "Коллекция очищена";
    }
}
