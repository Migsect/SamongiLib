package net.samongi.SamongiLib.Effects.Shapes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Location;

import net.samongi.SamongiLib.Vector.SamVector;

public class EffectLocationVector implements EffectVector
{
  private final Location loc;
  private final SamVector vec;
  public List<EffectVector> child_vectors = new ArrayList<>();
 
  public EffectLocationVector(Location loc, SamVector vec)
  {
    this.loc = loc;
    this.vec = vec;
  }
  
  @Override
  public SamVector getVector(){return vec;}
  @Override
  public Location getBaseLocation(){return this.loc;}
  @Override
  public Location getPointLocation(){return new Location(loc.getWorld(), loc.getX() + vec.getX(), loc.getY() + vec.getY(),loc.getZ() + vec.getZ());}

  @Override
  public List<Location> getRenderLocations(int particles)
  {
    List<Location> render_locs = new ArrayList<Location>();
    
    if(particles == 0) return render_locs;
    if(particles == 1)
    {
      render_locs.add(this.loc);
      return render_locs;
    }
    for(int i = 0 ; i <= particles - 1 ; i++)
    {
      double sub_vec_ratio = i / (double)(particles - 1);
      SamVector sub_vec = vec.multiply(sub_vec_ratio);
      Location new_loc = new Location(loc.getWorld(), loc.getX() + sub_vec.getX(), loc.getY() + sub_vec.getY(),loc.getZ() + sub_vec.getZ());
      render_locs.add(new_loc);
    }
    return render_locs;
  }

  @Override
  public List<Location> getRenderRandomLocations(int particles)
  {
    List<Location> render_locs = new ArrayList<Location>();
    
    if(particles == 0) return render_locs;
    if(particles == 1)
    {
      render_locs.add(this.loc);
      return render_locs;
    }
    Random rand = new Random();
    for(int i = 0 ; i <= particles - 1 ; i++)
    {
      double sub_vec_ratio = rand.nextDouble();
      SamVector sub_vec = vec.multiply(sub_vec_ratio);
      Location new_loc = new Location(loc.getWorld(), loc.getX() + sub_vec.getX(), loc.getY() + sub_vec.getY(),loc.getZ() + sub_vec.getZ());
      render_locs.add(new_loc);
    }
    return render_locs;
  }

  @Override
  public List<Location> getRenderFuzzyLocations(int particles, double dispersion)
  {
    List<Location> render_locs = new ArrayList<Location>();
    
    List<Location> random_locs = this.getRenderRandomLocations(particles);
    for(Location l : random_locs)
    {
      SamVector rand_vec = SamVector.getRandomWithinLength(dispersion);
      Location new_loc = new Location(l.getWorld(), l.getX() + rand_vec.getX(), l.getY() + rand_vec.getY(), l.getZ() + rand_vec.getZ());
      render_locs.add(new_loc);
    }
    
    return render_locs;
  }

  @Override
  public List<EffectShape> getSubShapes(){return new ArrayList<EffectShape>(child_vectors);}



}
