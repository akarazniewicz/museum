package com.museum;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Main entity to keep visit interval together with number of people visiting museum within this interval.
 *
 */
public class Interval {

    private LocalTime start;
    private LocalTime end;

    private int visits = 1;

    public Interval(LocalTime start, LocalTime end, Integer visits) {
        this.start = start;
        this.end = end;
        this.visits = visits;
    }

    public LocalTime getStart() {
        return start;
    }

    public void setStart(LocalTime start) {
        this.start = start;
    }

    public LocalTime getEnd() {
        return end;
    }

    public void setEnd(LocalTime end) {
        this.end = end;
    }

    public int getVisits() {
        return visits;
    }

    public void setVisits(int visits) {
        this.visits = visits;
    }

    @Override
    public String toString() {
        DateTimeFormatter f = DateTimeFormatter.ofPattern("kk:mm");
        return start.format(f) +
                " - " +  end.format(f) +
                ";" + visits;
    }


}
