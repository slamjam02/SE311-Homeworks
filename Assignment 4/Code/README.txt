To run the server, run the CalculatorServer.jar in the Server folder using the command line

java -jar CalculatorServer.jar <optional: PORT (default: 5555)>

If you do not have a log file, one will be generated within the working directory the first time you use the client to perform a successful equation.

To view the current count of successful equations and the list of equations from the log, simply type "print" or you
may exit the application with "quit".



To run the client, run the CalculatorClient.jar in the Client folder using the command line

java -jar CalculatorClient.jar <optional: IP (default: localhost)> <optional: PORT (default: 5555)>

If you are not running the server, the client will still work but it will display a message that logging is disabled.
If the client cannot find the server and attempts connecting for a while, it will timeout after 30 seconds and open afterwards.
