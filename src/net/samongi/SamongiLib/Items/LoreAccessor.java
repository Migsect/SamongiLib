package net.samongi.SamongiLib.Items;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**Used to manipulate the lore of an item as well as
 * access more information from the lore
 * 
 * @author Migsect
 *
 */
public class LoreAccessor
{
  private ItemStack item;
  private ItemMeta item_meta;
  
  private Map<Integer, String> lines = new HashMap<>();
  
  public LoreAccessor(ItemStack item)
  {
    item_meta = item.getItemMeta();
    List<String> lore = item_meta.getLore();
    if(lore == null) return;
    if(lore.size() == 0) return;
    for(int i = 0 ; i < lore.size() ; i ++)
    {
      lines.put(i, lore.get(i)); // Putting all the things into the map.
    }
  }
  
  /**Sets the item's lore to the lore defined within this object
   * This actually mutates the item it is reference.  
   */
  public void setLore()
  {
    item_meta.setLore(this.getLines());
    item.setItemMeta(item_meta);
  }
  
  /**Gets the lines represented as a list of strings.  
   * Some lines may be empty if they are not represented within the
   * internal map.
   * 
   * @return A list of strings represented by this object
   */
  public List<String> getLines()
  {
    List<String> lore = new ArrayList<>();
    int elements = lines.size();
    int remaining_elements = elements;
    int current_line = 0;
    while(remaining_elements != 0)
    {
      String line = lines.get(current_line);
      if(line == null) lore.add(""); // enter a blank line if it got nothing.
      else
      {
        lore.add(line); // because it's not null;
        remaining_elements--; // We found an element so reduce it.
      }
      current_line++; // increment the line we are on.
    }
    return lore;
  }
  
  /**Gets the line's string.  Null if no line exists yet.
   * 
   * @param line The line number;
   * @return The line string
   */
  public String getLine(int line){return this.lines.get(line);}
  /**Sets the line number to the set
   * 
   * @param line The line to be set
   * @param str The string to set the line to.
   */
  public void setLine(int line, String str){this.lines.put(line, str);}
  /**Checks to see if the line is set to anything yet.
   * 
   * @param line The line to check.
   * @return True if the line is set
   */
  public boolean lineExists(int line){return this.lines.containsKey(line);}
  
  public String[] getLoreEnchantData(String enchant)
  {
    // Loop through all the lines, strip all colors and lower case it and see if it starts with enchant.
    for(int line : lines.keySet())
    {
      if(!ChatColor.stripColor(lines.get(line)).toLowerCase().startsWith(enchant.toLowerCase())) continue; // if it doesn't match our criteria, continue
      String str_line = ChatColor.stripColor(lines.get(line)).toLowerCase();
      // The data_tail should be all the values remaining after we get rid of the head.
      String data_tail = str_line.replace(enchant, ""); // replacing the enchantment head.
      return data_tail.split("\\s+");// splits by spaces.
    }
    return null; // We didn't find the string.
  }
  
  public int getLoreEnchantLine(String enchant)
  {
    for(int line : lines.keySet())
    {
      if(!ChatColor.stripColor(lines.get(line)).toLowerCase().startsWith(enchant.toLowerCase())) continue; // if it doesn't match our criteria, continue
      return line;
    }
    return -1; // line not found
  }
  
}
