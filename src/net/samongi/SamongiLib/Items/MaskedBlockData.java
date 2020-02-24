package net.samongi.SamongiLib.Items;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.data.Ageable;
import org.bukkit.block.data.BlockData;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Will mask block data during comparison operations.
 */
public class MaskedBlockData {

    private static final BlockData AIR_BLOCKDATA = Bukkit.createBlockData(Material.AIR);

    /**The various masks that will determine how masked block data is compared during equality.
     * As more use cases are needed, then this will be updated to include them.
     */
    public enum Mask {
        MATERIAL,
        AGEABLE
    }

    private BlockData m_blockData = null;
    private Set<Mask> m_masks = new HashSet<>();

    public MaskedBlockData(BlockData blockData, Collection<Mask> masks)
    {
        m_blockData = blockData;
        m_masks.addAll(masks);
    }

    public MaskedBlockData(BlockData blockData, Mask[] masks)
    {
        m_blockData = blockData;
        m_masks.addAll(Arrays.asList(masks));
    }

    public BlockData getBlockData()
    {
        return m_blockData;
    }

    public boolean checkMaskMatch(BlockData other, Mask mask)
    {
        switch (mask)
        {
            case MATERIAL:
                return m_blockData.getMaterial().equals((other.getMaterial()));
            case AGEABLE:
                if(!(m_blockData instanceof Ageable && other instanceof Ageable))
                {
                    // If they aren't ageables, then they should be matching.
                    return true;
                }
                if(m_blockData instanceof Ageable != other instanceof Ageable)
                {
                    return false;
                }
                return ((Ageable) m_blockData).getAge() == ((Ageable) other).getAge();
            default:
                return false;
        }
    }

    public boolean equals(MaskedBlockData other) {
        return equals(other.getBlockData());
    }

    public boolean equals(BlockData other) {
        // All masks should match.
        for(Mask mask : m_masks)
        {
            if(!checkMaskMatch(other, mask))
            {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean equals(Object other) {
        if(other instanceof MaskedBlockData)
        {
            return equals((MaskedBlockData) other);
        }
        if(other instanceof BlockData)
        {
            return equals((BlockData) other);
        }
        return false;
    }

    private int masksHashCode() {
        int total = 0;
        for(Mask mask : m_masks)
        {
            total += mask.hashCode();
            // Comparing with air to see what if there are any keys.
            if(checkMaskMatch(AIR_BLOCKDATA, mask))
            {
                total += 1;
            }
        }
        return total;
    }

    @Override
    public int hashCode() {
        // We are just passing the hashcode to be the block data even though we should probably make it include the maps.
        return m_blockData.getMaterial().hashCode() + masksHashCode();
    }
}
