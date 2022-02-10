package lab5;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Prod extends Node {
    List<Node> args = new ArrayList<>();

    Prod(){}

    Prod(Node n1){
        args.add(n1);
    }
    Prod(double c){
        //wywołaj konstruktor jednoargumentowy przekazując new Constant(c)

        Constant constant = new Constant(c);
        args.add(constant);
    }

    Prod(Node n1, Node n2){
        args.add(n1);
        args.add(n2);
    }
    Prod(double c, Node n){
        //wywołaj konstruktor dwuargumentowy
        Constant constant = new Constant(c);
        args.add(constant);
        args.add(n);
    }


    Prod mul(Node n){
        args.add(n);
        return this;
    }

    Prod mul(double c){
        Constant constant = new Constant(c);
        args.add(constant);
        return this;
    }


    @Override
    double evaluate() {
        double result =1;
        // oblicz iloczyn czynników wołąjąc ich metodę evaluate

        for (Node n : args) {
            result *= n.evaluate();
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
                b.append('*');
            }
        }

        if(sign<0)b.append(")");
        return b.toString();
    }

    Sum diffVanilla(Variable var) {
        Sum r = new Sum();
        for(int i=0;i<args.size();i++){
            Prod m= new Prod();
            for(int j=0;j<args.size();j++){
                Node f = args.get(j);
                if(j==i) m.mul(f.diff(var));
                else m.mul(f);
            }
            r.add(m);
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
