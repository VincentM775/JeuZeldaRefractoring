package universite_paris8.iut.EtrangeEtrange.controller;

import javafx.scene.input.KeyCode;


public class ConstantesClavier {

    public static KeyCode deplacementHaut = KeyCode.Z;
    public static KeyCode deplacementBas = KeyCode.S;
    public static KeyCode deplacementDroite = KeyCode.D;
    public static KeyCode deplacementGauche = KeyCode.Q;
    public static KeyCode recupererObjetSol = KeyCode.E;
    public static KeyCode attaquer = KeyCode.M;

    public static KeyCode inventaire = KeyCode.I;
    public static KeyCode courrir = KeyCode.L;

    public static KeyCode menuDeplacementGauche = KeyCode.Q;
    public static KeyCode menuDeplacementDroite = KeyCode.D;
    public static KeyCode menuDeplacementHaut = KeyCode.Z;
    public static KeyCode menuDeplacementBas = KeyCode.S;
    public static KeyCode menuSelectionner =KeyCode.L;

    public static void setAttaquer(KeyCode attaquer) {
        ConstantesClavier.attaquer = attaquer;
    }

    public static void setCourrir(KeyCode courrir) {
        ConstantesClavier.courrir = courrir;
    }

    public static void setDeplacementBas(KeyCode deplacementBas) {
        ConstantesClavier.deplacementBas = deplacementBas;
    }

    public static void setDeplacementDroite(KeyCode deplacementDroite) {
        ConstantesClavier.deplacementDroite = deplacementDroite;
    }

    public static void setDeplacementGauche(KeyCode deplacementGauche) {
        ConstantesClavier.deplacementGauche = deplacementGauche;
    }

    public static void setDeplacementHaut(KeyCode deplacementHaut) {
        ConstantesClavier.deplacementHaut = deplacementHaut;
    }

    public static void setInventaire(KeyCode inventaire) {
        ConstantesClavier.inventaire = inventaire;
    }

    public static void setMenuDeplacementBas(KeyCode menuDeplacementBas) {
        ConstantesClavier.menuDeplacementBas = menuDeplacementBas;
    }

    public static void setMenuDeplacementDroite(KeyCode menuDeplacementDroite) {
        ConstantesClavier.menuDeplacementDroite = menuDeplacementDroite;
    }

    public static void setMenuDeplacementGauche(KeyCode menuDeplacementGauche) {
        ConstantesClavier.menuDeplacementGauche = menuDeplacementGauche;
    }

    public static void setMenuDeplacementHaut(KeyCode menuDeplacementHaut) {
        ConstantesClavier.menuDeplacementHaut = menuDeplacementHaut;
    }

    public static void setMenuSelectionner(KeyCode menuSelectionner) {
        ConstantesClavier.menuSelectionner = menuSelectionner;
    }

    public static void setRecupererObjetSol(KeyCode recupererObjetSol) {
        ConstantesClavier.recupererObjetSol = recupererObjetSol;
    }
}
