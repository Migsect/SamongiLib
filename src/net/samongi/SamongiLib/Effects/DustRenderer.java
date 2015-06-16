package net.samongi.SamongiLib.Effects;

import org.bukkit.Location;

import com.darkblade12.particleeffect.ParticleEffect;
import com.darkblade12.particleeffect.ParticleEffect.OrdinaryColor;

public class DustRenderer implements ParticleRenderer
{
  final OrdinaryColor color;
  public DustRenderer(int r, int g, int b)
  {
    this.color = new OrdinaryColor(r,g,b);
  }
  @Override
  public void render(Location loc)
  {
    ParticleEffect.REDSTONE.display(color, loc, 50);
  }

}
