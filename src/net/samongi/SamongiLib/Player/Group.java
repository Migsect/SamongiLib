package net.samongi.SamongiLib.Player;

import java.util.List;

import org.bukkit.entity.Player;

/**
 * 
 * @author Migsect
 *
 */
public interface Group
{
	/**Counts the number of players the group represents
	 * 
	 * @return The number of players the group currently contains.
	 */
	public int countPlayers();
	
	/**Counts the number of online players that the group represents.
	 * 
	 * @return The number of players the group currently contains.
	 */
	public int countOnlinePlayers();
	
	
	/**Gets a list of players during the current state of the group.
	 * 
	 * @return The list of players.
	 */
	public List<Player> getPlayers();
	
	/**Returns a static group that will not change dynamically.
	 * 
	 * @return A group that will not change dynamically.
	 */
	public Group getStaticGroup();
	
	/**Gets a subset of the current group that will dynamically adjust depending on the passed in expression
	 * 
	 * @param compare A lamba expression that compares returns a boolean based off a player.
	 * @return A dynamic group that changes depending on the passed comparison.
	 */
	public Group getSubSetGroup(Comparer compare);
	/** Used for Lambda expressions for getSubSetGroup */
	public interface Comparer
	{
		boolean include(Player p);
	}
	
	/**Performs an action based on the passed through lamba expression.
	 * 
	 * @param action A lamba expression that performs an action of a player.
	 */
	public void performAction(Action action);
	/** Used for Lambda expressions for performAction */
	public interface Action
	{
		void perform(Player p);
	}
	
}
