package test;

class SetCommand implements ICommand {
    private String[] args;
    private Database db;
    private String oldValue;

    public SetCommand(String[] args, Database db) {
        this.args = args;
        this.db = db;
    }

    @Override
    public ICommand clone(String[] args) {
        return new SetCommand(args, db);
    }

    @Override
    public void execute() {
        oldValue = db.set(args[1], args[2]);
    }
}