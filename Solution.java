import java.util.Scanner;

public class Solution {

    public static void main(String str[]){

        Scanner sc = new Scanner(System.in);
        String longUrl = sc.nextLine();
        UrlService ser= new UrlService();
        System.out.println(ser.UrlShortner(longUrl));
        String shortUrl = sc.nextLine();
        System.out.println(ser.getURL(shortUrl));
    }
}