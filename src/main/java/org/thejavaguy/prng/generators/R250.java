package org.thejavaguy.prng.generators;

import java.util.Random;

/**
 * Generator of pseudo-random numbers based on GFSR(250,103) algorithm.
 * @author Ivan Milosavljevic (TheJavaGuy)
 */
public final class R250 implements PRNG {
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
    public int nextInt() {
        int j = (index + 103) % NUM_ELEMENTS;
        int ret = buffer[index] ^ buffer[j];
        buffer[index] = ret;
        index = (index + 1) % NUM_ELEMENTS;
        return ret;
    }
}
