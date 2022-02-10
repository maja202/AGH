package lab4;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

public class ListItemTest {

    @Test
    public void ListItem() {
        ListItem newItem = new ListItem("test content");

        assertEquals("test content", newItem.content);
    }

    @Test
    public void writeHTML() {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);
        ListItem newItem = new ListItem("test content");
        newItem.writeHTML(ps);

        String result = null;

        try {
            result = os.toString("ISO-8859-2");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        assertTrue(result.contains("li>"));
        assertTrue(result.contains("test content"));
        assertTrue(result.contains("</"));
    }
}