package net.samongi.SamongiLib.Items.Comparators;

import org.bukkit.block.data.BlockData;

public class BlockDataMaterialComparator implements BlockDataComparator {

    @Override
    public int compare(BlockData first, BlockData second) {
        if(first.getMaterial().equals(second.getMaterial()))
        {
            return 0;
        }
        return 1;
    }
}
