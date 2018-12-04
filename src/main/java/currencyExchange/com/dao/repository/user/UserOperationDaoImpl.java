package currencyExchange.com.dao.repository.user;

import currencyExchange.com.config.ConnectionToDB;
import currencyExchange.com.dao.entity.RatesPrivat;
import currencyExchange.com.dao.repository.Privat.RatesPrivatDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@Repository
public class UserOperationDaoImpl implements UserOperationDao {
    @Autowired
    private RatesPrivatDao ratesPrivatDao;
    Connection connection = null;



    @Override
    public void saveOperationInDB(String operation, double sum, String nameUser, String currency) throws SQLException {
        PreparedStatement statement;
        Date date = new Date();
        double rate = 0;
        double roundOff = 0;
        String dataOperation=date.toString();
        System.out.println(dataOperation);
        List<RatesPrivat> ratesPrivatList = ratesPrivatDao.getRatesPrivatFromDB();
        for (RatesPrivat ratesPrivat : ratesPrivatList) {
            if (ratesPrivat.getNameCurrency().equals(currency)) {

                if (operation.equals("sale")) {
                    roundOff = sum * ratesPrivat.getBuy();
                    rate = ratesPrivat.getBuy();
                }
                if (operation.equals("buy")) {
                    roundOff = sum * ratesPrivat.getSale();
                    rate = ratesPrivat.getSale();
                }
            }
        }
        double outputSum = (double) Math.round(roundOff * 100.0) / 100.0;
        String status = "active";
        if (connection == null) {
            connection = ConnectionToDB.myCreateConnection();
        }
        statement = connection.prepareStatement("INSERT INTO user_operation" +
                "(user_name, currency, operation, sum_operation,rate_UAH, sum_UAH," +
                " status,date) VALUES (?,?,?,?,?,?,?,?)");
        statement.setString(1, nameUser);
        statement.setString(2, currency);
        statement.setString(3, operation);
        statement.setDouble(4, sum);
        statement.setDouble(5, rate);
        statement.setDouble(6, outputSum);
        statement.setString(7, status);
        statement.setString(8,dataOperation);
        statement.addBatch();
        statement.executeUpdate();
        statement.close();
    }
}
