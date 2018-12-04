package currencyExchange.com.dao.repository.Privat;

import currencyExchange.com.dao.entity.RatesPrivat;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface RatesPrivatDao {

    void saveCurrencyFromJsonPrivat() throws IOException, SQLException;
    List<RatesPrivat> getRatesPrivatFromDB() throws SQLException;

}
