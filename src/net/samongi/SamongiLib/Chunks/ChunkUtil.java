package net.samongi.SamongiLib.Chunks;

import org.bukkit.Chunk;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;

import java.util.HashMap;
import java.util.Map;

public class ChunkUtil {

    public static Map<Biome, Integer> calculateChunkBiomeCounts(Chunk chunk) {
        Map<Biome, Integer> counts = new HashMap<>();

        for (int indexX = 0; indexX < 16; indexX++) {
            for (int indexZ = 0; indexZ < 16; indexZ++) {
                Block block = chunk.getBlock(indexX, 0, indexZ);
                Biome biome = block.getBiome();
                counts.put(biome, counts.getOrDefault(biome, 0) + 1);
            }
        }

        return counts;
    }

    public static Map<Biome, Double> calculateChunkBiomeRatio(Chunk chunk) {
        Map<Biome, Integer> counts = calculateChunkBiomeCounts(chunk);
        Map<Biome, Double> ratios = new HashMap<>();

        double area = 16 * 16;
        for (Biome biome : counts.keySet()) {
            ratios.put(biome, counts.get(biome) / area);
        }

        return ratios;
    }
}
