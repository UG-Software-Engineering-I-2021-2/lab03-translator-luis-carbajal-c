package cs.ing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class CSTranslator {
    HashMap<String, String> hashMap; // using hashmap to reduce translation time
    String langFrom;
    String langTo;

    public CSTranslator(String langFrom, String langTo) {
        hashMap = new HashMap<>();
        this.langFrom = langFrom;
        this.langTo = langTo;
    }

    public String translate(String text) throws IOException {
        if (text.length() <= 0)
            return "No se ingreso texto.";   // no input handler

        if (text.length() > 500)
            return "La longitud del texto (" + text.length() + ") excede el limite de caracteres (500)."; // character limit

        if (hashMap.containsKey(text))
            return hashMap.get(text);   // if text was already translated, return translation from hashmap

        // translation with Google Apps Script
        String urlStr = "https://script.google.com/macros/s/AKfycbwwFixn3YdWpyj9ecARYM9tAB-iLs8dvnP6YCE9Iq47wae-g30a/exec" +
                "?q=" + URLEncoder.encode(text, StandardCharsets.UTF_8) + "&target=" + langTo + "&source=" + langFrom;

        var url = new URL(urlStr);
        var response = new StringBuilder();
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        var in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        hashMap.put(text, response.toString()); // add text with its translation to hashmap
        return response.toString();
    }
}
