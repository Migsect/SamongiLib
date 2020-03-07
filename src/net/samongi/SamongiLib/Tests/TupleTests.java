package net.samongi.SamongiLib.Tests;

import net.samongi.SamongiLib.Tuple.Tuple;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TupleTests {
    @Test public void constructorKeepsValues() {
        int value0 = 1;
        double value1 = 2.0;
        String value2 = "3";

        Tuple tuple = new Tuple(value0,value1,value2);
        assertEquals((int)tuple.get(0), value0);
        assertEquals((double)tuple.get(1), value1, 0.00001);
        assertEquals((String)tuple.get(2), value2);
    }

    @Test public void selfEqualsSelf() {
        int value0 = 1;
        int value1 = 2;

        Tuple tuple = new Tuple(value0,value1);
        assertEquals(tuple, tuple);
    }

    @Test public void selfHashCodeEqualsSelf() {
        int value0 = 1;
        int value1 = 2;

        Tuple tuple = new Tuple(value0, value1);
        assertEquals(tuple.hashCode(), tuple.hashCode());
    }

    @Test public void selfValueEqualsOther() {
        int value0 = 1;
        int value1 = 2;

        Tuple tuple0 = new Tuple(value0, value1);
        Tuple tuple1 = new Tuple(value0, value1);
        assertEquals(tuple0, tuple1);
    }

    @Test public void selfValueHashCodeEqualsOther() {
        int value0 = 1;
        int value1 = 2;

        Tuple tuple0 = new Tuple(value0, value1);
        Tuple tuple1 = new Tuple(value0, value1);
        assertEquals(tuple0.hashCode(), tuple1.hashCode());
    }
}
