package serialPack.collection;

import java.io.Serializable;

public enum UnitOfMeasure implements Serializable {
    SQUARE_METERS("square_meters"),
    PCS("pcs"),
    MILLILITERS("milliliters"),
    GRAMS("grams");

    private String unitOfMeasure;
    UnitOfMeasure(String unitOfMeasure) { this.unitOfMeasure = unitOfMeasure; }

    public static void outMeasure() {
        for (UnitOfMeasure i:values())
            System.out.println(i);
    }
}
