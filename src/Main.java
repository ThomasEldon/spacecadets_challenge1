import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.http.HttpRequest;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String input = reader.readLine();
            String name = getNameFromURL("https://www.southampton.ac.uk/people/" + input);
            if (name == null) {
                name = getNameFromURL("https://www.ecs.soton.ac.uk/people/" + input);
            }

            System.out.println(name);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static String getNameFromURL(String input) { //Test: 5wzk7h
        try {
            boolean found = false;
            {
                URL url = new URL(input);
                BufferedReader web_reader = new BufferedReader(new InputStreamReader(url.openStream()));
                String line;
                while ((line = web_reader.readLine()) != null) {
                    if (line.contains("            \"@type\": \"Person\",")) {
                        found = true;
                        //Check next line
                        line = web_reader.readLine();
                        String[] name_line = line.split("            \"name\": \"");
                        if (name_line.length > 1) {
                            String[] split_two = name_line[1].split("\",");
                            return split_two[0];
                        }
                    }
                }
            }
            if (!found) {
                return null;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "";
    }

    public static void getNameOld() {
//            URL url = new URL("https://www.ecs.soton.ac.uk/people/asda69");
//            BufferedReader web_reader = new BufferedReader(new InputStreamReader(url.openStream()));
//            String line;
//            while ((line = web_reader.readLine()) != null) {
//                if (line.contains(input + "@")) {
//                    String[] name_line = line.split("</a></td><td><span class=\"js-tableSort-position\" style=\"display:none\">");
//                    String[] split_two = name_line[0].split("\">");
//                    System.out.println(split_two[split_two.length-1]);
//                }
//            }
    }
}