package intervalle;

import evenement.Evenement;

import java.util.*;

import static evenement.Evenement.nombreEnDate;

/**
 * Dans la classe ABRIntervalle, nous créons un arbre de recherche qui va nous servir à rechercher les évènements par date.
 *
 * @author julien choukroun
 * @author jessica gourdon
 *
 */
public class ABRIntervalle {
    // L'élément.
    private Intervalle racine;
    // Le sous-arbre gauche.
    private ABRIntervalle gauche;
    // Le sous-arbre droit.
    private ABRIntervalle droite;
    // HashMap avec comme clés les intervalles et comme valeurs les évènements.
    private static HashMap<Intervalle, Evenement> tableau = new HashMap<>();
    // ArrayList d'évènements.
    private static ArrayList<Evenement> liste=new ArrayList<Evenement>();

    /**
     * Constructeur par défaut : arbre de recherche vide.
     */
    public ABRIntervalle() {
        this.racine = null;
        this.gauche = null;
        this.droite = null;
    }

    /**
     * Construit un arbre de racine i, de sous-arbre gauche et droit nuls.
     *
     * @param e Un évènement.
     */
    public ABRIntervalle(Evenement e) {
        int d = e.getDateDebutInt();
        int f = e.getDateFinInt();
        Intervalle i = new Intervalle(d, f);
        tableau.put(i, e);
        this.racine = i;
        this.gauche = null;
        this.droite = null;
    }

    /**
     * Construit un arbre de racine i, de sous-arbre gauche g et de sous-arbre droit d.
     *
     * @param e Un évènement.
     * @param g ABRIntervalle.
     * @param d ABRIntervalle.
     */
    private ABRIntervalle(Evenement e, ABRIntervalle g, ABRIntervalle d) {
        int deb = e.getDateDebutInt();
        int fi = e.getDateFinInt();
        Intervalle i = new Intervalle(deb, fi);
        tableau.put(i, e);
        this.racine = i;
        this.gauche = g;
        this.droite = d;
    }

    /**
     * Vérifie si l'arbre de recherche en question est vide.
     * @return true si l'arbre de recherche est vide.
     */
    public boolean vide() {
        if (racine==null && gauche==null && droite==null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Affiche l'arbre si celui-ci est non vide.
     * Pour cela affiche "Arbre vide" si l'arbre en question est vide.
     * Sinon, appelle une autre méthode specialisée toStringAux pour afficher l'arbre.
     */
    public String toString() {
        if (this.vide() == false){
            return this.toStringAux();
        }
        else {
            return "Arbre vide";
        }
    }

    public String toStringAux() {
        String s = "\nVALEUR : " + this.racine.intervalleToString()+ " ";
        if (this.gauche!=null)
            s = s + "GAUCHE : " + this.gauche.racine.intervalleToString() + " ";
        if (this.droite!=null)
            s = s + "DROITE : " + this.droite.racine.intervalleToString() + " ";
        if (this.gauche!=null) s = s + this.gauche.toStringAux();
        if (this.droite!=null) s = s + this.droite.toStringAux();
        return s;
    }

    /**
     * Consequent : this contient e et est un arbre de recherche.
     * Complexité : theta(log(n)) dans le meilleur des cas ie si l'arbre est équilibré, theta(n) dans le pire des cas ie si l'arbre est dégénéré.
     * NB: les clefs sont uniques dans l'arbre, donc si il existe un objet ayant la même clef que e dans l'arbre, alors e n'est pas ajouté.
     *
     * @param e Un évènement.
     */
    public void ajouter(Evenement e) {
        // Si l'arbre de recherche est vide alors la premiere valeur qu'on ajoute sera sa racine.
        if (this.vide() == true){
            int d = e.getDateDebutInt();
            int f = e.getDateFinInt();
            Intervalle i = new Intervalle(d, f);
            tableau.put(i, e);
            this.racine = i;
        }
        else {
            if (this.racine.getDebut() > e.getDateDebutInt()) {
                /* Si l'entier de gauche de l'intervalle est inférieur à celui de la racine et que l'arbre de recherche ne possède pas de sous arbre gauche,
                alors on crée un sous arbre gauche ayant comme racine notre intervalle associé à l'évènement e mais ne possèdant pas de sous arbre.
                */
                if (this.gauche == null) {
                    this.gauche = new ABRIntervalle(e);
                } else {
                    // Dans le cas où l'arbre de recherche possède un sous arbre gauche on applique la méthode sur ce sous arbre.
                    this.gauche.ajouter(e);
                }
            }
            else {
                if (this.racine.getDebut() < e.getDateDebutInt()) {
                    /* Si l'entier de gauche de l'intervalle est supérieur à celui de la racine et que l'arbre de recherche ne possède pas de sous arbre droit,
                    alors on crée un sous arbre droit ayant comme racine notre intervalle associé à l'évènement e mais ne possèdant pas de sous arbre.
                    */
                    if (this.droite == null){
                        this.droite = new ABRIntervalle(e);
                    }
                    else {
                        // Dans le cas où l'arbre de recherche possède un sous arbre droit on applique la méthode sur ce sous arbre.
                        this.droite.ajouter(e);
                    }
                }
                // Cas où l'entier de gauche de l'intervalle est égal à l'entier de gauche de la racine.
                // Par défaut on décide de l'ajouter à gauche.
                else {
                    /* Si l'arbre de recherche ne possède pas de sous arbre gauche,
                    alors on crée un sous arbre gauche ayant comme racine notre intervalle associé à l'évènement e mais ne possèdant pas de sous arbre.
                     */
                    if (this.gauche == null) {
                        this.gauche = new ABRIntervalle(e);
                    } else {
                        // Dans le cas où l'arbre de recherche possède un sous arbre gauche on applique la méthode sur ce sous arbre.
                        this.gauche.ajouter(e);
                    }
                }
            }
        }
    }

    /**
     * Recherche si l'intervalle correspond à un intervalle de l'arbre de recherche.
     * Si c'est le cas, on ajoute l'évènement correspondant à l'intervalle à l'ArrayList.
     * Utilise la méthode chercheHash pour savoir si l'intervalle est contenue dans le HashMap.
     *
     * @param dateATrouverDebut Date de début.
     * @param dateATrouverFin Date de fin.
     */
    public void rechercheParDate(int dateATrouverDebut, int dateATrouverFin){
        Intervalle interATrouver = new Intervalle(dateATrouverDebut, dateATrouverFin);
        if (racine.rechercheValeurIntervalle(interATrouver)) {
            Evenement e = chercheHash(racine);
            this.liste.add(e);
        }
        if (gauche!=null){
            gauche.rechercheParDate(dateATrouverDebut, dateATrouverFin);
        }
        if (droite!=null) {
            droite.rechercheParDate(dateATrouverDebut, dateATrouverFin);
        }
    }

    /**
     * Vérifie si l'intervalle est contenu dans le HashMap.
     *
     * @param inter Intervalle de dates.
     * @return l'évènement correspondant à l'intervalle.
     */
    public Evenement chercheHash(Intervalle inter) {
        Evenement ev = null;
        if (tableau.containsKey(inter)) {
            ev = tableau.get(inter);
        }
        return ev;
    }

    /**
     * Affiche les évènements qui sont dans l'ArrayList.
     *
     * @param ev L'ArrayList des évènements.
     */
    public String afficherEvenement(ArrayList<Evenement> ev){
        String z="";
        for (int i = 0; i < ev.size(); i++){
            z += ev.get(i).getTitre() + "    ( du " + nombreEnDate(ev.get(i).getDateDebutInt()) + " au " + nombreEnDate(ev.get(i).getDateFinInt()) + " )" + "\n";
        }
        if(z.equals("")){
            return "Aucun évènement ne correspond";
        }
        else {
            return z;
        }
    }

    /**
     * Recherche si les dates entrées de début et de fin par l'utilisateur correspondent à un intervalle de l'arbre de recherche.
     * Si c'est le cas, affiche les évènements correspondants.
     *
     * @param DateDebut Date de début saisie par l'utilisateur.
     * @param DateFin Date de fin saisie par l'utilisateur.
     */
    public void rechercheEvenementInt(int DateDebut, int DateFin){
        rechercheParDate(DateDebut, DateFin);
        System.out.println(afficherEvenement(liste));
        liste.clear();
    }

}