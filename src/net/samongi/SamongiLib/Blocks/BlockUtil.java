package net.samongi.SamongiLib.Blocks;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.util.BlockIterator;
import org.bukkit.util.Vector;

public class BlockUtil
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
  
	public static boolean hasBlocksBetween(Location loc1, Location loc2)
	{
	  Vector starting_vector = loc1.toVector();
	  Vector direction_vector = loc1.toVector().subtract(loc2.toVector());
	  BlockIterator iterator = new BlockIterator(loc1.getWorld(), starting_vector, direction_vector, 0, (int) Math.ceil(loc1.toVector().distance(loc2.toVector())));
	  while(iterator.hasNext())
	  {
	    if(iterator.next().getType().isSolid()) return true;
	  }
	  return false;
	}
	
	public static Location center(Location block_location)
	{
	  double x = block_location.getBlockX() + 0.5;
	  double y = block_location.getBlockY() + 0.5;
	  double z = block_location.getBlockZ() + 0.5;
	  return new Location(block_location.getWorld(), x, y, z);
	}
}
