package currencyExchange.com.services.privatService;

import currencyExchange.com.config.ConnectionToDB;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@Service
public class RatesPrivatServiceImpl implements RatesPrivatService {

    public final String SCRIPT_CREATE_TABLE_RATES_PRIVAT = "CREATE TABLE rates_privat\n" +
            "(\n" +
            "  id            SERIAL NOT NULL,\n" +
            "  name_currency VARCHAR(25),\n" +
            "  sale          DOUBLE PRECISION,\n" +
            "  buy           DOUBLE PRECISION\n" +
            ");";

    Connection connection = null;


    @Override
    public void createTableRatesPrivatInDB() throws SQLException {
        if (connection == null) {
            connection = ConnectionToDB.myCreateConnection();
        }
        Statement statement = null;
        statement = connection.createStatement();
        statement.executeUpdate(SCRIPT_CREATE_TABLE_RATES_PRIVAT);
        statement.close();
    }
}




