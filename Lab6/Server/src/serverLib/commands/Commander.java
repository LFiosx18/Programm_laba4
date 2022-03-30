package serverLib.commands;

import serialPack.collection.*;
import serverLib.CollectionMng;

import static serverLib.commands.PrintCommands.printCommands;

/**
 * Класс обработки введённых комманд
 */
public class Commander {
    private CollectionMng collectionMng;

    public Commander(CollectionMng collectionMng){
        this.collectionMng = collectionMng;
    }

    /**
     * Метод для инициализации комманд и их передачи в соответственные классы
     * @param command - введённая комманда
     */
    public String define(String command, String arg, Product product) {
        switch (command) {
            case("help"):
                return printCommands();
            case("info"):
                return new Info(collectionMng).getInfo();
            case("show"):
                return new Show(collectionMng).outCollection();
            case("add"):
                return new Add(collectionMng).add(product);
            case("update"):
                return new Update(collectionMng).updateId(arg, product);
            case("remove_by_id"):
                return new RemoveById(collectionMng).removeId(arg);
            case("clear"):
                return new Clear(collectionMng).clearCollection();
            case ("exit"):
                return new Exit(collectionMng).progExit();
            case("add_if_max"):
                return new AddIfMax(collectionMng).addMax(product);
            case("remove_greater"):
                return new RemoveGreater(collectionMng).removeGreater(product);
            case("remove_lower"):
                return new RemoveLower(collectionMng).removeLower(product);
            case("average_of_price"):
                return new AverageOfPrice(collectionMng).averagePrice();
            case("filter_contains_name"):
                return new FilterContainsName(collectionMng).filter(arg);
            case("print_unique_manufacture_cost"):
                return new UniqueCost(collectionMng).uniCost();
        }
        return "Команда не распознана";
    }
}
