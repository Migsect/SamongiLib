package net.samongi.SamongiLib.Crafting;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;

public class ItemCraftedEvent extends Event {

    // ---------------------------------------------------------------------------------------------------------------//
    private static final HandlerList HANDLERS_LIST = new HandlerList();

    public static HandlerList getHandlerList() {
        return HANDLERS_LIST;
    }

    @Override public HandlerList getHandlers() {
        return HANDLERS_LIST;
    }
    // ---------------------------------------------------------------------------------------------------------------//
    private final CraftItemEvent m_baseEvent;
    private int m_amountCrafted;
    private final ItemStack[] m_craftingMatrix;
    private final Recipe m_recipe;
    private final Player m_player;

    // ---------------------------------------------------------------------------------------------------------------//
    public ItemCraftedEvent(CraftItemEvent baseEvent, int amountCrafted) {
        m_baseEvent = baseEvent;
        m_amountCrafted = amountCrafted;

        m_craftingMatrix = baseEvent.getInventory().getMatrix().clone();
        for(int index = 0; index < m_craftingMatrix.length; index++)
        {
            ItemStack item = m_craftingMatrix[index];
            m_craftingMatrix[index] = item == null ? null : item.clone();
        }
        m_recipe = baseEvent.getRecipe();
        m_player = (Player) baseEvent.getWhoClicked();

    }

    // ---------------------------------------------------------------------------------------------------------------//
    public void setAmountCrafted(int amount) {
        m_amountCrafted = amount;
    }
    public int getAmountCrafted() {
        return m_amountCrafted;
    }

    public CraftItemEvent getBaseEvent() {
        return m_baseEvent;
    }

    public ItemStack[] getMatrix() {
        return m_craftingMatrix;
    }

    public Recipe getRecipe() {
        return m_recipe;
    }
    public Player getPlayer() {
        return m_player;
    }

    // ---------------------------------------------------------------------------------------------------------------//
}
