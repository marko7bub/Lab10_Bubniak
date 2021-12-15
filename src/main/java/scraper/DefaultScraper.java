package scraper;

import lombok.SneakyThrows;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import scraper.Scraper;

public class DefaultScraper implements Scraper {
    private static final String PRICE_SELECTOR = ".detail__info-xlrg";
    private static final String BEDS_SELECTOR = ".nhs_BedsInfo";
    private static final String BATHS_SELECTOR = ".nhs_BathsInfo";
    private static final String GARAGE_SELECTOR = ".nhs_GarageInfo";

    public DefaultScraper() {
    }

    @SneakyThrows
    public Home parse(String url) {
        try {
            Document doc = Jsoup.connect(url).get();
            System.out.println(getPrice(doc));
            System.out.println(getBeds(doc));
            System.out.println(getBaths(doc));
            System.out.println(getGarages(doc));
            return null;
        } catch (Throwable var3) {
            throw var3;
        }
    }

    private static int getPrice(Document doc) {
        Element priceTag = doc.selectFirst(".detail__info-xlrg");
        String priceText = priceTag.text().replaceAll("[^0-9]", "");
        return Integer.parseInt(priceText);
    }

    private static float getBeds(Document doc) {
        Element bedsTag = doc.selectFirst(".nhs_BedsInfo");
        String bedsText = bedsTag.text().replaceAll("[^0-9.,]", "");
        return Float.parseFloat(bedsText);
    }

    private static float getBaths(Document doc) {
        Element bathsTag = doc.selectFirst(".nhs_BathsInfo");
        String bathsText = bathsTag.text().replaceAll("[^0-9.,]", "");
        return Float.parseFloat(bathsText);
    }

    private static float getGarages(Document doc) {
        Element garagesTag = doc.selectFirst(".nhs_GarageInfo");
        String garagesText = garagesTag.text().replaceAll("[^0-9.,]", "");
        return Float.parseFloat(garagesText);
    }
}
