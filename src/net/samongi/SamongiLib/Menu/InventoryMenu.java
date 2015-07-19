package net.samongi.SamongiLib.Menu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import net.samongi.SamongiLib.Menu.ButtomAction.ButtonAction;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InventoryMenu
{
  private final static Map<UUID, InventoryMenu> player_menus = new HashMap<>();
  
  public static void removeMenu(Player player){InventoryMenu.removeMenu(player.getUniqueId());}
  public static void removeMenu(UUID player){InventoryMenu.player_menus.remove(player);}
  public static InventoryMenu getMenu(Player player){return InventoryMenu.getMenu(player.getUniqueId());}
  public static InventoryMenu getMenu(UUID player){return InventoryMenu.player_menus.get(player);}
  public static boolean hasMenuOpen(UUID player){return InventoryMenu.hasMenuOpen(Bukkit.getPlayer(player));}
  public static boolean hasMenuOpen(Player player)
  {
    Inventory menu_inv = player_menus.get(player.getUniqueId()).getInventory();
    if(menu_inv == null) return false;
    if(!player.getOpenInventory().getType().equals(menu_inv)) return false;
    return player.getOpenInventory().getTopInventory().equals(menu_inv);
  }
  public static boolean isMenu(Inventory inventory)
  {
    List<InventoryMenu> menus = InventoryMenu.getMenus();
    for(InventoryMenu m : menus) if(m.getInventory().equals(inventory)) return true;
    return false;
  }
  public static List<InventoryMenu> getMenus(){return new ArrayList<>(InventoryMenu.player_menus.values());}
  
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
    InventoryMenu.player_menus.put(player.getUniqueId(), this);
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
