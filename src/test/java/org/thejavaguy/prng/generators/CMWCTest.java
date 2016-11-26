package org.thejavaguy.prng.generators;

import org.thejavaguy.prng.generators.PRNG;

public final class CMWCTest extends CommonTests {
    @Override
    public PRNG.Smart generator() {
        return new CMWC.Smart(new CMWC());
    }
}
