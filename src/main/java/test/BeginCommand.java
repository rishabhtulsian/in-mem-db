package test;

class BeginCommand implements ICommand {
    private Database db;

    public BeginCommand(Database db) {
        this.db = db;
    }

    @Override
    public ICommand clone(String[] args) {
        return new BeginCommand(db);
    }

    @Override
    public void execute() {
        db.begin();
    }
}