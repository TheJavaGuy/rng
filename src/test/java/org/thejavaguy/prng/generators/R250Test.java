package org.thejavaguy.prng.generators;

import org.thejavaguy.prng.generators.PRNG;

public final class R250Test extends CommonTests {
    @Override
    public PRNG.Smart generator() {
        return new R250.Smart(new R250());
    }
}
