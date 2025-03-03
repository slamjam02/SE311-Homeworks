package drexel.se311.kwicserver;

public class ConsoleOutput extends Output {


    public ConsoleOutput(){
        super();
    }


    @Override
    public void addLineToOutput(String string) {
        super.outputLines.add(string);
    }

    @Override
    public void complete() {
        System.out.println();
        for (String string : super.outputLines) {
            System.out.println(string);
        }
        System.out.println();
    }

    
}