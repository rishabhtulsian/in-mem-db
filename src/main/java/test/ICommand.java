package test;

interface ICommand {
    ICommand clone(String[] args);

    void execute();

    void undo();
}