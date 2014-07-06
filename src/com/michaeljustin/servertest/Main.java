package com.michaeljustin.servertest;

import java.io.IOException;

public class Main
{

	public static void main(String[] args)
	{
		Server server  =new Server(1234, 5);
		server.addServerHandler(new ExampleServerHandler());
		try
		{
			server.startServer();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
