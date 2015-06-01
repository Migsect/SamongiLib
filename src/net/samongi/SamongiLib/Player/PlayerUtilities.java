package net.samongi.SamongiLib.Player;

import org.bukkit.craftbukkit.v1_8_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class PlayerUtilities
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
}
