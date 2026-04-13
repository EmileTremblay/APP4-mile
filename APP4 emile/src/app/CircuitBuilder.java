package app;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import electronique.CircuitParallele;
import electronique.CircuitSerie;
import electronique.Composant;
import electronique.Resistance;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CircuitBuilder {
    public CircuitBuilder(){

    }

    public Composant construireCircuit(String chemin){
        ObjectMapper map = new ObjectMapper();
        try {
                JsonNode root = map.readTree(new File(chemin));
                return lireComposant(root.get("circuit"));
        } catch (Exception e){
            throw new RuntimeException("Erreur JSON");
        }

    }

    private Composant lireComposant(JsonNode node){
        String type = node.get("type").asText();

        if (type.equals("resistance")) {
            return new Resistance(node.get("valeur").asDouble());
        }else if (type.equals("serie")) {

            List<Composant> composants = new ArrayList<>();

            for (JsonNode composantNode : node.get("composants")) {
                composants.add(lireComposant(composantNode));
            }

            return new CircuitSerie(composants);

        } else if (type.equals("parallele")) {

            List<Composant> composants = new ArrayList<>();

            for (JsonNode composantNode : node.get("composants")) {
                composants.add(lireComposant(composantNode));
            }

            return new CircuitParallele(composants);
        }

        throw new IllegalArgumentException("type de circuit invalide");

    }
}

