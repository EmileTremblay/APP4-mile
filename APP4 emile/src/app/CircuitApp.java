package app;

import electronique.Composant;

import java.io.File;
import java.util.Scanner;

public class CircuitApp {
    public CircuitApp() {}
    public static void  main (String[] args) {
        Scanner sc = new Scanner(System.in);
        CircuitBuilder build = new CircuitBuilder();

        File dossier = new File("donnees");
        File[] fichiers = dossier.listFiles((dir, name) -> name.endsWith(".json"));
        if (fichiers == null || fichiers.length == 0 ){
            System.out.println("Aucun fichier JSON trouvé.");
            return;
        }

        boolean continuer = true;

        while (continuer) {
            System.out.println("--- Sélection de circuit ---");
            for (int i = 0; i < fichiers.length; i++) {
                System.out.println("[" + (i + 1) + "] " + fichiers[i].getName());
        }
            int choix = -1;

            while (true) {
                System.out.print("Entrez le numéro du fichier : ");
                String num = sc.nextLine();

                try {
                    choix = Integer.parseInt(num);

                    if (choix >= 1 && choix <= fichiers.length) {
                        break;
                    } else {
                        System.out.println("Numéro invalide");
                    }

                } catch (NumberFormatException e) {
                    System.out.println("Entrée invalide");
                }
            }
            try {
                Composant circuit = build.construireCircuit(fichiers[choix - 1].getPath());
                double resultat = circuit.calculerResistance();

                System.out.printf("Résistance équivalente calculée : %.2f Ω", resultat);

            } catch (Exception e) {
                e.printStackTrace();
            }
            while (true) {
                System.out.print("[R] Tester un autre fichier | [Q] Quitter : ");
                String rep = sc.nextLine().toUpperCase();

                if (rep.equals("R")) {
                    break;
                } else if (rep.equals("Q")) {
                    continuer = false;
                    break;
                } else {
                    System.out.println("Choix invalide.");
                }
    }

}
    }
}
