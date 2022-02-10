package lab5;

import java.text.*;
import java.util.Locale;

public class Constant extends Node {
    double value;

    Constant(double value){
        this.sign = value<0?-1:1;
        this.value = value<0?-value:value;
    }


    @Override
    double evaluate() {
        return sign*value;
    }

    @Override
    public String toString() {
        String sgn=sign<0?"(-":"";
        DecimalFormat format = new DecimalFormat("0.#####",new DecimalFormatSymbols(Locale.US));
        sgn += format.format(value);
        sgn += sign<0?")":"";
        return sgn;
    }

    @Override
    Node diff(Variable var) {
        return new Constant(0);
    }

    @Override
    boolean isZero() {
        return Math.abs(value) < 0.00001;
    }

}
