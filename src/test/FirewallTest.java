package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.BeforeClass;
import org.junit.Test;

import dev.Firewall;

public class FirewallTest {
	
    static Firewall firewall = null;

	@BeforeClass
	public static void setUp() throws Exception {
		firewall = new Firewall("networkrules.csv");
	}

	
	@Test
	public void testAcceptPacket1() {
		boolean acceptPacket = firewall.acceptPacket("outbound", "tcp", 20000, "192.168.10.11");
		assertEquals("This will be true", true, acceptPacket);
	}
	
	@Test
	public void testAcceptPacket2() {
		boolean acceptPacket = firewall.acceptPacket("inbound", "tcp", 20000, "192.168.10.12");
		assertEquals("This will be false", false, acceptPacket);
	}
	@Test
	public void testAcceptPacket3() {
		boolean acceptPacket = firewall.acceptPacket("inbound", "tcp", 80, "192.168.1.2");
		assertEquals("This will be true", true, acceptPacket);
	}
	@Test
	public void testAcceptPacket4() {
		boolean acceptPacket = firewall.acceptPacket("outbound", "tcp", 20001, "192.168.10.11");
		assertEquals("This will be false", false, acceptPacket);
	}
	@Test
	public void testAcceptPacket5() {
		boolean acceptPacket = firewall.acceptPacket("outbound", "udp", 1000, "52.12.48.92");
		assertEquals("This will be true", true, acceptPacket);
	}
	
	@Test
	public void testAcceptPacket6() {
		boolean acceptPacket = firewall.acceptPacket("inbound", "udp", 53, "192.168.2.5");
		assertEquals("This will be true", true, acceptPacket);
	}
	
	@Test
	public void testAcceptPacket7() {
		boolean acceptPacket = firewall.acceptPacket("inbound", "udp", 53, "192.168.10.11");
		assertEquals("This will be false", false, acceptPacket);
	}
	
	
	@Test
	public void testAcceptPacket8() {
		boolean acceptPacket = firewall.acceptPacket("outbound", "udp", 20000, "192.168.10.11");
		assertEquals("This will be false", false, acceptPacket);
	}
	


}