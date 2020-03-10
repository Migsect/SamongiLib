package net.samongi.SamongiLib.Tests;

import net.samongi.SamongiLib.DataStructures.PartialKeyMap;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PartialKeyMapTests {

    @Test public void setGet_0() {

        String key = "Blah";

        PartialKeyMap<String, Integer> map = new PartialKeyMap<>();

        assertEquals(map.get(key), null);
    }

    @Test public void setGet_1() {

        String key = "Blah";
        Integer value = 20;

        PartialKeyMap<String, Integer> map = new PartialKeyMap<>();
        map.put(key, value);

        assertEquals(map.get(key), value);
    }

    @Test public void setGet_2() {

        List<String> key = new ArrayList<>();
        key.add("Key1");
        key.add("Key2");
        Integer value = 30;

        PartialKeyMap<String, Integer> map = new PartialKeyMap<>();
        map.put(key, value);

        assertEquals(map.get(key), value);
    }

    @Test public void setGetAll_2() {

        List<String> key1 = new ArrayList<>();
        key1.add("Key1");
        key1.add("Key2");
        Integer value1 = 30;

        List<String> key2 = new ArrayList<>();
        key2.add("Key1");
        Integer value2 = 60;

        List<String> key3 = new ArrayList<>();
        key3.add("Key2");
        Integer value3 = 90;

        PartialKeyMap<String, Integer> map = new PartialKeyMap<>();
        map.put(key1, value1);
        map.put(key2, value2);
        map.put(key3, value3);
        Collection<Integer> result = map.getSubsets(key1);
        assertTrue(result.toString(), result.contains(value1));
        assertTrue(result.toString(), result.contains(value2));
        assertTrue(result.toString(), result.contains(value3));

    }

    @Test public void clear_0() {
        List<String> key = new ArrayList<>();
        key.add("Key1");
        key.add("Key2");
        Integer value = 30;

        PartialKeyMap<String, Integer> map = new PartialKeyMap<>();
        map.put(key, value);
        map.clear();

        assertEquals(map.get(key), null);
    }

    @Test public void remove_0() {
        List<String> key = new ArrayList<>();
        key.add("Key1");
        key.add("Key2");
        Integer value = 30;

        PartialKeyMap<String, Integer> map = new PartialKeyMap<>();
        map.put(key, value);
        map.remove(key);

        assertEquals(map.get(key), null);
    }
}