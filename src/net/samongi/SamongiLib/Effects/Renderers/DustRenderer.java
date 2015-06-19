package net.samongi.SamongiLib.Effects.Renderers;

import org.bukkit.Location;

import com.darkblade12.particleeffect.ParticleEffect;
import com.darkblade12.particleeffect.ParticleEffect.OrdinaryColor;

public class DustRenderer implements ParticleRenderer
{
  final OrdinaryColor color;
  final double distance;
  public DustRenderer(int r, int g, int b, double distance)
  {
    this.color = new OrdinaryColor(r,g,b);
    this.distance = distance;
  }
  @Override
  public void render(Location loc)
  {
    ParticleEffect.REDSTONE.display(color, loc, distance);
  }

}
