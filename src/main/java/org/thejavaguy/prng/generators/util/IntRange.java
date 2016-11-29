package org.thejavaguy.prng.generators.util;

/**
 * @author Ivan Milosavljevic (TheJavaGuy)
 */
public final class IntRange {
    private final int lowerLimit;
    private final int upperLimit;
    
    public IntRange(int lowerLimit, int upperLimit) {
        super();
        if (lowerLimit >= upperLimit) {
            throw new IllegalArgumentException(String.format(
                    "upperLimit must be greater than lowerLimit, yet values are: lowerLimit = %d, upperLimit = %d",
                    lowerLimit, upperLimit));
        }
        this.lowerLimit = lowerLimit;
        this.upperLimit = upperLimit;
    }
    
    public int lowerLimit() {
        return lowerLimit;
    }
    
    public int upperLimit() {
        return upperLimit;
    }
    
    public long size() {
        return (long) upperLimit - lowerLimit + 1;
    }
    
    public boolean contains(int candidate) {
        return lowerLimit <= candidate && candidate <= upperLimit;
    }
    
    public boolean overlaps(IntRange other) {
        return other.contains(lowerLimit) || other.contains(upperLimit) || this.includes(other);
    }
    
    public boolean includes(IntRange other) {
        return contains(other.lowerLimit) && contains(other.upperLimit);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("IntRange [lowerLimit=");
        builder.append(lowerLimit);
        builder.append(", upperLimit=");
        builder.append(upperLimit);
        builder.append("]");
        return builder.toString();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + lowerLimit;
        result = prime * result + upperLimit;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        IntRange other = (IntRange) obj;
        if (lowerLimit != other.lowerLimit) {
            return false;
        }
        if (upperLimit != other.upperLimit) {
            return false;
        }
        return true;
    }
}
