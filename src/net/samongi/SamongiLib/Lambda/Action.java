package net.samongi.SamongiLib.Lambda;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public interface Action
{
  void perform(Object o);
  
  public interface PlayerAction
  {
    void perform(Player p);
  }
  public interface BlockAction
  {
    void perform(Block b);
  }
}
