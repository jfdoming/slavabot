package com.ekkongames.slavabot.utils;

import java.util.Arrays;
import java.util.Iterator;
import java.lang.Math;

/**
 * This class implements an "unfair" array shuffler. That is, it
 * returns random elements of an array, such that the probability
 * of a given element being generated decreases exponentially as
 * that number is generated more. Since it eventually becomes
 * practically impossible to represent numbers at the scale
 * required, the random generation will eventually exclude
 * elements which have occurred too frequently, until the other
 * elements show up enough to rectify the difference.
 *
 * @param <T> the type of the array to shuffle
 */
public class UnfairRandomIterator<T> implements Iterator<T> {
    private static final double SCALE_DOWN_FACTOR = 0.5;

    // 2**512, written out for efficiency.
    private static final double SCALE_UP_FACTOR =
            13407807929942597099574024998205846127479365820592393377723561443721764030073546976801874298166903427690031858186486050853753882811946569946433649006084096.0;

    private final T[] array;
    private final IntervalTree tree;

    public UnfairRandomIterator(T[] array) {
        this.array = array;

        double[] weights = new double[array.length];
        Arrays.fill(weights, 1);
        tree = new IntervalTree(weights);
    }

    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public T next() {
        double r = Math.random() * tree.getTotal();
        int i = tree.search(r);
        double w = tree.getWeight(i) * SCALE_DOWN_FACTOR;
        if (Math.ulp(tree.getTotal()) / SCALE_DOWN_FACTOR > w) {
            // One of our weights is now too small! Need to fix this.
            // https://repl.it/@jfdoming/FreeMediocreSlope#UnfairRandomIterator.java
            System.out.println("hello!");
        } else if (Double.MIN_VALUE / SCALE_DOWN_FACTOR > w) {
            // Danger! Our numbers are too small overall!
            // Scale up all exponents by adding 512 to compensate. This
            // is the rare case where we need to perform O(n log n) operations.
            // The alternative is to discard the whole tree and start again...
            for (int j = 0; j < array.length; ++j) {
                tree.set(j, tree.getWeight(j) * SCALE_UP_FACTOR);
            }
            w = tree.getWeight(i) * SCALE_DOWN_FACTOR;
        }
        tree.set(i, w);
        return array[i];
    }
}
