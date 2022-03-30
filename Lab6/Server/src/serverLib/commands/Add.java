package serverLib.commands;

import serialPack.collection.*;
import serverLib.CollectionMng;

public class Add {
    private CollectionMng collectionMng;

    public Add(CollectionMng collectionMng) {
        this.collectionMng = collectionMng;
    }

    public String add(Product product) {
        product.setId(generatorId());
        product.getManufacturer().setId(Integer.parseInt(product.getId().toString()));
        collectionMng.add(product);
        return "Элемент добавлен в коллекцию";
    }

    public Long generatorId() {
        Long id = 1L;
        boolean m = true;
        while (m) {
            for (Product val: collectionMng.getProducts()) {
                if (id.equals(val.getId())) {
                    id++;
                    m=true;
                    break;
                }
                m=false;
            }
            if (!m) return id;
        }
        return id;
    }
}
