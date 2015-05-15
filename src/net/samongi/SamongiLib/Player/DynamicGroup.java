package net.samongi.SamongiLib.Player;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class DynamicGroup implements Group
{
	Comparer filter;
	Group group;
	
	public DynamicGroup(Group group, Comparer filter)
	{
		this.group = group;
		this.filter = filter;
	}
	
	@Override
  public int countPlayers()
  {
	  return this.getStaticGroup().countPlayers();
  }

	@Override
  public int countOnlinePlayers()
  {
		return this.getStaticGroup().countOnlinePlayers();
  }

	@Override
  public List<Player> getPlayers()
  {
		return this.getStaticGroup().getPlayers();
  }

	@Override
  public Group getStaticGroup()
  {
		List<OfflinePlayer> players = new ArrayList<>();
	  for(Player p : group.getPlayers())
	  {
	  	if(filter.include(p)) players.add(p);
	  }
	  return new StaticGroup(players);
  }

	@Override
  public Group getSubSetGroup(Comparer compare)
  {
	  return new DynamicGroup(this, compare);
  }

	@Override
  public void performAction(Action action)
  {
	  this.getStaticGroup().performAction(action);
  }

}
