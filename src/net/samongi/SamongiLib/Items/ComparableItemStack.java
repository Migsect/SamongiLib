package net.samongi.SamongiLib.Items;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.inventory.ItemStack;

public class ComparableItemStack
{
  private final ItemStack item;
  private boolean doExclusion = true;
  private Set<ItemComparison> comparisons;
  
  public ComparableItemStack(ItemStack item)
  {
    this.item = item;
    this.comparisons = new HashSet<>();
  }
  public ComparableItemStack(ItemStack item, Set<ItemComparison> comparisons)
  {
    this.item = item;
    this.comparisons = comparisons;
  }
  public ComparableItemStack(ItemStack item, Set<ItemComparison> comparisons, boolean doExclusion)
  {
    this.item = item;
    this.comparisons = comparisons;
    this.doExclusion = doExclusion;
  }
  
  public boolean equals(Object o)
  {
    if((o instanceof ItemStack))
    {
      if(doExclusion) return exclusionEquals((ItemStack)o, this.comparisons);
      else return inclusionEquals((ItemStack)o, this.comparisons);
    }
    else if((o instanceof ComparableItemStack))
    {
      return ((ComparableItemStack)o).hashCode() == o.hashCode();
    }
    return false;
  }
  public boolean equals(ComparableItemStack o){return ((ComparableItemStack)o).hashCode() == o.hashCode();}
  public boolean equals(ItemStack o)
  {
    if(doExclusion) return exclusionEquals((ItemStack)o, this.comparisons);
    else return inclusionEquals((ItemStack)o, this.comparisons);
  }
  
  public int hashCode()
  {
    int hash_sum = 0;
    if(doExclusion) 
    {
      for(ItemComparison comp : ItemComparison.values()) if(!comparisons.contains(comp)) switch(comp)
      {
        case TYPE:          hash_sum += this.item.getType().hashCode(); break;
        case AMOUNT:        hash_sum += this.item.getAmount(); break;
        case DISPLAYNAME:   hash_sum += this.item.getItemMeta().hashCode(); break;
        case DURABILITY:    hash_sum += this.item.getDurability() * 64; break;
        case ENCHANTMENTS:  hash_sum += this.item.getEnchantments().hashCode(); break;
        case ITEM_META:     hash_sum += this.item.getItemMeta().hashCode(); break;
        case LORE:          hash_sum += this.item.getItemMeta().getLore().hashCode(); break;
        //case MATERIAL_DATA: hash_sum += this.item.getData().hashCode();
        case UNBREAKABLE:   if(this.item.getItemMeta().spigot().isUnbreakable()) hash_sum += 1; break;
      }
    }
    else 
    {  
      for(ItemComparison comp : ItemComparison.values()) if(comparisons.contains(comp)) switch(comp)
      {
        case TYPE:          hash_sum += this.item.getType().hashCode(); break;
        case AMOUNT:        hash_sum += this.item.getAmount(); break;
        case DISPLAYNAME:   hash_sum += this.item.getItemMeta().hashCode(); break;
        case DURABILITY:    hash_sum += this.item.getDurability() * 64; break;
        case ENCHANTMENTS:  hash_sum += this.item.getEnchantments().hashCode(); break;
        case ITEM_META:     hash_sum += this.item.getItemMeta().hashCode(); break;
        case LORE:          hash_sum += this.item.getItemMeta().getLore().hashCode(); break;
        //case MATERIAL_DATA: hash_sum += this.item.getData().hashCode();
        case UNBREAKABLE:   if(this.item.getItemMeta().spigot().isUnbreakable()) hash_sum += 1; break;
      }
    }
    return hash_sum;
  }
  
  public final boolean exclusionEquals(ItemStack item, Set<ItemComparison> comparator)
  {
    for(ItemComparison comp : ItemComparison.values()) if(!comparator.contains(comp)) switch(comp)
    {
      case TYPE:          if(!this.equalType(item)) return false;
      case AMOUNT:        if(!this.equalAmount(item)) return false;
      case DISPLAYNAME:   if(!this.equalDisplayName(item)) return false;
      case DURABILITY:    if(!this.equalDurability(item)) return false;
      case ENCHANTMENTS:  if(!this.equalEnchantments(item)) return false;
      case ITEM_META:     if(!this.equalItemMeta(item)) return false;
      case LORE:          if(!this.equalLore(item)) return false;
      //case MATERIAL_DATA: if(!this.equalMaterialData(item)) return false;
      case UNBREAKABLE:   if(!this.equalUnbreakable(item)) return false;  
    }
    return true;   
  }
  public final boolean inclusionEquals(ItemStack item, Set<ItemComparison> comparator)
  {
    for(ItemComparison comp : ItemComparison.values()) if(comparator.contains(comp)) switch(comp)
    {
      case TYPE:          if(!this.equalType(item)) return false;
      case AMOUNT:        if(!this.equalAmount(item)) return false;
      case DISPLAYNAME:   if(!this.equalDisplayName(item)) return false;
      case DURABILITY:    if(!this.equalDurability(item)) return false;
      case ENCHANTMENTS:  if(!this.equalEnchantments(item)) return false;
      case ITEM_META:     if(!this.equalItemMeta(item)) return false;
      case LORE:          if(!this.equalLore(item)) return false;
      //case MATERIAL_DATA: if(!this.equalMaterialData(item)) return false;
      case UNBREAKABLE:   if(!this.equalUnbreakable(item)) return false;  
    }
    return true;  
  }
  
  public final ItemStack getItem(){return this.item;}
  
  public final boolean equalType(ItemStack item){return this.item.getType().equals(item.getType());}
  public final boolean equalDurability(ItemStack item){return this.item.getDurability() == item.getDurability();}
  public final boolean equalAmount(ItemStack item){return this.item.getAmount() == item.getAmount();}
  public final boolean equalMaterialData(ItemStack item){return this.item.getData().equals(item.getData());}
  public final boolean equalDisplayName(ItemStack item){return this.item.getItemMeta().getDisplayName().equals(item.getItemMeta().getDisplayName());}
  public final boolean equalLore(ItemStack item){return this.item.getItemMeta().getLore().equals(item.getItemMeta().getLore());}
  public final boolean equalEnchantments(ItemStack item){return this.item.getItemMeta().getEnchants().equals(item.getItemMeta().getEnchants());}
  public final boolean equalUnbreakable(ItemStack item){return this.item.getItemMeta().spigot().isUnbreakable() == item.getItemMeta().spigot().isUnbreakable();}
  public final boolean equalItemMeta(ItemStack item){return this.item.getItemMeta().equals(item.getItemMeta());}
  
  public enum ItemComparison
  {
    TYPE,
    DURABILITY,
    AMOUNT,
    //MATERIAL_DATA,
    DISPLAYNAME,
    LORE,
    ENCHANTMENTS,
    UNBREAKABLE,
    ITEM_META;
  }
}
