package cs.ing;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Logger;

public class Main {
    static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) throws IOException {
        var csTranslator = new CSTranslator("es", "en");
        var sc = new Scanner(System.in);
        logger.info("CS-Translator\n--------------------");
        logger.info("Ingrese texto a ser traducido: ");
        String text = sc.nextLine();
        String translatedText = csTranslator.translate(text);
        logger.info(translatedText);
    }
}
