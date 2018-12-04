package currencyExchange.com.services.userService;

import currencyExchange.com.config.ConnectionToDB;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@Service
public class UserServiceImpl implements UserService {

    public final String SCRIPT_CREATE_TABLE_USER_OPERATION = "CREATE TABLE user_operation\n" +
            "(\n" +
            "  id            SERIAL NOT NULL,\n" +
            "  user_name     VARCHAR(25),\n" +
            "  currency      VARCHAR(20),\n" +
            "  operation     VARCHAR(20),\n" +
            "  sum_operation DOUBLE PRECISION,\n" +
            "  rate_uah      DOUBLE PRECISION,\n" +
            "  sum_uah       DOUBLE PRECISION,\n" +
            "  status        VARCHAR(20),\n" +
            "  date       VARCHAR(50)\n" +
            ");";

    Connection connection = null;


    @Override
    public void createTableUserOperation() throws SQLException {
        if (connection == null) {
            connection = ConnectionToDB.myCreateConnection();
        }
        Statement statement = null;
        statement = connection.createStatement();
        statement.executeUpdate(SCRIPT_CREATE_TABLE_USER_OPERATION);
        statement.close();
    }
}
