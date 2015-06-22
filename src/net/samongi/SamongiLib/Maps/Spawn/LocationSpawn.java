package net.samongi.SamongiLib.Maps.Spawn;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class LocationSpawn implements Spawn
{
  private final Location loc;
  
  public LocationSpawn(Location loc)
  {
    this.loc = loc;
  }
  public Location getLocation(){return this.loc;}
  
  @Override
  public double getDistanceRelation(List<Player> players)
  {
    double ratio_sum = 0;
    for(Player p : players) ratio_sum += 1 / Math.ceil(p.getLocation().distance(this.loc));
    return ratio_sum / players.size();
  }
  
  @Override
  public void spawn(Player player)
  {
    player.teleport(loc);
  }
  
  @Override
  public boolean isWithinRange(List<Player> players, double range)
  {
    for(Player p : players) if(p.getLocation().distance(loc) < range) return true;
    return false;
  }

}
