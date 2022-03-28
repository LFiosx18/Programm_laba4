package commands;

import collection.CollectionMng;
import reader.Scan;

public class Clear {
    private CollectionMng collectionMng;
    Scan scan = new Scan();
    public Clear(CollectionMng collectionMng) {
        this.collectionMng = collectionMng;
    }

    public void clearCollection() {
        collectionMng.clear();
        scan.print("Коллекция очищена");
    }
}
