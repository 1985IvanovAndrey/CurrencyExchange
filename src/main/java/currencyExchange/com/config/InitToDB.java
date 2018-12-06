package currencyExchange.com.config;

import currencyExchange.com.dao.repository.Nbu.RatesNbuDao;
import currencyExchange.com.dao.repository.Privat.RatesPrivatDao;
import currencyExchange.com.services.nbuService.RatesNbuService;
import currencyExchange.com.services.privatService.RatesPrivatService;
import currencyExchange.com.services.userService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.io.*;
import java.sql.SQLException;

@Transactional
@Component
public class InitToDB {

    @Autowired
    private RatesNbuService ratesNbuService;
    @Autowired
    private RatesNbuDao ratesNbuDao;
    @Autowired
    private RatesPrivatService ratesPrivatService;
    @Autowired
    private RatesPrivatDao ratesPrivatDao;
    @Autowired
    private UserService userService;


    @PostConstruct
    private void init() throws SQLException, IOException {
        ratesNbuService.createTableRatesNbuInDB();
        ratesNbuDao.saveCurrencyFromJsonNbu();

        ratesPrivatService.createTableRatesPrivatInDB();
        ratesPrivatDao.saveCurrencyFromJsonPrivat();

        userService.createTableUserOperation();
    }
}
