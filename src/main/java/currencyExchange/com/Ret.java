package currencyExchange.com;

import currencyExchange.com.dao.repository.Nbu.RatesNbuDaoImpl;

import java.io.IOException;
import java.sql.SQLException;

public class Ret {
    public static void main(String[] args) throws IOException, SQLException {
//        RatesPrivatDaoImpl currencyDao=new RatesPrivatDaoImpl();
//        currencyDao.getCurrencyFromJsonPrivat();
        RatesNbuDaoImpl ratesNbuDao=new RatesNbuDaoImpl();
//        ratesNbuDao.saveCurrencyFromJsonPrivat();

    }
}
