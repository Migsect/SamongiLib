package net.samongi.SamongiLib.Potion;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PotionUtil
{
  
  public static PotionEffect parsePotionConfiguration(ConfigurationSection section)
  {
    PotionEffectType type = PotionEffectType.getByName(section.getString("type", ""));
    if(type == null) return null;
    int ticks = section.getInt("ticks", 60);
    int amplified = section.getInt("amplifier", 0);
    boolean particles = section.getBoolean("particles", true);
    boolean ambient = section.getBoolean("particles", false);
    
    PotionEffect effect = new PotionEffect(type, ticks, amplified, particles, ambient);
    return effect;
  }
  public static List<PotionEffect> parseConfiguration(ConfigurationSection section)
  {
    List<PotionEffect> effects = new ArrayList<>();
    if(section == null) return effects;
    Set<String> keys = section.getKeys(false);
    if(keys == null || keys.size() == 0) return effects;
    for(String k : keys)
    {
      PotionEffect e = parsePotionConfiguration(section.getConfigurationSection(k));
      if(e == null) continue;
      effects.add(e);
    }
    return effects;
  }
}
