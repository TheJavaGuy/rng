package org.thejavaguy.prng.generators;

import org.thejavaguy.prng.generators.PRNG;

public class XorshiftPlusTest extends CommonTests {
    @Override
    public PRNG.Smart generator() {
        return new XorshiftPlus.Smart(new XorshiftPlus());
    }
}
