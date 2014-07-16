package net.elitesource.serverengine;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import net.elitesource.serverengine.server.Server;

public class ConnectedClient
{

	private Socket socket;
	private ObjectInputStream inputStream;
	private ObjectOutputStream outputStream;
	private ClientListener clientListener;
	private Server server;

	public ConnectedClient(Socket socket, Server server)
	{
		super();
		try
		{
			System.out.println("[==========] Client [==========]");
			System.out.println("Creating Client Object: " + socket.getInetAddress().getHostAddress() + " [" + socket.getPort() + "]");
			this.server = server;
			this.socket = socket;
			System.out.println("Creating output stream ...");
			this.outputStream = new ObjectOutputStream(socket.getOutputStream());
			System.out.println("Greeting the client...");
			this.outputStream.writeObject("*Handshake*");
			System.out.println("Creating input stream ...");
			this.inputStream = new ObjectInputStream(socket.getInputStream());
			System.out.println("Initializing the client listener...");
			this.clientListener = new ClientListener(this);
			new Thread(this.clientListener).start();
			System.out.println("Client creation successful!");
			System.out.println("[==============================]");

		} catch (Exception e)
		{
			System.out.println("Client Creation failed: " + e.getLocalizedMessage());
			e.printStackTrace();
		}
	}

	public void sendObject(Object object) throws IOException
	{
		for (int i = 0; i < clientListener.getActionListeners().size(); i++)
		{
			clientListener.getActionListeners().get(i).onOutput(this, object);
		}
		this.getOutputStream().writeObject(object);
		this.getOutputStream().flush();
	}

	public boolean sendHeartbeat()
	{
		try
		{
			sendObject("heartbeat");
			return true;
		} catch (Exception e)
		{
			return false;
		}
	}

	public ClientListener getClientListener()
	{
		return this.clientListener;
	}

	public Socket getSocket()
	{
		return socket;
	}

	public ObjectInputStream getInputStream()
	{
		return inputStream;
	}

	public ObjectOutputStream getOutputStream()
	{
		return outputStream;
	}

	public Server getServer()
	{
		return this.server;
	}

	public void kill()
	{
		try
		{
			for (int i = 0; i < clientListener.getActionListeners().size(); i++)
			{
				clientListener.getActionListeners().get(i).onDisconnect(this);
			}
			this.inputStream.close();
			this.outputStream.close();
			this.socket.close();
		} catch (Exception e)
		{
			System.out.println("Unable to correctly terminate client connections for: " + this.socket.getInetAddress().getHostAddress());
			e.printStackTrace();
		}
	}

}
