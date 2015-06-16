package net.samongi.SamongiLib.Maps.Spawn;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class LocationSpawn implements Spawn
{
  private final Location loc;
  
  public LocationSpawn(Location loc)
  {
    this.loc = loc;
  }
  
  @Override
  public void spawn(Player player)
  {
    player.teleport(loc);
  }

}
