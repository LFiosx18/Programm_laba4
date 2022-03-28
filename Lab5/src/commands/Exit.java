package commands;

import reader.Scan;

public class Exit {
    Scan scan = new Scan();

    public void progExit() {
        scan.print("Программа завершена");
        System.exit(0);
    }
}
