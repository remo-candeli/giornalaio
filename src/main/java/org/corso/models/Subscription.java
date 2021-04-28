package org.corso.models;


import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Subscription {

    private Set<Customer> abbonati;
    private Magazine rivista;

    public Subscription(Magazine rivista) {
        this.rivista = rivista;
        this.abbonati = new HashSet<>();
    }

    public void addSubscriber(Customer cliente) {
        abbonati.add(cliente);
    }

    public void removeSubscriber(Customer cliente) {
        abbonati.remove(cliente);
    }

    public Magazine getMagazine() {
        return rivista;
    }

    public Set<Customer> getAbbonati() {
        return abbonati;
    }

    public Magazine getRivista() {
        return rivista;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subscription that = (Subscription) o;
        return Objects.equals(rivista, that.rivista);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rivista);
    }
}
