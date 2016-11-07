package org.thejavaguy.prng.generators;

import static org.junit.Assert.*;

import java.util.Set;
import java.util.TreeSet;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class R250Test {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void nextDouble_IsAlwaysGEQ0andL1() {
        R250 sut = new R250();
        int reps = 10_000_000;
        for (int i = 0; i < reps; ++i) {
            double actual = sut.nextDouble();
            assertTrue(actual >= 0);
            assertTrue(actual < 1);
        }
    }

    @Test
    public void nextInt_WithUpperLimit_IsAlwaysWithinLimit() {
        R250 sut = new R250();
        int reps = 10_000_000;
        for (int i = 0; i < reps; ++i) {
            int actual = sut.nextInt(10);
            assertTrue(actual >= 0);
            assertTrue(actual <= 10);
        }
    }

    @Test
    public void nextInt_WithUpperLimit_IsAlwaysWithinLimit2() {
        R250 sut = new R250();
        int reps = 10_000_000;
        for (int i = 0; i < reps; ++i) {
            int actual = sut.nextInt(1);
            assertTrue(actual >= 0);
            assertTrue(actual <= 1);
        }
    }

    @Test
    public void nextInt_WithNegativeLimits_IsAlwaysWithinLimits() {
        R250 sut = new R250();
        int reps = 10_000_000;
        Set<Integer> results = new TreeSet<>();
        for (int i = 0; i < reps; ++i) {
            int actual = sut.nextInt(-10, -1);
            results.add(Integer.valueOf(actual));
            assertTrue(actual >= -10);
            assertTrue(actual <= -1);
        }
        assertEquals(10, results.size());
    }

    @Test
    public void nextByte_IsAlwaysWithinLimits() {
        R250 sut = new R250();
        int reps = 10_000_000;
        Set<Integer> results = new TreeSet<>();
        for (int i = 0; i < reps; ++i) {
            byte actual = sut.nextByte();
            results.add(Integer.valueOf(actual));
            assertTrue(actual >= -128);
            assertTrue(actual <= 127);
        }
        assertEquals(256, results.size());
    }

}
