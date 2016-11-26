package org.thejavaguy.prng.generators;

import org.thejavaguy.prng.generators.PRNG;

public final class R250_521Test extends CommonTests {
    @Override
    public PRNG.Smart generator() {
        return new R250_521.Smart(new R250_521());
    }
}
