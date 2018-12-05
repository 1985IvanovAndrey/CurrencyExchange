package currencyExchange.com.dao.repository.user;

import currencyExchange.com.config.ConnectionToDB;
import currencyExchange.com.dao.entity.RatesPrivat;
import currencyExchange.com.dao.entity.UserOperation;
import currencyExchange.com.dao.repository.Privat.RatesPrivatDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Repository
public class UserOperationDaoImpl implements UserOperationDao {
    @Autowired
    private RatesPrivatDao ratesPrivatDao;
    Connection connection = null;

    private final String SQL_FIND_USER_OPERATION = "select * from user_operation where id = ?";
    private final String SQL_UPDATE_USER_OPERATION = "UPDATE user_operation SET user_name=?,currency=?,operation=?,sum_operation=?,rate_uah=?,sum_uah=? WHERE id = ?";
    private final String SQL_UPDATE_STATUS_USER_OPERATION = "UPDATE user_operation SET status=? WHERE id = ?";

    private final String SQL_INSERT_USER_OPERATION = "INSERT INTO user_operation\" +\n" +
            "                \"(user_name, currency, operation, sum_operation,rate_UAH, sum_UAH,\" +\n" +
            "                \" status,date) VALUES (?,?,?,?,?,?,?,?)";


    @Override
    public void saveOperationInDB(String operation, double sum, String nameUser, String currency) throws SQLException {
        PreparedStatement statement;
        Date date = new Date();
        double rate = 0;
        double roundOff = 0;
        String status = "enabled";

        String dataOperation = date.toString();
        System.out.println(dataOperation);
        List<RatesPrivat> ratesPrivatList = ratesPrivatDao.getRatesPrivatFromDB();
        for (RatesPrivat ratesPrivat : ratesPrivatList) {
            if (ratesPrivat.getCcy().equals(currency)) {

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

        if (connection == null) {
            connection = ConnectionToDB.myCreateConnection();
        }
        statement = connection.prepareStatement("INSERT INTO user_operation (user_name, currency, operation, sum_operation,rate_UAH, sum_UAH, status,date) VALUES (?,?,?,?,?,?,?,?)");
        statement.setString(1, nameUser);
        statement.setString(2, currency);
        statement.setString(3, operation);
        statement.setDouble(4, sum);
        statement.setDouble(5, rate);
        statement.setDouble(6, outputSum);
        statement.setString(7, status);
        statement.setString(8, dataOperation);
        statement.addBatch();
        statement.executeUpdate();
        statement.close();
    }

    @Override
    public List<UserOperation> getAllOperationFromDB() throws SQLException {
        List<UserOperation> userOperationList = new ArrayList<>();
        String script = "SELECT * FROM user_operation";
        PreparedStatement statement;
        if (connection == null) {
            connection = ConnectionToDB.myCreateConnection();
        }
        statement = connection.prepareStatement(script);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            userOperationList.add(new UserOperation(rs.getInt(1), rs.getString(2), rs.getString(3),
                    rs.getString(4), rs.getDouble(5), rs.getDouble(6), rs.getDouble(7),
                    rs.getString(8), rs.getString(9)));
        }
        statement.close();
        rs.close();
        Collections.sort(userOperationList);
        return userOperationList;

    }

    @Override
    public UserOperation getUserOperationById(int id) {
        return null;
    }


    @Override
    public void deleteUserOperation(int id) throws SQLException {
        PreparedStatement statement;

        if (connection == null) {
            connection = ConnectionToDB.myCreateConnection();
        }
        statement = connection.prepareStatement(SQL_UPDATE_STATUS_USER_OPERATION);
        statement.setString(1, "disabled");
        statement.setInt(2, id);
        statement.addBatch();
        statement.executeUpdate();
        statement.close();

    }

    @Override
    public List<UserOperation> checkStatus(List<UserOperation> userOperations) {
        List<UserOperation> operationList = new ArrayList<>();
        for (UserOperation userOperation : userOperations) {
            if (userOperation.getStatus().equals("enabled")) {
                operationList.add(userOperation);
            }
        }
        return operationList;
    }

    @Override
    public void editOperation(int id, String name, String currency, String operation, Double rate,
                              Double sumUah, Double sumOperation) throws SQLException {
        PreparedStatement statement;

        if (connection == null) {
            connection = ConnectionToDB.myCreateConnection();
        }
        statement = connection.prepareStatement(SQL_UPDATE_USER_OPERATION);
        statement.setString(1, name);
        statement.setString(2, currency);
        statement.setString(3, operation);
        statement.setDouble(4, sumOperation);
        statement.setDouble(5, rate);
        statement.setDouble(6, sumUah);
        statement.setInt(7, id);
        statement.addBatch();
        statement.executeUpdate();
        statement.close();
    }

}
