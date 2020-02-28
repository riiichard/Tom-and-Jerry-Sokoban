package DesignPatterns;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LibraryParser {

    /**
     * Prints library parsed from JSON data to console
     * @param jsonData  string containing JSON data
     * @throws JSONException when jsonData cannot be parsed as a JSONArray
     */
    public void parseLibrary(String jsonData) throws JSONException {
        JSONArray library = new JSONArray(jsonData);

        for (int index = 0; index < library.length(); index++) {
            JSONObject book = library.getJSONObject(index);
            parseBook(book);
        }
    }

    /**
     * Prints book parsed from JSON object to console
     * @param book  a JSON object representing a book
     * @throws JSONException when book does not have a title or an author field
     */
    public void parseBook(JSONObject book) throws JSONException {
        String city = book.getString("name");
        String base = book.getString("base");
        int visi = book.getInt("visibility");
        Integer.toString(visi);

        System.out.println("Today's weather:");
        System.out.println("City: " + city);
        System.out.println("Base: " + base);
        System.out.println("Visibility: " + visi);
        System.out.println();
    }
}
