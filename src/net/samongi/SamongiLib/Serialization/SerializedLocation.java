package net.samongi.SamongiLib.Serialization;

import java.io.Serializable;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class SerializedLocation implements Serializable
{
  private static final long serialVersionUID = 4094535798118065173L;
  private double x;
  private double y;
  private double z;
  private String world;
  
  /**Constructs a serilizable location object.
   * 
   * @param loc The location to make serializable.
   */
  public SerializedLocation(Location loc)
  {
    this.x = loc.getX();
    this.y = loc.getY();
    this.z = loc.getZ();
  }
  public Location getLocation()
  {
    return new Location(Bukkit.getWorld(world),x,y,z);
  }
}
