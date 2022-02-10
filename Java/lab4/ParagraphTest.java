package lab4;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

public class ParagraphTest {

    @org.junit.Test
    public void Paragraph() {
        Paragraph paragraph = new Paragraph("test content");

        assertEquals("test content", paragraph.content);
    }

    @org.junit.Test
    public void setContent() {
        Paragraph p = new Paragraph("test content");
        assertEquals("test content", p.content);
        p.setContent("new content");
        assertEquals("new content", p.content);
    }

    @org.junit.Test
    public void writeHTML() {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);
        Paragraph p = new Paragraph("test content");
        p.writeHTML(ps);

        String result = null;

        try {
            result = os.toString("ISO-8859-2");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        assertTrue(result.contains("p>"));
        assertTrue(result.contains("test content"));
        assertTrue(result.contains("</"));
    }
}