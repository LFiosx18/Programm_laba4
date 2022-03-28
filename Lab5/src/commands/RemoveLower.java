package commands;

import collection.CollectionMng;
import collection.Product;
import reader.Scan;

public class RemoveLower {
    private CollectionMng collectionMng;
    Scan scan = new Scan();
    public RemoveLower(CollectionMng collectionMng) {
        this.collectionMng = collectionMng;
    }

    public void removeLower() {
        scan.print("Задайте элемент, с которым хотите сравнивать элементы коллекции");
        Product element = new Add(collectionMng).creationProduct();
        Product maxProduct = collectionMng.findMin();
        int count = 0;
        while (element.compareTo(maxProduct)>0) {
            collectionMng.removeById(maxProduct.getId());
            maxProduct = collectionMng.findMin();
            count++;
        }
        System.out.println("Из коллекции удалено " + count + " элементов!");
    }
}
