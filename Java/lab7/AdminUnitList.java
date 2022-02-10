package lab7;

import java.io.PrintStream;
import java.util.*;
import java.util.function.Predicate;

public class AdminUnitList {
    List<AdminUnit> units = new ArrayList<>();
    Map<AdminUnit, Long> unitToParent = new HashMap<>();
    Map<Long, AdminUnit> idToUnit = new HashMap<>();

    public void read(String filename) throws Exception {
        CSVReader reader = new CSVReader(filename, ",", true);

        while (reader.next()) {
            long id = reader.getLong("id");
            long parent = reader.getLong("parent");
            String name = reader.get("name");
            int admin_level = reader.getInt("admin_level");
            double population = reader.getDouble("population");
            double area = reader.getDouble("area");
            double density = reader.getDouble("density");
            double x1 = reader.getDouble("x1");
            double y1 = reader.getDouble("y1");
            double x2 = reader.getDouble("x2");
            double y2 = reader.getDouble("y2");
            double x3 = reader.getDouble("x3");
            double y3 = reader.getDouble("y3");
            double x4 = reader.getDouble("x4");
            double y4 = reader.getDouble("y4");
            double x5 = reader.getDouble("x5");
            double y5 = reader.getDouble("y5");

            AdminUnit newUnit = new AdminUnit();

            newUnit.id = id;
            newUnit.name = name;
            newUnit.adminLevel = admin_level;
            newUnit.population = population;
            newUnit.area = area;
            newUnit.density = density;
            newUnit.bbox.addPoint(x1, y1);
            newUnit.bbox.addPoint(x2, y2);
            newUnit.bbox.addPoint(x3, y3);
            newUnit.bbox.addPoint(x4, y4);
            newUnit.bbox.addPoint(x5, y5);

            idToUnit.put(id, newUnit);
            unitToParent.put(newUnit, parent);

            units.add(newUnit);
        }

        for(AdminUnit unit: units) {
            if (unitToParent.get(unit) == -1) {
                unit.parent = null;
            } else {
                unit.parent = idToUnit.get(unitToParent.get(unit));
                unit.parent.children.add(unit);
            }
        }

        fixMissingValues();

    }

    void list(PrintStream out){
        for (int i = 0; i < units.size(); i++) {
            units.get(i).printToString(out);
        }
    }

    void list(PrintStream out,int offset, int limit ){
        if (limit > this.units.size()) {
            limit = this.units.size();
        }

        for (int i = offset; i < limit; i++) {
            units.get(i).printToString(out);
        }
    }

    AdminUnitList selectByName(String pattern, boolean regex){
        AdminUnitList ret = new AdminUnitList();

        if (regex) {
            for (int i = 0; i < this.units.size(); i++) {
                if (this.units.get(i).name.matches(pattern)) {
                    ret.units.add(this.units.get(i));
                }
            }
        } else {
            for (int i = 0; i < this.units.size(); i++) {
                if (this.units.get(i).name.contains(pattern)) {
                    ret.units.add(this.units.get(i));
                }
            }
        }

        return ret;
    }

    private void fixMissingValues() {

        for (AdminUnit unit : units) {
            if ( unit.population == -1 && unit.parent != null) {
                if(unit.parent.density == -1 && unit.parent.population == -1){
                    unit.parent.fixMissingValues();
                }
                unit.density = unit.parent.density;
                unit.population = unit.parent.density * unit.area;
            }

        }
    }

    AdminUnitList getNeighbors(AdminUnit unit, double maxdistance) {

        AdminUnitList newUnitList = new AdminUnitList();

        if (unit.adminLevel < 8) {
            for (AdminUnit a : units) {
                if (a.adminLevel == unit.adminLevel) {
                    if (a.bbox.intersects(unit.bbox)) {
                        newUnitList.units.add(a);
                    }

                }
            }

        }

        else {
            for (AdminUnit a : units) {
                if (a.adminLevel == unit.adminLevel) {
                    if (a.bbox.distanceTo(unit.bbox) < maxdistance) {
                        newUnitList.units.add(a);
                    }
                }
            }
        }

        return newUnitList;
    }

    public AdminUnitList findWithAdminLevel(int level) {
        AdminUnitList newList = new AdminUnitList();

        for (AdminUnit a : units) {
            if (a.adminLevel == level) {
                newList.units.add(a);
            }
        }

        return newList;
    }

    AdminUnitList sortInplaceByName(){
        class myComparator implements Comparator<AdminUnit> {

            @Override
            public int compare(AdminUnit o1, AdminUnit o2) {
                return o1.name.compareTo(o2.name);
            }
        }
        myComparator newComp = new myComparator();
        this.units.sort(newComp);

        return this;
    }

    AdminUnitList sortInplaceByArea(){
        class myComparator implements Comparator<AdminUnit> {

            @Override
            public int compare(AdminUnit o1, AdminUnit o2) {
                return Double.compare(o1.area, o2.area);
            }
        }
        myComparator newComp = new myComparator();
        this.units.sort(newComp);

        return this;
    }

    AdminUnitList sortInplaceByPopulation(){
        units.sort((AdminUnit o1, AdminUnit o2) -> Double.compare(o1.population, o2.population));
        return this;
    }

    AdminUnitList sortInPlace(Comparator<AdminUnit> cmp){
        units.sort(cmp);
        return this;
    }

    AdminUnitList sort(Comparator<AdminUnit> cmp){
        // Tworzy wyjściową listę
        // Kopiuje wszystkie jednostki
        // woła sortInPlace
        AdminUnitList copy = new AdminUnitList();
        copy.units = new ArrayList<>(this.units);

        return copy.sortInPlace(cmp);
    }

    AdminUnitList filter(Predicate<AdminUnit> pred) {
        AdminUnitList newList = new AdminUnitList();

        for (AdminUnit u : units) {
            if (pred.test(u)) {
                newList.units.add(u);
            }
        }

        return newList;
    }

    AdminUnitList filter(Predicate<AdminUnit> pred, int limit){
        AdminUnitList newList = new AdminUnitList();
        int i = 0;

        for (AdminUnit u : units) {
            if (pred.test(u) && i < limit) {
                newList.units.add(u);
                i++;
            } else if (i >= limit) {
                break;
            }
        }

        return newList;
    }

    AdminUnitList filter(Predicate<AdminUnit> pred, int offset, int limit){
        AdminUnitList newList = new AdminUnitList();
        int i = 0;

        if (offset >= units.size()) {
            throw new RuntimeException("offset is too big!");
        }

        for (AdminUnit u : units) {
            if (pred.test(u) && i >= offset && i < limit + offset) {
                newList.units.add(u);
                i++;
            } else if (i >= limit) {
                break;
            }
        }

        return newList;
    }
}


