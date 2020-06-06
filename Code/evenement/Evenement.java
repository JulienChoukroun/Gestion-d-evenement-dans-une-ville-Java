package evenement;

import java.time.LocalDate;
import java.time.Month;

/**
 * Dans la classe Evenement, nous créons un évènement.
 *
 * @author julien choukroun
 * @author jessica gourdon
 *
 */
public abstract class Evenement {
    // Titre de l'évènement.
    private String titre;
    // Référence de l'évènement.
    protected String reference;
    // Date du début de l'évènement.
    private int dateDebut;
    // Date de fin de l'évènement, si l'évènement ne dure qu'une journée elle sera la même que la date de début.
    private int dateFin;
    // Lieu de l'évènement.
    private String lieu;
    // Type de l'évènement (Musical, Culturel, ...).
    protected String type;

    /**
     * Crée un évènement.
     *
     * @param titre Le titre de l'évènement.
     * @param dateDebut Date du début de l'évènement.
     * @param dateFin Date de fin de l'évènement.
     * @param lieu Lieu de l'évènement.
     */
    public Evenement(String titre, int dateDebut, int dateFin, String lieu ){
        this.titre = titre;
        this.lieu = lieu;
        validiteDateDebut(dateDebut);
        validiteDateFin(dateDebut, dateFin);
    }

    /**
     * Vérifie si la date de début est bien comprise entre 1 et 365.
     *
     * @param dateAVerifier La date à vérifier.
     */
    public void validiteDateDebut(int dateAVerifier){
        if(dateAVerifier>0 && dateAVerifier<366){
            this.dateDebut = dateAVerifier;
        }
        else{
            System.err.println("Veuillez entrer un entier compris entre 1 et 365.");
        }
    }

    /**
     * Vérifie si la date de fin est bien comprise entre 1 et 365.
     * La date de fin doit être après la date de début ou être égale à celle-ci.
     *
     * @param dateDebut La date du début.
     * @param dateAVerifierFin La date à vérifier.
     */
    public void validiteDateFin(int dateDebut, int dateAVerifierFin){
        if (dateAVerifierFin>0 && dateAVerifierFin<366 && dateAVerifierFin>=dateDebut){
            this.dateFin = dateAVerifierFin;
        }
        else {
            System.err.println("Veuillez entrer un entier compris entre 1 et 365 qui soit supérieur ou égal à la date de début.");
        }
    }

    /**
     * Retourne le titre de l'évènement.
     * @return le titre de l'évènement.
     */
    public String getTitre(){
        return titre;
    }

    /**
     * Retourne la date de début de l'évènement.
     * @return la date de début de l'évènement.
     */
    public int getDateDebutInt(){
        return dateDebut;
    }

    /**
     * Retourne la date de fin de l'évènement.
     * @return la date de fin de l'évènement.
     */
    public int getDateFinInt(){
        return dateFin;
    }

    /**
     * Retourne le lieu de l'évènement.
     * @return le lieu de l'évènement.
     */
    public String getLieu(){
        return lieu;
    }

    /**
     * Retourne le type de l'évènement.
     * @return le type de l'évènement.
     */
    public abstract String getType();

    /**
     * Retourne la référence de l'évènement.
     * @return la référence de l'évènement.
     */
    public abstract String getReference();

    /**
     * Converti une date (String) en nombre (int).
     *
     * @param dateEntree La date à convertir.
     * @return le jour (en int) correspondant à la date entrée.
     */
    public static int dateEnNombre(String dateEntree){
        // Le tableau éléments contient les 3 composantes de la date sous forme de String.
        String elements[] = dateEntree.split("/");
        int jour = Integer.parseInt(elements[0]);
        int month = Integer.parseInt(elements[1]);
        // Convertit un objet int entre 1 et 12 en un objet Month (format Month.JANUARY par exemple).
        Month mois = Month.of(month);
        int an = Integer.parseInt(elements[2]);
        // Crée une nouvelle LocalDate avec ces données.
        LocalDate nowDay = LocalDate.of(an,mois,jour);
        // Récupère le nombre de jour s'étant déroulé depuis le début de l'année.
        int f= nowDay.getDayOfYear();
        return f;
    }

    /**
     * Converti un nombre (int) en date (String).
     *
     * @param nombreEntre Le nombre à convertir.
     * @return la date (en String) correspondant au nombre entré.
     */
    public static String nombreEnDate(int nombreEntre){
        // 1e jour de l'année 2020, équivaut à l'entier 1.
        LocalDate firstDay = LocalDate.of(2020, Month.JANUARY, 1);
        // On enlève 1 à ce nombre pour effectuer l'addition à partir du jour 1.
        Long nb = Long.valueOf(nombreEntre)-1;
        // On récupère la nouvelle date avec le nombre de jour ajouté au jour 1.
        LocalDate nowDay = firstDay.plusDays(nb);
        // On récupère les composantes de la nouvelle date pour créer la chaine de caractère de sortie.
        int jour = nowDay.getDayOfMonth();
        int mois =nowDay.getMonthValue();
        int an = nowDay.getYear();
        String s = jour + "/" + mois + "/" + an;
        return s;
    }

}
