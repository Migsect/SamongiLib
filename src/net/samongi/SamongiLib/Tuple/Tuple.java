package net.samongi.SamongiLib.Tuple;

import java.util.Arrays;
import java.util.Objects;

public class Tuple {

    private final Object[] m_values;

    public Tuple(Object... values) {
        m_values = values;
    }

    public <T> T get(int index) {
        return (T) m_values[index];
    }

    public int size() {
        return m_values.length;
    }

    public Tuple range(int start) {
        return range(start, m_values.length);
    }

    public Tuple range(int start, int end) {
        if (end > m_values.length || start < 0) {
            throw new IndexOutOfBoundsException();
        }

        int length = end - start;
        Object[] range = new Object[length];
        for (int index = 0; index < length; index++) {
            range[index] = m_values[index + start];
        }
        return new Tuple(range);
    }

    public boolean equals(Tuple other) {
        if (other.size() != size()) {
            return false;
        }
        for (int index = 0; index < m_values.length; index++) {
            if (!Objects.equals(m_values[index], other.m_values[index])) {
                return false;
            }
        }
        return true;
    }

    @Override public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (other instanceof Tuple) {
            return equals((Tuple) other);
        }
        return false;
    }

    @Override public int hashCode() {
        return Arrays.hashCode(m_values);
    }


    @Override public String toString() {
        String outputString = "[";
        int index = 0;
        for (Object object : m_values) {
            if (object == null) {
                outputString += "null";
            } else {
                outputString += object;
            }
            if (index != m_values.length - 1) {
                outputString += ",";
            }
            index++;
        }
        return outputString + "]";
    }

}
