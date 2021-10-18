package LLaba;

public class Lunatic extends Data{
    public Lunatic() {
        super("Лунатики", 20);
    }
    public void beg()
    {
        System.out.println("Появились " + getName());
    }
    public void done() {
        System.out.println(getName() + " покупают " + Product.Stock + " не только для себя");
        System.out.println(" но и для перепродажи по более высокой цене");
    }
}
