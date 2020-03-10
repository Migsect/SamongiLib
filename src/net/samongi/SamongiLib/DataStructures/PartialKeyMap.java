package net.samongi.SamongiLib.DataStructures;

import java.util.*;
import java.util.stream.Collectors;

public class PartialKeyMap<Key, Value> {

    // ---------------------------------------------------------------------------------------------------------------//
    private Map<Set<Key>, Value> m_mapping = new HashMap<>();
    private Map<Key, Set<Set<Key>>> m_keyCache = new HashMap<>();

    // ---------------------------------------------------------------------------------------------------------------//
    public int size() {
        return m_mapping.size();
    }

    public void clear() {
        m_mapping.clear();
        m_keyCache.clear();
    }

    public Value put(Key key, Value value) {
        Set<Key> keySet = new HashSet<>();
        keySet.add(key);
        return put(keySet, value);
    }

    public Value put(Collection<Key> keys, Value value) {
        Set<Key> keySet = new HashSet<>(keys);
        assert !keys.isEmpty();
        for (Key key : keys) {
            Set<Set<Key>> involvedKeySets = m_keyCache.getOrDefault(key, new HashSet<>());
            involvedKeySets.add(keySet);
            m_keyCache.put(key, involvedKeySets);
        }
        m_mapping.put(keySet, value);
        return value;
    }

    public Value get(Key key) {
        Set<Key> keySet = new HashSet<>();
        keySet.add(key);
        return get(keySet);
    }

    public Value get(Collection<Key> keys) {
        Set<Key> keySet = new HashSet<>(keys);
        return m_mapping.get(keySet);
    }

    public Collection<Value> getSubsets(Collection<Key> keys) {
        Set<Set<Key>> validKeys = new HashSet<>();
        for (Key key : keys) {
            // Finding all the key subsets.
            Set<Set<Key>> involvedKeySets = m_keyCache.getOrDefault(key, new HashSet<>());
            for (Set<Key> keySet : involvedKeySets) {
                if (keys.containsAll(keySet)) {
                    validKeys.add(keySet);
                }
            }
        }
        return validKeys.stream().map((Set<Key> keySet) -> get(keySet)).collect(Collectors.toList());
    }

    public Value remove(Collection<Key> keys) {
        Set<Key> keySet = new HashSet<>(keys);
        for (Key key : keys) {
            Set<Set<Key>> involvedKeySets = m_keyCache.getOrDefault(key, new HashSet<>());
            involvedKeySets.remove(keySet);
            m_keyCache.put(key, involvedKeySets);
        }
        return m_mapping.remove(keySet);
    }

    public Set<Set<Key>> keySet() {
        return m_mapping.keySet();
    }

    public Collection<Value> values() {
        return m_mapping.values();
    }

    // ---------------------------------------------------------------------------------------------------------------//
}
