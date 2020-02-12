package net.samongi.SamongiLib.Items;

import java.io.File;
import java.util.*;

import net.samongi.SamongiLib.SamongiLib;
import net.samongi.SamongiLib.Configuration.ConfigAccessor;
import net.samongi.SamongiLib.Configuration.ConfigFile;
import net.samongi.SamongiLib.Utilities.TextUtil;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerItemBreakEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.material.MaterialData;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import javax.annotation.Nonnull;

public class ItemUtil
{

  private static void debugLog(String msg)
  {
    SamongiLib.debugLog("[ItemUtil] " + msg);
  }
  @SuppressWarnings("unused")
  private static void log(String msg)
  {
    SamongiLib.log("[ItemUtil] " + msg);
  }

  /**
   * Returns true if the input material is a hoe.
   * 
   * @param Material
   *          -> Checked material.
   * @return Boolean -> Returned true if material is a hoe.
   */
  public static boolean isHoe(Material material)
  {
    if (material.equals(Material.WOODEN_HOE)) return true;
    if (material.equals(Material.STONE_HOE)) return true;
    if (material.equals(Material.IRON_HOE)) return true;
    if (material.equals(Material.GOLDEN_HOE)) return true;
    if (material.equals(Material.DIAMOND_HOE)) return true;
    return false;
  }
  /**
   * Returns true if the input material is a hoe.
   * 
   * @param Material
   *          -> Checked material.
   * @return Boolean -> Returned true if material is a hoe.
   */
  public static boolean isSword(Material material)
  {
    if (material.equals(Material.WOODEN_SWORD)) return true;
    if (material.equals(Material.STONE_SWORD)) return true;
    if (material.equals(Material.IRON_SWORD)) return true;
    if (material.equals(Material.GOLDEN_SWORD)) return true;
    if (material.equals(Material.DIAMOND_SWORD)) return true;
    return false;
  }
  /**
   * Returns true if the input material is a Sword.
   * 
   * 
   * @param Material
   *          -> Checked material.
   * @return Boolean -> Returned true if material is a Sword.
   */
  public static boolean isPickaxe(Material material)
  {
    if (material.equals(Material.WOODEN_PICKAXE)) return true;
    if (material.equals(Material.STONE_PICKAXE)) return true;
    if (material.equals(Material.IRON_PICKAXE)) return true;
    if (material.equals(Material.GOLDEN_PICKAXE)) return true;
    if (material.equals(Material.DIAMOND_PICKAXE)) return true;
    return false;
  }
  /**
   * Returns true if the input material is a Pickaxe.
   * 
   * @param Material
   *          -> Checked material.
   * @return Boolean -> Returned true if material is a Pickaxe.
   */
  public static boolean isAxe(Material material)
  {
    if (material.equals(Material.WOODEN_AXE)) return true;
    if (material.equals(Material.STONE_AXE)) return true;
    if (material.equals(Material.IRON_AXE)) return true;
    if (material.equals(Material.GOLDEN_AXE)) return true;
    if (material.equals(Material.DIAMOND_AXE)) return true;
    return false;
  }
  /**
   * Returns true if the input material is a Spade.
   * 
   * @param Material
   *          -> Checked material.
   * @return Boolean -> Returned true if material is a Spade.
   */
  public static boolean isSpade(Material material)
  {
    if (material.equals(Material.WOODEN_SHOVEL)) return true;
    if (material.equals(Material.STONE_SHOVEL)) return true;
    if (material.equals(Material.IRON_SHOVEL)) return true;
    if (material.equals(Material.GOLDEN_SHOVEL)) return true;
    if (material.equals(Material.DIAMOND_SHOVEL)) return true;
    return false;
  }

  public static boolean isArmor(Material material)
  {
    switch(material)
    {
      case LEATHER_BOOTS:
      case LEATHER_CHESTPLATE:
      case LEATHER_HELMET:
      case LEATHER_LEGGINGS:
      case IRON_BOOTS:
      case IRON_CHESTPLATE:
      case IRON_HELMET:
      case IRON_LEGGINGS:
      case GOLDEN_BOOTS:
      case GOLDEN_CHESTPLATE:
      case GOLDEN_HELMET:
      case GOLDEN_LEGGINGS:
      case DIAMOND_BOOTS:
      case DIAMOND_CHESTPLATE:
      case DIAMOND_HELMET:
      case DIAMOND_LEGGINGS:
        return true;
      default:
        return false;
    }
  }

  /**
   * Grabs an ItemStack from the config that is not a bukkit serial based one
   * but a custom setup one (more human readable imo)
   * 
   * @param config
   *          The config.
   * @param path
   *          The path within the config to the itemstack
   * @return The item stack represented by the path.
   */
  public static ItemStack getConfigItemStack(ConfigAccessor config, String path)
  {
    ConfigurationSection section = config.getConfig().getConfigurationSection(path);
    return getConfigItemStack(section);
  }
  /**
   * Grabs an ItemStack from the config that is not a bukkit serial based one
   * but a custom setup one (more human readable imo)
   * 
   * @param config
   *          The config.
   * @param path
   *          The path within the config to the itemstack
   * @return The item stack represented by the path.
   */
  @SuppressWarnings("deprecation")
  public static ItemStack getConfigItemStack(ConfigurationSection section)
  {
    // Getting the material. Defaults to grass.
    ItemUtil.debugLog("Parsing ItemStack for path: '" + section.getCurrentPath() + "'");
    String mat_str = section.getString("material");
    if (mat_str == null) return null;
    Material material = Material.getMaterial(mat_str);
    if (material == null) material = Material.GRASS;
    ItemUtil.debugLog("  Found material: " + material.toString());

    // Getting the amount. Defaults to 1.
    int amount = section.getInt("amount", 1);
    ItemUtil.debugLog("  Found amount: " + amount);

    // Getting the durability
    short durability = (short) section.getInt("durability", 0);
    ItemUtil.debugLog("  Found durability: " + durability);

    // making the first object:
    ItemStack itemstack = new ItemStack(material, amount, durability);
    ItemMeta im = itemstack.getItemMeta();

    // Getting the display name and formatting it. If it is NONE, then it will
    // not set display. Defualt is NONE.
    String display = TextUtil.formatString(section.getString("display-name", "NONE"));
    ItemUtil.debugLog("  Found display: " + display);
    if (!display.equals("NONE")) im.setDisplayName(display);

    // Getting the lore.

    List<String> lore = null;
    if (section.getKeys(false).contains("lore")) lore = TextUtil.formatString(section.getStringList("lore"));
    if (lore != null)
    {
      ItemUtil.debugLog("  Found Lore:");
      if (SamongiLib.debug()) for (String l : lore)
      {
        ItemUtil.debugLog("   - " + l);
      }
      if (lore.size() != 0) im.setLore(lore);
    }

    // Getting if the item is unbreakable:
    boolean bool = section.getBoolean("unbreakable", false);
    im.setUnbreakable(bool);

    // Getting the enchantments
    if (section.getKeys(false).contains("enchantments")) // check to see if the
                                                         // config section
                                                         // exists.
    {
      ItemUtil.debugLog("  Found enchantments:");
      Map<Enchantment, Integer> enchants = new HashMap<>();
      ConfigurationSection enchantments = section.getConfigurationSection("enchantments");
      List<String> enchant_keys = new ArrayList<String>(enchantments.getKeys(false));
      for (String key : enchant_keys)
      {
        Enchantment ench = Enchantment.getByName(key);
        if (ench == null) continue;
        int ench_level = enchantments.getInt(key, 1);
        enchants.put(ench, ench_level);
        ItemUtil.debugLog("   - " + ench.toString() + " : " + ench_level);
      }
      // setting the enchants.
      for (Enchantment e : enchants.keySet())
      {
        im.addEnchant(e, enchants.get(e), true);
      }
    }

    // Checking to see if the item is leather, if it is we will set the color.
    if (im instanceof LeatherArmorMeta)
    {
      // Getting RGB values
      int r = section.getInt("color.red", -1);
      int g = section.getInt("color.green", -1);
      int b = section.getInt("color.blue", -1);
      if (r > 0 && g > 0 && b > 0)
      {
        Color color = Color.fromRGB(r, g, b);
        ((LeatherArmorMeta) im).setColor(color);
      }
    }

    // Checking to see if the item is a potion, if it is we will do potion
    // stuff.
    if (im instanceof PotionMeta)
    {
      PotionMeta pm = (PotionMeta) im;
      // Getting all the effects to be used:
      if (section.getKeys(false).contains("effects")) // check to see if the
                                                      // config section exists.
      {
        List<PotionEffect> effects = new ArrayList<>();
        ConfigurationSection potion_effects = section.getConfigurationSection("effects");
        List<String> effect_keys = new ArrayList<>(potion_effects.getKeys(false));
        for (String e_str : effect_keys)
        {
          SamongiLib.debugLog(" Reading Potion Types: ");
          String type = potion_effects.getString(e_str + ".type");
          SamongiLib.debugLog("    Got type: '" + type + "'");
          PotionEffectType potion_effect = PotionEffectType.getByName(type);
          if (potion_effect == null) continue;
          int strength = potion_effects.getInt(e_str + ".strength", 0);
          SamongiLib.debugLog("    Got strength: '" + strength + "'");
          int duration = potion_effects.getInt(e_str + ".duration", 0);
          SamongiLib.debugLog("    Got duration: '" + duration + "'");
          boolean ambient = potion_effects.getBoolean(e_str + ".ambient", true);
          SamongiLib.debugLog("    Got ambient: '" + ambient + "'");
          boolean particles = potion_effects.getBoolean(e_str + ".particles", true);
          SamongiLib.debugLog("    Got particles: '" + particles + "'");

          PotionEffect effect = new PotionEffect(potion_effect, duration, strength, ambient, particles);
          effects.add(effect);
        }

        if (effects.size() > 0) pm.setMainEffect(effects.get(0).getType());
        if (effects.size() > 0) for (PotionEffect e : effects)
          pm.addCustomEffect(e, true);
      }
    }

    // Setting the itemmeta
    itemstack.setItemMeta(im);

    return itemstack;
  }

  /**
   * Saves an itemstack as a file.
   * 
   * @param to_file
   *          The file to save to
   * @param item
   *          The item to be saved
   */
  public static void serializeItemStack(File to_file, ItemStack item)
  {
    // Loads the config file to save to.
    FileConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(to_file);
    fileConfiguration.set("item", item);
  }
  /**
   * Loads an ItemStack from a singular item file.
   * 
   * @param from_file
   *          The file the itemstack is gotten from.
   * @return The file being returned.
   */
  public static ItemStack deserializeItemStack(File from_file)
  {
    if (!from_file.exists()) {
      return null;
    }
    return YamlConfiguration.loadConfiguration(from_file).getItemStack("item");
  }

  /**
   * Retrieves material data from a string that is in the format:
   * 
   * [Material]
   * [MaterialID]
   * [Material]:[Durability]
   * [MaterialID]:[Durability]
   * 
   * @param mat_str
   *          A string that indicates material and its durability
   * @return
   */
  @SuppressWarnings("deprecation")
  public static MaterialData getMaterialData(String mat_str)
  {
    String[] split_mat = mat_str.split(":");

    Material mat = Material.getMaterial(split_mat[0]);

    if (mat == null) return null;
    if (split_mat.length == 1) return new MaterialData(mat);

    short durability = 0;
    try
    {
      durability = Short.parseShort(split_mat[1]);
    }
    catch (NumberFormatException e)
    {
      durability = -1;
    }
    return new MaterialData(mat, (byte) durability);

  }
  @SuppressWarnings("deprecation")
  public static ItemStack getItemStack(String mat_str)
  {
    String[] split = mat_str.split(":");

    Material mat =  Material.getMaterial(split[0]);

    if (mat == null) return null;
    if (split.length == 1) return new ItemStack(mat);

    // Getting and setting the durability on the item
    short durability = 0;
    try
    {
      durability = Short.parseShort(split[1]);
    }
    catch (NumberFormatException e)
    {
      durability = -1;
    }
    if (split.length == 2) // If the
    {
      ItemStack ret = new ItemStack(mat);
      ret.setDurability(durability);
      return ret;
    }

    int amount = 1;
    try
    {
      amount = Integer.parseInt(split[2]);
    }
    catch (NumberFormatException e)
    {
      amount = 0;
    }
    if (amount < 1)
    {
      ItemStack ret = new ItemStack(mat);
      ret.setDurability(durability);
      return ret;
    }
    ItemStack ret = new ItemStack(mat);
    ret.setDurability(durability);
    ret.setAmount(amount);
    return ret;

  }

  /**
   * This will return the global itemstack that is stored within
   * %SERVER_ROOT%/itemstacks as an item yml
   * 
   * item_str will first be split by ":". If it does not contain a split
   * the root of the config file that is found will be used to determine
   * the itemstacks found.
   * 
   * Will return null if the file does not exist.
   * 
   * Example usage-input:
   * "example_items.yml"
   * "example_items.yml:key1.key2.key3"
   * "example_items:key1"
   * 
   * @param item_str
   *          The item string that represents the file.
   * @return A list of itemstacks found in the location.
   */
  public static List<ItemStack> getGlobalItems(String item_str)
  {
    ItemUtil.debugLog("Getting Global Item for: '" + item_str + "'");
    File root = Bukkit.getServer().getWorldContainer();
    File items_root = new File(root, "items");
    if (!items_root.isDirectory()) items_root = new File(root, "itemstacks");
    if (!items_root.isDirectory())
    {
      ItemUtil.debugLog("Could not find ItemStacks directory");
      return null;
    }

    String[] split = item_str.split(":");
    // The file name is the first item in the split. The root of this file will
    // be used
    String file_name = split[0];
    String section_path;
    if (split.length < 2) section_path = "";
    else section_path = split[1]; // Second half of the split is the
                                  // ConfigurationSection to use.

    File items_file = new File(items_root, file_name);
    if (!items_file.exists() || items_file.isDirectory()) items_file = new File(items_root, file_name + ".yml");
    if (!items_file.exists() || items_file.isDirectory())
    {
      ItemUtil.debugLog("Could not find item directory '" + file_name + "'");
      return null;
    }

    ConfigFile config = new ConfigFile(items_file);
    ConfigurationSection section = config.getConfig().getConfigurationSection(section_path);
    if (section == null)
    {
      ItemUtil.debugLog("Section '" + section_path + "' returned null.  Could not find any items.");
      return new ArrayList<ItemStack>();
    }

    List<ItemStack> items = new ArrayList<>();
    ItemStack base_item = ItemUtil.getConfigItemStack(section);

    if (base_item == null)
    {
      Set<String> keys = section.getKeys(false);
      for (String k : keys)
      {
        ItemStack i = ItemUtil.getConfigItemStack(section.getConfigurationSection(k));
        if (i == null) ItemUtil.debugLog("Skipping over key '" + k + "'");
        if (i == null) continue;
        items.add(i);
      }
    }
    else items.add(base_item);
    ItemUtil.debugLog("Found item count: " + items.size());
    return items;
  }

  /**Attempts to damage the item as if the item was just used. Follows rules for unbreaking and indestructable.
   * If the item was not damaged for any reason (canceled events, not a damageable item) then this will return false;
   * @param ItemStack
   * @return
   */
  public static boolean damageItem(@Nonnull Player player, @Nonnull ItemStack item)
  {
    ItemMeta itemMeta = item.getItemMeta();
    if (!(itemMeta instanceof Damageable)) {
      return false;
    }
    Damageable damageableMeta = (Damageable) itemMeta;

    if(item.getItemMeta().isUnbreakable())
    {
      return false;
    }

    int unbreakingLevel = item.getItemMeta().getEnchants().getOrDefault(Enchantment.DURABILITY, 0);
    if(unbreakingLevel > 0)
    {
      double durableChance = 0;
      if(isArmor(item.getType()))
      {
        durableChance = 0.6 + (0.4 / (unbreakingLevel + 1) );
      } else {
        durableChance = 1.0 / (unbreakingLevel + 1);
      }

      Random rand = new Random();
      if(durableChance < rand.nextDouble()) {
        return false;
      }
    }

    PlayerItemDamageEvent itemDamageEvent = new PlayerItemDamageEvent(player, item, 1);
    Bukkit.getPluginManager().callEvent(itemDamageEvent);
    if (itemDamageEvent.isCancelled()) {
      return false;
    }

    damageableMeta.setDamage(damageableMeta.getDamage() + 1);
    item.setItemMeta((ItemMeta)damageableMeta);

    // We are now going to break the item if its above its max durability.
    if (damageableMeta.getDamage() < item.getType().getMaxDurability()) {
      return true;
    }
    PlayerItemBreakEvent itemBreakEvent = new PlayerItemBreakEvent(player, item);
    Bukkit.getPluginManager().callEvent(itemBreakEvent);

    // We are going to set the item to air.
    item.setType(Material.AIR);

    // Making the sound the broken item.
    player.playSound(
        player.getLocation(),
        Sound.ENTITY_ITEM_BREAK, 1F, 1F
    );
    return true;
  }

}
