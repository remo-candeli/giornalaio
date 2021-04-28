package org.corso.models;

import java.time.DayOfWeek;
import java.util.Objects;
import java.util.Set;


/**
 * Magazine - è la rivista e contiene il giorno della settimana in cui esce la rivista.
 *
 * implementa il metodo equals sul titolo della rivista
 */
public class Magazine {

    private String title;
    private Set<DayOfWeek> daysOfWeekPubblications;

    public Magazine(String title, Set<DayOfWeek> daysOfWeekPubblications) {
        this.title = title;
        this.daysOfWeekPubblications = daysOfWeekPubblications;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<DayOfWeek> getDaysOfWeekPubblications() {
        return daysOfWeekPubblications;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Magazine magazine = (Magazine) o;
        return Objects.equals(title, magazine.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }
}
