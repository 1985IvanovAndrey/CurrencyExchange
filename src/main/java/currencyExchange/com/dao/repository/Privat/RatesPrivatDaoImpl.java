package currencyExchange.com.dao.repository.Privat;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import currencyExchange.com.config.ConnectionToDB;
import currencyExchange.com.dao.entity.RatesPrivat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

@Repository
public class RatesPrivatDaoImpl implements RatesPrivatDao {
    private final String INSERT_SCRIPT = "INSERT INTO rates_privat(name_currency, sale, buy) VALUES (";
    Connection connection = null;


    public List<RatesPrivat> getCurrencyFromJsonPrivat() throws IOException {
        List<RatesPrivat> ratesPrivatList = new ArrayList<RatesPrivat>();
        URL url = new URL("https://api.privatbank.ua/p24api/pubinfo?json&exchange&coursid=5");
        Scanner in = new Scanner((InputStream) url.getContent());
        String result = "";
        while (in.hasNext()) {
            result += in.nextLine();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode node = objectMapper.readTree(result);
        ArrayNode nodes = (ArrayNode) node;
        for (JsonNode jsonNode : nodes) {
            RatesPrivat RatesPrivat = new RatesPrivat();
            RatesPrivat.setNameCurrency(jsonNode.get("ccy").asText());
            RatesPrivat.setBuy(jsonNode.get("buy").asDouble());
            RatesPrivat.setSale(jsonNode.get("sale").asDouble());
            ratesPrivatList.add(RatesPrivat);
        }
        return ratesPrivatList;
    }

    public void saveCurrencyFromJsonPrivat() throws SQLException, IOException {
        PreparedStatement statement;
        List<RatesPrivat> ratesPrivatList = getCurrencyFromJsonPrivat();
        for (RatesPrivat ratesPrivat : ratesPrivatList) {
            if (connection == null) {
                connection = ConnectionToDB.myCreateConnection();
            }
            statement = connection.prepareStatement("INSERT INTO rates_privat(name_currency,sale,buy) VALUES (?,?,?)");
            statement.setString(1, ratesPrivat.getNameCurrency());
            statement.setDouble(2, ratesPrivat.getSale());
            statement.setDouble(3, ratesPrivat.getBuy());
            statement.addBatch();
            statement.executeUpdate();
            statement.close();
        }
    }

    @Override
    public List<RatesPrivat> getRatesPrivatFromDB() throws SQLException {
        String script = "SELECT * FROM rates_privat";
        List<RatesPrivat> ratesPrivatList = new ArrayList<>();
        if (connection == null) {
            connection = ConnectionToDB.myCreateConnection();
        }
        PreparedStatement pr = connection.prepareStatement(script);
        ResultSet rs = pr.executeQuery();
        while (rs.next()) {
            ratesPrivatList.add(new RatesPrivat(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getDouble(4)));
        }
        pr.close();
        rs.close();
        Collections.sort(ratesPrivatList);
        ratesPrivatList.remove(0);
        return ratesPrivatList;
    }
}



