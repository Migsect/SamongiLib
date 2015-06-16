package net.samongi.SamongiLib.Player;

import java.util.List;

import org.bukkit.Bukkit;
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
}
