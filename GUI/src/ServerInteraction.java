import javax.swing.JOptionPane;

public class ServerInteraction
{
	ConnectionDataParser dataParser;
	ServerInteraction()
	{
		dataParser = new ConnectionDataParser();
	}
	boolean connectionRequest(String ip, String port)
	{
		String ipParsed = dataParser.ipParser(ip);
		try 
		{
			Integer.parseInt(ipParsed);
			Integer.parseInt(port);
		}
		catch (NumberFormatException e)
		{
			return false;
		}
		/*
		 * Tutaj nalezy dodac kod ktory bedzie wchodzuilz  interakcje z serwerem
		 *
		 * 
		 */
		
		/*if wszystko przebieglo pomyslnie
		 *
		 *return true;
		 *
		 * else
		 * 
		 * return false;
		 */
		return true;
	}
	
}
