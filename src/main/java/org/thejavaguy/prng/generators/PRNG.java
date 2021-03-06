package org.thejavaguy.prng.generators;

import org.thejavaguy.prng.generators.util.IntRange;

public interface PRNG {
    /**
     * Returns pseudo-random integer in range [Integer.MIN_VALUE, Integer.MAX_VALUE]
     */
    int nextInt();
    
    class Smart {
        private final PRNG origin;
        
        public Smart(PRNG origin) {
            this.origin = origin;
        }
        
        /**
         * Returns pseudo-random double in range [0, 1)
         */
        public double nextDouble() {
            return Math.abs(origin.nextInt() / ((double) Integer.MAX_VALUE + 2));
        }

        public int nextInt(IntRange range) {
            return nextInt(range.lowerLimit(), range.upperLimit());
        }
        
        /**
         * Returns pseudo-random integer in range [lowerLimit, upperLimit]
         * @param lowerLimit Lower limit of range
         * @param upperLimit Upper limit of range
         * @throws IllegalArgumentException if the lowerLimit is greater than or equal to the upperLimit
         */
        private int nextInt(int lowerLimit, int upperLimit) {
            if (lowerLimit >= upperLimit) {
                throw new IllegalArgumentException(String.format(
                        "upperLimit must be greater than lowerLimit, yet values are: lowerLimit = %d, upperLimit = %d",
                        lowerLimit, upperLimit));
            }
            //if branch handles negative integer overflow case, else branch handles normal case
            if (lowerLimit < 0 && upperLimit >= 0 && (upperLimit - lowerLimit) < 0) {
                IntRange range = new IntRange(lowerLimit, upperLimit);
                for (;;) {
                    int ret = origin.nextInt();
                    if (range.contains(ret)) {
                        return ret;
                    }
                }
            } else {
                return lowerLimit + nextInt(upperLimit - lowerLimit);
            }
        }
        
        /**
         * Returns pseudo-random integer in range [0, upperLimit]
         *
         * @param upperLimit Upper limit of range
         * @throws IllegalArgumentException if the upperLimit is not positive
         */
        private int nextInt(int upperLimit) {
            if (upperLimit <= 0) {
                throw new IllegalArgumentException("upperLimit must be positive, yet it is " + upperLimit);
            }
            return (int) Math.floor(((double) upperLimit + 1) * nextDouble());
        }

        /**
         * Returns pseudo-random boolean
         */
        public boolean nextBoolean() {
            return nextInt(1) == 1;
        }

        /**
         * Returns pseudo-random byte
         */
        public byte nextByte() {
            return (byte) nextInt(Byte.MIN_VALUE, Byte.MAX_VALUE);
        }

        /**
         * Returns pseudo-random short
         */
        public short nextShort() {
            return (short) nextInt(Short.MIN_VALUE, Short.MAX_VALUE);
        }

        /**
         * Returns pseudo-random char
         */
        public char nextChar() {
            return (char) nextInt(Character.MIN_VALUE, Character.MAX_VALUE);
        }
    }
}
