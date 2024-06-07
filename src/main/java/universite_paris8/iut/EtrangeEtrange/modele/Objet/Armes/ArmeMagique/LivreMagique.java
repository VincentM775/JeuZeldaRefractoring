package universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeMagique;

import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Utilisable;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.Arme;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeMagique.Sort.Sortilege;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeMagique.Sort.Attaque.SortilegePluitDeFleche;
import universite_paris8.iut.EtrangeEtrange.modele.ParametreActionSurObjet.ParametreAction;
import universite_paris8.iut.EtrangeEtrange.modele.ParametreActionSurObjet.ParametreAttaque.ParametreLivreMagique.ParametreLivreMagique;


import java.util.ArrayList;

public  class LivreMagique implements Arme
{
    private ArrayList<Sortilege> sortileges;
    private final int sortMaximum = 3;




    public LivreMagique()
    {
        this.sortileges = new ArrayList<>();
        this.sortileges.add(new SortilegePluitDeFleche());
    }

    @Override
    public String getNom() {
        return "Livre magique";
    }


    @Override
    public void utilise(ParametreAction param)
    {
        if (param instanceof ParametreLivreMagique parametre)
        {
            int numSort = parametre.getNumSort();

            if (numSort < sortileges.size())
            {
                Sortilege sortilege = this.sortileges.get(numSort);
                sortilege.action(parametre.getOrigineAction());
            }
        }
    }



    public void ajoutSortilege(Sortilege sortilege)
    {
        if (sortileges.size() +1 < sortMaximum)
            sortileges.add(sortilege);
    }

    @Override
    public int stackMax() {
        return 1;
    }
}
