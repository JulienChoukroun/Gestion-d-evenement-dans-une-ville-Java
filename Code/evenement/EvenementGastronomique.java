package evenement;

/**
 * Dans la classe EvenementGastronomique, nous créons un évènement de type gastronomique.
 *
 * @author julien choukroun
 * @author jessica gourdon
 *
 */
public class EvenementGastronomique extends Evenement {
    // L'évènement est du type gastronomique.
    private String type = "Gastronomique";
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

    /**
     * Crée un évènement de type gastronomique par défaut.
     * Ici, l'évènement est gratuit par défault.
     *
     * @param titre Le titre de l'évènement.
     * @param dateDebut Date du début de l'évènement.
     * @param dateFin Date de fin de l'évènement.
     * @param lieu Lieu de l'évènement.
     */
    public EvenementGastronomique(String titre, int dateDebut, int dateFin, String lieu){
        super(titre, dateDebut, dateFin, lieu);
        setReference();
        payant = false;
        prix = 0;
    }

    /**
     * Crée un évènement de type gastronomique.
     * Ici, l'évènement est payant.
     *
     * @param titre Le titre de l'évènement.
     * @param dateDebut Date du début de l'évènement.
     * @param dateFin Date de fin de l'évènement.
     * @param lieu Lieu de l'évènement.
     * @param prix Prix de l'entrée.
     */
    public EvenementGastronomique(String titre, int dateDebut, int dateFin, String lieu, int prix){
        super(titre, dateDebut, dateFin, lieu);
        setReference();
        payant = true;
        this.prix = prix;
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

}
