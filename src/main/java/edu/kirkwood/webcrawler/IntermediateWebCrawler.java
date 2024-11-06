package edu.kirkwood.webcrawler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class IntermediateWebCrawler {
    public static void main(String[] args) {
        String pageContent = getPageContent();
        List<Shoe> shoes = getShoes(pageContent);
        shoes.forEach(System.out::println);
    }

    public static List<Shoe> getShoes(String pageContent) {
        List<Shoe> shoes = new ArrayList<>();
        int indexStartH2 = pageContent.indexOf("<h2>");
        while(indexStartH2 >= 0) {
            Shoe shoe = new Shoe();
            // Get ranking
            int indexStartRanking = indexStartH2 + 4;
            int indexEndRanking = pageContent.indexOf(".", indexStartRanking);
            String ranking = pageContent.substring(indexStartRanking, indexEndRanking);
//            System.out.println(ranking);
            shoe.setRanking(Integer.parseInt(ranking));

            // Get shoe name
            int indexStartShoeName = indexEndRanking + 2;
            int indexEndShoeName = pageContent.indexOf("</h2>", indexStartShoeName);
            String shoeName = pageContent.substring(indexStartShoeName, indexEndShoeName);
//            System.out.println(shoeName);
            shoe.setShoeName(shoeName);

            // Get image
            int indexStartShoeImage = pageContent.indexOf("https", indexEndShoeName);
            int indexEndShoeImage = pageContent.indexOf(".jpg", indexStartShoeImage) + 4;
            String shoeImage = pageContent.substring(indexStartShoeImage, indexEndShoeImage);
//            System.out.println(shoeImage);
            shoe.setShoeImage(shoeImage);


            shoes.add(shoe);
            indexStartH2 = pageContent.indexOf("<h2>", indexStartH2 + 1);
        }
        return shoes;
    }

    public static String getPageContent() {
        String urlString = "https://www.espn.com/sportsnation/story/_/id/14734617/ranking-every-air-jordan-sneaker-1-xx9";
        //        String urlString = "https://www.espn.com/espn/feature/story/_/id/39771146/sneakerhead-guide-every-nba-wnba-signature-sneaker-history";
        String pageContent = "";
        try {
            // NOTE: Once a URL object is created, you cannot change it.
            URL url = new URL(urlString);
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                pageContent += line;
            }
        } catch (MalformedURLException ex) {
            System.out.println("ERROR: MalformedURLException " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("ERROR: IOException " + ex.getMessage());
        }
        return pageContent;
    }
}

class Shoe {
    private int ranking;
    private String shoeName;
    private String shoeImage;

    public Shoe() {
    }

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

    public String getShoeName() {
        return shoeName;
    }

    public void setShoeName(String shoeName) {
        this.shoeName = shoeName;
    }

    public String getShoeImage() {
        return shoeImage;
    }

    public void setShoeImage(String shoeImage) {
        this.shoeImage = shoeImage;
    }

    @Override
    public String toString() {
        return "ESPNShoe{" +
                "ranking=" + ranking +
                ", shoeName='" + shoeName + '\'' +
                ", shoeImage='" + shoeImage + '\'' +
                '}';
    }
}
