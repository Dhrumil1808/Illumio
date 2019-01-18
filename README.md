# Illumio

This repository contains the code for the coding challenge provided by Illumio.


# Thought Process

In order to tackle this problem, I have created HashSet of the NetworkRule Class in order to make it time efficient because in hashset search operation is done in constant time i.e. O(1) time. I overrided the equals and hashCode method in the NetworkRule Class.  Moreover, in the Firewall Class, I took various scenarious for the inputs like whether the given input has range of IP address and range of ports or they contain single IP address and similarly, single port. Based on different conditions, I added those rules in my HashSet.
The "AcceptPacket" method based on the above implementation returns true of false depending on the rule whether it exists in the sert or not.


# Testing
For Testing, I have used JUnit 4 along with Mockito. I have created 8 test cases considering edge cases too and tested my code for those cases too. The test cases are written in "Firewalltests.java" file.



# Refinements

If I would have more time, I would have implemednted design pattern like Proxy design pattern in order to make the code more readable and more efficient.



# Teams

I am interested in joining the Performance Team.
