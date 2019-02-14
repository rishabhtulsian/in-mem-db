package test;

import java.util.Scanner;

public final class CommandParser {

    public void run() {
        Database db = new Database();
        CommandFactory factory = new CommandFactory(db);
        Scanner scanner = new Scanner(System.in);

        boolean running = true;
        while(running) {
            try {
                System.out.print(">>> ");
                String[] tokens = scanner.nextLine().split(" ");
                ICommand command = factory.getCommand(tokens);
                if (command == null) {
                    System.out.println("UNKNOWN COMMAND");
                } else {
                    command = command.clone(tokens);
                    command.execute();
                }
            } catch (ApplicationEndedException e) {
                running = false;
            }
        }
        scanner.close();
    }
}
