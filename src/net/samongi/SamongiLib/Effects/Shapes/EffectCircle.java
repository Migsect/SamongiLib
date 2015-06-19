package net.samongi.SamongiLib.Effects.Shapes;

import java.util.ArrayList;
import java.util.List;

import net.samongi.SamongiLib.SamongiLib;
import net.samongi.SamongiLib.Vector.SamVector;

import org.bukkit.Location;

public class EffectCircle implements EffectShape
{
  private static double GOLDEN_ANGLE = Math.PI * (3 - Math.sqrt(5));
  private final Location loc;
  private final SamVector planar_vec;
  private final double radius;
  private final boolean solid;
  
  public EffectCircle(Location loc, SamVector planar_vec, double radius)
  {
    this.loc = loc;
    this.planar_vec = planar_vec.normalize();
    this.radius = radius;
    this.solid = false;
  }
  public EffectCircle(Location loc, SamVector planar_vec, double radius, boolean solid)
  {
    this.loc = loc;
    this.planar_vec = planar_vec.normalize();
    this.radius = radius;
    this.solid = solid;
  }
  public double getRadius(){return radius;}
  public SamVector getPlanarVector(){return planar_vec;}
  public boolean isSolid(){return solid;}
  public EffectCircle getSolidVersion(){return new EffectCircle(this.loc, this.planar_vec, this.radius, true);}
  
  @Override
  public List<Location> getRenderLocations(int particles)
  {
    List<Location> locs = new ArrayList<>();
    SamVector perp = planar_vec.getRandomPerpendicular();
    if(solid)
    {
      SamongiLib.debugLog("Rendering Solid Circle with radius: " + this.radius);
      SamongiLib.debugLog("  Golen Angle: " + GOLDEN_ANGLE);
      for(int i = 0 ; i < particles ; i++)
      {
        double theta = i * GOLDEN_ANGLE;
        double r = Math.sqrt(i / (double)particles) * this.radius;
        SamongiLib.debugLog("  i: " + i + " radius: " + r + " theta: " + theta);
        SamVector rot_perp = perp.rotate(theta, planar_vec).normalize().multiply(r);
        SamongiLib.debugLog("  Added rot perp: " + rot_perp.getX() + ", " + rot_perp.getY() + ", " + rot_perp.getZ());
        Location rot_loc = new Location(this.loc.getWorld(), this.loc.getX() + rot_perp.getX(), this.loc.getY() + rot_perp.getY(), this.loc.getZ() + rot_perp.getZ());
        SamongiLib.debugLog("  Added render loc: " + rot_loc.getX() + ", " + rot_loc.getY() + ", " + rot_loc.getZ());
        SamongiLib.debugLog("");
        locs.add(rot_loc);
      }
      return locs;
    }
    else
    {
      for(int i = 0 ; i < particles ; i++)
      {
        double theta = i / particles;
        double r = this.radius;
        SamVector rot_perp = perp.rotate(theta, planar_vec).normalize().multiply(r);
        Location rot_loc = new Location(this.loc.getWorld(), this.loc.getX() + rot_perp.getX(), this.loc.getY() + rot_perp.getY(), this.loc.getZ() + rot_perp.getZ());
        locs.add(rot_loc);
      }
      return locs;
    }
  }

  @Override
  public List<Location> getRenderRandomLocations(int particles)
  {
    return null;
  }

  @Override
  public List<Location> getRenderFuzzyLocations(int particles, double dispersion)
  {
    return null;
  }

  @Override
  public List<EffectShape> getSubShapes(){return null;}

}
