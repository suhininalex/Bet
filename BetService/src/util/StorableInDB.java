package util;

import java.sql.Connection;
import java.sql.ResultSet;

public interface StorableInDB extends Storable{
    void setConnectionToUse(Connection useConnection);
    Connection getConnectionToUse();
    void load(ResultSet resultSet);
}
