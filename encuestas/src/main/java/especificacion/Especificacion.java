package especificacion;

import java.util.function.Predicate;

public class Especificacion<T> {

    private Predicate<T> reglas;

    public Especificacion(Predicate<T> inicial){
        reglas = inicial;
    }
    
    public Especificacion<T> and(Predicate<T> rule) {
        reglas = reglas.and(rule);
        return this;
    }

    public Especificacion<T> or(Predicate<T> rule) {
        reglas = reglas.or(rule);
        return this;
    }

    public Especificacion<T> not(Predicate<T> rule) {
        reglas = reglas.and(rule).negate();
        return this;
    }

    public boolean isSatisfiedBy(T object) {
        return reglas.test(object);
    }
}