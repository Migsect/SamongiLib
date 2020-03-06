package net.samongi.SamongiLib.Blocks;

import net.samongi.SamongiLib.Items.MaskedBlockData;
import net.samongi.SamongiLib.Vector.SamIntVector;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;

public class RelativeBlock {

    private final SamIntVector m_displacement;
    private final MaskedBlockData m_blockData;

    public RelativeBlock(MaskedBlockData blockData, SamIntVector displacement) {
        m_blockData = blockData;
        m_displacement = displacement;
    }

    public MaskedBlockData getBlockData() {
        return m_blockData;
    }

    public SamIntVector getDisplacement() {
        return m_displacement;
    }

    public boolean matchRelativeToBlock(Block block) {
        Block relativeBlock = block.getRelative(m_displacement.X(), m_displacement.Y(), m_displacement.Z());
        return m_blockData.equals(relativeBlock.getBlockData());

    }

    public boolean matchRelativeToBlock(Location location) {
        return matchRelativeToBlock(location.getBlock());
    }

}
