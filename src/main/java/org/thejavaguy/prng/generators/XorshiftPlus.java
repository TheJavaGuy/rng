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
    public double nextDouble() {
        return Math.abs(nextInt() / ((double) Integer.MAX_VALUE + 2));
    }

    @Override
    public int nextInt() {
        return (int) (nextLong() & 0xffffffff);
    }

    @Override
    public int nextInt(int upperLimit) {
        if (upperLimit <= 0) {
            throw new IllegalArgumentException("upperLimit must be positive, yet it is " + upperLimit);
        }
        return (int) Math.floor(((double) upperLimit + 1) * nextDouble());
    }

    @Override
    public int nextInt(int lowerLimit, int upperLimit) {
        if (lowerLimit >= upperLimit) {
            throw new IllegalArgumentException(String.format(
                    "upperLimit must be greater than lowerLimit, yet values are: lowerLimit = %d, upperLimit = %d",
                    lowerLimit, upperLimit));
        }
        if (lowerLimit < 0 && upperLimit >= 0 && (upperLimit - lowerLimit) < 0) {
            int ret = 0;
            for (;;) {
                ret = nextInt();
                if (ret >= lowerLimit && ret <= upperLimit) {
                    break;
                }
            }
            return ret;
        } else {
            return lowerLimit + nextInt(upperLimit - lowerLimit);
        }
    }

    @Override
    public boolean nextBoolean() {
        return nextInt(1) == 1;
    }

    @Override
    public byte nextByte() {
        return (byte) nextInt(-128, 127);
    }

    @Override
    public short nextShort() {
        return (short) nextInt(-32768, 32767);
    }

    @Override
    public char nextChar() {
        return (char) nextInt(0, 65535);
    }
}
