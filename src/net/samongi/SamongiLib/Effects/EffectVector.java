package net.samongi.SamongiLib.Effects;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.util.Vector;

public class EffectVector
{
  private final Location location;
  private final Vector vector;
  private final ParticleRenderer renderer;
  
  public EffectVector(Location location, Vector vector, ParticleRenderer renderer)
  {
    this.location = location;
    this.vector = vector;
    this.renderer = renderer;
  }
  
  public void renderRandomly(int amount)
  {
    Random rand = new Random();
    for(int i = 0 ; i < amount ; i++)
    {
      Vector displacement = vector.multiply(rand.nextDouble());
      Location disp_loc = location.toVector().add(displacement).toLocation(location.getWorld());
      this.renderer.render(disp_loc);
    }
  }
  public void renderUniform(int amount)
  {
    for(int i = 0 ; i < amount ; i++)
    {
      Vector displacement = vector.multiply(i / (amount-1.0));
      Location disp_loc = displacement.add(location.toVector()).toLocation(location.getWorld());
      this.renderer.render(disp_loc);
    }
  }
  
}
