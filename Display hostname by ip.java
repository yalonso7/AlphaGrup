// Program to Display host name by IP address.

import java.net.*;

class HostName
{
	public static void main( String args[ ] ) 
	{
		// byte ad[ ] = { (byte)192, (byte)168, 1, 2 };
		
		try
		{
			// InetAddress ip = InetAddress.getByAddress( ad );
			InetAddress ip = InetAddress.getByAddress( new byte[ ] { (byte)192, (byte)168, 1, 2 } );
			System.out.println("  " + ip.getHostName());
		}
		catch( UnknownHostException ue )
		{
			System.out.println(" Can't find given host.");
		}		
	}
}


