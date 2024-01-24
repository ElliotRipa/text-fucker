import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {
    
    public static void main(String[] args) throws InterruptedException, IOException {
        
        System.out.println("Would you like to:");
        System.out.println("1. Run the programme\nor");
        // TODO: Implement modification of settings.
        System.out.println("2. Modify the settings?");
        
        Scanner scanner = new Scanner(System.in);
        
        if(scanner.nextLine().equals("1")) {
            
            Properties props = new Properties();
            try {
                props.load(Files.newInputStream(Paths.get("settings.properties")));
            } catch(IOException e) {
                e.printStackTrace();
            }
            
            modifyRepeatedly(props);

        }
        
    }
    
    public static void modifyRepeatedly(Properties props) throws InterruptedException, IOException {
        
        Fucker fucker = new Fucker(props);
        int timeBetweenModifications = Integer.parseInt(props.getProperty("timeBetweenModifications"));
        String inputPath = props.getProperty("inputPath");
        String input = FileReader.readFile(inputPath).get(0);
        boolean useOtherOutput = Boolean.parseBoolean(props.getProperty("useOtherOutput"));
        
        String outputPath;

        if(useOtherOutput) {
            outputPath = props.getProperty("otherOutputPath");
        } else {
            outputPath = inputPath;
        }

        FileWriter fileWriter;        
        
        long time;
        while(true) {
            //time = System.currentTimeMillis();
            TimeUnit.MILLISECONDS.sleep(timeBetweenModifications);
            fileWriter = new FileWriter(outputPath, false);
            String output = fucker.FuckUpString(input);
            System.out.println(output);
            input = output;

            new PrintWriter(outputPath).close();
            fileWriter.write(output);
            fileWriter.flush();
            fileWriter.close();
            //System.out.println((System.currentTimeMillis() - time));
            
        }

    }
    

    
    
    
}