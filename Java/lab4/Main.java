package lab4;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

public class Main {

    public static void main(String[] args) {
        Document cv = new Document("Maja Swierk - CV");
        cv.setPhoto("https://lelum.pl/wp-content/uploads/2020/03/pszcz%C3%B3%C5%82ka-maja2.jpg");
        cv.addSection("Wykształcenie")
                .addParagraph("2003-2007 Przedszkole Pod Wawelem w Krakowie")
                .addParagraph("2007-2013 SP34 im Obrońców poczty gdańskiej w Krakowie")
                .addParagraph("2013-2016 Gimnazjum Ojców Pijarów")
                .addParagraph("2016-2019 8 LO")
                .addParagraph("2019-teraz AGH");
        cv.addSection("Umiejętności")
                .addParagraph(
                        new ParagraphWithList("Umiejętności")
                                .additemToList("C")
                                .additemToList("C++")
                                .additemToList("Java")
                                .additemToList("JS")
                                .additemToList("Vue.js")

                );

        try {
            cv.writeHTML(new PrintStream("cv.html","ISO-8859-2"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
