package lab4;

import java.io.*;
import java.util.*;

public class Section {
    String title;
    List<Paragraph> paragraphs = new ArrayList<>() ;

    Section(String title) {
        this.title = title;
    }

    Section setTitle(String title){
        this.title = title;

        return this;
    }

    Section addParagraph(String paragraphText){
        Paragraph newParagraph = new Paragraph(paragraphText);
        paragraphs.add(newParagraph);

        return this;
    }

    Section addParagraph(Paragraph p){
        paragraphs.add(p);

        return this;
    }

    void writeHTML(PrintStream out){
        out.println("<h2>");
        out.println(this.title);
        out.println("</h2>");

        for (Paragraph p : paragraphs) {
            p.writeHTML(out);
        }
    }
}
