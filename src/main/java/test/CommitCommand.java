package test;

class CommitCommand implements ICommand {
    private Database db;

    public CommitCommand(Database db) {
        this.db = db;
    }

    @Override
    public ICommand clone(String[] args) {
        return new CommitCommand(db);
    }

    @Override
    public void execute() {
        db.commit();
    }
}