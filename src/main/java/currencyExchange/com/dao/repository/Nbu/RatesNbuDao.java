package currencyExchange.com.dao.repository.Nbu;

import currencyExchange.com.dao.entity.RatesNbu;
import currencyExchange.com.dao.entity.RatesPrivat;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface RatesNbuDao {

    List<RatesNbu> getCurrencyFromJsonNbu() throws IOException;

    void saveCurrencyFromJsonNbu() throws SQLException, IOException;

    List<RatesNbu> getRatesNbuFromDB() throws SQLException;

    RatesNbu getJsonRateByCurrency(String currency) throws IOException;


}
