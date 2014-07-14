package net.elitesource.serverengine.server;

import java.util.ArrayList;

import net.elitesource.serverengine.ConnectedClient;

public class HeartbeatTimer implements Runnable
{
	private int interval;
	private boolean running = false;
	private long nextTime, currentTime;
	private Server server;

	public HeartbeatTimer(Server server, int interval)
	{
		super();
		this.interval = interval;
		this.server = server;
	}

	public void startTimer()
	{
		this.running = true;
		this.nextTime = System.currentTimeMillis() + (interval);
		while (running)
		{

			this.currentTime = System.currentTimeMillis();
			if (currentTime >= nextTime)
			{
				this.nextTime = System.currentTimeMillis() + (interval);
				this.sendHeartbeats();
			}
		}
	}

	public void stopTimer()
	{
		this.running = false;
	}

	public void sendHeartbeats()
	{
		ArrayList<ConnectedClient> deadClients = new ArrayList<ConnectedClient>();
		for (int i = 0; i < server.getConnectedClients().size(); i++)
		{
			if (!server.getConnectedClients().get(i).sendHeartbeat())
			{
				deadClients.add(server.getConnectedClients().get(i));
			}
		}

		for (int j = 0; j < deadClients.size(); j++)
		{
			server.removeClient(deadClients.get(j));
		}
	}

	@Override
	public void run()
	{
		startTimer();
	}
}
