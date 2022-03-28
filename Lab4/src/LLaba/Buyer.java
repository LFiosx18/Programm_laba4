package LLaba;

public class Buyer extends Data{
    public Buyer() {
        super("Покупатель", 48);
    }
    public void scream() {
        System.out.println(getName() + " также кричит название " + Product.Stock + ", которую хочет купить");
    }
    public String mood() {
        return("Нам не особо важно его настроение");
    }
}
