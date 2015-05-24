package net.samongi.SamongiLib.Lambda;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public interface Comparison
{
  public boolean includes(Object o);
  
  public interface PlayerComparison extends Comparison
  {
    public boolean includes(Player p);
  }  
  public interface BlockComparison extends Comparison
  {
    public boolean includes(Block b);
  }
}
