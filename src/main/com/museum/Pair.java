package com.museum;

/**
 * Simple Pair class to keep pair of associated elements and avoid unnecessary java beans.
 * Usually this should be some existing Touple implementation (vavr, apache commons) bt for sake of simplicity
 * and self containing project we implemented it here.
 *
 * @param <L> Type of firt pair element
 * @param <R> type of second pair element
 */
class Pair<L, R> {

    private  L l;
    private  R r;

    /**
     * Public constructor.
     * @param l left element
     * @param r right element
     */
    public Pair(L l, R r) {
        this.l = l;
        this.r = r;
    }

    /**
     * Accessor method.
     * @return left element.
     */
    public L left() { return l; }

    /**
     * Accessor method.
     * @return right element
     */
    public R right() { return r; }

    public String toString() { return "(" + l + ", " + r + ")"; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pair<?, ?> pair = (Pair<?, ?>) o;

        if (l != null ? !l.equals(pair.l) : pair.l != null) return false;
        return r != null ? r.equals(pair.r) : pair.r == null;
    }

    @Override
    public int hashCode() {
        int result = l != null ? l.hashCode() : 0;
        result = 31 * result + (r != null ? r.hashCode() : 0);
        return result;
    }


}