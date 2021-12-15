package scraper;

import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class CacheScraper implements Scraper {
    private Scraper scraper = new DefaultScraper();
    @SneakyThrows
    public Home parse(String url) {
        // Created connection to DB
        Connection connection = DriverManager.getConnection("jdbc:sqlite:db.sqlite");
        Statement statement = connection.createStatement();

        // Execute query
        String query = String.format("select count(*) as count from homes where url='%s'", url);
        ResultSet rs = statement.executeQuery(query);

        // Extract result
        if (rs.getInt("count") > 0) {
            query = String.format("select * from homes where url='%s'", url);
            rs = statement.executeQuery(query);
            return new Home(rs.getInt("price"), rs.getDouble("beds"),  rs.getDouble("baths"), rs.getDouble("garages"));
        } else {
            Home home = scraper.parse(url);
            String query2 = String.format("insert into homes(url, price, beds, bathes, garages) values ('localhost', '%s', '%s', '%s', '%s')", home.getPrice(), home.getBeds(), home.getBathes(), home.getGarages());
            statement.executeUpdate(query2);
        }
        return null;
    }
}