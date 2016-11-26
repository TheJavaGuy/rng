package org.thejavaguy.prng.generators;

import org.thejavaguy.prng.generators.PRNG;

public final class MersenneTwisterTest extends CommonTests {
    @Override
    public PRNG.Smart generator() {
        return new MersenneTwister.Smart(new MersenneTwister());
    }
}
