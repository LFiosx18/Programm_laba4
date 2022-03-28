package commands;

import collection.CollectionMng;

public class Show {
    private CollectionMng collectionMng;
    public Show(CollectionMng collectionMng) {
        this.collectionMng = collectionMng;
    }
    public void outCollection() {
        collectionMng.show();
    }
}