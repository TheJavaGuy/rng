package org.thejavaguy.prng.generators;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ CMWCTest.class, MersenneTwisterTest.class, R250_521Test.class, R250Test.class, XorshiftPlusTest.class })
public class AllTests {

}
