import java.io.File;

public class PathChecker {
    public static String getValidPath(Input input){
        System.out.println("Please provide a filename... ");

        String path = input.getInput();

        File file = new File(path);
        while(!path.endsWith(".txt") || !(file.canRead())){
            System.out.println("The file you are trying to open either doesn't exist or is unsupported... Try again.");

            path = input.getInput();
            file = new File(path);
        }

        return path;
    }
}
