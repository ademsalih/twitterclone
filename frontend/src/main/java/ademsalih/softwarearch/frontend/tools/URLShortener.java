package ademsalih.softwarearch.frontend.tools;

public class URLShortener {

    public URLShortener() {
    }

    public String shorten(String URL) {

        if (URL.contains("https://www.")) {
            return URL.substring(12);

        } else if (URL.contains("http://www.")) {
            return URL.substring(11);

        } else if (URL.contains("https://")) {
            return URL.substring(8);

        } else if (URL.contains("http://")) {
            return URL.substring(7);
        }

        return URL;
    }
}
