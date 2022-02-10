package lab5;

import java.util.ArrayList;
import java.util.List;

public class Sum extends Node {

    List<Node> args = new ArrayList<>();

    Sum(){}

    Sum(Node n1, Node n2){
        args.add(n1);
        args.add(n2);
    }


    Sum add(Node n){
        args.add(n);
        return this;
    }

    Sum add(double c){
        args.add(new Constant(c));
        return this;
    }

    Sum add(double c, Node n) {
        Node mul = new Prod(c,n);
        args.add(mul);
        return this;
    }

    @Override
    double evaluate() {
        double result =0;
        //oblicz sumę wartości zwróconych przez wywołanie evaluate skłądników sumy

        for (Node n : args) {
            result += n.evaluate();
        }

        return sign*result;
    }

    int getArgumentsCount(){return args.size();}

    public String toString(){
        StringBuilder b =  new StringBuilder();
        if(sign<0)b.append("-(");

        for (int i = 0; i < getArgumentsCount(); i++) {
            b.append(args.get(i).toString());

            if(i != getArgumentsCount() - 1) {
                b.append(" + ");
            }
        }

        if(sign<0)b.append(")");
        return b.toString();
    }

    Sum diffVanilla(Variable var) {
        Sum r = new Sum();
        for(Node n:args){
            r.add(n.diff(var));
        }
        return r;
    }

    @Override
    Node diff(Variable var) {
        Sum n= diffVanilla(var);
        Sum newSum= new Sum();
        for (Node x : n.args) {
            if(!x.isZero())
                newSum.add(x);
        }
        if(newSum.getArgumentsCount()==0)
            return new Constant(0);
        else
            return newSum;
    }

    @Override
    boolean isZero() {
        return Math.abs(evaluate()) < 0.00001;
    }

}
