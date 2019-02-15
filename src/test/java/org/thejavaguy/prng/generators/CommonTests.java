package org.thejavaguy.prng.generators;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.thejavaguy.prng.generators.util.IntRange;

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
        IntRange range = new IntRange(0, 10);
        Set<Integer> results = new TreeSet<>();
        for (int i = 0; i < reps; ++i) {
            int actual = sut.nextInt(range);
            results.add(actual);
            assertTrue(range.contains(actual));
        }
        assertEquals(range.size(), results.size());
    }

    @Test
    public void nextInt_WithUpperLimit_HasCorrectAverage() {
        int reps = 1_000_000;
        IntRange range = new IntRange(1, 10);
        double avg = 0.0d;
        long sum = 0L;
        for (int i = 0; i < reps; ++i) {
            sum += sut.nextInt(range);
        }
        avg = (double)sum / reps;
        assertEquals(5.50, avg, 0.01);
    }

    @Test
    public void nextInt_WithUpperLimit_HasCorrectMedian() {
        int reps = 601;
        IntRange range = new IntRange(1, 5);
        List<Integer> results = new LinkedList<>();
        for (int i = 0; i < reps; ++i) {
            int actual = sut.nextInt(range);
            results.add(actual);
        }
        Collections.sort(results);
        assertEquals(3, ((Integer)results.get(reps/2)).intValue());
    }

    @Test
    public void nextInt_WithUpperLimit_IsAlwaysWithinLimit2() {
        int reps = 10_000_000;
        IntRange range = new IntRange(0, 1);
        for (int i = 0; i < reps; ++i) {
            int actual = sut.nextInt(range);
            assertTrue(range.contains(actual));
        }
    }

    @Test
    public void nextInt_WithNegativeLimits_IsAlwaysWithinLimits() {
        int reps = 10_000_000;
        IntRange range = new IntRange(-10, -1);
        Set<Integer> results = new TreeSet<>();
        for (int i = 0; i < reps; ++i) {
            int actual = sut.nextInt(range);
            results.add(Integer.valueOf(actual));
            assertTrue(range.contains(actual));
        }
        assertEquals(range.size(), results.size());
    }

    @Test
    public void nextByte_IsAlwaysWithinLimits() {
        int reps = 10_000_000;
        IntRange range = new IntRange(Byte.MIN_VALUE, Byte.MAX_VALUE);
        Set<Integer> results = new TreeSet<>();
        for (int i = 0; i < reps; ++i) {
            byte actual = sut.nextByte();
            results.add(Integer.valueOf(actual));
            assertTrue(range.contains(actual));
        }
        assertEquals(range.size(), results.size());
    }
    
    @Test
    public void nextShort_IsAlwaysWithinLimits() {
        int reps = 10_000_000;
        IntRange range = new IntRange(Short.MIN_VALUE, Short.MAX_VALUE);
        Set<Short> results = new HashSet<>();
        for (int i = 0; i < reps; ++i) {
            short actual = sut.nextShort();
            results.add(Short.valueOf(actual));
            assertTrue(range.contains(actual));
        }
        assertEquals(range.size(), results.size());
    }
    
    @Test
    public void nextChar_IsAlwaysWithinLimits() {
        int reps = 10_000_000;
        IntRange range = new IntRange(Character.MIN_VALUE, Character.MAX_VALUE);
        Set<Character> results = new HashSet<>();
        for (int i = 0; i < reps; ++i) {
            char actual = sut.nextChar();
            results.add(Character.valueOf(actual));
            assertTrue(range.contains(actual));
        }
        assertEquals(range.size(), results.size());
    }

    @Test
    public void nextInt_WithNegativeEdgeLimits_IsAlwaysWithinLimits() {
        int reps = 10_000_000;
        IntRange range = new IntRange(Integer.MIN_VALUE, 0);
        for (int i = 0; i < reps; ++i) {
            int actual = sut.nextInt(range);
            assertTrue(range.contains(actual));
        }
    }

    @Test
    public void nextInt_WithPositiveEdgeLimits_IsAlwaysWithinLimits() {
        int reps = 10_000_000;
        IntRange range = new IntRange(0, Integer.MAX_VALUE);
        for (int i = 0; i < reps; ++i) {
            int actual = sut.nextInt(range);
            assertTrue(range.contains(actual));
        }
    }

    @Test
    public void nextInt_WithEdgeLimits_IsAlwaysWithinLimits() {
        int reps = 10_000_000;
        IntRange range = new IntRange(Integer.MIN_VALUE, Integer.MAX_VALUE);
        for (int i = 0; i < reps; ++i) {
            int actual = sut.nextInt(range);
            assertTrue(range.contains(actual));
        }
    }

    @Test
    public void nextInt_WithWorstNegativeEdgeLimits_IsAlwaysWithinLimits() {
        int reps = 10_000_000;
        IntRange range = new IntRange(Integer.MIN_VALUE, Integer.MIN_VALUE + 1);
        for (int i = 0; i < reps; ++i) {
            int actual = sut.nextInt(range);
            assertTrue(range.contains(actual));
        }
    }

    @Test
    public void nextInt_WithWorstPositiveEdgeLimits_IsAlwaysWithinLimits() {
        int reps = 10_000_000;
        IntRange range = new IntRange(Integer.MAX_VALUE - 1, Integer.MAX_VALUE);
        for (int i = 0; i < reps; ++i) {
            int actual = sut.nextInt(range);
            assertTrue(range.contains(actual));
        }
    }

    @Test
    public void nextInt_WithPossibleNegativeOverflow_IsAlwaysWithinLimits() {
        int reps = 10_000_000;
        IntRange range = new IntRange(Integer.MIN_VALUE, 1);
        for (int i = 0; i < reps; ++i) {
            int actual = sut.nextInt(range);
            assertTrue(range.contains(actual));
        }
    }

    @Test
    public void nextInt_WithPossiblePositiveOverflow_IsAlwaysWithinLimits() {
        int reps = 10_000_000;
        IntRange range = new IntRange(-1, Integer.MAX_VALUE);
        for (int i = 0; i < reps; ++i) {
            int actual = sut.nextInt(range);
            assertTrue(range.contains(actual));
        }
    }
}
