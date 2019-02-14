package test;

class DeleteCommand implements ICommand {
    private String[] args;
    private Database db;

    public DeleteCommand(String[] args, Database db) {
        this.args = args;
        this.db = db;
    }

    @Override
    public ICommand clone(String[] args) {
        return new DeleteCommand(args, db);
    }

    @Override
    public void execute() {
        db.delete(args[1]);
    }
}