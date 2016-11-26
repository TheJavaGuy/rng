package org.thejavaguy.prng.generators;

import static org.junit.Assert.*;

import java.util.Set;
import java.util.TreeSet;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public abstract class CommonTests {
    private PRNG.Smart sut;

    @Before
    public void setUp() throws Exception {
        sut = generator();
    }

    @After
    public void tearDown() throws Exception {
        sut = null;
    }
    
    public abstract PRNG.Smart generator();

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

    @Test
    public void nextInt_WithNegativeEdgeLimits_IsAlwaysWithinLimits() {
        int reps = 10_000_000;
        for (int i = 0; i < reps; ++i) {
            int actual = sut.nextInt(Integer.MIN_VALUE, 0);
            assertTrue(actual >= Integer.MIN_VALUE);
            assertTrue(actual <= 0);
        }
    }

    @Test
    public void nextInt_WithPositiveEdgeLimits_IsAlwaysWithinLimits() {
        int reps = 10_000_000;
        for (int i = 0; i < reps; ++i) {
            int actual = sut.nextInt(0, Integer.MAX_VALUE);
            assertTrue(actual >= 0);
            assertTrue(actual <= Integer.MAX_VALUE);
        }
    }

    @Test
    public void nextInt_WithEdgeLimits_IsAlwaysWithinLimits() {
        int reps = 10_000_000;
        for (int i = 0; i < reps; ++i) {
            int actual = sut.nextInt(Integer.MIN_VALUE, Integer.MAX_VALUE);
            assertTrue(actual >= Integer.MIN_VALUE);
            assertTrue(actual <= Integer.MAX_VALUE);
        }
    }

    @Test
    public void nextInt_WithWorstNegativeEdgeLimits_IsAlwaysWithinLimits() {
        int reps = 10_000_000;
        for (int i = 0; i < reps; ++i) {
            int actual = sut.nextInt(Integer.MIN_VALUE, Integer.MIN_VALUE + 1);
            assertTrue(actual >= Integer.MIN_VALUE);
            assertTrue(actual <= Integer.MIN_VALUE + 1);
        }
    }

    @Test
    public void nextInt_WithWorstPositiveEdgeLimits_IsAlwaysWithinLimits() {
        int reps = 10_000_000;
        for (int i = 0; i < reps; ++i) {
            int actual = sut.nextInt(Integer.MAX_VALUE - 1, Integer.MAX_VALUE);
            assertTrue(actual >= Integer.MAX_VALUE - 1);
            assertTrue(actual <= Integer.MAX_VALUE);
        }
    }

    @Test
    public void nextInt_WithPossibleNegativeOverflow_IsAlwaysWithinLimits() {
        int reps = 10_000_000;
        for (int i = 0; i < reps; ++i) {
            int actual = sut.nextInt(Integer.MIN_VALUE, 1);
            assertTrue(actual >= Integer.MIN_VALUE);
            assertTrue(actual <= 1);
        }
    }

    @Test
    public void nextInt_WithPossiblePositiveOverflow_IsAlwaysWithinLimits() {
        int reps = 10_000_000;
        for (int i = 0; i < reps; ++i) {
            int actual = sut.nextInt(-1, Integer.MAX_VALUE);
            assertTrue(actual >= -1);
            assertTrue(actual <= Integer.MAX_VALUE);
        }
    }
}
