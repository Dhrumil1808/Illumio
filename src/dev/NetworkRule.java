package dev;

public class NetworkRule {

	 protected String direction;
     protected String protocol;
     protected int port;
     protected long IPaddress;

   
     public NetworkRule(String direction, String protocol, String port, String ipAddress) {
         this.direction = direction;
         this.protocol = protocol;
         this.port = Integer.parseInt(port);
         this.IPaddress = Long.parseLong(ipAddress.replaceAll("\\.", "")); 
     }

     public NetworkRule(String direction, String protocol, String port, long ipAddress) {
         this.direction = direction;
         this.protocol = protocol;
         this.port = Integer.parseInt(port);
         this.IPaddress = ipAddress;
     }

     public NetworkRule(String direction, String protocol, int port, long ipAddress) {
         this.direction = direction;
         this.protocol = protocol;
         this.port = port;
         this.IPaddress = ipAddress;
     }

     public NetworkRule(String direction, String protocol, int port, String ipAddress) {
         this.direction = direction;
         this.protocol = protocol;
         this.port = port;
         this.IPaddress = Long.parseLong(ipAddress.replaceAll("\\.", "")); 
     }
     
     @Override
     public boolean equals(Object o) {
         if (this == o) return true;
         if (!(o instanceof NetworkRule)) return false;
         NetworkRule networkRule = (NetworkRule) o;
         return  direction.equalsIgnoreCase(networkRule.direction) && protocol.equalsIgnoreCase(networkRule.protocol)
        		 && port == networkRule.port && IPaddress == networkRule.IPaddress;
     }

     @Override
     public String toString() {
         return this.direction +  ", " + this.protocol + ", " + Integer.toString(this.port) + ", " + Long.toString(this.IPaddress);
     }

     public int hashCode() {
         long hash =  31 * (this.IPaddress + this.port + this.direction.hashCode() + this.protocol.hashCode());
         return Long.valueOf(hash).hashCode();
     }
}
