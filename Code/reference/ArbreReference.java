package reference;

import evenement.Evenement;
import intervalle.ABRIntervalle;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Dans la classe ArbreReference, nous créons un arbre de recherche qui va nous servir à rechercher les évènements par référence.
 *
 * @author julien choukroun
 * @author jessica gourdon
 *
 */
public class ArbreReference {
    // L'élément.
    private String racine;
    // Le sous-arbre gauche.
    private ArbreReference gauche;
    // Le sous-arbre droit.
    private ArbreReference droite;
    // HashMap avec comme clés les références (en String) et comme valeurs les évènements.
    private static HashMap<String, Evenement> tableau = new HashMap<>();
    // Un évènement.
    private static Evenement evATrouver=null;
    // ArrayList d'évènements.
    private static ArrayList<Evenement> liste=new ArrayList<Evenement>();

    /**
     * Constructeur par défaut : arbre de recherche vide.
     */
    public ArbreReference() {
        this.racine = null;
        this.gauche = null;
        this.droite = null;
    }

    /**
     * Construit un arbre de racine i, de sous-arbre gauche et droit nuls.
     *
     * @param e Un évènement.
     */
    public ArbreReference(Evenement e) {
        String r = e.getReference();
        tableau.put(r, e);
        this.racine = r;
        this.gauche = null;
        this.droite = null;
    }

    /**
     * Construit un arbre de racine i, de sous-arbre gauche g et de sous-arbre droit d.
     *
     * @param e Un évènement.
     * @param g ArbreReference.
     * @param d ArbreReference.
     */
    private ArbreReference(Evenement e, ArbreReference g, ArbreReference d) {
        String r = e.getReference();
        tableau.put(r, e);
        this.racine = r;
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
        String s = "\nVALEUR : " + this.racine+" ";
        if (this.gauche!=null)
            s = s + "GAUCHE : " + this.gauche+ " ";
        if (this.droite!=null)
            s = s + "DROITE : " + this.droite+ " ";
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
            String r = e.getReference();
            tableau.put(r, e);
            this.racine = r;
        }
        else {
            if (this.racine.compareTo(e.getReference())>0) {
                /* Si la référence de gauche est alphabétiquement inférieur à la racine et que l'arbre de recherche ne possède pas de sous arbre gauche,
                alors on crée un sous arbre gauche ayant comme racine notre référence associée à l'évènement e mais ne possèdant pas de sous arbre.
                */
                if (this.gauche == null) {
                    this.gauche = new ArbreReference(e);
                } else {
                    // Dans le cas où l'arbre de recherche possède un sous arbre gauche on applique la méthode sur ce sous arbre.
                    this.gauche.ajouter(e);
                }
            }
            else {
                if (this.racine.compareTo(e.getReference())<0) {
                    /* Si la référence de gauche est alphabétiquement supérieur à la racine et que l'arbre de recherche ne possède pas de sous arbre droit,
                    alors on crée un sous arbre droit ayant comme racine notre référence associée à l'évènement e mais ne possèdant pas de sous arbre.
                    */
                    if (this.droite == null){
                        this.droite = new ArbreReference(e);
                    }
                    else {
                        // Dans le cas où l'arbre de recherche possède un sous arbre droit on applique la méthode sur ce sous arbre.
                        this.droite.ajouter(e);
                    }
                }
                // Cas où la référence de gauche est alphabétiquement égale à la référence de gauche de la racine.
                // Par défaut on décide de l'ajouter à gauche.
                else {
                    /* Si l'arbre de recherche ne possède pas de sous arbre gauche,
                    alors on crée un sous arbre gauche ayant comme racine notre référence associée à l'évènement e mais ne possèdant pas de sous arbre.
                     */
                    if (this.gauche == null) {
                        this.gauche = new ArbreReference(e);
                    } else {
                        // Dans le cas où l'arbre de recherche possède un sous arbre gauche on applique la méthode sur ce sous arbre.
                        this.gauche.ajouter(e);
                    }
                }
            }
        }
    }

    /**
     * Recherche si la référence correspond à une référence de l'arbre de recherche.
     * Si c'est le cas, on associe l'évènement correspondant à la référence.
     * Utilise la méthode reference pour savoir si la référence est contenue dans le HashMap.
     *
     * @param refATrouver Référence à trouver dans l'arbre de recherche.
     */
    public void rechercheParRef(String refATrouver){
        if (racine.equals(refATrouver)) {
            evATrouver = reference(racine);
        }
        if (this.gauche != null){
            this.gauche.rechercheParRef(refATrouver);
        }
        if (droite != null) {
            droite.rechercheParRef(refATrouver);
        }
    }

    /**
     * Vérifie si la référence est contenu dans le HashMap.
     *
     * @param ref Référence.
     * @return l'évènement correspondant à la référence.
     */
    public Evenement reference(String ref){
        Evenement ev = null;
        if(tableau.containsKey(ref)){
            ev = tableau.get(ref);
        }
        return ev;
    }

    /**
     * Recherche si la référence entrée par l'utilisateur correspond à une référence de l'arbre de recherche.
     * Si c'est le cas, affiche l'évènement correspondant.
     *
     * @param refATrouver Référence saisie par l'utilisateur.
     */
    public void rechercheEvenementRef(String refATrouver){
        rechercheParRef(refATrouver);
        if(evATrouver != null) {
            System.out.println(evATrouver.getTitre());
        }
        else System.out.println("Aucun évènement ne correspond");
        evATrouver = null;
    }

    /**
     * Recherche si le type correspond à un type de l'arbre de recherche.
     * Si c'est le cas, on associe l'évènement correspondant au type.
     * Pour cela, on va comparer les deux premières lettres du type avec les deux dernières lettres des références.
     * En effet, les deux dernières lettres des références sont les deux premières lettres du type.
     * Utilise la méthode reference pour savoir si la référence est contenue dans le HashMap.
     *
     * @param typeATrouver Type à trouver.
     */
    public void rechercheParType(String typeATrouver){
        String cherche = "";
        cherche += typeATrouver.charAt(0);
        cherche = cherche.toUpperCase();
        cherche += typeATrouver.charAt(1);

        String initialType = "";
        initialType += racine.charAt(racine.length()-2);
        initialType += racine.charAt(racine.length()-1);

        if (initialType.equals(cherche)) {
            Evenement e = reference(racine);
            this.liste.add(e);
        }
        if (gauche != null){
            gauche.rechercheParType(typeATrouver);
        }
        if (droite != null) {
            droite.rechercheParType(typeATrouver);
        }
    }

    /**
     * Affiche les évènements qui sont dans l'ArrayList.
     *
     * @param ev L'ArrayList des évènements.
     */
    public String afficherEvenement(ArrayList<Evenement> ev){
        String z = "";
        for (int i = 0; i < ev.size(); i++){
            z += ev.get(i).getTitre() + "\n";
        }
        if(z.equals("")){
            return "Aucun évènement ne correspond";
        }
        else {
            return z;
        }
    }

    /**
     * Recherche si le type entré par l'utilisateur correspond à un type de l'arbre de recherche.
     * Si c'est le cas, affiche l'évènement correspondant.
     *
     * @param type Type saisie par l'utilisateur.
     */
    public void rechercheEvenementType(String type){
        rechercheParType(type);
        System.out.println(afficherEvenement(liste));
        liste.clear();
    }

    /**
     * Recherche si le type et la date entrés par l'utilisateur correspondent à un type de l'arbre de recherche de référence,
     * ainsi qu'à un intervalle de l'arbre de recherche par date ABRIntervalle.
     * Si c'est le cas, affiche l'évènement correspondant.
     * On effectue d'abord la recherche par type sur l'arbre de recherche.
     * Si des évènements sont trouvés, on crée un arbre de type ABRIntervalle et on y stocke les évènements contenus dans l’ArrayList liste.
     * Enfin, on effectue la recherche par date.
     *
     * @param type Type saisie par l'utilisateur.
     * @param DateDebut Date de début saisie par l'utilisateur.
     * @param DateFin Date de fin saisie par l'utilisateur.
     */
    public void rechercheEvenementTypeDate(String type, int DateDebut, int DateFin){
        rechercheParType(type);
        ABRIntervalle abrdate = new ABRIntervalle();
        if(liste.size() == 0){
            System.out.println("Aucun évènement ne correspond");
        }
        else {
            for (int i = 0; i < liste.size(); i++) {
                abrdate.ajouter(liste.get(i));
            }
            abrdate.rechercheEvenementInt(DateDebut, DateFin);
            liste.clear();
        }
    }

}