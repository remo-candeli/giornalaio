package org.corso.services;


import org.corso.exceptions.MagazineNeverSubscribedException;
import org.corso.exceptions.MagazineNotFoundException;
import org.corso.models.Customer;
import org.corso.models.Magazine;
import org.corso.models.Subscription;

import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.util.*;

/**
 * SubsctiptionAgent - è la classe che gestisce gli abbonamenti
 *
 *  - contiene le riviste che vende
 *  - contiene gli abbonamenti che vende
 *
 */
public class SubsctiptionAgent {

    private Set<Magazine> magazines;        // riviste possedute dal giornalaio
    private List<Subscription> subscriptions;         //    sottoscrizioni attive


    /**
     * creiamo l´agent con un elenco di Riviste a disposizione
     */
    public SubsctiptionAgent(Set<Magazine> magazines) {
        this.subscriptions = new ArrayList<>();
        this.magazines = magazines;
    }


    /**
     * aggiunge uno o più clienti tra gli abbonati della rivista
     *
     * @param rivista
     * @param clienti
     */
    public void addSubscriptions(Magazine rivista, Set<Customer> clienti) throws MagazineNotFoundException {

        // verifica se la rivista esiste.
        if (findMagazineByName(rivista.getTitle())==null)
            throw new MagazineNotFoundException("rivista "+rivista.getTitle()+" non trovata.");

        // cerca la rivista tra gli abbonamenti
        Subscription subscriptionFounded = findMagazineSubscription(rivista);

        // se la rivista non è tra gli abbonamenti registrati allora la registra
        if (subscriptionFounded==null)
            subscriptionFounded = new Subscription(rivista);

        // sottoscrive l'abbonamento ai clienti che ne hanno fatto richiesta.
        for(Customer cliente: clienti)
            subscriptionFounded.addSubscriber(cliente);

        subscriptions.add(subscriptionFounded);
    }


    /**
     * rimuove uno o più clienti tra gli abbonati della rivista
     *
     *
     * @param rivista
     * @param clienti
     * @throws MagazineNotFoundException
     * @throws MagazineNeverSubscribedException
     */
    public void removeSubscriptions(Magazine rivista, Set<Customer> clienti) throws MagazineNotFoundException, MagazineNeverSubscribedException {

        // verifica se la rivista esiste.
        if (findMagazineByName(rivista.getTitle())==null)
            throw new MagazineNotFoundException("rivista "+rivista.getTitle()+" non trovata.");

        // cerca la rivista tra gli abbonamenti
        Subscription subscriptionFounded = findMagazineSubscription(rivista);

        // se la rivista non è tra gli abbonamenti registrati allora la registra
        if (subscriptionFounded==null)
            throw new MagazineNeverSubscribedException("La rivista " + rivista +" non è mai stata sottoscritta da nessuno!");

        // annulla l'abbonamento ai clienti che ne hanno fatto richiesta.
        for(Customer cliente: clienti)
            subscriptionFounded.removeSubscriber(cliente);

        subscriptions.remove(subscriptionFounded);
    }

    /**
     * Cerca la rivista tra quelle in possesso del giornalaio
     *
     * @param
     * @return
     */
    public Magazine findMagazineByName(String nomeRivista) {
        Magazine rivistaSet;
        for(Magazine r: magazines) {
            if (r.getTitle().equals(nomeRivista)) {
                return r;
            }
        }
        return null;
    }


    /**
     * Cerca un abbonamento alla rivista richiesta.
     *
     * @param rivista
     * @return
     */
    private Subscription findMagazineSubscription(Magazine rivista) {
        // cerca la rivista tra gli abbonamenti
        Subscription subscriptionFounded = null;
        for(Subscription subscription: subscriptions) {
            if (subscription.getMagazine().equals(rivista)) {
                subscriptionFounded=subscription;
            }
        }
        return subscriptionFounded;
    }


    /**
     * Stampa gli abbonamenti per giorno della settimana
     *
     * @param clienti
     */
    public void printSubscriptions(List<Customer> clienti) {

        // parte dal ciclo dei gg della settimana
        for(DayOfWeek giornoSettimana: DayOfWeek.values()) {
            System.out.println("\nGiorno "+giornoSettimana.getDisplayName(TextStyle.SHORT, Locale.ITALIAN));
            boolean foundShipping = false;

            // per ogni rivista...
            for (Magazine rivista : magazines) {

                // verifica se esce in quel giorno della settimana
                if (verificaSePubblicare(giornoSettimana, rivista.getDaysOfWeekPubblications())) {

                    // cicla tutti i clienti del giornalaio
                    for(Customer cliente: clienti) {

                        // ...e ad ogni abbonamento ...
                        for (Subscription abbonamento : subscriptions) {
                            // chiede se il cliente è abbonato a quella rivista
                            if (abbonamento.getAbbonati().contains(cliente) && abbonamento.getMagazine().equals(rivista)) {
                                System.out.println("  Spedire al cliente " + cliente.getName() + " la rivista: " + abbonamento.getMagazine().getTitle());
                                foundShipping = true;
                            }
                        }
                    }
                }
            }

            if (!foundShipping)
                System.out.println("** nessun cliente al quale spedire riviste **");
        }
    }

    private boolean verificaSePubblicare(DayOfWeek giornoInCorso, Set<DayOfWeek> giorniUscita) {
        for(DayOfWeek g: giorniUscita) {
            if (g.equals(giornoInCorso))
                return true;
        }
        return false;
    }

    public Set<Magazine> getMagazines() {
        return magazines;
    }

}
