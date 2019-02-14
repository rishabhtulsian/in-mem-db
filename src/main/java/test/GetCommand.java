package test;

class GetCommand implements ICommand {
    private String[] args;
    private Database db;

    public GetCommand(String[] args, Database db) {
        this.args = args;
        this.db = db;
    }

    @Override
    public ICommand clone(String[] args) {
        return new GetCommand(args, db);
    }

    @Override
    public void execute() {
        System.out.println(db.get(args[1]));
    }
}