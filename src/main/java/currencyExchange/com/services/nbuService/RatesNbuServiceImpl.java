package currencyExchange.com.services.nbuService;

import currencyExchange.com.config.ConnectionToDB;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@Service
public class RatesNbuServiceImpl implements RatesNbuService {

    public final String SCRIPT_CREATE_TABLE_RATES_NBU = "CREATE TABLE rates_nbu\n" +
            "(\n" +
            "  id            SERIAL NOT NULL,\n" +
            "  name_currency VARCHAR(25),\n" +
            "  rate          DOUBLE PRECISION\n" +
            ");";

    Connection connection = null;

    @Override
    public void createTableRatesNbuInDB() throws SQLException {
        if (connection == null) {
            connection = ConnectionToDB.myCreateConnection();
        }
        Statement statement = null;
        statement = connection.createStatement();
        statement.executeUpdate(SCRIPT_CREATE_TABLE_RATES_NBU);
        statement.close();
    }
}
