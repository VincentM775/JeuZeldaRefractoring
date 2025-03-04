package universite_paris8.iut.EtrangeEtrange.controller;

import javafx.collections.ListChangeListener;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Acteur;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.Personnage.Joueur;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Objet;
import universite_paris8.iut.EtrangeEtrange.modele.Map.Monde;
import universite_paris8.iut.EtrangeEtrange.modele.Parametres.ConstantesAffichage;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;
import universite_paris8.iut.EtrangeEtrange.vues.GestionSon;


public class GestionActeur implements ListChangeListener<Acteur>
{
    private Pane pane;
    private Monde monde;
    private GestionSon gestionSon;

    public GestionActeur(Monde monde, Pane pane, GestionSon gestionSon)
    {
        this.monde = monde;
        this.pane = pane;
        this.gestionSon = gestionSon;
    }

    @Override
    public void onChanged(Change<? extends Acteur> change) {
        while (change.next())
        {
            if (change.wasAdded())
            {
                for (Acteur acteur : change.getAddedSubList())
                {
                    if (acteur.typeActeur().equals("fleche") || acteur.typeActeur().equals("orbe")) {
                        initSpriteProjectile(acteur);
                        gestionSon.lanceSong((Objet) acteur);
                    }

                    else if(acteur.typeActeur().equals("epee")){
                        gestionSon.lanceSong((Objet) acteur);
                    }
                    else if(acteur.typeActeur().equals("bloc")){
                        initSpriteBloc(acteur);
                    }


                    listenerCollision(acteur);
                    listenerPv(acteur);
                }
            } else if (change.wasRemoved()) {
                for (Acteur acteur : change.getRemoved()) {
                    suppSpriteActeur(acteur);
                }
            }
        }
    }

    public void listenerPv(Acteur acteur)
    {
        acteur.getStatsPv().getPvActuelleProperty().addListener((obs, old, nouv)->{
            if (Math.round(nouv.doubleValue()) == 0)
                this.monde.ajoutActeurAsupprimer(acteur);
        });
    }

    public void listenerCollision(Acteur acteur)
    {
        acteur.getPosition().getXProperty().addListener((obs, old, nouv)-> {verifCollision(acteur);});
        acteur.getPosition().getYProperty().addListener((obs, old, nouv)-> {verifCollision(acteur);});
    }

    private void verifCollision(Acteur acteur)
    {
        Joueur joueur = monde.getJoueur();

        if(acteur != joueur && monde.collisionAvecActeur(acteur,joueur))
        {
            acteur.subitCollision(joueur);
        }

        monde.verifCollision(acteur);
    }


    private void suppSpriteActeur(Acteur acteur)
    {
        Node node = pane.lookup("#"+acteur.getID());

        if (node != null)
            this.pane.getChildren().remove(node);
    }

    private void initSpriteProjectile(Acteur acteur)
    {
        String typeActeur = acteur.typeActeur();

        double reglagePositionX;
        double reglagePositionY;

        ImageView imageView = new ImageView(new Image("file:src/main/resources/universite_paris8/iut/EtrangeEtrange/texture/objet/Projectile/"+typeActeur+".png"));


        imageView.setId(acteur.getID()+"");
        Direction direction = acteur.getDirection();

        int rotation;

        if(direction==Direction.HAUT) {
            rotation = 0;
            reglagePositionY = 15;
            reglagePositionX = 5.5;
        }
        else if(direction==Direction.BAS){
            rotation=2;
            reglagePositionY = 15;
            reglagePositionX = 5.5;
        }
        else if(direction==Direction.DROITE) {
            rotation = 1;
            reglagePositionY = 5.5;
            reglagePositionX = 15;
        }
        else
        {
            rotation = 3;
            reglagePositionY = 5.5;
            reglagePositionX = 15;
        }

        imageView.setTranslateX(acteur.getPosition().getX()* ConstantesAffichage.tailleTile-reglagePositionX);
        imageView.setTranslateY(acteur.getPosition().getY()* ConstantesAffichage.tailleTile-reglagePositionY);

        imageView.setRotate(rotation*90);
        this.pane.getChildren().add(imageView);

        acteur.getPosition().getXProperty().addListener((obs, old, nouv)->
        {
            imageView.setTranslateX(acteur.getPosition().getX()* ConstantesAffichage.tailleTile-reglagePositionX);
        });

        acteur.getPosition().getYProperty().addListener((obs, old, nouv)->
        {
            imageView.setTranslateY(acteur.getPosition().getY()* ConstantesAffichage.tailleTile-reglagePositionY);
        });


    }


    public void initSpriteBloc(Acteur acteur){
        ImageView imageView;
        if(acteur.typeActeur()=="bloc"){
            imageView = new ImageView("file:src/main/resources/universite_paris8/iut/EtrangeEtrange/texture/sprite/Bloc/boite.png");
        }
        else{
            imageView = new ImageView();
        }

        imageView.setTranslateX(acteur.getPosition().getX()* ConstantesAffichage.tailleTile-32);
        imageView.setTranslateY(acteur.getPosition().getY()* ConstantesAffichage.tailleTile-32);
        imageView.setId(acteur.getID()+"");
        this.pane.getChildren().add(imageView);

        acteur.getPosition().getXProperty().addListener((obs, old, nouv)->
        {
            imageView.setTranslateX(acteur.getPosition().getX()* ConstantesAffichage.tailleTile-32);
        });

        acteur.getPosition().getYProperty().addListener((obs, old, nouv)->
        {
            imageView.setTranslateY(acteur.getPosition().getY()* ConstantesAffichage.tailleTile-32);
        });
    }





}
