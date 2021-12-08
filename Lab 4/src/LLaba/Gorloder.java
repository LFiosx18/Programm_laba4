package LLaba;

public class Gorloder extends Data {
    public Gorloder() {
        super("Горлодёрик", 10);
    }
    public void action(Data mas) {
        System.out.println(getName() + "и работали на нескольких " + mas.getName());
    }
    public void buy(Data mas) {
        System.out.println("Для одного " + mas.getName() + "а они покупали одни " + Product.Stock);
        System.out.println("Для другого - другие");
        System.out.println("А для третьего " + mas.getName() + "а " + getName() + "и вообще не покупали " + Product.Stock + ", а наоборот - продавали");
    }

    public String mood() {
        return("Нам не особо важно его настроение");
    }
}
