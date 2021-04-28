package org.corso;




import org.corso.exceptions.MagazineNotFoundException;
import org.corso.models.Customer;
import org.corso.models.Magazine;
import org.corso.services.SubsctiptionAgent;

import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.util.*;

public class Client {

    public static void main(String[] args) {

        Set<Magazine> riviste = buildMagazines();
        SubsctiptionAgent giornalaio = new SubsctiptionAgent(riviste);
        List<Customer> clienti = new ArrayList<>(buildCustomers());

        buildSubscriptions(clienti, giornalaio, new ArrayList(riviste));

        printMagazinePubblications(riviste);
        giornalaio.printSubscriptions(clienti);
    }

    private static void printMagazinePubblications(Set<Magazine> riviste) {
        for(Magazine magazine: riviste) {
            System.out.print(magazine.getTitle() + " esce");
            String conj = " ";
            for(DayOfWeek d: magazine.getDaysOfWeekPubblications()) {
                System.out.print(conj + d.getDisplayName(TextStyle.FULL, Locale.ITALIAN));
                conj = ", ";
            }
            System.out.println();
        }
    }


    /**
     * crea le riviste
     *
     * @return
     */
    private static Set<Magazine> buildMagazines() {
        Set<Magazine> riviste = new HashSet<>();
        riviste.add(new Magazine("Chi", new HashSet<>(Arrays.asList(DayOfWeek.MONDAY, DayOfWeek.THURSDAY))));
        riviste.add(new Magazine("Eva", new HashSet<>(Arrays.asList(DayOfWeek.THURSDAY))));
        riviste.add(new Magazine("Novella2000", new HashSet<>(Arrays.asList(DayOfWeek.WEDNESDAY, DayOfWeek.FRIDAY))));
        riviste.add(new Magazine("MotoGP", new HashSet<>(Arrays.asList(DayOfWeek.THURSDAY, DayOfWeek.SATURDAY))));
        riviste.add(new Magazine("F1", new HashSet<>(Arrays.asList(DayOfWeek.SUNDAY))));
        return riviste;
    }


    /**
     * Crea i clienti
     *
     * @return
     */
    private static List<Customer> buildCustomers() {
        List<Customer> clienti = new ArrayList<>();
        clienti.add(new Customer("Remo Candeli", "CNDRME"));
        clienti.add(new Customer("Isaac Newton", "NNILLZ"));
        clienti.add(new Customer("Niels Bhor", "MRCNT"));
        clienti.add(new Customer("Karl Heisemberg", "JFJFJDJD"));
        clienti.add(new Customer("Marie Curie", "EMDCJJ"));
        return clienti;
    }


    /**
     * crea gli abbonamenti ai vari clienti
     *
     * @param clienti
     * @param giornalaio
     * @param riviste
     */
    private static void buildSubscriptions(List<Customer> clienti, SubsctiptionAgent giornalaio, List<Magazine> riviste) {

        /** creiamo abbonamento per due clienti ***/
        try {
            Magazine chi = giornalaio.findMagazineByName("Chi");
            giornalaio.addSubscriptions(chi, new HashSet<Customer>(Arrays.asList(clienti.get(0), clienti.get(1))));

            /** creiamo abbonamento per tre clienti ***/
            Magazine eva = giornalaio.findMagazineByName("Eva");
            giornalaio.addSubscriptions(eva, new HashSet<Customer>(Arrays.asList(clienti.get(0), clienti.get(1), clienti.get(2))));

            /** creiamo abbonamento per 1 clienti ***/
            Magazine novella2000 = giornalaio.findMagazineByName("Novella2000");
            giornalaio.addSubscriptions(novella2000, new HashSet<Customer>(Arrays.asList(clienti.get(3))));

            /** creiamo abbonamento per 1 client1 ***/
            Magazine motoGP = giornalaio.findMagazineByName("MotoGP");
            giornalaio.addSubscriptions(motoGP, new HashSet<Customer>(Arrays.asList(clienti.get(4))));

            /** creiamo abbonamento per 1 client2 ***/
            Magazine f1 = giornalaio.findMagazineByName("F1");
            giornalaio.addSubscriptions(f1, new HashSet<Customer>(Arrays.asList(clienti.get(4))));

        } catch (MagazineNotFoundException e) {
            e.printStackTrace();
        }

    }


}
