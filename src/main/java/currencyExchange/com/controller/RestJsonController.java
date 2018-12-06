package currencyExchange.com.controller;

import currencyExchange.com.dao.entity.RatesNbu;
import currencyExchange.com.dao.entity.RatesPrivat;
import currencyExchange.com.dao.repository.Nbu.RatesNbuDao;
import currencyExchange.com.dao.repository.Privat.RatesPrivatDao;
import currencyExchange.com.services.privatService.RatesPrivatService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


@RestController
@RequestMapping("/rest")
public class RestJsonController {
    private static final Logger logger = Logger.getLogger(RestJsonController.class);

    @Autowired
    private RatesPrivatDao ratesPrivatDao;
    @Autowired
    private RatesNbuDao ratesNbuDao;
    @Autowired
    private RatesPrivatService ratesPrivatService;

    @GetMapping("/get/json/privat")//Для вывода всех курсов Привата JSON
    public List<RatesPrivat> getAllRatesPrivatJson() throws IOException, SQLException {
       logger.info("Вывод Json Privata");
        return ratesPrivatDao.getRatesPrivatFromDB();
    }

    @GetMapping("/get/json/nbu")//Для вывода всех курсов НБУ JSON
    public List<RatesNbu> getAllRatesNbuJson() throws IOException, SQLException {
       logger.info("Вывод Json NBU");
        return ratesNbuDao.getRatesNbuFromDB();
    }

    @GetMapping("/get/json/privat/{currency}")//Для вывода курса одной валюты Привата
    public RatesPrivat getOneRatePrivatJson(@PathVariable String currency) throws IOException {
       logger.info("Вывод курса "+currency+" Json Privat");
        return ratesPrivatDao.getJsonRateByCurrency(currency);
    }

    @GetMapping("/get/json/nbu/{currency}")//Для вывода курса одной валюты НБУ
    public RatesPrivat getOneRateNbuJson(@PathVariable String currency) throws IOException {
        logger.info("Вывод курса "+currency+" Json NBU");
        return ratesPrivatDao.getJsonRateByCurrency(currency);
    }

    @PostMapping("/edit/rates/privat")// Для обновления курсов Привата в базе данных
    public String editRatesPrivat(@RequestBody String newRates) throws IOException, SQLException {
        ratesPrivatService.editRatesPrivatInDB(newRates);
        logger.info("Внесены изменения в курс Привата");
        return "ok";
    }
}