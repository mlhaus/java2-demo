package edu.kirkwood.webcrawler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;

public class SimpleWebCrawler {

    public static void main(String[] args) {
        String urlString = "https://www.kirkwood.edu";
//         String urlString = String.format("https://graphical.weather.gov/"
//                 + "xml/sample_products/browser_interface/ndfdXMLclient.php?"
//                 + "lat=41.911150&lon=-91.652149&product=time-series&"
//                 + "begin=%s&end=%s&maxt=maxt&mint=mint", LocalDateTime.now().with(LocalTime.MIN), LocalDateTime.now().with(LocalTime.MAX));
        try {
            // NOTE: Once a URL object is created, you cannot change it.
            URL url = new URL(urlString);
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            String line;
            int lineNumber = 1;
            while ((line = reader.readLine()) != null) {
                System.out.printf("%4d %s\n", lineNumber++, line);
            }
        } catch (MalformedURLException ex) {
            System.out.println("ERROR: MalformedURLException " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("ERROR: IOException " + ex.getMessage());
        }
    }

}