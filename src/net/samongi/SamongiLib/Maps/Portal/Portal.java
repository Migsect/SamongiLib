package net.samongi.SamongiLib.Maps.Portal;

import org.bukkit.entity.Player;

import net.samongi.SamongiLib.Blocks.BlockGroup;

public interface Portal
{
  public void onPlayerEntry();
  
  public BlockGroup getBlockGroup();
  
  public boolean playerIsWithin(Player player);
}
