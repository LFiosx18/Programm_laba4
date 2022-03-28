package commands;

import collection.CollectionMng;
import reader.ReaderValidator;
import reader.Scan;

public class RemoveById {
    private CollectionMng collectionMng;
    Scan scan = new Scan();
    ReaderValidator valid = new ReaderValidator();
    public RemoveById(CollectionMng collectionMng) {
        this.collectionMng = collectionMng;
    }

    public void removeId(String id) {
        Long newid = valid.readId(id);
        if (collectionMng.removeById(newid))
            scan.print("Элемент удалён!");
        else
            scan.print("Элемента с таким id не существует");
    }
}
