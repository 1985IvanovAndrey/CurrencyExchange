package currencyExchange.com.controller;

import currencyExchange.com.dao.repository.Nbu.RatesNbuDao;
import currencyExchange.com.dao.repository.Privat.RatesPrivatDao;
import currencyExchange.com.dao.repository.user.UserOperationDao;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.sql.SQLException;

@Controller
@RequestMapping("/")
public class UserController {
    private static final Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    private RatesPrivatDao ratesPrivatDao;
    @Autowired
    private RatesNbuDao ratesNbuDao;
    @Autowired
    private UserOperationDao userOperationDao;


    @GetMapping
    public String getAllGroups(Model model) throws SQLException, IOException {
        model.addAttribute("ratesNbuList", ratesNbuDao.getRatesNbuFromDB());
        logger.info("Выведены курсы НБУ");
        model.addAttribute("ratesPrivatList", ratesPrivatDao.getRatesPrivatFromDB());
        logger.info("Выведены курсы Привата");
        model.addAttribute("allOperation", userOperationDao.checkStatus(userOperationDao.getAllOperationFromDB()));
        ratesNbuDao.getRatesNbuFromDB();

        return "index";
    }

    @GetMapping("operation")
    public String saveOpertionInDB(@RequestParam(value = "name") String name,
                                   @RequestParam(value = "currency") String currency,
                                   @RequestParam(value = "operation") String operation,
                                   @RequestParam(value = "sum") Double sum) throws SQLException {
        if (!name.equals("") && sum != null) {
            userOperationDao.saveOperationInDB(operation, sum, name, currency);
            logger.info("Совершена операция");
        }
        return "redirect:/";
    }
}
