import java.util.HashMap;

public class UrlService {

    private static int count = 1000000000;
    private static String st;
    private static HashMap<String, Integer> longToshort;
    private static HashMap<Integer, String> shortTolong;



    UrlService() {
        this.longToshort = new HashMap<String, Integer>();
        this.shortTolong = new HashMap<Integer, String>();
        this.st = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    }

    public static int convert(char c) {
        if (c >= '0' && c <= '9')
            return c - '0';
        if (c >= 'a' && c <= 'z') {
            return c - 'a' + 10;
        }
        if (c >= 'A' && c <= 'Z') {
            return c - 'A' + 36;
        }
        return -1;
    }


    public static String base62(int n) {
        StringBuilder sb = new StringBuilder();
        while (n != 0) {
            sb.insert(0, st.charAt(n % 62));
            n /= 62;
        }
        while (sb.length() != 7) {
            sb.insert(0, '0');
        }
        return sb.toString();
    }

    public static int base10(String s) {
        int n = 0;
        for (int i = 0; i < s.length(); i++) {
            n = n * 62 + convert(s.charAt(i));
        }
        return n;

    }

    public static String UrlShortner(String url) {
        String shorturl = base62(count);
        longToshort.put(url, count);
        shortTolong.put(count, url);
        count++;
        return "http://tiny.url/" + shorturl;
    }

    public static String getURL(String url) {
        url = url.substring("http://tiny.url/".length());
        int n = base10(url);
        return shortTolong.get(n);
    }


}