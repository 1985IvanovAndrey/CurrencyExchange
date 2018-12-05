package currencyExchange.com.services.privatService;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import currencyExchange.com.config.ConnectionToDB;
import currencyExchange.com.dao.entity.RatesPrivat;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Service
public class RatesPrivatServiceImpl implements RatesPrivatService {

    public final String SCRIPT_CREATE_TABLE_RATES_PRIVAT = "CREATE TABLE rates_privat\n" +
            "(\n" +
            "  id            SERIAL NOT NULL,\n" +
            "  name_currency VARCHAR(25),\n" +
            "  base_ccy VARCHAR(25),\n" +
            "  sale          DOUBLE PRECISION,\n" +
            "  buy           DOUBLE PRECISION\n" +
            ");";
    private final String SQL_UPDATE_RATES_PRIVAT = "update rates_privat set sale=?,buy=? where name_currency = ?";

    Connection connection = null;


    @Override
    public void createTableRatesPrivatInDB() throws SQLException {
        if (connection == null) {
            connection = ConnectionToDB.myCreateConnection();
        }
        Statement statement = null;
        statement = connection.createStatement();
        statement.executeUpdate(SCRIPT_CREATE_TABLE_RATES_PRIVAT);
        statement.close();
    }

    @Override
    public void editRatesPrivatInDB(String json) throws IOException, SQLException {
        PreparedStatement statement;
        List<RatesPrivat> ratesPrivatList = new ArrayList<RatesPrivat>();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode node = objectMapper.readTree(json);
        ArrayNode nodes = (ArrayNode) node;
        for (JsonNode jsonNode : nodes) {
            RatesPrivat RatesPrivat = new RatesPrivat();
            RatesPrivat.setCcy(jsonNode.get("ccy").asText());
            RatesPrivat.setBuy(jsonNode.get("buy").asDouble());
            RatesPrivat.setSale(jsonNode.get("sale").asDouble());
            ratesPrivatList.add(RatesPrivat);
        }
        for (RatesPrivat ratesPrivat : ratesPrivatList) {
            if (connection == null) {
                connection = ConnectionToDB.myCreateConnection();
            }
            statement = connection.prepareStatement(SQL_UPDATE_RATES_PRIVAT);
            statement.setDouble(1, ratesPrivat.getSale());
            statement.setDouble(2, ratesPrivat.getBuy());
            statement.setString(3, ratesPrivat.getCcy().toUpperCase());
            statement.addBatch();
            statement.executeUpdate();
            statement.close();
        }
    }
}







