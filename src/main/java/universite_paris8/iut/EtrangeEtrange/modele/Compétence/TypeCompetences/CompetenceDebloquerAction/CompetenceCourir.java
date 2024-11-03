package universite_paris8.iut.EtrangeEtrange.modele.Compétence.TypeCompetences.CompetenceDebloquerAction;

import universite_paris8.iut.EtrangeEtrange.modele.Compétence.TypeCompetences.Competence;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.Joueur;


public class CompetenceCourir extends Competence
{
    @Override
    public int niveauMax() {
        return 1;
    }
    @Override
    public void monterDeNiveau(Joueur joueur) {
        if (niveauCompetence < niveauMax())
            niveauCompetence++;
    }
}
