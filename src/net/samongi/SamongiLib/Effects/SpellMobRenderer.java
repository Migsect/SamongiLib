package net.samongi.SamongiLib.Effects;

import org.bukkit.Location;

import com.darkblade12.particleeffect.ParticleEffect;
import com.darkblade12.particleeffect.ParticleEffect.OrdinaryColor;

public class SpellMobRenderer implements ParticleRenderer
{
  final OrdinaryColor color;
  public SpellMobRenderer(int r, int g, int b)
  {
    this.color = new OrdinaryColor(r,g,b);
  }
  @Override
  public void render(Location loc)
  {
    ParticleEffect.SPELL_MOB.display(color, loc, 50);
  }

}
