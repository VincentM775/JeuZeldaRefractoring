package universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.EntiteOffensif;

import universite_paris8.iut.EtrangeEtrange.modele.Compétence.TypeCompetence;
import universite_paris8.iut.EtrangeEtrange.modele.Map.Monde;

import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeMagique.LivreMagique;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeMagique.Sort.Attaque.SortilegePluitDeFleche;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeMagique.Sort.Sortilege;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.Epee;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Soins.Potion;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Aetoile;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Hitbox;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Position;

public class RoiSquelette extends EntiteOffensif
{
    public static final int PV_ROI_SQUELETTE = 1000;
    public static final int ATTAQUE_ROI_SQUELETTE = 20;
    public static final int DEFENSE_ROI_SQUELETTE = 20;
    public static final int ATTAQUE_SPECIALE_ROI_SQUELETTE = 20;
    public static final int DEFENSE_SPECIALE_ROI_SQUELETTE = 20;
    public static final double VITESSE_ROI_SQUELETTE = 0.3;

    private long dernierTempsAttaque;
    private long delaiAttaque = 75;
    private Position positionInitiale;
    private int etapeAttaque;
    private Position positionMilieu;
    private Position position5_2;
    private boolean joueurDetecte = false;
    private double distanceDetection = 5.0;
    private LivreMagique livreMagique;
    private Epee epee;

    public RoiSquelette( double x, double y, Direction direction) {
        super(x, y, direction,
                PV_ROI_SQUELETTE,
                ATTAQUE_ROI_SQUELETTE,
                DEFENSE_ROI_SQUELETTE,
                ATTAQUE_SPECIALE_ROI_SQUELETTE,
                DEFENSE_SPECIALE_ROI_SQUELETTE,
                VITESSE_ROI_SQUELETTE,
                new Hitbox(1, 1));
        this.dernierTempsAttaque = System.currentTimeMillis();
        this.positionInitiale = new Position(x, y);
        this.etapeAttaque = 0;
        this.positionMilieu = new Position(x, y);
        this.position5_2 = new Position(x-5, y);
        setPosition(x, y); // Positionnement initial du Roi Squelette
        livreMagique = new LivreMagique();

    }


    @Override
    public void attaque() {
        Epee epee = new Epee();
        epee.utilise(this);
    }

    @Override
    public void lanceUnSort(int numSort) {
        Sortilege sortilege = new SortilegePluitDeFleche();
        sortilege.utilise(this);
    }

    @Override
    public void agir() {
        // Vérifie si le joueur a été détecté
        if (!joueurDetecte) {

            if (detecteJoueurDansRayon(distanceDetection)) {
                joueurDetecte = true;
                setSeDeplace(true);
            } else {
                return;
            }
        }
        if (Monde.getInstance().estDansRayon(getPosition(), 2)){
            attaque();
        }

        long tempsActuel = System.currentTimeMillis();
        if (tempsActuel - dernierTempsAttaque >= delaiAttaque)
        {

            switch (etapeAttaque) {
                case 0:
                    seDeplacerVers(positionMilieu);
                    if (positionAtteinte(positionMilieu)) {
                        etapeAttaque++;
                    }
                    break;
                case 1:
                    seDeplacerVers(positionMilieu);
                    if (positionAtteinte(positionMilieu)) {
                        invoquerSquelettes();
                        etapeAttaque++;
                    }
                    break;
                case 2:
                    seDeplacerVers(position5_2);
                    if (positionAtteinte(position5_2)) {
                        etapeAttaque = 0; // Recommencer le cycle
                    }
                    break;
            }
            dernierTempsAttaque = tempsActuel;
        }
    }




    // Détecte si le joueur est dans un certain rayon autour du Roi Squelette
    private boolean detecteJoueurDansRayon(double rayon) {
        Position positionJoueur = Monde.getInstance().getJoueur().getPosition();
        double distance = Math.sqrt(Math.pow(positionJoueur.getX() - getPosition().getX(), 2) +
                Math.pow(positionJoueur.getY() - getPosition().getY(), 2));
        return distance <= rayon;
    }


    // Invoque des squelettes pour aider le Roi Squelette
    private void invoquerSquelettes()
    {

        Position positionHaut = new Position(getPosition().getX(), getPosition().getY()-2);
        Position positionBas = new Position(getPosition().getX(), getPosition().getY()+2);
        Squelette squeletteGauche = new Squelette(positionHaut.getX(), positionHaut.getY(), Direction.BAS, new Hitbox(0.5, 0.5), Monde.getInstance().getJoueur(), new Aetoile());
        Squelette squeletteDroite = new Squelette(positionBas.getX(), positionBas.getY(), Direction.BAS, new Hitbox(0.5, 0.5), Monde.getInstance().getJoueur(), new Aetoile());
        Monde.getInstance().ajoutActeur(squeletteGauche);
        Monde.getInstance().ajoutActeur(squeletteDroite);
        new Potion().utilise(this);
        new Potion().utilise(this);
        new Potion().utilise(this);
    }

    // Déplace le Roi Squelette vers une destination donnée
    private void seDeplacerVers(Position destination)
    {
        double deltaX = destination.getX() - getPosition().getX();
        double deltaY = destination.getY() - getPosition().getY();

        if (Math.abs(deltaX) > Math.abs(deltaY)) {
            setDirection(deltaX > 0 ? Direction.DROITE : Direction.GAUCHE);
        } else {
            setDirection(deltaY > 0 ? Direction.BAS : Direction.HAUT);
        }
            seDeplace(1);
    }

    // Vérifie si le Roi Squelette a atteint une certaine position
    private boolean positionAtteinte(Position position) {return Math.abs(getPosition().getX() - position.getX()) < 0.1 && Math.abs(getPosition().getY() - position.getY()) < 0.1;}


    @Override
    public String typeActeur() {
        return "roisquelette";
    }

    @Override
    public void derniereAction() {TypeCompetence.COURIR.getCompetence().monterDeNiveau(Monde.getInstance().getJoueur());}

    @Override
    public boolean estUnEnemie() {
        return true;
    }




}
