package net.samongi.SamongiLib.Locale;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class LocaleUtil
{
  /**Will attempt to get the natural of the player.
   * 
   * @param player The player to get the language for.
   * @return a string depicting the player's lamguage.
   * 
   * @see <a href="https://bukkit.org/threads/get-a-players-minecraft-language.172468/">sheigutn</a>
   */
  public static String getLocale(Player player)
  {
    Object ep = null;
    try
    {
      ep = getMethod("getHandle", player.getClass()).invoke(player, (Object[]) null);
    }
    catch (IllegalAccessException | IllegalArgumentException| InvocationTargetException e)
    {
      e.printStackTrace();
    }
    Field f = null;
    try
    {
      f = ep.getClass().getDeclaredField("locale");
    }
    catch (NoSuchFieldException | SecurityException e)
    {
      e.printStackTrace();
    }
    f.setAccessible(true);
    String language = null;
    try
    {
      language = (String) f.get(ep);
    }
    catch (IllegalArgumentException | IllegalAccessException e)
    {
      e.printStackTrace();
    }
    return language;
  }
  private static Method getMethod(String name, Class<?> clazz) {
    for (Method m : clazz.getDeclaredMethods()) {
    if (m.getName().equals(name))
    return m;
    }
    return null;
  }

  public static String capitalizeFully(String name) {
    if (name != null)
    {
        if (name.length() > 1) 
        {
            if (name.contains("_")) 
            {
                StringBuilder sbName = new StringBuilder();
                for (String subName : name.split("_")) sbName.append(subName.substring(0, 1).toUpperCase() + subName.substring(1).toLowerCase()).append(" ");
                return sbName.toString().substring(0, sbName.length() - 1);
            } 
            else 
            {
                return name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
            }
        } 
        else 
        {
            return name.toUpperCase();
        }
    }
    else 
    {
        return "";
    }
  }
  
  /**Returns the friendly name of a material.
   * 
   * @param material the material
   * @return The friendly name
   * 
   * @see <a href="https://bukkit.org/threads/reflection-friendly-item-names.339467/">KingFaris11</a>
   */
  public static String getFriendlyName(Material material) {
    return material == null ? "Air" : getFriendlyName(new ItemStack(material), false);
  }
  
  private static Class<?> localeClass = null;
  private static Class<?> craftItemStackClass = null, nmsItemStackClass = null, nmsItemClass = null;
  private static String OBC_PREFIX = Bukkit.getServer().getClass().getPackage().getName();
  private static String NMS_PREFIX = OBC_PREFIX.replace("org.bukkit.craftbukkit", "net.minecraft.server");
  
  /**Returns the friendly name of an itemstack
   * 
   * @param itemStack The item stack
   * @param checkDisplayName if true, it will return the display name if the item has one.
   * @return The friendly name.
   * 
   * @see <a href="https://bukkit.org/threads/reflection-friendly-item-names.339467/">KingFaris11</a>
   */
  public static String getFriendlyName(ItemStack itemStack, boolean checkDisplayName) {
    if (itemStack == null || itemStack.getType() == Material.AIR) return "Air";
    try {
        if (craftItemStackClass == null)craftItemStackClass = Class.forName(OBC_PREFIX + ".inventory.CraftItemStack");
        Method nmsCopyMethod = craftItemStackClass.getMethod("asNMSCopy", ItemStack.class);
  
        if (nmsItemStackClass == null) nmsItemStackClass = Class.forName(NMS_PREFIX + ".ItemStack");
        Object nmsItemStack = nmsCopyMethod.invoke(null, itemStack);
        
        Object itemName = null;
        if (checkDisplayName) 
        {
            Method getNameMethod = nmsItemStackClass.getMethod("getName");
            itemName = getNameMethod.invoke(nmsItemStack);
        } 
        else 
        {
            Method getItemMethod = nmsItemStackClass.getMethod("getItem");
            Object nmsItem = getItemMethod.invoke(nmsItemStack);
  
            if (nmsItemClass == null) nmsItemClass = Class.forName(NMS_PREFIX + ".Item");
  
            Method getNameMethod = nmsItemClass.getMethod("getName");
            Object localItemName = getNameMethod.invoke(nmsItem);
  
            if (localeClass == null) localeClass = Class.forName(NMS_PREFIX + ".LocaleI18n");
            Method getLocaleMethod = localeClass.getMethod("get", String.class);
  
            Object localeString = localItemName == null ? "" : getLocaleMethod.invoke(null, localItemName);
            itemName = ("" + getLocaleMethod.invoke(null, localeString.toString() + ".name")).trim();
        }
        return itemName != null ? itemName.toString() : capitalizeFully(itemStack.getType().name().replace("_", " ").toLowerCase());
    }
    catch (Exception ex) 
    {
        ex.printStackTrace();
    }
    return capitalizeFully(itemStack.getType().name().replace("_", " ").toLowerCase());
  }
}