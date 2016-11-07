package org.thejavaguy.prng.generators;

import java.util.Random;

/**
 * Generator of pseudo-random numbers based on GFSR(250,103) algorithm.
 * @author Ivan Milosavljevic
 */
public class R250 implements PRNG {
    private static final int NUM_ELEMENTS = 250;
    private final int[] buffer;
    private int index;

    public R250() {
        this(System.nanoTime());
    }

    public R250(long seed) {
        buffer = new int[NUM_ELEMENTS];
        index = 0;
        Random randomGenerator = new Random(seed);
        for (int i = 0; i < buffer.length; ++i) {
            buffer[i] = randomGenerator.nextInt();
        }
        int msb = 0x80000000;
        int mask = 0xffffffff;
        for (int j = 0; j < 32; ++j) {
            int indexOfVector = 7 * j + 3;
            buffer[indexOfVector] &= mask;
            buffer[indexOfVector] |= msb;
            mask >>= 1;
        msb >>= 1;
        }
    }

    @Override
    public double nextDouble() {
        return Math.abs(nextInt() / ((double) Integer.MAX_VALUE + 2));
    }

    @Override
    public int nextInt() {
        int j = (index + 103) % NUM_ELEMENTS;
        int ret = buffer[index] ^ buffer[j];
        buffer[index] = ret;
        index = (index + 1) % NUM_ELEMENTS;
        return ret;
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
