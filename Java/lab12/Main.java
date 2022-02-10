package lab12;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
	    String s = "fv nr 2-09/11/2020 przyczyna";

	    Pattern p = Pattern.compile("[0-9]-[0-9][0-9]/[0-9][0-9]/[0-9][0-9][0-9][0-9]");
	    String[] result = p.split(s);

	    for (String data:result) {
	        System.out.println(data);
        }

//	    Matcher m = p.matcher(s);
//
//	    if (m.matches()) {
//	        System.out.println(m.group(0));
//        }
    }
}
