package com.michaeljustin.servertest;

import java.net.Socket;

public interface IServerHandler
{
	public void onConnectionAccept(Socket socket);

	public void onConnectionLost(Socket socket);

	public void onInformationReceived(Socket socket);

	public void onServerStart(Server server);

	public void onServerStop(Server server);
}
