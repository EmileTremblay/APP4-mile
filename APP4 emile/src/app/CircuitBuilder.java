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
        }
        List<Composant> liste = new ArrayList<>();

        for (JsonNode enfant : node.get("composants")) {
            liste.add(lireComposant(enfant));
        }

        if (type.equals("serie")) {
            return new CircuitSerie(liste);
        }
        if (type.equals("parallele")) {
            return new CircuitParallele(liste);
        }
        throw new IllegalArgumentException("Type invalide");
    }

    }

