package org.thejavaguy.prng.generators;

import java.util.Random;

/**
 * @author Ivan Milosavljevic (TheJavaGuy)
 */
public final class CMWC implements PRNG {
    private static final int CMWC_CYCLE = 4096;
    private static final int CMWC_C_MAX = 809_430_660;
    private int[] Q;
    private int c;
    private int index;

    public CMWC() {
        this(System.nanoTime());
    }

    public CMWC(long seed) {
        Random r = new Random(seed);
        Q = new int[CMWC_CYCLE];
        for (int i = 0; i < CMWC_CYCLE; ++i) {
            Q[i] = rand32(r);
        }
        do {
            c = rand32(r);
        } while (c >= CMWC_C_MAX);
    }

    private int rand32(Random r) {
        int ret = r.nextInt();
        ret <<= 16;
        ret |= r.nextInt();
        return ret;
    }

    @Override
    public double nextDouble() {
        return Math.abs(nextInt() / ((double) Integer.MAX_VALUE + 2));
    }

    @Override
    public int nextInt() {
        index = CMWC_CYCLE - 1;
        long t = 0;
        long a = 18782;
        int r = 0xfffffffe;
        int x = 0;
        index = (index + 1) & (CMWC_CYCLE - 1);
        t = a * Q[index] + c;
        c = (int) (t >> 32);
        x = (int) ((t + c) & 0xffffffff);
        if (x < c) {
            ++x;
            ++c;
        }
        return Q[index] = r - x;
    }

    @Override
    public int nextInt(int upperLimit) {
        if (upperLimit <= 0) {
            throw new IllegalArgumentException("upperLimit must be positive, yet it is " + upperLimit);
        }
        return (int) Math.floor((upperLimit + 1) * nextDouble());
    }

    @Override
    public int nextInt(int lowerLimit, int upperLimit) {
        if (lowerLimit > upperLimit) {
            throw new IllegalArgumentException(String.format(
                    "lowerLimit must be greater than upperLimit, yet values are: lowerLimit = %d, upperLimit = %d",
                    lowerLimit, upperLimit));
        }
        return lowerLimit + nextInt(upperLimit - lowerLimit);
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
