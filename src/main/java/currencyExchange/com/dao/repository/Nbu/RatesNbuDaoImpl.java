package currencyExchange.com.dao.repository.Nbu;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import currencyExchange.com.config.ConnectionToDB;
import currencyExchange.com.dao.entity.RatesNbu;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

@Repository
public class RatesNbuDaoImpl implements RatesNbuDao {
    Connection connection = null;

    public List<RatesNbu> getCurrencyFromJsonNbu() throws IOException {
        List<RatesNbu> ratesNbuList = new ArrayList<>();
        URL url = new URL(" https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?json");
        Scanner in = new Scanner((InputStream) url.getContent());
        String result = "";
        while (in.hasNext()) {
            result += in.nextLine();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode node = objectMapper.readTree(result);
        ArrayNode nodes = (ArrayNode) node;
        for (JsonNode jsonNode : nodes) {
            RatesNbu ratesNbu = new RatesNbu();
            if (jsonNode.get("cc").asText().equals("USD")) {
                ratesNbu.setNameCurrency(jsonNode.get("cc").asText());
                ratesNbu.setRate(jsonNode.get("rate").asDouble());
                ratesNbuList.add(ratesNbu);
            }
            if (jsonNode.get("cc").asText().equals("EUR")) {
                ratesNbu.setNameCurrency(jsonNode.get("cc").asText());
                ratesNbu.setRate(jsonNode.get("rate").asDouble());
                ratesNbuList.add(ratesNbu);
            }
            if (jsonNode.get("cc").asText().equals("RUB")) {
                ratesNbu.setNameCurrency(jsonNode.get("cc").asText());
                ratesNbu.setRate(jsonNode.get("rate").asDouble());
                ratesNbuList.add(ratesNbu);
            }
        }
        return ratesNbuList;
    }

    public void saveCurrencyFromJsonNbu() throws SQLException, IOException {
        PreparedStatement statement;
        List<RatesNbu> ratesNbuList = getCurrencyFromJsonNbu();
        for (RatesNbu ratesNbu : ratesNbuList) {
            if (connection == null) {
                connection = ConnectionToDB.myCreateConnection();
            }
            statement = connection.prepareStatement("INSERT INTO rates_nbu(name_currency, rate) VALUES (?,?)");
            statement.setString(1, ratesNbu.getNameCurrency());
            statement.setDouble(2, ratesNbu.getRate());
            statement.addBatch();
            statement.executeUpdate();
            statement.close();
        }
    }

    @Override
    public List<RatesNbu> getRatesNbuFromDB() throws SQLException {
        String script = "SELECT * FROM rates_nbu";
        List<RatesNbu> ratesNbuList = new ArrayList<>();
        if (connection == null) {
            connection = ConnectionToDB.myCreateConnection();
        }
        PreparedStatement pr = connection.prepareStatement(script);
        ResultSet rs = pr.executeQuery();
        while (rs.next()) {
            ratesNbuList.add(new RatesNbu(rs.getInt(1), rs.getString(2), rs.getDouble(3)));
        }
        pr.close();
        rs.close();
        Collections.sort(ratesNbuList);
        return ratesNbuList;
    }

}


