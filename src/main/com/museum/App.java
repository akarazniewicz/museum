package com.museum;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalTime;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static java.util.Comparator.comparing;

/**
 * Main class to solve museum problem. Basic idea to solve this challenge is to flatten intervals into list
 * if entries (how many people entered or left museum ant given time). Then we sort list of entries and calculate
 * elementary intervals to keep number of visitors in given interval.
 *
 * For example having those intervals:
 *<pre>
 *   7 8  9   10 11 12     13     14  15
 *   [--------------------------------]
 *     [------] [---]       [-----]
 *         [-------------------]
 *         9:30                13:30
 *   112222333223333222222223332221111
 *
 * we end up with
 *  * [7:00, 8:00] with one visitor
 *  * [9:00, 9:00] with two visitors
 *  * [9:30, 10:00] with three visitors
 *  * [10:00, 11:00] with two  visitors
 *  etc.
 *</pre>
 *  Then we basically find interval with most visitors in it.
 *
 *  If we have multiple periods with the same number of (maximum) concurrent visitors this
 *  will find only one (earliest) interval.
 *
 */
public class App {

    private static List<Pair<LocalTime, Integer>> ents = new LinkedList<>();

    /**
     * Helper shortcut method.
     * @param s string to parse to LocalTime
     * @return LocalTime instance
     */
    private static LocalTime d(String s) {
        return LocalTime.parse(s);
    }


    /**
     * Read input data from file and create list of entries. Entries from data file are then sorted against
     * entry/exit date so we can keep record of:
     * 1. How many people entered at what time
     * 2. How many people left at what time
     *
     * @param path - file path with list of entries
     * @throws Exception - IOException or parsing exception
     */
    static void readAndFlattenData(String path) throws Exception {

        BufferedReader is = new BufferedReader(new FileReader(path));
        String line;
        while ((line = is.readLine()) != null) {

            String[] entry = line.trim().split(",");

            // add entry when somebody enters museum (we increase number of vistors by one)
            ents.add(new Pair<>(d(entry[0]), +1));

            // add entry when given person leaves museum (we decrease number of visitors by one)
            ents.add(new Pair<>(d(entry[1]).plusNanos(1), -1));

        }

        // and finally sort entries against entry/exit time
        Collections.sort(ents, comparing(Pair::left));

    }

    /**
     * Actual method to find interval with highest number of visitors. Beware that if we have multiple
     * periods with the same (max) number of visitors it will return only one (earliest) interval.
     *
     * @return Interval with highest number of visitors (this is Optional as it may be empty)
     */
    static Optional<Interval> findIntervalWithMostVisitors(List<Pair<LocalTime, Integer>> entries) {
        LinkedList<Interval> intervals = new LinkedList<>();

        int currentNumberOfVisits = 0;
        Pair<LocalTime, Integer> lastEntryOrLeave = null;

        for (Pair<LocalTime, Integer> v : entries) {
            if (lastEntryOrLeave != null) {

                // skip duplicates
                if (!(lastEntryOrLeave.equals(v))) {

                    intervals.add(new Interval(lastEntryOrLeave.left(),
                            v.left(), currentNumberOfVisits));

                }
            }
            lastEntryOrLeave = v;
            currentNumberOfVisits += v.right();
        }

        return intervals.stream().max(comparing(Interval::getVisits));
    }


    /**
     * Main entry point
     *
     * @param args - list of arguments. Program requires name of the file.
     */
    public static void main(String[] args) {

        try {

            System.out.println(args[0]);
            readAndFlattenData(args[0]);
            Optional<Interval> topvisitors = findIntervalWithMostVisitors(ents);

            if (topvisitors.isPresent()) {
                System.out.println(topvisitors.get());
                System.exit(0);
            } else {
                System.out.println("Seems like input file is empty. Cannot find any valid record.");
                System.exit(-1);
            }

        } catch (Exception e) {
            System.out.println("error: " + e.toString());
            System.exit(-1);
        }

    }

}
