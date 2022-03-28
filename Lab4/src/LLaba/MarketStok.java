package LLaba;

public class MarketStok extends Data {
    public MarketStok() {
        super("Акционный рынок", 22);
    }
    public void cout() {System.out.println(getName() + ", там продают: " + Product.Stock);}
}
