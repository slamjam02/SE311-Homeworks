public class ConsoleOutput extends Output {
    public ConsoleOutput(){
        super();
    }

    @Override
    public void produceOutput(String string) {
        System.out.print(string);
    }

    @Override
    public boolean completeOutput() {
        return true;
    } 
}