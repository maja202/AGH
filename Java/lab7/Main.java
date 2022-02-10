package lab7;

import java.io.IOException;
import java.io.PrintStream;
import java.util.Comparator;
import java.util.Locale;

public class Main {

    public static void main(String[] args) throws Exception {
//        System.out.println("----------------test print----------------------");
//        testPrint();
//        System.out.println("----------------test find with admin level----------------------");
//        testFindWithAminLevel();
//        System.out.println("----------------test find neighbour----------------------");
//        testFindNeighbour();
        System.out.println("----------------test query 1----------------------");
        testQuery1(System.out);
        System.out.println("----------------test query 2----------------------");
        testQuery2(System.out);

    }

    public static void testPrint() throws Exception {
        AdminUnitList unit = new AdminUnitList();
        unit.read("admin-units.csv");

        //list everything
//        unit.list(System.out);

        //list with offset
        unit.list(System.out, 0, 10);

        //list with selected name
//        unit.selectByName("Kolonia", false).list(System.out, 0, 10);

    }

    public static void testFindWithAminLevel() throws Exception {
        AdminUnitList unit = new AdminUnitList();
        AdminUnitList unit2 = new AdminUnitList();

        unit.read("admin-units.csv");

        //find admin level
        unit2 = unit.findWithAdminLevel(4);
        unit2.list(System.out);
    }

    public static void testFindNeighbour() throws Exception {
        AdminUnitList unit = new AdminUnitList();
        AdminUnitList unit2 = new AdminUnitList();

        //find with admin level
        unit.read("admin-units.csv");
        unit2 = unit.findWithAdminLevel(4);

        //find neigbours
        unit2 = unit.getNeighbors(unit2.units.get(0), 15);
        unit2.list(System.out);

        //how much time
        System.out.println("Time:");
        double t1 = System.nanoTime()/1e6;
        double t2 = System.nanoTime()/1e6;
        System.out.printf(Locale.US,"t2-t1=%f\n",t2-t1);
    }

    public static void testQuery1(PrintStream out) throws Exception {
        AdminUnitList unit = new AdminUnitList();
        unit.read("admin-units.csv");

        AdminUnitQuery query = new AdminUnitQuery()
                .selectFrom(unit)
                .where(a->a.id<500)
                .and(a->a.name.startsWith("K"))
                .sort(Comparator.comparingDouble(a -> a.id))
                .limit(100);
        query.execute().list(out);
    }

    public static void testQuery2(PrintStream out) throws Exception {
        AdminUnitList unit = new AdminUnitList();
        unit.read("admin-units.csv");

        AdminUnitQuery query = new AdminUnitQuery()
                .selectFrom(unit)
                .where(a->a.id>500)
                .and(a->a.id<1000)
                .and(a->a.name.startsWith("Po"))
                .sort(Comparator.comparingDouble(a -> a.id))
                .limit(100);
        query.execute().list(out);
    }
}
