package lab4;

import java.io.PrintStream;

public class ListItem {
    String content;

    ListItem(String content) {
        this.content = content;
    }

    void writeHTML(PrintStream out) {
        out.println("<li>");
        out.println(this.content);
        out.println("</li>");
    }
}
