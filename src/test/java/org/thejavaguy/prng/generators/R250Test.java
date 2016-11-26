package org.thejavaguy.prng.generators;

import static org.junit.Assert.*;
import java.util.Set;
import java.util.TreeSet;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class R250Test {
    private R250.Smart sut;

    @Before
    public void setUp() throws Exception {
        sut = new R250.Smart(new R250());
    }

    @After
    public void tearDown() throws Exception {
        sut = null;
    }

    @Test
    public void nextDouble_IsAlwaysGEQ0andL1() {
        int reps = 10_000_000;
        for (int i = 0; i < reps; ++i) {
            double actual = sut.nextDouble();
            assertTrue(actual >= 0);
            assertTrue(actual < 1);
        }
    }

    @Test
    public void nextInt_WithUpperLimit_IsAlwaysWithinLimit() {
        int reps = 10_000_000;
        Set<Integer> results = new TreeSet<>();
        for (int i = 0; i < reps; ++i) {
            int actual = sut.nextInt(10);
            results.add(actual);
            assertTrue(actual >= 0);
            assertTrue(actual <= 10);
        }
        assertEquals(11, results.size());
    }

    @Test
    public void nextInt_WithUpperLimit_IsAlwaysWithinLimit2() {
        int reps = 10_000_000;
        for (int i = 0; i < reps; ++i) {
            int actual = sut.nextInt(1);
            assertTrue(actual >= 0);
            assertTrue(actual <= 1);
        }
    }

    @Test
    public void nextInt_WithNegativeLimits_IsAlwaysWithinLimits() {
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
