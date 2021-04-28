package org.corso.models;

import java.util.Objects;


/**
 * Customer - contiene le informazioni relative al sinigolo cliente
 *
 * implementa il metodo equals
 */
public class Customer {

    private String name;
    private String ssn;

    public Customer() {

    }

    public Customer(String name, String ssn) {
        this.name = name;
        this.ssn = ssn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(name, customer.name) &&
                Objects.equals(ssn, customer.ssn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, ssn);
    }
}
