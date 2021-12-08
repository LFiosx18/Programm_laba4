package LLaba;

public class MarketNorm extends Data implements Out{
    public MarketNorm() {
        super("Обычный рынок", 21);
    }
    public void cout() {
        System.out.println(getName()+", там продают: " + Product.Apples + ", " + Product.Tomatoes + ", " + Product.Potato + ", " + Product.Cabbage);
    }

    @Override
    public void out() {
        Out.super.out();
    }

    public String mood() {
        return("Нам не особо важно его настроение");
    }
}
