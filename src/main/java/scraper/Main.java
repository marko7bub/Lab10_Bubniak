package scraper;

public class Main {
    public Main() {
    }

    public static void main(String[] args) {
        DefaultScraper defaultScraper = new DefaultScraper();
        String url = "https://www.newhomesource.com/plan/encore-wlh-taylor-morrison-austin-tx/1771471";
        defaultScraper.parse(url);
    }
}