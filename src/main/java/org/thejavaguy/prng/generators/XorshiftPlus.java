package org.thejavaguy.prng.generators;

import java.util.Random;

/**
 * @author Ivan Milosavljevic (TheJavaGuy)
 */
public final class XorshiftPlus implements PRNG {
    private long[] s;

    public XorshiftPlus() {
        this(System.nanoTime());
    }

    public XorshiftPlus(long seed) {
        Random r = new Random(seed);
        s = new long[2];
        for (;;) {
            s[0] = r.nextLong();
            s[1] = r.nextLong();
            if (s[0] != 0 || s[1] != 0) {
                break;
            }
        }
    }

    private long nextLong() {
        long x = s[0];
        long y = s[1];
        s[0] = y;
        x ^= x << 23;
        s[1] = x ^ y ^ (x >>> 17) ^ (y >>> 26);
        return s[1] + y;
    }

    @Override
    public int nextInt() {
        return (int) (nextLong() & 0xffffffff);
    }
}
