package net.samongi.SamongiLib.Menu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import net.samongi.SamongiLib.Menu.ButtomAction.ButtonAction;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InventoryMenu
{
  private final static Map<UUID, Set<InventoryMenu>> player_menus = new HashMap<>();
  
  public static void removeMenu(Player player,InventoryMenu menu){InventoryMenu.removeMenu(player.getUniqueId(), menu);}
  public static void removeMenu(UUID player, InventoryMenu menu)
  {
    InventoryMenu.player_menus.remove(player);
  }
  public static void addMenu(Player player, InventoryMenu menu){InventoryMenu.addMenu(player.getUniqueId(), menu);}
  public static void addMenu(UUID player, InventoryMenu menu)
  {
    if(!player_menus.containsKey(player)) player_menus.put(player, new HashSet<>());
    player_menus.get(player).add(menu);
  }
  public static Set<InventoryMenu> getMenus(Player player){return InventoryMenu.getMenus(player.getUniqueId());}
  public static Set<InventoryMenu> getMenus(UUID player)
  {
    return InventoryMenu.player_menus.get(player);
  }
  public static InventoryMenu getMenu(Player player, Inventory inv){return getMenu(player.getUniqueId(), inv);}
  public static InventoryMenu getMenu(UUID player, Inventory inv)
  {
    Set<InventoryMenu> menus = InventoryMenu.getMenus();
    for(InventoryMenu m : menus)
    {
      if(m.getInventory().equals(inv)) return m;
    }
    return null;
  }
  public static boolean hasMenuOpen(UUID player){return InventoryMenu.hasMenuOpen(Bukkit.getPlayer(player));}
  public static boolean hasMenuOpen(Player player)
  {
    Set<InventoryMenu> menus = player_menus.get(player.getUniqueId());
    if(menus == null) return false;
    for(InventoryMenu m : menus)
    {
      Inventory m_inv = m.getInventory();
      if(!player.getOpenInventory().getType().equals(m_inv)) return false;
      if(!player.getOpenInventory().getTopInventory().equals(m_inv)) return false;
    }
    return true;
  }
  public static boolean isMenu(Inventory inventory)
  {
    Set<InventoryMenu> menus = InventoryMenu.getMenus();
    for(InventoryMenu m : menus) if(m.getInventory().equals(inventory)) return true;
    return false;
  }
  public static Set<InventoryMenu> getMenus()
  {
    Set<InventoryMenu> menus = new HashSet<>();
    for(Set<InventoryMenu> m : InventoryMenu.player_menus.values())
    {
      menus.addAll(m);
    }
    return menus;
  }
  
  private final Map<Integer, List<ButtonAction>> left_click_buttons = new HashMap<>();
  private final Map<Integer, List<ButtonAction>> shift_left_click_buttons = new HashMap<>();
  private final Map<Integer, List<ButtonAction>> right_click_buttons = new HashMap<>();
  private final Map<Integer, List<ButtonAction>> shift_right_click_buttons = new HashMap<>();
  
  private final Inventory inventory;
  private final UUID player;
  
  public InventoryMenu(Player player, int rows, String title)
  {
    int slots = rows * 9;
    this.player = player.getUniqueId();
    this.inventory = Bukkit.createInventory(null, slots, title);
    InventoryMenu.addMenu(player.getUniqueId(), this);
  }
  
  public void openMenu(){Bukkit.getPlayer(player).openInventory(inventory);}
  
  public void setItem(int slot, ItemStack item){inventory.setItem(slot, item);}
  public Player getPlayer(){return Bukkit.getPlayer(this.player);}
  public Inventory getInventory(){return this.inventory;}
  public void onInventoryClickEvent(InventoryClickEvent event)
  {
    if(event.isRightClick() && event.isShiftClick())
    {
      List<ButtonAction> actions = shift_right_click_buttons.get(event.getSlot());
      if(actions != null) for(ButtonAction a : actions) a.onButtonPress();
    }
    else if(event.isRightClick())
    {
      List<ButtonAction> actions = right_click_buttons.get(event.getSlot());
      if(actions != null) for(ButtonAction a : actions) a.onButtonPress();
    }
    else if(event.isLeftClick() && event.isShiftClick())
    {
      List<ButtonAction> actions = shift_left_click_buttons.get(event.getSlot());
      if(actions != null) for(ButtonAction a : actions) a.onButtonPress();
    }
    else if(event.isLeftClick())
    {
      List<ButtonAction> actions = left_click_buttons.get(event.getSlot());
      if(actions != null) for(ButtonAction a : actions) a.onButtonPress();
    }
  }
  
  public void addClickAction(int slot, ButtonAction action)
  {
    this.addLeftClickAction(slot, action);
    this.addRightClickAction(slot, action);
  }
  public void addLeftClickAction(int slot, ButtonAction action)
  {
    if(!left_click_buttons.containsKey(slot)) left_click_buttons.put(slot, new ArrayList<>());
    left_click_buttons.get(slot).add(action);
    if(!shift_left_click_buttons.containsKey(slot)) shift_left_click_buttons.put(slot, new ArrayList<>());
    shift_left_click_buttons.get(slot).add(action);
  }
  public void addLeftClickAction(int slot, ButtonAction action, boolean shift)
  {
    if(!shift)
    {
      if(!left_click_buttons.containsKey(slot)) left_click_buttons.put(slot, new ArrayList<>());
      left_click_buttons.get(slot).add(action);
    }
    else
    {
      if(!shift_left_click_buttons.containsKey(slot)) shift_left_click_buttons.put(slot, new ArrayList<>());
      shift_left_click_buttons.get(slot).add(action);
    }
  }
  public void addRightClickAction(int slot, ButtonAction action)
  {
    if(!right_click_buttons.containsKey(slot)) right_click_buttons.put(slot, new ArrayList<>());
    right_click_buttons.get(slot).add(action);
    if(!shift_right_click_buttons.containsKey(slot)) shift_right_click_buttons.put(slot, new ArrayList<>());
    shift_right_click_buttons.get(slot).add(action);
  }
  public void addRightClickAction(int slot, ButtonAction action, boolean shift)
  {
    if(!shift)
    {
      if(!right_click_buttons.containsKey(slot)) right_click_buttons.put(slot, new ArrayList<>());
      right_click_buttons.get(slot).add(action);
    }
    else
    {
      if(!shift_right_click_buttons.containsKey(slot)) shift_right_click_buttons.put(slot, new ArrayList<>());
      shift_right_click_buttons.get(slot).add(action);
    }
  }
}
