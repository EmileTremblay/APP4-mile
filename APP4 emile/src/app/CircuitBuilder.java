package app;

import com.fasterxml.jackson.databind.JsonNode;
import electronique.Composant;

public class CircuitBuilder {
    public CircuitBuilder(){

    }


    private Composant lireComposant(JsonNode node){
        String type = node.get("type").asText();

        if("")
    }
}
