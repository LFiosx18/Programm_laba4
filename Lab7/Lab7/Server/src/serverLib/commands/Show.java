package serverLib.commands;

import serverLib.CollectionMng;

public class Show {
    private CollectionMng collectionMng;
    public Show(CollectionMng collectionMng) {
        this.collectionMng = collectionMng;
    }

    public String outCollection() {
        return collectionMng.show();
    }
}