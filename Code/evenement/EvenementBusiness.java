package evenement;

/**
 * Dans la classe EvenementBusiness, nous créons un évènement de type business.
 *
 * @author julien choukroun
 * @author jessica gourdon
 *
 */
public class EvenementBusiness extends Evenement {
    // L'évènement est du type business.
    private String type = "Business";
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

    /**
     * Crée un évènement de type business.
     *
     * @param titre Le titre de l'évènement.
     * @param dateDebut Date du début de l'évènement.
     * @param dateFin Date de fin de l'évènement.
     * @param lieu Lieu de l'évènement.
     */
    public EvenementBusiness(String titre, int dateDebut, int dateFin, String lieu){
        super(titre, dateDebut, dateFin, lieu);
        setReference();
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
        res += nombreEnDate(getDateDebutInt());
        res += "-";
        res += nombreEnDate(getDateFinInt());
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

}
