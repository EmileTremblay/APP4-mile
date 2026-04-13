package electronique;

import java.util.List;

public class CircuitParallele extends Circuit{
    public CircuitParallele(List<Composant> composants){
        super(composants);
    }

    @Override
    public double calculerResistance() {
        double somme = 0;
        double inverser = 0;
        for(Composant c : composants){
            somme += 1/c.calculerResistance();
            inverser = 1/somme;
        }
        return inverser;

    }

    @Override
    public void close() throws Exception {

    }
}
