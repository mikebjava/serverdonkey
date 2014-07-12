package net.elitesource.serverengine.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import net.elitesource.serverengine.ClientActionListener;
import net.elitesource.serverengine.ConnectedClient;
import net.elitesource.serverengine.demo.DemoStart;

public class Server implements Runnable
{

	private ServerSocket serverSocket;
	private boolean isRunning;
	private ArrayList<ConnectedClient> connectedClients = new ArrayList<ConnectedClient>();
	private ArrayList<ClientActionListener> defaultClientListeners = new ArrayList<ClientActionListener>();

	public Server(int port) throws IOException
	{
		super();
		this.serverSocket = new ServerSocket(port);

	}

	public boolean isRunning()
	{
		return isRunning;
	}

	public void setRunning(boolean isRunning)
	{
		this.isRunning = isRunning;
	}

	public ServerSocket getServerSocket()
	{
		return serverSocket;
	}

	public void addClientListener(ClientActionListener cal)
	{
		this.defaultClientListeners.add(cal);
	}

	public void removeClient(ConnectedClient client)
	{
		if (connectedClients.contains(client))
		{
			this.connectedClients.remove(client);
			client.kill();
		}
	}

	@Override
	public void run()
	{
		isRunning = true;
		while (isRunning)
		{
			try
			{
				Socket clientSocket = serverSocket.accept();
				ConnectedClient client = new ConnectedClient(clientSocket);
				if (defaultClientListeners.size() > 0)
				{
					for (int i = 0; i < defaultClientListeners.size(); i++)
					{
						client.getClientListener().getActionListeners().add(defaultClientListeners.get(i));
					}
				}
				if (connectedClients.size() <= 0)
				{
					connectedClients.add(client);
				} else if (connectedClients.size() > 0)
				{
					if (!connectedClients.contains(client))
					{
						connectedClients.add(client);
					}
				}

			} catch (Exception e)
			{
				e.printStackTrace();
			}

		}
	}
}
