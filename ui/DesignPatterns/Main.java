package DesignPatterns;

import org.json.JSONException;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

public class Main {
    public static void main(String[] args) throws MalformedURLException, IOException {
        BufferedReader br = null;

        try {
            String apikey = "08416234de145a456cbb8c8d0f3a1a16"; //fill this in with the API key they email you
            String londonweatherquery = "https://api.openweathermap.org/data/2.5/weather?q=Vancouver,ca&APPID=";
            String theURL=londonweatherquery+apikey;
            URL url = new URL(theURL);
            br = new BufferedReader(new InputStreamReader(url.openStream()));

            String line;

            StringBuilder sb = new StringBuilder();

            while ((line = br.readLine()) != null) {

                sb.append(line);
                sb.append(System.lineSeparator());
            }
            System.out.println(sb);
            parse(sb.toString());
        } finally {

            if (br != null) {
                br.close();
            }
        }

    }

    /**
     * Parse JSON library file and print data to console
     */
    private static void parse(String jsonData) {
        try {
            LibraryParser libParser = new LibraryParser();
            libParser.parseLibrary("["+jsonData+"]");
        }  catch (JSONException e) {
            System.out.println("Error parsing JSON data");
            e.printStackTrace();
        }

    }

    /**
     * Read source data from input stream as string
     *
     * @param is input stream connected to source data
     * @return source data as string
     * @throws IOException when error occurs reading data from file
     */
    private String readSource(InputStream is) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line;

        while ((line = br.readLine()) != null) {
            sb.append(line);
            sb.append("\n");
        }

        br.close();

        return sb.toString();
    }
}