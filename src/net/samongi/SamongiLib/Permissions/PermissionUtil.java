package net.samongi.SamongiLib.Permissions;

import org.bukkit.entity.Player;

public class PermissionUtil
{
  public static boolean hasWildCard(String perm, Player player)
  {
    String[] split_perm = perm.split(".");
    if(split_perm.length == 1) return false;
    String wild_card = split_perm[0];
    for(int i = 1; i < split_perm.length ; i++)
    {
      if(player.hasPermission(wild_card + ".*")) return true;
      wild_card += "." + split_perm[i];
    }
    return false;
  }
}
