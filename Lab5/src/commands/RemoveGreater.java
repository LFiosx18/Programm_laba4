package commands;

import collection.CollectionMng;
import collection.Product;
import reader.Scan;

public class RemoveGreater {
    private CollectionMng collectionMng;
    Scan scan = new Scan();
    public RemoveGreater(CollectionMng collectionMng) {
        this.collectionMng = collectionMng;
    }

    public void removeGreater() {
        scan.print("Задайте элемент, с которым хотите сравнивать элементы коллекции");
        Product element = new Add(collectionMng).creationProduct();
        Product maxProduct = collectionMng.findMax();
        int count = 0;
        while (element.compareTo(maxProduct)<0) {
            collectionMng.removeById(maxProduct.getId());
            maxProduct = collectionMng.findMax();
            count++;
        }
        System.out.println("Из коллекции удалено " + count + " элементов!");
    }
}
