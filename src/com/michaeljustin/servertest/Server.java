package com.michaeljustin.servertest;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server
{
	private int PORT;
	private int maxConnections;
	private Socket socket;
	private ServerSocket serverSocket;
	private boolean running;
	private ArrayList<IServerHandler> serverHandlers = new ArrayList<IServerHandler>();

	public Server(int PORT, int maxConnections)
	{
		this.PORT = PORT;
		this.maxConnections = maxConnections;
	}

	public void startServer() throws IOException
	{

		serverSocket = new ServerSocket(PORT);
		setRunning(true);

		for (int i = 0; i < serverHandlers.size(); i++)
		{
			serverHandlers.get(i).onServerStart(this);
		}

		while (running)
		{
			socket = serverSocket.accept();
			for (int i = 0; i < serverHandlers.size(); i++)
			{
				serverHandlers.get(i).onConnectionAccept(socket);
			}
		}

	}

	public void stopServer() throws IOException
	{
		// TODO close sockets and disconnect users
		for (int i = 0; i < serverHandlers.size(); i++)
		{
			serverHandlers.get(i).onServerStop(this);
		}
		socket.close();
		serverSocket.close();
		System.out.println("Sockets closed");
	}

	public int getPort()
	{
		return PORT;
	}

	public void setPort(int PORT)
	{
		this.PORT = PORT;
	}

	public int getMaxConnections()
	{
		return maxConnections;
	}

	public void setMaxConnections(int maxConnections)
	{
		this.maxConnections = maxConnections;
	}

	public Socket getSocket()
	{
		return socket;
	}

	public ServerSocket getServerSocket()
	{
		return serverSocket;
	}

	public boolean isRunning()
	{
		return running;
	}

	public void setRunning(boolean running)
	{
		this.running = running;
	}

	public void addServerHandler(IServerHandler handler)
	{
		if (handler != null)
		{
			this.serverHandlers.add(handler);
		} else
		{
			throw new NullPointerException("Server could not register server handler; serverhandler is null.");
		}
	}
}
