package intervalle;

/**
 * Dans la classe Intervalle, nous créons un intervalle qui va contenir les dates de début et de fin d'un évènement.
 *
 * @author julien choukroun
 * @author jessica gourdon
 *
 */
class Intervalle {
    // Valeur de début de l'intervalle.
    private Integer debut;
    // Valeur de fin de l'intervalle.
    private Integer fin;

    /**
     * Constructeur par défaut : crée intervalle vide.
     */
    Intervalle(){
        this.debut = null;
        this.fin = null;
    }

    /**
     * Crée un intervalle.
     *
     * @param debut Valeur de début de l'intervalle.
     * @param fin Valeur de fin de l'intervalle.
     */
    Intervalle(int debut, int fin){
        this.debut = debut;
        this.fin = fin;
    }

    /**
     * Vérifie si l'intervalle intersecte un autre intervalle.
     *
     * @param interTest Intervalle à tester.
     * @return true si l'intervalle intersecte un autre intervalle.
     */
    boolean rechercheValeurIntervalle(Intervalle interTest){
        boolean rep = false;
        for(int t = interTest.getDebut(); t < interTest.getFin()+1; t++){
            for (int i = debut; i < fin+1; i++){
                if(i == t) {
                    rep = true;
                }
            }
        }
        return rep;
    }

    /**
     * Retourne la valeur du début de l'intervalle.
     * @return la valeur du début de l'intervalle.
     */
    Integer getDebut(){
        return debut;
    }

    /**
     * Retourne la valeur de fin de l'intervalle.
     * @return la valeur de fin de l'intervalle.
     */
    Integer getFin(){
        return fin;
    }

    /**
     * Vérifie si l'intervalle est vide.
     *
     * @return true si l'intervalle est vide.
     */
    boolean intervalleNull(){
        if(this.getDebut()==null && this.getFin()==null){
            return true;
        }
        else return false;
    }

    /**
     * Renvoie l'intervalle.
     */
    String intervalleToString(){
        String s = " [ " + debut + " ; " + fin + " ] ";
        return s;
    }

}
