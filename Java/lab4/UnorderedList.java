package lab4;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class UnorderedList {
    List<ListItem> listItems = new ArrayList<>();

    UnorderedList addItem(String content) {
        ListItem newItem = new ListItem(content);
        listItems.add(newItem);

        return this;
    }

    UnorderedList addItem(ListItem item) {
        listItems.add(item);

        return this;
    }

    void writeHTML(PrintStream out) {
        out.println("<ul>");

        for (ListItem l : listItems) {
            l.writeHTML(out);
        }

        out.println("</ul>");
    }

}
