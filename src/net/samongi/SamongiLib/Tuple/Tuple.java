package net.samongi.SamongiLib.Tuple;

public class Tuple {
    private final Class<?>[]m_classes;
    private final Object[] m_values;

    public Tuple(Object... values) {
        m_values = values;
        m_classes = new Class<?>[m_values.length];
        for(int index = 0; index < m_values.length; index++)
        {
            m_classes[index] = m_values[index].getClass();
        }
    }

    public <T> T get(int index)
    {
        return (T) m_values[index];
    }

    public int size()
    {
        return m_values.length;
    }

    public Tuple range(int start)
    {
        return range(start, m_values.length);
    }

    public Tuple range(int start, int end)
    {
        if(end > m_values.length || start < 0)
        {
            throw new IndexOutOfBoundsException();
        }

        int length = end - start;
        Object[] range = new Object[length];
        for(int index = 0; index < length; index++)
        {
            range[index] = m_values[index + start];
        }
        return new Tuple(range);
    }

    public boolean equals(Tuple other) {
        if(other.size() != size()) {
            return false;
        }
        for(int index = 0; index < m_values.length; index++){
            if(!m_values[index].equals(other.m_values[index]))
            {
                return false;
            }
        }
        return true;
    }
    @Override public boolean equals(Object other) {
        if(other instanceof Tuple) {
            return equals((Tuple) other);
        }
        return false;
    }

    @Override public int hashCode() {
        return m_classes.hashCode() + 31 * m_values.hashCode();
    }
}
