package utilisateur;

import evenement.*;
import intervalle.ABRIntervalle;
import reference.ArbreReference;

import java.util.Scanner;

import static evenement.Evenement.dateEnNombre;

/**
 * Dans la classe Utilisateur, nous appelons toutes nos autres classes et nous nous servons des Scanners pour créer une plateforme d’utilisation de notre projet.
 *
 * @author julien choukroun
 * @author jessica gourdon
 *
 */
public class Utilisateur {
    Scanner sc = new Scanner(System.in);

    EvenementMusical plages = new EvenementMusical("Les Plages Electroniques 2020",221,223,"Palais des Festivals, Cannes",false,50);
    EvenementMusical nrj = new EvenementMusical("NRJ Music Awards 2020",312,312,"Palais des Festivals, Cannes",true,200);
    EvenementMusical bach = new EvenementMusical("Soirée Anniversaire de Bach",81,81,"Eglise Notre Dame de Bon Voyage, Cannes",false);
    EvenementCinematographique fif = new EvenementCinematographique("Festival International du Film",133,144,"Palais des Festivals, Cannes",true,false);
    EvenementCinematographique series = new EvenementCinematographique("CannesSeries",222,227,"Palais des Festivals, Cannes",true,true);
    EvenementBusiness mipim = new EvenementBusiness("MIPIM : marché international des professionnels de l'immobilier",154,157,"Palais des Festivals, Cannes");
    EvenementBusiness security = new EvenementBusiness("It & It Security Meeting",182,184,"Palais des Festivals, Cannes");
    EvenementBusiness mapic = new EvenementBusiness("MAPIC : marché international de l'implantation commerciale et de la distribution",322,324,"Palais des Festivals, Cannes");
    EvenementBusiness finance = new EvenementBusiness("Finance and RH Meeting",330,331,"Palais des Festivals, Cannes");
    EvenementCulturel planete = new EvenementCulturel("Exposition - La lune et les satellites des planètes",97,101,"Espace Miramar, Cannes",false);
    EvenementCulturel korbas = new EvenementCulturel("Exposition Peinture - L'oeuvre du peintre Korbas",92,102,"Maison des Associations Broussailles, Cannes",false);
    EvenementGastronomique salonVin = new EvenementGastronomique("Salon du Vin et de la Gastronomie",87,89,"Gare maritime du Vieux Port, Cannes",10);
    EvenementGastronomique cuisineCannoise = new EvenementGastronomique("Cuisine Cannoise en Fête",95,104,"Hotel Martinez, Cannes",20);

    ABRIntervalle abrinter = new ABRIntervalle();
    ArbreReference abrref = new ArbreReference();

    /**
     * Crée un utilisateur.
     */
    public Utilisateur(){
        ArbreDat();
        ArbreRef();
        menu();
    }

    /**
     * Crée un l'arbre de recherche par date.
     */
    public void ArbreDat(){
        abrinter.ajouter(plages);
        abrinter.ajouter(nrj);
        abrinter.ajouter(bach);
        abrinter.ajouter(fif);
        abrinter.ajouter(series);
        abrinter.ajouter(mipim);
        abrinter.ajouter(security);
        abrinter.ajouter(mapic);
        abrinter.ajouter(finance);
        abrinter.ajouter(planete);
        abrinter.ajouter(korbas);
        abrinter.ajouter(salonVin);
        abrinter.ajouter(cuisineCannoise);
    }

    /**
     * Crée un l'arbre de recherche par référence.
     */
    public void ArbreRef(){
        abrref.ajouter(plages);
        abrref.ajouter(nrj);
        abrref.ajouter(bach);
        abrref.ajouter(fif);
        abrref.ajouter(series);
        abrref.ajouter(mipim);
        abrref.ajouter(security);
        abrref.ajouter(mapic);
        abrref.ajouter(finance);
        abrref.ajouter(planete);
        abrref.ajouter(korbas);
        abrref.ajouter(salonVin);
        abrref.ajouter(cuisineCannoise);
    }


    /**
     * Menu intéractif avec l'utilisateur grâce aux Scanners.
     */
    public void menu(){
        String choix = "";
        String recommence = "";
        while (!recommence.equals("non")){
            System.out.println("Voulez-vous afficher les évènements par 'date' ou par 'référence' ou par 'type' ou encore par 'combiné' ?");
            choix = sc.next();
            if (choix.equals("date")) {
                System.out.println("Quel est la date d'arrivée ? (entrez une date au format JJ/MM/AAAA) ");
                String arrive = sc.next();
                while (! arrive.matches("\\d{1,2}/\\d{1,2}/202\\d{1}")){ // On donne une date à partir de l'année 2020 pour pouvoir rajouter plus tard des évènements en 2021, 2022, ...
                    System.out.println("Date mal écrite, recommencez : ");
                    System.out.println("Quel est la date d'arrivée ? (entrez une date au format JJ/MM/AAAA) ");
                    arrive = sc.next();
                }
                System.out.println("Quel est la date de départ ? (entrez une date au format JJ/MM/AAAA)");
                String partir = sc.next();
                while (! partir.matches("\\d{1,2}/\\d{1,2}/202\\d{1}")){
                    System.out.println("Date mal écrite, recommencez : ");
                    System.out.println("Quel est la date de départ ? (entrez une date au format JJ/MM/AAAA) ");
                    partir = sc.next();
                }
                System.out.println("Voici tous les évènements durant cette période : ");
                int arriveInt = dateEnNombre(arrive);
                int partirInt = dateEnNombre(partir);
                abrinter.rechercheEvenementInt(arriveInt, partirInt);
            }
            else if (choix.equals("référence")){
                System.out.println("Veuillez entrer une référence : ");
                String reference = sc.next();
                System.out.println("Voici l'évènement à la référence " + reference + " : ");
                abrref.rechercheEvenementRef(reference);
            }
            else if (choix.equals("type")) {
                System.out.println("Veuillez entrer un type ('Business', 'Cinématographique', 'Culturel', 'Gastronomique' ou 'Musical') : ");
                String type = sc.next();
                while (! (type.equals("Business") | type.equals("Cinématographique") | type.equals("Culturel") | type.equals("Gastronomique") | type.equals("Musical"))){
                    System.out.println("Type mal écrit, recommencez : ");
                    System.out.println("Quel est le type ? ('Business', 'Cinématographique', 'Culturel', 'Gastronomique' ou 'Musical') ");
                    type = sc.next();
                }
                System.out.println("Voici les évènements du type " + type + " : ");
                abrref.rechercheEvenementType(type);
            }
            else if (choix.equals("combiné")) {
                System.out.println("Veuillez entrer un type ('Business', 'Cinématographique', 'Culturel', 'Gastronomique' ou 'Musical') :");
                String type = sc.next();
                while (! (type.equals("Business") | type.equals("Cinématographique") | type.equals("Culturel") | type.equals("Gastronomique") | type.equals("Musical"))) {
                    System.out.println("Type mal écrit, recommencez : ");
                    System.out.println("Quel est le type ? ('Business', 'Cinématographique', 'Culturel', 'Gastronomique' ou 'Musical') ");
                    type = sc.next();
                }
                System.out.println("Quel est la date d'arrivée ? (entrez une date au format JJ/MM/AAAA) ");
                String dateDebut = sc.next();
                while (! dateDebut.matches("\\d{1,2}/\\d{1,2}/202\\d{1}")){ // On donne une date à partir de l'année 2020 pour pouvoir rajouter plus tard des évènements en 2021, 2022, ...
                    System.out.println("Date mal écrite, recommencez : ");
                    System.out.println("Quel est la date d'arrivée ? (entrez une date au format JJ/MM/AAAA) ");
                    dateDebut = sc.next();
                }
                System.out.println("Quel est la date de départ ? (entrez une date au format JJ/MM/AAAA)");
                String dateFin = sc.next();
                while (! dateFin.matches("\\d{1,2}/\\d{1,2}/202\\d{1}")){
                    System.out.println("Date mal écrite, recommencez : ");
                    System.out.println("Quel est la date de départ ? (entrez une date au format JJ/MM/AAAA) ");
                    dateFin = sc.next();
                }
                int dateDebutInt = dateEnNombre(dateDebut);
                int dateFinInt = dateEnNombre(dateFin);
                System.out.println("Voici tous les évènements du type " + type + " durant cette période : ");
                abrref.rechercheEvenementTypeDate(type, dateDebutInt, dateFinInt);
            }
            else {
                System.out.println("Veuillez écrire 'date' ou 'référence' ou 'type' ou 'combiné");
            }
            System.out.println("Voulez-vous faire une autre recherche ? (oui/non)");
            recommence = sc.next();
        }
    }

}
