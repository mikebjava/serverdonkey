package net.elitesource.serverengine;

public interface ClientActionListener
{
	/** Method that is called when the server receives input from the client. */
	public void onInput(ConnectedClient source, Object input);

	/** Method that is called when the server sends data to the client. */
	public void onOutput(ConnectedClient source, Object output);

	/** Method that is called when the server detects that the client has disconnected. */
	public void onDisconnect(ConnectedClient source);

	/** Method that is called when the server has successfully established a connection with the client. */
	public void onConnect(ConnectedClient source);
}
