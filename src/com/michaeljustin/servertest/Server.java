package com.michaeljustin.servertest;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server
{
	private int PORT;
	private int maxConnections;
	private Socket socket;
	private ServerSocket serverSocket;
	private boolean running;

	public Server(int PORT, int maxConnections)
	{
		this.PORT = PORT;
		this.maxConnections = maxConnections;
	}

	public void startServer() throws IOException
	{
		serverSocket = new ServerSocket(PORT);
		setRunning(true);
		while (running)
		{
			socket = serverSocket.accept();
		}
	}

	public void StopServer(int exitValue) throws IOException
	{
		// TODO close sockets and disconnect users
		socket.close();
		serverSocket.close();
		System.out.println("Sockets closed");
		System.exit(exitValue);
	}

	// getters/setters
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

}
