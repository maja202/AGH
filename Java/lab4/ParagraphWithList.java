package lab4;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class ParagraphWithList extends Paragraph{

    UnorderedList list = new UnorderedList();

    ParagraphWithList(String content) {
        super(content);
    }


    ParagraphWithList setContent(String content) {
        super.setContent(content);

        return this;
    }

    ParagraphWithList additemToList(String text) {
        list.addItem(new ListItem(text));

        return this;
    }

    void writeHTML(PrintStream out) {
        out.println("<div>");
        this.list.writeHTML(out);
        out.println("</div>");
    }
}
