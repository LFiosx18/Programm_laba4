package LLaba;

public class Main{

    static class BuyerStock extends Data{
        BuyerStock() {
            super("Покупатель акций", (int)Math.round(Math.random()));
        }

        public void buy() {
            System.out.println("Один " + getName() + " собрался купить " + Product.Stock);
            System.out.println("Он посчитал, сколько у него денег");
        }
        public String mood() {
            if (Math.round(Math.random())==0) {
                return("грустный");
            }
            else {
                return("весёлый");
            }
        }

        public int money = 0;
        public void notmoney() {
            money = 0;
            System.out.println("Но оказалось, что у него нет денег");
            System.out.println("Поэтому он " + mood() + " вернулся домой");
        }
        public void havemoney() {
            money = (int)Math.round(Math.random()*1000);
            System.out.println("У него оказалось " + money + " рублей");
        }
        public int getMoney() {return money;}

        public void checkbuy() throws BuyStoksException{
            if (getId()==0) {
                throw new BuyStoksException();
            }
        }
        public void check() {
            try {
                checkbuy();
                havemoney();
            } catch (Exception e) {
                notmoney();
            }
        }

        public void ask() {
            System.out.println(getName() +  " пошёл узнавать цены на " + Product.Stock);
        }
        public void done() {
            System.out.println("Отлично! " + getName() + " купил нужные акции и пошёл домой.");
        }
        public void notdone() {
            System.out.println(getName() + " подумал, что это слишком дорого и пошёл искать другие предложения");
        }
    }

    public static void main(String[] args) {
        MarketNorm marketn = new MarketNorm();
        MarketStok markets = new MarketStok();
        Buyer buyer = new Buyer();
        Salesman salesmanF = new Salesman("F");
        Salesman salesmanV = new Salesman("V");
        Salesman salesmanS = new Salesman("S");
        Lunatic lunatic = new Lunatic();
        Dealer dealer = new Dealer();
        Gorloder gorloder = new Gorloder();
        Master master = new Master();
        BuyerStock buyerStock = new BuyerStock();

        marketn.out();
        marketn.cout();
        markets.cout();
        System.out.println(salesmanF.getWork() + " и " + salesmanV.getWork());
        Salesman.sale();
        salesmanS.stock();
        buyer.scream();
        lunatic.beg();
        lunatic.done();
        dealer.come();
        dealer.saller();
        dealer.have();
        dealer.went();
        dealer.naim(gorloder);
        gorloder.action(master);
        gorloder.buy(master);
        buyerStock.buy();
        buyerStock.check();

        if (buyerStock.getMoney()!=0) {
            buyerStock.ask();
            salesmanS.screamone();
            while (buyerStock.getMoney() < salesmanS.getCost()) {
                buyerStock.notdone();
                salesmanS.screamchange();
            }
            buyerStock.done();
        }

    }
}
