package collection;

public enum UnitOfMeasure {
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
