package electronique;

public abstract class Composant implements AutoCloseable {
    public Composant(){

    }
    public abstract double calculerResistance();
}
