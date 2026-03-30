package electronique;

public class Resistance extends Composant {
    public Resistance (double resistance){
        this.calculerResistance();
    }

    @Override
    public double calculerResistance() {
        return resistance;
    }
}
