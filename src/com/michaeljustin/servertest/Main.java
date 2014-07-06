package com.michaeljustin.servertest;

public class Main
{

	public static void main(String[] args)
	{
		new Server(1234, 5).addServerHandler(new ExampleServerHandler());
	}

}
