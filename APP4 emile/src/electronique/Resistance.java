package electronique;

public class Resistance extends Composant {
    private double valRes;
    public Resistance (double valRes){
        this.valRes = valRes;
    }

    @Override
    public double calculerResistance() {
        return valRes;
    }
}
