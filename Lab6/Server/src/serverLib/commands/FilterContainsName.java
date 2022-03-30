package serverLib.commands;

import serverLib.CollectionMng;

public class FilterContainsName {
    private CollectionMng collectionMng;
    public FilterContainsName(CollectionMng collectionMng) {
        this.collectionMng = collectionMng;
    }

    public String filter(String name) {
        String el = collectionMng.filterName(name);
        if(el.equals(""))
            return "Элементы не найдены";
        return ("Найдены следующие элементы:\n" + el);
    }
}
