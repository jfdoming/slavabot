package com.ekkongames.slavabot.utils;

public class IntervalTree {
    private final double[] tree;

    public IntervalTree(double[] boundaries) {
        tree = boundaries;
        for (int i = 1; i < tree.length; ++i) {
            int j = i + (i & -i);
            if (j <= tree.length) {
                tree[j - 1] += tree[i - 1];
            }
        }
    }

    public double getTotal() {
        double sum = 0;
        for (int i = tree.length; i > 0; i -= i & -i) {
            sum += tree[i - 1];
        }
        return sum;
    }

    public double getWeight(int i) {
        double sum = tree[i];
        int j = i + 1;
        j -= j & -j;
        for (; i > j; i -= i & -i) {
            sum -= tree[i - 1];
        }
        return sum;
    }

    public void adjust(int i, double amount) {
        ++i;
        for (; i <= tree.length; i += (i & -i)) {
            tree[i - 1] += amount;
        }
    }

    public void set(int i, double value) {
        adjust(i, value - getWeight(i));
    }

    // Many thanks to https://stackoverflow.com/a/34701217 for the idea
    // of how to combine binary search and Fenwick Trees!
    public int search(double value) {
        int j = 1;
        while (j < tree.length) {
            j <<= 1;
        }
        j >>= 1;
        int i = -1;
        while (j > 0) {
            if (i + j < tree.length && value > tree[i + j]) {
                value -= tree[i + j];
                i += j;
            }
            j >>= 1;
        }
        return i + 1;
    }
}
