package test;

class RollbackCommand implements ICommand {
    private Database db;

    public RollbackCommand(Database db) {
        this.db = db;
    }

    @Override
    public ICommand clone(String[] args) {
        return new RollbackCommand(db);
    }

    @Override
    public void execute() {
        db.rollback();
    }
}