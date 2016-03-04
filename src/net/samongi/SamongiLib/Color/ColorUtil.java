package net.samongi.SamongiLib.Color;

import java.awt.Color;

import org.bukkit.potion.PotionEffectType;

public class ColorUtil
{
  /**Returns an approximate color for the potion effect type
   * This is based off the Minecraft wiki page's listed colors
   * And then cross referenced with wikipedia's colors
   * 
   * @param type
   * @return
   */
  public static Color getPotionColor(PotionEffectType type)
  {
    if(type.equals(PotionEffectType.SPEED))             return new Color(125, 249, 255);
    if(type.equals(PotionEffectType.SLOW))              return new Color(102, 153, 204);
    if(type.equals(PotionEffectType.FAST_DIGGING))      return new Color(255, 255,   0);
    if(type.equals(PotionEffectType.SLOW_DIGGING))      return new Color(189, 183, 107);
    if(type.equals(PotionEffectType.INCREASE_DAMAGE))   return new Color(139,   0,   0);
    if(type.equals(PotionEffectType.HEAL))              return new Color(255,   0,   0);
    if(type.equals(PotionEffectType.HARM))              return new Color(128,   0,   0);
    if(type.equals(PotionEffectType.JUMP))              return new Color(102, 255,   0);
    if(type.equals(PotionEffectType.CONFUSION))         return new Color(128,   0, 128);
    if(type.equals(PotionEffectType.REGENERATION))      return new Color(255, 192, 203);
    if(type.equals(PotionEffectType.DAMAGE_RESISTANCE)) return new Color(192,  64,   0);
    if(type.equals(PotionEffectType.FIRE_RESISTANCE))   return new Color(255, 126,   0);
    if(type.equals(PotionEffectType.WATER_BREATHING))   return new Color(  0,   0, 255);
    if(type.equals(PotionEffectType.INVISIBILITY))      return new Color(192, 192, 192);
    if(type.equals(PotionEffectType.BLINDNESS))         return new Color(169, 169, 169);
    if(type.equals(PotionEffectType.NIGHT_VISION))      return new Color(  0,   0, 205);
    if(type.equals(PotionEffectType.HUNGER))            return new Color(128, 128,   0);
    if(type.equals(PotionEffectType.WEAKNESS))          return new Color(128, 128, 128);
    if(type.equals(PotionEffectType.POISON))            return new Color(  0, 255,   0);
    if(type.equals(PotionEffectType.WITHER))            return new Color( 61,  43,  31);
    if(type.equals(PotionEffectType.HEALTH_BOOST))      return new Color(255, 127,   0);
    if(type.equals(PotionEffectType.ABSORPTION))        return new Color(  0, 127, 255);
    if(type.equals(PotionEffectType.SATURATION))        return new Color(255,   0,   0);
    return new Color(0,0,0);
  }
}
