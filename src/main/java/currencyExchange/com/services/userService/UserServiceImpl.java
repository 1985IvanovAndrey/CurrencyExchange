package currencyExchange.com.services.userService;

import currencyExchange.com.config.ConnectionToDB;
import currencyExchange.com.dao.entity.UserOperation;
import currencyExchange.com.dao.repository.user.UserOperationDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserOperationDao userOperationDao;
    Connection connection = null;

    private final String SQL_FIND_USER_OPERATION = "select * from user_operation where id = ?";
    private final String SQL_DELETE_USER_OPERATION = "delete from user_operation where id = ?";
    private final String SQL_UPDATE_USER_OPERATION = "update user_operation set status=? where id = ?";

    private final String SQL_INSERT_USER_OPERATION = "INSERT INTO user_operation\" +\n" +
            "                \"(user_name, currency, operation, sum_operation,rate_UAH, sum_UAH,\" +\n" +
            "                \" status,date) VALUES (?,?,?,?,?,?,?,?)";


    public final String SCRIPT_CREATE_TABLE_USER_OPERATION = "CREATE TABLE user_operation\n" +
            "(\n" +
            "  id            SERIAL NOT NULL,\n" +
            "  user_name     VARCHAR(25),\n" +
            "  currency      VARCHAR(20),\n" +
            "  operation     VARCHAR(20),\n" +
            "  sum_operation DOUBLE PRECISION,\n" +
            "  rate_uah      DOUBLE PRECISION,\n" +
            "  sum_uah       DOUBLE PRECISION,\n" +
            "  status        VARCHAR(20),\n" +
            "  date       VARCHAR(50)\n" +
            ");";




    @Override
    public void createTableUserOperation() throws SQLException {
        if (connection == null) {
            connection = ConnectionToDB.myCreateConnection();
        }
        Statement statement = null;
        statement = connection.createStatement();
        statement.executeUpdate(SCRIPT_CREATE_TABLE_USER_OPERATION);
        statement.close();
    }

//    @Override
//    public UserOperation getUserOperationById(int id) {
//        return null;
//    }

//    @Override
//    public List<UserOperation> getAllUserOperation() throws SQLException {
//        return userOperationDao.getAllOperationFromDB();
//    }

//    @Override
//    public void deleteUserOperation(int id) throws SQLException {
//        PreparedStatement statement;
//
//        if (connection == null) {
//            connection = ConnectionToDB.myCreateConnection();
//        }
//        statement = connection.prepareStatement(SQL_UPDATE_USER_OPERATION);
//        statement.setString(1,"disabled");
//        statement.setInt(2,id);
//        statement.addBatch();
//        statement.executeUpdate();
//        statement.close();
//
//    }

//    @Override
//    public boolean updateUserOperation(UserOperation userOperation) {
//        return false;
//    }
//
//    @Override
//    public boolean createUserOperation(UserOperation userOperation) {
//        return false;
//    }
//
//    @Override
//    public List<UserOperation> checkStatus(List<UserOperation> userOperations) {
//       List<UserOperation>operationList=new ArrayList<>();
//        for (UserOperation userOperation : userOperations) {
//            if (userOperation.getStatus().equals("enabled")){
//                operationList.add(userOperation);
//            }
//        }
//        return operationList;
//    }
}
