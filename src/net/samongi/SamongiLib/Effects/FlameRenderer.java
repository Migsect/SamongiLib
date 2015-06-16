package net.samongi.SamongiLib.Effects;

import org.bukkit.Location;
import org.bukkit.util.Vector;

import com.darkblade12.particleeffect.ParticleEffect;

public class FlameRenderer implements ParticleRenderer
{
  final Vector direction;
  final float speed;
  
  FlameRenderer()
  {
    direction = new Vector(0,0,0);
    this.speed = 0;
  }
  FlameRenderer(Vector direction, float speed)
  {
    this.direction = direction;
    this.speed = speed;
  }
  @Override
  public void render(Location loc)
  {
    ParticleEffect.FLAME.display(direction, speed, loc, 50);
    
  }

}
