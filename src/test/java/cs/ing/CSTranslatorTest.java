package cs.ing;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CSTranslatorTest {
    static final Logger logger = Logger.getLogger(CSTranslatorTest.class.getName());
    CSTranslator csTranslator = new CSTranslator("es", "en");

    @Test(threadPoolSize = 80, timeOut = 10000)
    public void MainTest() throws IOException {
        String input = "Hola Mundo";
        InputStream inStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inStream);
        Main program = new Main();
        program.main(null);
    }

    @Test(threadPoolSize = 80, invocationCount = 10,  timeOut = 10000)
    public void Test01() throws IOException {
        csTranslator.translate("Hola Mundo");
        long start = System.currentTimeMillis();
        long maxExecTime = 400;
        String text = csTranslator.translate("Hola Mundo");
        long end = System.currentTimeMillis();
        long execTime = end - start;
        Assert.assertTrue(execTime < maxExecTime);
        Assert.assertEquals(text, "Hello World");
    }

    @Test(threadPoolSize = 80, invocationCount = 10,  timeOut = 10000)
    public void Test02() throws IOException {
        long start = System.currentTimeMillis();
        long maxExecTime = 400;
        String text = csTranslator.translate("aaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        long end = System.currentTimeMillis();
        long execTime = end - start;
        Assert.assertTrue(execTime < maxExecTime);
        Assert.assertEquals(text, "La longitud del texto (501) excede el limite de caracteres (500).");
    }

    @Test(threadPoolSize = 80, invocationCount = 10,  timeOut = 10000)
    public void Test03() throws IOException {
        long start = System.currentTimeMillis();
        long maxExecTime = 400;
        String text = csTranslator.translate("");
        long end = System.currentTimeMillis();
        long execTime = end - start;
        Assert.assertTrue(execTime < maxExecTime);
        Assert.assertEquals(text, "No se ingreso texto.");
    }
}
