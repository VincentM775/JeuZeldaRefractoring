package universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeMagique;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.Entite;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Arme;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Rechargeable;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeMagique.Sort.Sortilege;
import universite_paris8.iut.EtrangeEtrange.modele.Parametres.ConstanteObjet;


import java.util.ArrayList;

public  class LivreMagique implements Arme, Rechargeable
{

    private static final Sortilege SORTILEGE1 = ConstanteObjet.SORTILEGE1_LIVRE_MAGIQUE;
    private static final Sortilege SORTILEGE2 = ConstanteObjet.SORTILEGE2_LIVRE_MAGIQUE;
    private static final Sortilege SORTILEGE3 = ConstanteObjet.SORTILEGE3_LIVRE_SOIN;
    private static final int NOMBRE_SORT_MAXIMUM = ConstanteObjet.SORT_MAXIMUM_LIVRE_MAGIQUE;
    private static final int PRIX_ACHAT = ConstanteObjet.PRIX_ACHAT_LIVRE_MAGIQUE;
    private static final int STACK_MAX = ConstanteObjet.STACK_MAX_LIVRE_MAGIQUE;
    private static final int DURABILITEE = ConstanteObjet.DURABILITE_LIVRE_MAGIQUE;
    private ArrayList<Sortilege> sortileges;
    private long derniereApelle;
    private boolean peutTirer;

    public LivreMagique()
    {
        this.sortileges = new ArrayList<>();
        this.sortileges.add(SORTILEGE1);
        this.sortileges.add(SORTILEGE2);
        this.sortileges.add(SORTILEGE3);


        this.peutTirer = true;
        this.derniereApelle = 0;
    }


    @Override
    public void utilise(Entite entite)
    {
        if (peutTirer)
        {
            Sortilege sortilege = this.sortileges.get(0);
            sortilege.utilise(entite);
            this.derniereApelle = System.currentTimeMillis();
            entite.getMonde().ajoutRechargeable(this);
            peutTirer = false;
        }
    }

    public void ajoutSortilege(Sortilege sortilege)
    {
        if (sortileges.size()+1 < NOMBRE_SORT_MAXIMUM)
            sortileges.add(sortilege);
    }

    public Sortilege getSortilege(int num)
    {
        Sortilege sortilege = null;

        if (num >= 0 && num < sortileges.size())
            sortilege = sortileges.get(num);

        return sortilege;
    }



    @Override
    public String getNom() {
        return "livremagique";
    }
    @Override
    public int stackMax() {
        return STACK_MAX;
    }
    @Override
    public double durabilitee() { return DURABILITEE; }
    @Override
    public int prixAchat() {
        return PRIX_ACHAT;
    }
    @Override
    public long delaie() {
        return SORTILEGE1.delaie();
    }

    @Override
    public boolean cooldown() {
        boolean actionFait = false;
        long apelle = System.currentTimeMillis();

        if (apelle - derniereApelle >= delaie())
        {
            System.out.println("refait");
            this.derniereApelle = 0;
            this.peutTirer = true;
            actionFait = true;
        }
        return actionFait;
    }
}
