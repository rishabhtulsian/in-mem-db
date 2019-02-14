package test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

class Database {

    private Map<String, String> db;
    private Map<String, Integer> valueCount;
    private List<Map<String, String>> transactions;

    public Database() {
        db = new HashMap<>();
        valueCount = new HashMap<>();
        transactions = new ArrayList<>();
    }

    public String set(String key, String value) {
        String oldValue = db.get(key);
        db.put(key, value);

        if (transactions.size() > 0) {
            Map<String, String> tdb = transactions.get(transactions.size() - 1);
            if (!tdb.containsKey(key)) {
                tdb.put(key, oldValue);
            }
        }

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

        if (transactions.size() > 0) {
            Map<String, String> tdb = transactions.get(transactions.size() - 1);
            if (!tdb.containsKey(key)) {
                tdb.put(key, value);
            }
        }

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
        transactions.add(new HashMap<>());
    }

    public void commit() {
        transactions = new ArrayList<>();
    }

    public void rollback() {
        if (transactions.size() > 0) {
            Map<String, String> tdb = transactions.get(transactions.size() - 1);
            for (Entry<String, String> entry : tdb.entrySet()) {
                if (entry.getValue() == null) {
                    this.delete(entry.getKey());
                } else {
                    this.set(entry.getKey(), entry.getValue());
                }
            }
        }
    }
}