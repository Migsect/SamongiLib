package net.samongi.SamongiLib.Effects.Shapes;

import java.util.List;

import org.bukkit.Location;

import net.samongi.SamongiLib.Effects.Renderers.ParticleRenderer;

public interface EffectShape
{
  public default void render(int particles, ParticleRenderer renderer){for(Location l : this.getRenderLocations(particles)) renderer.render(l);}
  public default void renderRandom(int particles, ParticleRenderer renderer){for(Location l : this.getRenderRandomLocations(particles)) renderer.render(l);}
  public default void renderFuzzy(int particles, ParticleRenderer renderer, double dispersion){for(Location l : this.getRenderFuzzyLocations(particles, dispersion)) renderer.render(l);}
  
  public List<Location> getRenderLocations(int particles);
  public List<Location> getRenderRandomLocations(int particles);
  public List<Location> getRenderFuzzyLocations(int particles, double dispersion);
  
  public default boolean hasSubShapes(){return this.getSubShapes() != null && this.getSubShapes().size() != 0;}
  public List<EffectShape> getSubShapes();
}
