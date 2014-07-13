package com.exampleuse.src;

import net.elitesource.serverengine.server.Server;

public class ExampleMain
{

	static Server server;

	public static void main(String[] args)
	{
		try
		{
			new ExampleMain();
		} catch (Exception e)
		{
			System.err.println("Exmaple has failed to start.");
			e.printStackTrace();
		}
	}

	public ExampleMain() throws Exception
	{
		server = new Server(9214);
		server.addClientListener(new ExampleClientListener());
		Thread serverThread = new Thread(server);
		serverThread.start();
	}

}
