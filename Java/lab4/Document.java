package lab4;

import java.io.*;
import java.util.*;

public class Document {
    String title;
    Photo photo;
    List<Section> sections = new ArrayList<>();

    Document (String title) {
        this.title = title;
    }

    Document setTitle(String title){
        this.title = title;

        return this;
    }

    Document setPhoto(String photoUrl){
        photo = new Photo(photoUrl);

        return this;
    }

    Section addSection(String sectionTitle){
        Section newSection = new Section(sectionTitle);
        sections.add(newSection);

        return newSection;
    }
    Document addSection(Section s){
        sections.add(s);

        return this;
    }

    void writeHTML(PrintStream out){
        out.println("<!DOCTYPE html>");
        out.println("<html lang=\"en\">");

        out.println("<h1>");
        out.println(this.title);
        out.println("</h1>");

        out.println("<body>");
        photo.writeHTML(out);

        for (Section s : sections) {
            s.writeHTML(out);
        }
        out.println("</body>");

        out.println("</html>");

    }
}
