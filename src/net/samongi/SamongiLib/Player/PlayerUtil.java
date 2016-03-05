package net.samongi.SamongiLib.Player;

import java.util.List;

import net.samongi.SamongiLib.Vector.SamVector;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class PlayerUtil
{
  public static void removeArrows(Player player)
  {
    CraftPlayer craft_player = (CraftPlayer) player;
    craft_player.getHandle().getDataWatcher().watch(9, (byte)0);
  }
  public static void setArrows(Player player, byte amount)
  {
    CraftPlayer craft_player = (CraftPlayer) player;
    craft_player.getHandle().getDataWatcher().watch(9, amount);
  }
  public static byte getArrows(Player player)
  {
    CraftPlayer craft_player = (CraftPlayer) player;
    return craft_player.getHandle().getDataWatcher().getByte(9);
  }
  
  public static Player getWithItem(ItemStack item)
  {
    @SuppressWarnings("unchecked")
    List<Player> players = (List<Player>) Bukkit.getServer().getOnlinePlayers();
    for(Player p : players)
    {
      if(p.getInventory().contains(item)) return p;
    }
    return null;
  }
  /**Gets the block the player is looking at til the max-distance.
   * If there is no block and everything is just air, this will return null.
   * 
   * @param player
   * @param max_distance
   * @return
   */
  public static Block getTargetedBlock(Player player, double max_distance)
  {
    SamVector direction = new SamVector(player.getLocation().getDirection()).normalize();
    SamVector player_location = new SamVector(player.getEyeLocation().toVector());
    
    Block block = null;
    double increment = 1.0;
    double scalar = 0.0;
    while(scalar <= max_distance)
    {
      Block found_block = direction.multiply(scalar).add(player_location).toLocation(player.getWorld()).getBlock();
      if(found_block.getType() != Material.AIR) block = found_block;
      scalar += increment;
    }
    return block;
  }
  /**Gets the air block the player would be looking at.  This air block
   * occurs before there is a solid block.
   * 
   * This will not return null if there isn't a solid block. It will merely
   * return the last block it had before it got the max_distance.
   * 
   * @param player
   * @param max_distance
   * @return
   */
  public static Block getTargetedAirBlock(Player player, double max_distance)
  {
    SamVector direction = new SamVector(player.getLocation().getDirection()).normalize();
    SamVector player_location = new SamVector(player.getEyeLocation().toVector());
    
    Block block = null;
    double increment = 1.0;
    double scalar = 0.0;
    while(scalar <= max_distance)
    {
      Block found_block = direction.multiply(scalar).add(player_location).toLocation(player.getWorld()).getBlock();
      if(found_block.getType() != Material.AIR) return block;
      scalar += increment;
      block = found_block;
    }
    return block;
  }
}
