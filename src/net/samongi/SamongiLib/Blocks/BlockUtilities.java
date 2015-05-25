package net.samongi.SamongiLib.Blocks;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.block.Block;

public class BlockUtilities
{
	/**Finds a chain of blocks that match together
	 * 
	 * @param block The block to find all connecting blocks
	 * @return A list of all blocks found.
	 */
	public static List<Block> getBlockChain(Block block, int max_distance)
	{
		List<Block> found_blocks = new ArrayList<>();
		checkNeighbors(block, found_blocks, max_distance);
		return found_blocks;
	}
	
	/**Checks neighbors for similar blocks
	 * 
	 * @param block The block that is being checked around.
	 * @param found_blocks Blocks that should not be included in the checks.
	 */
	private static void checkNeighbors(Block block, List<Block> found_blocks, int max_distance)
	{
		if(max_distance < 0) return;
		List<Block> to_check = new ArrayList<>();
		for(int x = -1; x <= 1 ; x++)
		{
			for(int y = -1; y <= 1 ; y++)
			{
				for(int z = -1; z <= 1 ; z++)
				{
					if(x == 0 && y == 0 && z == 0) continue;
					Block relative_block = block.getRelative(x, y, z);
					if(relative_block.getType().equals(block.getType())) to_check.add(block);
				}
			}
		}
		for(Block b : to_check)
		{
			found_blocks.add(b);
			checkNeighbors(b, found_blocks, max_distance - 1);
		}
		if(to_check.size() == 0) return;
	}
}
