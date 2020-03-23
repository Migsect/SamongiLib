package net.samongi.SamongiLib.DataStructures;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
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

    public Value put(@Nonnull Key key, @Nonnull Value value) {
        Set<Key> keySet = new HashSet<>();
        keySet.add(key);
        return put(keySet, value);
    }

    public Value put(@Nonnull Collection<Key> keys, @Nonnull Value value) {
        Set<Key> keySet = new HashSet<>(keys);
        assert !keys.isEmpty();
        for (Key key : keys) {
            Set<Set<Key>> involvedKeySets = getInclusiveKeysDirect(key);
            involvedKeySets.add(keySet);
            m_keyCache.put(key, involvedKeySets);
        }
        m_mapping.put(keySet, value);
        return value;
    }

    public Value get(@Nonnull Key key) {
        Set<Key> keySet = new HashSet<>();
        keySet.add(key);
        return get(keySet);
    }

    public Value get(@Nonnull Collection<Key> keys) {
        Set<Key> keySet = new HashSet<>(keys);
        return m_mapping.get(keySet);
    }

    public @Nonnull Value getOrDefault(@Nonnull Collection<Key> keys, @Nonnull Value defaultValue) {
        Value value = get(keys);
        return value == null ? defaultValue : value;
    }

    public @Nonnull Set<Set<Key>> getValidKeys(@Nonnull Collection<Key> keys) {
        Set<Set<Key>> validKeys = new HashSet<>();
        for (Key key : keys) {
            // Finding all the key subsets.
            Set<Set<Key>> involvedKeySets = getInclusiveKeys(key);
            for (Set<Key> keySet : involvedKeySets) {
                if (keys.containsAll(keySet)) {
                    validKeys.add(keySet);
                }
            }
        }
        return validKeys;
    }

    public @Nonnull List<Value> getSubsets(@Nonnull Collection<Key> keys) {
        Set<Set<Key>> validKeys = getValidKeys(keys);
        return validKeys.stream().map(this::get).collect(Collectors.toList());
    }

    public Value remove(@Nonnull Collection<Key> keys) {
        Set<Key> keySet = new HashSet<>(keys);
        for (Key key : keys) {
            Set<Set<Key>> involvedKeySets = getInclusiveKeysDirect(key);
            involvedKeySets.remove(keySet);
            m_keyCache.put(key, involvedKeySets);
        }
        return m_mapping.remove(keySet);
    }

    public @Nonnull Set<Set<Key>> keySet() {
        return m_mapping.keySet();
    }
    /**
     * Ensures that the key cache is actually directly accessed instead of doing a form of customized pre-processing.
     *
     * @param key The key to get the inclusive keys for.
     * @return The set of inclusive key sets.
     */
    protected final Set<Set<Key>> getInclusiveKeysDirect(Key key) {
        return m_keyCache.getOrDefault(key, new HashSet<>());
    }
    public Set<Set<Key>> getInclusiveKeys(Key key) {
        return getInclusiveKeysDirect(key);
    }
    public @Nonnull Set<Key> getCachedKeys() {
        return m_keyCache.keySet();
    }

    public @Nonnull Collection<Value> values() {
        return m_mapping.values();
    }

    // ---------------------------------------------------------------------------------------------------------------//
}
