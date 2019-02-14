package test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Database {

    private Map<String, String> db;
    private Map<String, Integer> valueCount;
    private List<List<ICommand>> transactions;

    public Database() {
        db = new HashMap<>();
        valueCount = new HashMap<>();
        transactions = new ArrayList<>();
    }

    public String set(String key, String value) {
        String oldValue = db.get(key);
        db.put(key, value);

        if (oldValue == value) {
            return oldValue;
        }
        int count = 1;
        if (valueCount.containsKey(value)) {
            count = valueCount.get(value) + 1;
        }
        valueCount.put(value, count);

        if (oldValue == null) {
            return oldValue;
        }
        count = valueCount.get(oldValue) - 1;
        if (count == 0) {
            valueCount.remove(oldValue);
        } else {
            valueCount.put(oldValue, count);
        }
        return oldValue;
    }

    public String get(String key) {
        if (db.containsKey(key)) {
            return db.get(key);
        } else {
            return "NULL";
        }
    }

    public String delete(String key) {
        String value = db.remove(key);

        if (value == null) {
            return null;
        }
        int count = valueCount.get(value) - 1;
        if (count == 0) {
            valueCount.remove(value);
        } else {
            valueCount.put(value, count);
        }
        return value;
    }

    public int count(String value) {
        if (valueCount.containsKey(value)) {
            return valueCount.get(value);
        }
        return 0;
    }

    public void begin() {
        transactions.add(new ArrayList<>());
    }

    public void commit() {
        transactions = new ArrayList<>();
    }

    public void rollback() {
        if (transactions.size() > 0) {
            List<ICommand> commands = transactions.get(transactions.size() - 1);
            for (int index = commands.size() - 1; index >= 0; index--) {
                commands.get(index).undo();
            }
        }
    }

    public void addCommand(ICommand command) {
        if (transactions.size() > 0) {
            transactions.get(transactions.size() - 1).add(command);
        }
    }
}