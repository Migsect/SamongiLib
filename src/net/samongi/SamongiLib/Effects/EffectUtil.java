package net.samongi.SamongiLib.Effects;

import java.util.Random;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import com.darkblade12.particleeffect.ParticleEffect;
import com.darkblade12.particleeffect.ParticleEffect.OrdinaryColor;

public class EffectUtil
{
  /**Creates a cloud of randomly distributed colored dust particles around the location.
   * 
   * @param loc The location to center the particles around.
   * @param r The red channel color of the particle
   * @param g The green channel color of the particle
   * @param b The blue channel color of the particle
   * @param amount The amount of particles to generate
   * @param radius The maximum radius a particle will be at
   */
  public static void displayDustSphereCloud(Location loc, int r, int g, int b, int amount, double radius)
  {
    double radius_sqr = radius * radius;
    
    OrdinaryColor color = new OrdinaryColor(r,g,b);
    
    Random rand = new Random();
    
    for(int i = 0; i < amount; i++)
    {
      double x_sqr = radius_sqr * rand.nextDouble();
      double y_sqr = (radius_sqr - x_sqr) * rand.nextDouble();
      double z_sqr = (radius_sqr - x_sqr - y_sqr) * rand.nextDouble();
      double x = Math.sqrt(x_sqr);
      double y = Math.sqrt(y_sqr);
      double z = Math.sqrt(z_sqr);
      if(rand.nextBoolean()) x = -x;
      if(rand.nextBoolean()) y = -y;
      if(rand.nextBoolean()) z = -z;
      Location particle_loc = new Location(loc.getWorld(), loc.getX() + x, loc.getY() + y, loc.getZ() + z);
      ParticleEffect.REDSTONE.display(color, particle_loc, 25);
      // loc.getWorld().spigot().playEffect(particle_loc, effect, 0, 1, r / 256, g / 256, b / 256, 1, 0, 20);
    }
  }
  public static void displayDustCylinderCloud(Location loc, int r, int g, int b, int amount, double radius, double height)
  {
    double radius_sqr = radius * radius;
    double height_half = height / 2;
    
    OrdinaryColor color = new OrdinaryColor(r,g,b);
    
    Random rand = new Random();
    
    for(int i = 0; i < amount; i++)
    {
      double x_sqr = radius_sqr * rand.nextDouble();
      double y_sqr = height_half * rand.nextDouble();
      double z_sqr = (radius_sqr - x_sqr) * rand.nextDouble();
      double x = Math.sqrt(x_sqr);
      double y = Math.sqrt(y_sqr);
      double z = Math.sqrt(z_sqr);
      if(rand.nextBoolean()) x = -x;
      if(rand.nextBoolean()) y = -y;
      if(rand.nextBoolean()) z = -z;
      Location particle_loc = new Location(loc.getWorld(), loc.getX() + x, loc.getY() + y, loc.getZ() + z);
      ParticleEffect.REDSTONE.display(color, particle_loc, 25);
      // loc.getWorld().spigot().playEffect(particle_loc, effect, 0, 1, r / 256, g / 256, b / 256, 1, 0, 20);
    }
  }
  /**Creates a cloud of randomly distributed colored spell particles around the location.
   * 
   * @param loc The location to center the particles around.
   * @param r The red channel color of the particle
   * @param g The green channel color of the particle
   * @param b The blue channel color of the particle
   * @param amount The amount of particles to generate
   * @param radius The maximum radius a particle will be at
   */
  public static void displayMagicSphereCloud(Location loc, int r, int g, int b, int amount, double radius)
  {
    double radius_sqr = radius * radius;
    
    OrdinaryColor color = new OrdinaryColor(r,g,b);
    
    Random rand = new Random();
    
    for(int i = 0; i < amount; i++)
    {
      double x_sqr = radius_sqr * rand.nextDouble();
      double y_sqr = (radius_sqr - x_sqr) * rand.nextDouble();
      double z_sqr = (radius_sqr - x_sqr - y_sqr) * rand.nextDouble();
      double x = Math.sqrt(x_sqr);
      double y = Math.sqrt(y_sqr);
      double z = Math.sqrt(z_sqr);
      if(rand.nextBoolean()) x = -x;
      if(rand.nextBoolean()) y = -y;
      if(rand.nextBoolean()) z = -z;
      Location particle_loc = new Location(loc.getWorld(), loc.getX() + x, loc.getY() + y, loc.getZ() + z);
      ParticleEffect.SPELL_MOB.display(color, particle_loc, 25);
      // loc.getWorld().spigot().playEffect(particle_loc, effect, 0, 1, r / 256, g / 256, b / 256, 1, 0, 20);
    }
  }
  /**Creates a cloud of randomly distributed fire  particles around the location.
   * 
   * @param loc The location to center the particles around.
   * @param amount The amount of particles to generate
   * @param radius The maximum radius a particle will be at
   */
  public static void displayFireSphereCloud(Location loc, int amount, double radius)
  {
    double radius_sqr = radius * radius;
    
    
    Random rand = new Random();
    
    for(int i = 0; i < amount; i++)
    {
      double x_sqr = radius_sqr * rand.nextDouble();
      double y_sqr = (radius_sqr - x_sqr) * rand.nextDouble();
      double z_sqr = (radius_sqr - x_sqr - y_sqr) * rand.nextDouble();
      double x = Math.sqrt(x_sqr);
      double y = Math.sqrt(y_sqr);
      double z = Math.sqrt(z_sqr);
      if(rand.nextBoolean()) x = -x;
      if(rand.nextBoolean()) y = -y;
      if(rand.nextBoolean()) z = -z;
      Location particle_loc = new Location(loc.getWorld(), loc.getX() + x, loc.getY() + y, loc.getZ() + z);
      ParticleEffect.FLAME.display(new Vector(0,0,0), 0, particle_loc, 25);
      // loc.getWorld().spigot().playEffect(particle_loc, effect, 0, 1, r / 256, g / 256, b / 256, 1, 0, 20);
    }
  }
  
  public static void markLocation(JavaPlugin plugin, Location location, Effect effect, int ticks)
  {
    BukkitRunnable marker_task = new BukkitRunnable()
    {
      int remaining_ticks = ticks;
      @Override
      public void run()
      {
        if(remaining_ticks < 0) return;
        remaining_ticks--;
        location.getWorld().playEffect(location, effect, 0);
        this.runTaskLater(plugin, 1);
      }
    };
    marker_task.run();
  }
}
