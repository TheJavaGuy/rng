package org.thejavaguy.prng.generators;

public interface PRNG {
	/**
	 * Returns pseudo-random double in range [0, 1)
	 */
	double nextDouble();

	/**
	 * Returns pseudo-random integer in range [Integer.MIN_VALUE, Integer.MAX_VALUE]
	 */
	int nextInt();

	/**
	 * Returns pseudo-random integer in range [0, upperLimit]
	 *
	 * @param upperLimit Upper limit of range
	 */
	int nextInt(int upperLimit);

	/**
	 * Returns pseudo-random integer in range [lowerLimit, upperLimit]
	 * @param lowerLimit Lower limit of range
	 * @param upperLimit Upper limit of range
	 */
	int nextInt(int lowerLimit, int upperLimit);

	/**
	 * Returns pseudo-random boolean
	 */
	boolean nextBoolean();

	/**
	 * Returns pseudo-random char
	 */
	char nextChar();
}
