package net.samongi.SamongiLib.Lambda;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public interface Action
{
  void perform(Object o);
  
  public interface PlayerAction extends Action
  {
    void perform(Player p);
  }
  public interface BlockAction extends Action
  {
    void perform(Block b);
  }
}
