package serverLib.commands;

import database.DataBaseMng;
import serialPack.collection.*;
import serialPack.user.User;
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
    public String define(String command, String arg, Product product, User user, DataBaseMng dataBaseMng) {
        switch (command) {
            case("help"):
                return printCommands();
            case("info"):
                return new Info(collectionMng).getInfo();
            case("show"):
                return new Show(collectionMng).outCollection();
            case("add"):
                return new Add(collectionMng, dataBaseMng).add(product, user);
            case("update"):
                return new Update(collectionMng, dataBaseMng).updateId(arg, product, user);
            case("remove_by_id"):
                return new RemoveById(collectionMng, dataBaseMng).removeId(arg, user);
            case("clear"):
                return new Clear(collectionMng, dataBaseMng).clearCollection(user);
            case ("exit"):
                return new Exit().progExit();
            case("add_if_max"):
                return new AddIfMax(collectionMng, dataBaseMng).addMax(product, user);
            case("remove_greater"):
                return new RemoveGreater(collectionMng, dataBaseMng).removeGreater(product, user);
            case("remove_lower"):
                return new RemoveLower(collectionMng, dataBaseMng).removeLower(product, user);
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
