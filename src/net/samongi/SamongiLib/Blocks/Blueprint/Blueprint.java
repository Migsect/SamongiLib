package net.samongi.SamongiLib.Blocks.Blueprint;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Material;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.material.MaterialData;
import org.bukkit.util.BlockVector;

public class Blueprint implements Cloneable
{
  // BlockVector 0,0 dictates the location.
  private final Map<BlockVector, MaterialData> relative_blocks = new HashMap<>();
  
  public Blueprint(Player player, Location p1, Location p2)
  {
    this.addCuboid(player, p1, p2);
  }
  
  public void addCuboid(Player player, Location p1, Location p2)
  {
    int r_x = player.getLocation().getBlockX();
    int r_y = player.getLocation().getBlockY();
    int r_z = player.getLocation().getBlockZ();
    
    int p1_x = p1.getBlockX();
    int p1_y = p1.getBlockY();
    int p1_z = p1.getBlockZ();
    
    int p2_x = p2.getBlockX();
    int p2_y = p2.getBlockY();
    int p2_z = p2.getBlockZ();
    
    // realliging x to the lesser
    int s_x = 0;
    int e_x = 0;
    if(p1_x > p2_x){ s_x = p2_x; e_x = p1_x; }
    else { s_x = p1_x; e_x = p2_x; }
    
    // realligning y to the lesser
    int s_y = 0;
    int e_y = 0;
    if(p1_y > p2_y){ s_y = p2_y; e_y = p1_y; }
    else { s_y = p1_y; e_y = p2_y; }

    // realligning z to the lesser
    int s_z = 0;
    int e_z = 0;
    if(p1_z > p2_z){ s_z = p2_z; e_z = p1_z; }
    else { s_z = p1_z; e_z = p2_z; }
    
    // looping through all block locations.  Including the end and start.
    for(int x = s_x ; x <= e_x; x++) for(int y = s_y ; y <= e_y; y++) for(int z = s_z ; z <= e_z; z++)
    {
      Location loc = new Location(p1.getWorld(), x, y, z);
      MaterialData data = loc.getBlock().getState().getData().clone();
      BlockVector vector = new BlockVector(r_x - x, r_y - y, r_z - z);
      this.relative_blocks.put(vector, data);
    }
  }
  public void add(Blueprint other){this.add(other, false);}
  public void add(Blueprint other, boolean overwrite)
  {
    if(overwrite) for(BlockVector v : other.relative_blocks.keySet())
    {
      this.relative_blocks.put(v, other.relative_blocks.get(v));
    }
    else for(BlockVector v : other.relative_blocks.keySet())
    {
      if(this.relative_blocks.containsKey(v)) continue;
      this.relative_blocks.put(v, other.relative_blocks.get(v));
    }
  }
  public void rotateX(int degrees)
  {
    if(degrees % 90 != 0)  return; 
    degrees = degrees % 360;
    
    Map<BlockVector, MaterialData> new_relative_blocks = new HashMap<>();
    switch (degrees)
    {
      case 0:
        for(BlockVector v : this.relative_blocks.keySet())
        {
          BlockVector new_v = new BlockVector(v.getX(), v.getY(), v.getZ());
          new_relative_blocks.put(new_v, this.relative_blocks.get(v));
        }
        break;
      case 90:
        for(BlockVector v : this.relative_blocks.keySet())
        {
          BlockVector new_v = new BlockVector(v.getX(), -v.getZ(), v.getY());
          new_relative_blocks.put(new_v, this.relative_blocks.get(v));
        }
        break;
      case 180:
        for(BlockVector v : this.relative_blocks.keySet())
        {
          BlockVector new_v = new BlockVector(v.getX(), -v.getY(), -v.getZ());
          new_relative_blocks.put(new_v, this.relative_blocks.get(v));
        }
        break;
      case 270:
        for(BlockVector v : this.relative_blocks.keySet())
        {
          BlockVector new_v = new BlockVector(v.getX(), v.getZ(), -v.getY());
          new_relative_blocks.put(new_v, this.relative_blocks.get(v));
        }
        break;
    }
  }
  
  public void purgeAir()
  {
    for(BlockVector v : relative_blocks.keySet())
    {
      if(relative_blocks.get(v).getItemType().equals(Material.AIR)) relative_blocks.remove(v);
    }
    
  }
}
