import java.util.Scanner;

public class ConsoleInput extends Input {
    Scanner scanner;
    public ConsoleInput(){
        super();
        scanner = new Scanner(System.in);
    }
    public String getInput(){
        return scanner.nextLine();
    }
}
