package currencyExchange.com.services.privatService;

import java.io.IOException;
import java.sql.SQLException;

public interface RatesPrivatService {

    void createTableRatesPrivatInDB() throws SQLException;

    void editRatesPrivatInDB(String json) throws IOException, SQLException;

}
