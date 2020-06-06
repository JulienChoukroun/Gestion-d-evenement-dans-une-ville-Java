package evenement;

/**
 * Dans la classe EvenementMusical, nous créons un évènement de type musical.
 *
 * @author julien choukroun
 * @author jessica gourdon
 *
 */
public class EvenementMusical extends Evenement {
    // L'évènement est du type musical.
    private String type = "Musical";
    // Titre de l'évènement.
    private String titre;
    // Référence de l'évènement.
    private String reference;
    // Date du début de l'évènement.
    private int dateDebut;
    // Date de fin de l'évènement, si l'évènement ne dure qu'une journée elle sera la même que la date de début.
    private int dateFin;
    // Lieu de l'évènement.
    private String lieu;
    // L'évènement est-il gratuit ou payant ?
    private boolean payant;
    // Prix de l'entrée si elle est payante.
    private int prix;
    // Indique si les personnes y assistant ont des places attitrées ou non.
    private boolean places;

    /**
     * Crée un évènement de type musical par défaut.
     * Ici, l'évènement est gratuit par défault.
     *
     * @param titre Le titre de l'évènement.
     * @param dateDebut Date du début de l'évènement.
     * @param dateFin Date de fin de l'évènement.
     * @param lieu Lieu de l'évènement.
     * @param places Indique si les personnes y assistant ont des places attitrées ou non.
     */
    public EvenementMusical(String titre, int dateDebut, int dateFin, String lieu, boolean places){
        super(titre, dateDebut, dateFin, lieu);
        setReference();
        payant = false;
        prix = 0;
        this.places = places;
    }

    /**
     * Crée un évènement de type musical.
     * Ici, l'évènement est payant.
     *
     * @param titre Le titre de l'évènement.
     * @param dateDebut Date du début de l'évènement.
     * @param dateFin Date de fin de l'évènement.
     * @param lieu Lieu de l'évènement.
     * @param places Indique si les personnes y assistant ont des places attitrées ou non.
     * @param prix Prix de l'entrée.
     */
    public EvenementMusical(String titre, int dateDebut, int dateFin, String lieu, boolean places, int prix){
        super(titre, dateDebut, dateFin, lieu);
        setReference();
        payant = true;
        this.prix = prix;
        this.places = places;
    }

    /**
     * La référence va être composée des lettres majuscules qui composent le titre de l'évènement,
     * puis des dates de début et de fin de l'évènement
     * et enfin des deux premières lettres du type d'événement.
     * Tous les éléments seront séparées par un tiret.
     */
    public void setReference(){
        String res = "";
        for (int i = 0; i < getTitre().length(); i++) {
            char ch = getTitre().charAt(i);
            if (Character.isUpperCase(ch)) {
                res += ch;
            }
        }
        res += "-";
        res += Integer.toString(getDateDebutInt());
        res += "-";
        res += Integer.toString(getDateFinInt());
        res += "-";
        res += getType().charAt(0);
        res += getType().charAt(1);
        this.reference = res;
    }

    /**
     * Retourne le type de l'évènement.
     * @return le type de l'évènement.
     */
    public String getType(){
        return type;
    }

    /**
     * Retourne la référence de l'évènement.
     * @return la référence de l'évènement.
     */
    public String getReference(){
        return reference;
    }

    /**
     * Retourne le prix de l'évènement.
     * @return le prix de l'évènement.
     */
    public int getPrix(){
        return prix;
    }

    /**
     * Vérifie si les places sont attitrées ou non.
     * @return true si les places sont attitrées.
     */
    public boolean getPlacesAttribuees(){
        return places;
    }

}
