package org.thejavaguy.prng.generators;

import java.util.Random;

/**
 * Generator of pseudo-random numbers based on GFSR(521,168) algorithm.
 * @author Ivan Milosavljevic
 */
public final class R250_521 implements PRNG {
    private static final int NUM_ELEMENTS_250 = 250;
    private static final int NUM_ELEMENTS_521 = 521;
    private static final int MODULO_250 = 103;
    private static final int MODULO_521 = 168;
    private final int[] r250_buffer;
    private final int[] r521_buffer;
    private int r250_index;
    private int r521_index;

    public R250_521() {
        this(System.nanoTime());
    }

    /**
     * Konstruise R250_521 generator sa zadatim semenom.
     *
     * @param seed Pocetno seme za generator.
     */
    public R250_521(long seed) {
        r250_buffer = new int[NUM_ELEMENTS_250];
        r521_buffer = new int[NUM_ELEMENTS_521];
        Random r = new Random(seed);
        int i = NUM_ELEMENTS_521;
        while (i-- > 250) {
            r521_buffer[i] = r.nextInt();
        }
        while (i-- > 31) {
            r250_buffer[i] = r.nextInt();
            r521_buffer[i] = r.nextInt();
        }
        int mask1 = 1;
        int mask2 = 0xFFFFFFFF;
        while (i-- > 0) {
            r250_buffer[i] = (r.nextInt() | mask1) & mask2;
            r521_buffer[i] = (r.nextInt() | mask1) & mask2;
            mask2 ^= mask1;
            mask1 >>= 1;
        }
        r250_buffer[0] = mask1;
        r521_buffer[0] = mask2;
        r250_index = 0;
        r521_index = 0;
    }

    @Override
    public double nextDouble() {
        return Math.abs(nextInt() / ((double) Integer.MAX_VALUE + 2));
    }

    @Override
    public int nextInt() {
        int i1 = r250_index;
        int i2 = r521_index;
        int j1 = i1 - (NUM_ELEMENTS_250 - MODULO_250);
        if (j1 < 0) {
            j1 = i1 + MODULO_250;
        }
        int j2 = i2 - (NUM_ELEMENTS_521 - MODULO_521);
        if (j2 < 0) {
            j2 = i2 + MODULO_521;
        }
        int r = r250_buffer[j1] ^ r250_buffer[i1];
        r250_buffer[i1] = r;
        int s = r521_buffer[j2] ^ r521_buffer[i2];
        r521_buffer[i2] = s;
        i1 = (i1 != NUM_ELEMENTS_250 - 1) ? (i1 + 1) : 0;
        r250_index = i1;
        i2 = (i2 != NUM_ELEMENTS_521 - 1) ? (i2 + 1) : 0;
        r521_index = i2;
        return r ^ s;
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
