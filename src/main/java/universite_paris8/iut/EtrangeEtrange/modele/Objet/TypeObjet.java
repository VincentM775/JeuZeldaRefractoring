package universite_paris8.iut.EtrangeEtrange.modele.Objet;

import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.ElementStockable;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeMagique.LivreMagique;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.Epee;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.Arc;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.EpeeLourde;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Soins.Potion;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Projectile.Fleche;

public enum TypeObjet
{
    EPEE,
    ARC,
    LIVRE_MAGIQUE,


    FLECHE,

    POTION;




    public static ElementStockable nouvelleInstance(TypeObjet typeObjet)
    {
        ElementStockable objet = null;

        switch (typeObjet)
        {
            case EPEE -> objet = new EpeeLourde();
            case ARC -> objet = new Arc();
            case LIVRE_MAGIQUE -> objet = new LivreMagique();
            case POTION -> objet = new Potion();
            case FLECHE -> objet = new Fleche();
        }


        return objet;
    }
}
