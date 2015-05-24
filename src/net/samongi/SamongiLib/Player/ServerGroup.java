package net.samongi.SamongiLib.Player;

import java.util.ArrayList;
import java.util.List;

import net.samongi.SamongiLib.Lambda.Action.PlayerAction;
import net.samongi.SamongiLib.Lambda.Comparison.PlayerComparison;

import org.bukkit.OfflinePlayer;
import org.bukkit.Server;
import org.bukkit.entity.Player;

public class ServerGroup implements Group
{
	private Server server;
	
	public ServerGroup(Server server)
	{
		this.server = server;
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
		players.addAll(server.getOnlinePlayers());
	  return new StaticGroup(players);
  }

	@Override
  public Group getSubSet(PlayerComparison compare)
  {
	  return new DynamicGroup(this, compare);
  }

	@Override
  public void performAction(PlayerAction action)
  {
	  this.getStaticGroup().performAction(action);
  }

}
