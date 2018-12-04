package currencyExchange.com.dao.repository.user;

import currencyExchange.com.dao.entity.UserOperation;

import java.sql.SQLException;

public interface UserOperationDao {

     void saveOperationInDB(String operation, double sum, String nameUser, String currency) throws SQLException;
}
