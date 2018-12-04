package currencyExchange.com.dao.repository.Nbu;

import currencyExchange.com.dao.entity.RatesNbu;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface RatesNbuDao {

    void saveCurrencyFromJsonNbu() throws SQLException, IOException;

    List<RatesNbu>getRatesNbuFromDB() throws SQLException;

}
