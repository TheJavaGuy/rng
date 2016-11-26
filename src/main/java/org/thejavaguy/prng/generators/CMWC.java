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
}
