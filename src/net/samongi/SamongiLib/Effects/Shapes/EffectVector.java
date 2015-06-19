package net.samongi.SamongiLib.Effects.Shapes;

import org.bukkit.Location;

import net.samongi.SamongiLib.Vector.SamVector;

public interface EffectVector extends EffectShape
{
  public SamVector getVector();
  public Location getBaseLocation();
  public Location getPointLocation();
}
