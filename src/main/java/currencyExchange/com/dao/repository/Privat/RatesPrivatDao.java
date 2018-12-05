package currencyExchange.com.dao.repository.Privat;

import currencyExchange.com.dao.entity.RatesPrivat;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface RatesPrivatDao {

    List<RatesPrivat> getCurrencyFromJsonPrivat() throws IOException;

    void saveCurrencyFromJsonPrivat() throws IOException, SQLException;

    List<RatesPrivat> getRatesPrivatFromDB() throws SQLException;

    RatesPrivat getJsonRateByCurrency(String currency) throws IOException;

}
