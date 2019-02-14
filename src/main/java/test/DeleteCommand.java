package test;

class DeleteCommand implements ICommand {
    private String[] args;
    private Database db;
    private String value;

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
        value = db.delete(args[1]);
        db.addCommand(this);
    }

    @Override
    public void undo() {
        if (value != null) {
            db.set(args[1], value);
        }
    }

}