package lab7;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class AdminUnit {
    String name;
    long id;
    int adminLevel;
    double population;
    double area;
    double density;
    AdminUnit parent;
    BoundingBox bbox = new BoundingBox();
    List<AdminUnit> children = new ArrayList<>();

    public void printToString(PrintStream out) {
        out.print(this.id + " ");
        out.print(this.name + " ");
        out.print(this.adminLevel + " ");
        out.print(this.population + " ");
        out.print(this.area + " ");
        out.print(this.density + " ");
        bbox.printToString(out);
        out.println();
    }

    public void fixMissingValues(){
        if(density == -1 && population == -1 && parent != null){
            if(parent.density == -1 && parent.population ==-1){
                parent.fixMissingValues();
            }
            density = parent.density;
            population = parent.density * area;

        }

    }
}
