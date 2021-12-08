package LLaba;

public enum Product {
    Apples("яблоки"),
    Tomatoes("помидоры"),
    Potato("картофель"),
    Cabbage("капуста"),
    Stock("акции"),
    Stocks("акций"),
    Fruit("фруктов"),
    Vegetable("овощей");
    private String title;
    Product(String title) {
        this.title=title;
    }
    @Override
    public String toString() {
        return this.title;
    }
}

