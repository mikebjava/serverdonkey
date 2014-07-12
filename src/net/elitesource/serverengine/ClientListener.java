package net.elitesource.serverengine;

import java.util.ArrayList;

public class ClientListener implements Runnable
{

	private ConnectedClient client;
	private ArrayList<ClientActionListener> clientActionListeners = new ArrayList<ClientActionListener>();

	public ClientListener(ConnectedClient client)
	{
		super();
		this.client = client;
	}

	public ArrayList<ClientActionListener> getActionListeners()
	{
		return this.clientActionListeners;
	}

	public ConnectedClient getClient()
	{
		return this.client;
	}

	@Override
	public void run()
	{
		while (!client.getSocket().isClosed())
		{
			Object object = null;
			try
			{
				if ((object = client.getInputStream().readObject()) != null)
				{
					for (int i = 0; i < clientActionListeners.size(); i++)
					{
						clientActionListeners.get(i).onInput(this.getClient(), object);
					}
				}
			} catch (Exception e)
			{
				client.kill();
				e.printStackTrace();
				return;
			}
		}
		System.out.println("Client connection terminated: " + client.getSocket().getInetAddress().getHostAddress());
	}
}
