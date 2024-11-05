package model;

import java.util.List;

public interface QueryDao {
    public List<String> executeQuery(String query);
    public List<String> executeStatement(String statement);
}
