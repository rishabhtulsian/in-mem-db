package test;

import java.util.Map;
import java.util.HashMap;

class CommandFactory {
    private Map<String, ICommand> commands;
    private ICommand nullCommand;

    public CommandFactory(Database db) {
        commands = new HashMap<>();
        commands.put("set", new SetCommand(null, db));
        commands.put("get", new GetCommand(null, db));
        commands.put("delete", new DeleteCommand(null, db));
        commands.put("count", new CountCommand(null, db));
        commands.put("begin", new BeginCommand(db));
        commands.put("rollback", new RollbackCommand(db));
        commands.put("commit", new CommitCommand(db));

        nullCommand = new ICommand(){

            @Override
            public ICommand clone(String[] args) {
                return this;
            }

            @Override
            public void execute() {

            }
        };
    }
    public ICommand getCommand(String[] tokens) throws ApplicationEndedException {
        if (tokens.length == 0) {
            return nullCommand;
        }
        String command = tokens[0];
        if (command.equalsIgnoreCase("end")) {
            throw new ApplicationEndedException();
        }
        return commands.get(command.toLowerCase());
    }
}