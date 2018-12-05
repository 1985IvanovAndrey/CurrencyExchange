package currencyExchange.com.controller;

import currencyExchange.com.dao.entity.RatesPrivat;
import currencyExchange.com.dao.repository.Privat.RatesPrivatDao;
import currencyExchange.com.dao.repository.user.UserOperationDao;
import currencyExchange.com.services.userService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.SQLException;


@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserOperationDao userOperationDao;
    @Autowired
    private RatesPrivatDao ratesPrivatDao;
    int idik;

    @GetMapping
    public String start(Model model) throws SQLException {
        model.addAttribute("allOperation", userOperationDao.getAllOperationFromDB());
        return "admin";
    }

    @GetMapping("/remove/{id}")
    public String editStatusOperationById(@PathVariable("id") int id, Model model) throws SQLException {
        userOperationDao.deleteUserOperation(id);
        return "redirect:/admin";
    }

    @GetMapping("/edit/{id}")
    public String editOperationById(@PathVariable("id") int id, Model model) throws SQLException {
        idik = id;
        return "edit";
    }

    @GetMapping("/edit/operation")
    public String edit(@RequestParam("name") String name, @RequestParam("currency") String currency,
                       @RequestParam("operation") String operation, @RequestParam("sumOperation") Double sumOperation,
                       @RequestParam("rate") Double rate, @RequestParam("sumUah") Double sumUah) throws SQLException {
        try {
//            if (!name.equals("") && sumOperation != null && rate != null && sumUah != null) {
            userOperationDao.editOperation(idik, name, currency, operation, sumOperation, rate, sumUah);

            //return "redirect:/admin";
        } catch (Exception e) {
            e.printStackTrace();
            return "edit";
        }
        return "redirect:/admin";
    }
}