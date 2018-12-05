package currencyExchange.com.dao.repository.user;

import currencyExchange.com.dao.entity.UserOperation;

import java.sql.SQLException;
import java.util.List;

public interface UserOperationDao {

     void saveOperationInDB(String operation, double sum, String nameUser, String currency) throws SQLException;

     List<UserOperation> getAllOperationFromDB() throws SQLException;

     UserOperation getUserOperationById(int id);

     void deleteUserOperation(int id) throws SQLException;

     List<UserOperation>checkStatus(List<UserOperation> userOperations);

     void editOperation(int id, String name, String currency, String operation, Double rate,
                        Double sumUah, Double sumOperation) throws SQLException;
}
