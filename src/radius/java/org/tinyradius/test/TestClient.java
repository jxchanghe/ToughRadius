/**
 * $Id: TestClient.java,v 1.4 2006/02/17 18:14:54 wuttke Exp $
 * Created on 08.04.2005
 * @author Matthias Wuttke
 * @version $Revision: 1.4 $
 */
package org.tinyradius.test;

import org.tinyradius.packet.AccessRequest;
import org.tinyradius.packet.AccountingRequest;
import org.tinyradius.packet.RadiusPacket;
import org.tinyradius.util.RadiusClient;

/**
 * Simple Radius command-line client.
 */
public class TestClient {
	
	/**
	 * Radius command line client.
	 * <br/>Usage: TestClient <i>hostName sharedSecret userName password</i>
	 * @param args arguments
	 * @throws Exception
	 */
	public static void main(String[] args) 
	throws Exception {
//		if (args.length != 4) {
//			System.out.println("Usage: TestClient hostName sharedSecret userName password");
//			System.exit(1);
//		}
		
		String host = "127.0.0.1";//args[0];
		String shared = "123456";//args[1];
		String user = "test";//args[2];
		String pass = "test";//args[3];
		
		RadiusClient rc = new RadiusClient(host, shared);
		rc.setAcctPort(1813);

		// 1. Send Access-Request
		AccessRequest ar = new AccessRequest(user, pass);
		ar.setAuthProtocol(AccessRequest.AUTH_PAP); // or AUTH_CHAP
		ar.addAttribute("NAS-Identifier", "this.is.my.nas-identifier.de");
		ar.addAttribute("NAS-IP-Address", "192.168.1.100");
		ar.addAttribute("Service-Type", "Login-User");
		ar.addAttribute("WISPr-Redirection-URL", "http://www.sourceforge.net/");
		ar.addAttribute("WISPr-Location-ID", "net.sourceforge.ap1");
		
		System.out.println("Packet before it is sent\n" + ar + "\n");
		RadiusPacket response = rc.authenticate(ar);
		System.out.println("Packet after it was sent\n" + ar + "\n");
		System.out.println("Response\n" + response + "\n");

		// 2. Send Accounting-Request
		AccountingRequest acc = new AccountingRequest("test", AccountingRequest.ACCT_STATUS_TYPE_START);
		acc.addAttribute("Framed-IP-Address", "192.168.1.100");
		acc.addAttribute("Acct-Session-Id", "1234567890");
		acc.addAttribute("NAS-Identifier", "this.is.my.nas-identifier.de");
		acc.addAttribute("NAS-Port", "0");
	
		System.out.println(acc + "\n");	
		RadiusPacket response2 = rc.account(acc);
		System.out.println("Response: " + response2);
		
		rc.close();
	}
	
}
