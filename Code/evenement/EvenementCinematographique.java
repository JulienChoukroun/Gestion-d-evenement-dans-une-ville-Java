package evenement;

/**
 * Dans la classe EvenementCinematographique, nous créons un évènement de type cinématographique.
 *
 * @author julien choukroun
 * @author jessica gourdon
 *
 */
public class EvenementCinematographique extends Evenement {
    // L'évènement est du type cinématographique.
    private String type = "Cinematographique";
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
    // L'évènement est-il accessible à tous ou nécessite-t-il d'être invité ?
    private boolean libreAcces;
    // Indique si les personnes y assistant ont des places attitrées ou non.
    private boolean places;

    /**
     * Crée un évènement de type cinématographique.
     *
     * @param titre Le titre de l'évènement.
     * @param dateDebut Date du début de l'évènement.
     * @param dateFin Date de fin de l'évènement.
     * @param lieu Lieu de l'évènement.
     * @param places Indique si les personnes y assistant ont des places attitrées ou non.
     * @param libreAcces L'évènement est-il accessible à tous librement ou nécessite-t-il d'être invité ?
     */
    public EvenementCinematographique(String titre, int dateDebut, int dateFin, String lieu, boolean places, boolean libreAcces){
        super(titre, dateDebut, dateFin, lieu);
        setReference();
        this.places = places;
        this.libreAcces = libreAcces;
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
     * Vérifie si l'évènement est accessible à tous librement ou si il faut y être invité.
     * @return true si l'évènement est accessible à tous librement.
     */
    public boolean getLibreAccesOuPas(){
        return libreAcces;
    }

    /**
     * Vérifie si les places sont attitrées ou non.
     * @return true si les places sont attitrées.
     */
    public boolean getPlacesAttribuees(){
        return places;
    }

}
