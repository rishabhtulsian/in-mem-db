package test;

public final class App {

    private CommandParser parser;

    private App() {
        parser = new CommandParser();
    }

    private void run() {
        parser.run();
    }

    public static void main(String[] args) {
        App app = new App();
        app.run();
    }
}
