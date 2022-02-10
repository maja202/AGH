package lab6;

import java.io.IOException;
import java.io.StringReader;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Main {

    public static void main(String[] args) throws Exception {
//        testWithHeader();
//        testNoHeader();
//        testAccelerator();
//        testMissingValues();
//        testElec();
        testTimeDate();
    }

    public static void testWithHeader() throws IOException {
        CSVReader reader = new CSVReader("../Lab6/with-header.csv", ";", true);

        for (int i = 0; i < reader.columnLabels.size(); i++) {
            System.out.print(reader.columnLabels.get(i) + " ");
        }

        System.out.println("");

        while (reader.next()) {
            int id = reader.getInt("id");
            String imie = reader.get("imie");
            String nazwisko = reader.get("nazwisko");
            String ulica = reader.get("ulica");
            int nrdomu = reader.getInt("nrdomu");
            int nrmieszkania = reader.getInt("nrmieszkania");

            System.out.printf("%d %s %s %s %d %d", id, imie, nazwisko, ulica, nrdomu, nrmieszkania);
            System.out.println("");
        }
    }

    public static void testNoHeader() throws IOException {
        CSVReader reader = new CSVReader("../Lab6/no-header.csv", ";", false);
        while (reader.next()) {
            int id = reader.getInt(0);
            String imie = reader.get(1);
            String nazwisko = reader.get(2);
            String ulica = reader.get(3);
            int nrdomu = reader.getInt(4);
            int nrmieszkania = reader.getInt(5);

            System.out.printf("%d %s %s %s %d %d", id, imie, nazwisko, ulica, nrdomu, nrmieszkania);
            System.out.println("");
        }
    }

    public static void testAccelerator() throws IOException {
        CSVReader reader = new CSVReader("../Lab6/accelerator.csv", ";", true);

        for (int i = 0; i < reader.columnLabels.size(); i++) {
            System.out.print(reader.columnLabels.get(i) + " ");
        }

        System.out.println("");

        while (reader.next()) {
            double X = reader.getDouble("X");
            double Y = reader.getDouble("Y");
            double longitude = reader.getDouble("longitude");
            double latitude = reader.getDouble("latitude");
            double speed = reader.getDouble("speed");
            double time = reader.getDouble("time");
            int label = reader.getInt("label");

            System.out.printf("%f %f %f %f %f %f %d", X, Y, longitude, latitude, speed, time, label);
            System.out.println("");
        }
    }

    public static void testMissingValues() throws IOException {
        CSVReader reader = new CSVReader("../Lab6/missing-values.csv", ";", true);

        for (int i = 0; i < reader.columnLabels.size(); i++) {
            System.out.print(reader.columnLabels.get(i) + " ");
        }

        System.out.println("");

        while (reader.next()) {
            long id = reader.getLong("id");
            long parent = reader.getLong("parent");
            String name = reader.get("name");
            int admin_level = reader.getInt("admin_level");
            long population = reader.getLong("population");
            double area = reader.getDouble("area");
            if (reader.getRecordLength() == 7) {
                double density = reader.getDouble("density");
                System.out.printf("%d %d %s %d %d %f %f", id, parent, name, admin_level, population, area, density);
            } else {
                System.out.printf("%d %d %s %d %d %f", id, parent, name, admin_level, population, area);
            }

            System.out.println("");
        }
    }

    public static void testElec() throws IOException {
        CSVReader reader = new CSVReader("../Lab6/elec.csv", ",", true);

        for (int i = 0; i < reader.columnLabels.size(); i++) {
            System.out.print(reader.columnLabels.get(i) + " ");
        }

        System.out.println("");

        while (reader.next()) {
            int date = reader.getInt("date");
            int day = reader.getInt("day");
            double period = reader.getDouble("period");
            double nswprice = reader.getDouble("nswprice");
            double nswdemand = reader.getDouble("nswdemand");
            double vicprice = reader.getDouble("vicprice");
            double vicdemand = reader.getDouble("vicdemand");
            double transfer = reader.getDouble("transfer");
            String className = reader.get("class");

            System.out.printf("%d %d %f %f %f %f %f %f %s", date, day, period, nswprice, nswdemand, vicprice, vicdemand, transfer, className);
            System.out.println("");
        }
    }

    public static void testTimeDate() {
        LocalTime time = LocalTime.parse("10:09", DateTimeFormatter.ofPattern("HH:mm"));
        System.out.println(time);
        time = LocalTime.parse("10:09:17",DateTimeFormatter.ofPattern("HH:mm:ss"));
        System.out.println(time);

        LocalDate date = LocalDate.parse("2021-12-06", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        System.out.println(date);
        date = LocalDate.parse("06.12.2021", DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        System.out.println(date);
    }
}

