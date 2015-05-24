package net.samongi.SamongiLib.Blocks;

import java.util.Set;

import net.samongi.SamongiLib.Lambda.Comparison.BlockComparison;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;

public interface BlockGroup
{
  public Set<Block> getBlocks();
  
  public Set<Location> getLocations();
  
  public BlockGroup getSubSet(BlockComparison compare);
  
  public int countBlocks();
  
  /**Check to see if the player is standing within the block 
   * 
   * @param player The player to check for.
   * @return True if the player is standing within the block group.
   */
  public default boolean standingWithin(Player player)
  {
    return this.contains(player.getLocation());
  }
  
  /**Will check to see if the player's head is within the BlockGroup
   * 
   * @param player The player to check for.
   * @return True if the player's head is within the location.
   */
  public default boolean lookingWithin(Player player)
  {
    return this.getBlocks().contains(player.getLocation().getBlock().getRelative(BlockFace.UP));
  }
  
  /**Checks to see if the player is standing on one of the blocks within the blockgroup.
   * 
   * @param player The player to check for.
   * @return True if the player is standing on one of the blocks within the region.
   */
  public default boolean standingOn(Player player)
  {
    return this.getBlocks().contains(player.getLocation().getBlock().getRelative(BlockFace.DOWN));
  }
  
  public default boolean contains(Location loc)
  {
   return this.getBlocks().contains(loc.getBlock());
  }
  public default boolean contains(Block block)
  {
    return this.getBlocks().contains(block);
  }
}
