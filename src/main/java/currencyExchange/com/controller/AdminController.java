package currencyExchange.com.controller;

import currencyExchange.com.dao.entity.RatesPrivat;
import currencyExchange.com.dao.repository.Privat.RatesPrivatDao;
import currencyExchange.com.dao.repository.user.UserOperationDao;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private static final Logger logger = Logger.getLogger(AdminController.class);
    @Autowired
    private UserOperationDao userOperationDao;
    @Autowired
    private RatesPrivatDao ratesPrivatDao;
    int idik;

    @GetMapping
    public String start(Model model) throws SQLException {
        model.addAttribute("allOperation", userOperationDao.getAllOperationFromDB());
        logger.info("Вывод всех операций");
        return "admin";
    }

    @GetMapping("/remove/{id}")
    public String editStatusOperationById(@PathVariable("id") int id, Model model) throws SQLException {
        userOperationDao.deleteUserOperation(id);
        logger.info("Изменен статус операции id=" + id + " на disabled");
        return "redirect:/admin";
    }

    @GetMapping("/edit/{id}")
    public String editOperationById(@PathVariable("id") int id, Model model) throws SQLException {
        logger.info("Переход на edit.jsp");
        idik = id;
        return "edit";
    }

    @GetMapping("/edit/operation")
    public String edit(@RequestParam("name") String name, @RequestParam("currency") String currency,
                       @RequestParam("operation") String operation, @RequestParam("sumOperation") Double sumOperation,
                       @RequestParam("rate") Double rate, @RequestParam("sumUah") Double sumUah) throws SQLException {
        try {
            userOperationDao.editOperation(idik, name, currency, operation, sumOperation, rate, sumUah);
            logger.info("Операция имеющая id=" + idik + " была изменена");
        } catch (Exception e) {
            e.printStackTrace();
            return "edit";
        }
        return "redirect:/admin";

    }
}