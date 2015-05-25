package net.samongi.SamongiLib.Lambda;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public interface Comparison
{
  public boolean includes(Object o);
  
  public interface PlayerComparison
  {
    public boolean includes(Player p);
  }  
  public interface BlockComparison
  {
    public boolean includes(Block b);
  }
}
