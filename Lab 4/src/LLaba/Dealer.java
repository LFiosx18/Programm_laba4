package LLaba;

public class Dealer extends Data {
    public Dealer() {
        super("Торговцы", 50);
    }
    public void come() {
        System.out.println("Появились " + getName());
    }
    public  void saller() {
        System.out.println(getName() + " покупали и продавали " + Product.Stock + " в больших количествах");
    }
    public void have() {
        System.out.println(getName() + " получали на этом большие деньги");
    }
    public void went() {
        System.out.println(getName() + " уже не ходили на рынок");
    }
    public void naim(Data gorl) {
        System.out.println(getName() + " нанимали специальных " + gorl.getName() + "ов");
    }

    public String mood() {
        return("Нам не особо важно его настроение");
    }
}
