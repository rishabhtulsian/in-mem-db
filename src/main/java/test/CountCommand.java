package test;

class CountCommand implements ICommand {
    private String[] args;
    private Database db;

    public CountCommand(String[] args, Database db) {
        this.args = args;
        this.db = db;
    }

    @Override
    public ICommand clone(String[] args) {
        return new CountCommand(args, db);
    }

    @Override
    public void execute() {
        System.out.println(db.count(args[1]));
    }
}