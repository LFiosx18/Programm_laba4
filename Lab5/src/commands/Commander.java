package commands;

import collection.*;
import reader.Scan;

import static commands.PrintCommands.printCommands;

/**
 * Класс обработки введённых комманд
 */
public class Commander {
    private CollectionMng collectionMng;
    Scan scan = new Scan();

    public Commander(CollectionMng collectionMng){
        this.collectionMng = collectionMng;
    }

    /**
     * Метод для инициализации комманд и их передачи в соответственные классы
     * @param command - введённая комманда
     */
    public void define(String command) {
        switch (getWord(command, 0)) {
            case("help"):
                printCommands();
                break;
            case("info"):
                new Info(collectionMng).getInfo();
                break;
            case("show"):
                new Show(collectionMng).outCollection();
                break;
            case("add"):
                new Add(collectionMng).add();
                break;
            case("update"):
                new Update(collectionMng).updateId(getWord(command, 1));
                break;
            case("remove_by_id"):
                new RemoveById(collectionMng).removeId(getWord(command, 1));
                break;
            case("clear"):
                new Clear(collectionMng).clearCollection();
                break;
            case("save"):
                Save save = new Save(collectionMng);
                save.save();
                break;
            case("execute_script"):
                new EScript(collectionMng).script(getWord(command, 1));
                break;
            case ("exit"):
                new Exit().progExit();
                break;
            case("add_if_max"):
                new AddIfMax(collectionMng).addMax();
                break;
            case("remove_greater"):
                new RemoveGreater(collectionMng).removeGreater();
                break;
            case("remove_lower"):
                new RemoveLower(collectionMng).removeLower();
                break;
            case("average_of_price"):
                new AverageOfPrice(collectionMng).averagePrice();
                break;
            case("filter_contains_name"):
                new FilterContainsName(collectionMng).filter(getWord(command, 1));
                break;
            case("print_unique_manufacture_cost"):
                new UniqueCost(collectionMng).uniCost();
                break;
            default:
                scan.printErr("Команда не распознана, попробуйте ещё раз");
        }
    }

    /**
     * Метод для проверки наличия требуемых аргументов в определённых коммандах
     * @param command - введённая команда (строка)
     * @param n - порядковый номер элемента, который необходимо извлеч из строки (массива слов)
     */
    public String getWord(String command, int n) {
        try {
            String[] words = command.trim().split("\\s+");
            return words[n];
        }
        catch (Exception e) {
            return "";
        }
    }
}
