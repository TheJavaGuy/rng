package org.thejavaguy.prng.generators;

/**
 * @author Ivan Milosavljevic (TheJavaGuy)
 */
public final class MersenneTwister implements PRNG {
    private static final int N = 624;
    private static final int M = 397;
    private static final int INIT_MULTIPLICATOR = 1_812_433_253;
    private static final int MATRIX_A = 0x9908b0df;
    private static final int UPPER_MASK = 0x80000000;
    private static final int LOWER_MASK = 0x7fffffff;
    private static final int TEMPERING_MASK_B = 0x9d2c5680;
    private static final int TEMPERING_MASK_C = 0xefc60000;
    private static final int DEFAULT_SEED = 5489;
    private int[] mt;
    private int mti;
    private int[] mag01;

    public MersenneTwister() {
        this(DEFAULT_SEED);
    }

    public MersenneTwister(int seed) {
        if (seed == 0) {
            throw new IllegalArgumentException("seed must not be 0");
        }
        mt = new int[N];
        mt[0] = seed;
        for (mti = 1; mti < N; ++mti) {
            mt[mti] = (INIT_MULTIPLICATOR * (mt[mti-1] ^ (mt[mti-1] >>> 30)) + mti);
        }
        mag01 = new int[2];
        mag01[0] = 0;
        mag01[1] = MATRIX_A;
    }

    @Override
    public int nextInt() {
        int ret = 0;
        if (mti >= N) {
            int kk = 0;
            for (kk = 0; kk < N-M; ++kk) {
                ret = (mt[kk] & UPPER_MASK) | (mt[kk+1] & LOWER_MASK);
                mt[kk] = mt[kk+M] ^ (ret >>> 1) ^ mag01[ret & 0x1];
            }
            for (; kk < N-1; ++kk) {
                ret = (mt[kk] & UPPER_MASK) | (mt[kk+1] & LOWER_MASK);
                mt[kk] = mt[kk+(M-N)] ^ (ret >>> 1) ^ mag01[ret & 0x1];
            }
            ret = (mt[N-1] & UPPER_MASK) | (mt[0] & LOWER_MASK);
            mt[N-1] = mt[M-1] ^ (ret >>> 1) ^ mag01[ret & 0x1];
            mti = 0;
        }
        ret = mt[mti++];
        ret ^= (ret >>> 11);
        ret ^= (ret << 7) & TEMPERING_MASK_B;
        ret ^= (ret << 15) & TEMPERING_MASK_C;
        ret ^= (ret >>> 18);
        return ret;
    }
}
