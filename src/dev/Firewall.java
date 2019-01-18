package dev;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Set;


public class Firewall{

	static Set<NetworkRule> networkrule = new HashSet<>();

	public static void main(String[] args) throws FileNotFoundException {
		
		Firewall firewall = new Firewall("networkrules.csv");
		boolean output1 = firewall.acceptPacket("outbound", "tcp",20000, "192.168.10.11");
		boolean output2 = firewall.acceptPacket("inbound", "tcp",80,"192.168.1.2");
		boolean output3 = firewall.acceptPacket("inbound", "udp",53,"192.168.2.1");
		boolean output4 = firewall.acceptPacket("inbound", "tcp",81,"192.168.1.2");
		boolean output5 = firewall.acceptPacket("inbound", "udp", 24, "52.12.48.92");
		
		System.out.println(output1);
		System.out.println(output2);
		System.out.println(output3);
		System.out.println(output4);
		System.out.println(output5);
		
	}

	public Firewall(String filename) throws FileNotFoundException{
		
		BufferedReader bufferedreader = new BufferedReader(new FileReader(filename));
		
		try{
			String line;
			while((line = bufferedreader.readLine())!= null) {

				String[] rules = line.split(",");

				// covers the port with ranges
				if (rules[2].contains("-")) { 
					
					String[] ports = rules[2].split("-");
					int minport = Integer.parseInt(ports[0]);
					int maxport = Integer.parseInt(ports[1]);
					int portRange = maxport-minport;

					// covers IP address having ranges
					if (rules[3].contains("-")) {
						String[] rangeipAddress = rules[3].split("-");
						long minipAddress = Long.parseLong(rangeipAddress[0].replaceAll("\\.", ""));
						long maxipAddress = Long.parseLong(rangeipAddress[1].replaceAll("\\.", ""));
						long rangeIp = maxipAddress - minipAddress;

				// loop through all the possible ports and the IP Addresses
						for (int i = 0; i <= portRange; i++) {
							for (int j = 0; j <= rangeIp; j++) {
								NetworkRule currRule = new NetworkRule(rules[0], rules[1], minport + i, minipAddress + j);
								networkrule.add(currRule);
							}
						}

						// loop through all the possible ports and the IP Addresses
						for (int i = 0; i <= portRange; i++) {
							for (int j = 0; j <= rangeIp; j++) {
								NetworkRule currRule = new NetworkRule(rules[0], rules[1], minport + i, minipAddress + j);
								networkrule.add(currRule);
							}
						}
					}else{

						// loop through all the possible ports and the IP Addresses
						for (int i = 0; i <= portRange; i++) {
							NetworkRule currRule = new NetworkRule(rules[0],rules[1], minport + i, rules[3]);
							networkrule.add(currRule);
						}
					}
				}  

				else{ 
					/* When rule has range of ip address with just one port*/
					if (rules[3].contains("-")) {
						String [] ipAddressRanges = rules[3].split("-");
						long minipAddress = Long.parseLong(ipAddressRanges[0].replaceAll("\\.", ""));
						long maxipAddress = Long.parseLong(ipAddressRanges[1].replaceAll("\\.", ""));
						long ipRange = maxipAddress - minipAddress;

						//iterate through all possible IP Addresses add them to map
						for (int i = 0; i <= ipRange; i++) {
							NetworkRule currRule = new NetworkRule(rules[0],rules[1],rules[2], minipAddress + i);
							networkrule.add(currRule);
						}
					}
					else{ 
						NetworkRule currRule = new NetworkRule(rules[0],rules[1],rules[2],rules[3]);
						networkrule.add(currRule);
					}

				}

			}
		}
		catch (Exception e) {
			System.out.println("Cannot find file "+ filename );
		}
		
	}

	
	public boolean acceptPacket(String direction, String protocol, int port, String ipAddress) {
		NetworkRule rule = new NetworkRule(direction, protocol, port, ipAddress);
		if (networkrule.contains(rule)) {
			return true;
		}
		else {
			return false;
		}

	}

}
