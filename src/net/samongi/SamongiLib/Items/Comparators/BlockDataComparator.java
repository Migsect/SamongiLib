package net.samongi.SamongiLib.Items.Comparators;

import org.bukkit.block.data.BlockData;

import java.util.Comparator;

/**Used to compare two different block datas in a specific way that is distinct
 * from .equals()
 */
public interface BlockDataComparator extends Comparator<BlockData> {

    int compare(BlockData first, BlockData second);

}
