import java.util.ArrayList;
import java.util.Properties;
import java.util.Random;

public class Fucker {

    ArrayList<FuckUp> fuckersUppers = new ArrayList<>();
    float switchOdds;
    float increaseOdds;
    float decreaseOdds;
    float dropOdds;
    float cloneOdds;

    Random random;

    public Fucker(Properties props) {
        
        switchOdds = Float.parseFloat(props.getProperty("switchOdds"));
        fuckersUppers.add(new Swapper(switchOdds));
        
        increaseOdds = Float.parseFloat(props.getProperty("increaseOdds"));
        fuckersUppers.add(new Increaser(increaseOdds));
        
        decreaseOdds = Float.parseFloat(props.getProperty("decreaseOdds"));
        fuckersUppers.add(new Decreaser(decreaseOdds));
        
        dropOdds = Float.parseFloat(props.getProperty("dropOdds"));
        fuckersUppers.add(new Dropper(dropOdds));
        
        cloneOdds = Float.parseFloat(props.getProperty("cloneOdds"));
        fuckersUppers.add(new Cloner(cloneOdds));

        random = new Random();
    }

    public String FuckUpString(String input) {

        StringBuilder output = new StringBuilder(input);
        
        for(FuckUp fucker : fuckersUppers) {
            output = fucker.tryToFuckUpStringBuilder(output, random.nextFloat());
        }

        return output.toString();

    }
    
    
    // TODO: Make better constructors, or a factory.
    public class Swapper extends FuckUp {

        public Swapper(float odds) {
            super(odds);
        }

        public StringBuilder fuckUpStringBuilder(StringBuilder stringBuilder) {
            int swapIndex = random.nextInt(stringBuilder.length() - 1);

            char temp = stringBuilder.charAt(swapIndex);
            stringBuilder.setCharAt(swapIndex, stringBuilder.charAt(swapIndex + 1));
            stringBuilder.setCharAt(swapIndex + 1, temp);
            
            return stringBuilder;
        }
        
    }

    public class Decreaser extends FuckUp {

        public Decreaser(float odds) {
            super(odds);
        }

        public StringBuilder fuckUpStringBuilder(StringBuilder stringBuilder) {
            
            int decreaseIndex = random.nextInt(stringBuilder.length());
            stringBuilder.setCharAt(decreaseIndex, (char)(stringBuilder.charAt(decreaseIndex) - 1));

            return stringBuilder;
        }

    }

    public class Increaser extends FuckUp {

        public Increaser(float odds) {
            super(odds);
        }

        public StringBuilder fuckUpStringBuilder(StringBuilder stringBuilder) {

            int decreaseIndex = random.nextInt(stringBuilder.length());
            stringBuilder.setCharAt(decreaseIndex, (char)(stringBuilder.charAt(decreaseIndex) + 1));

            return stringBuilder;
        }

    }

    public class Dropper extends FuckUp {

        public Dropper(float odds) {
            super(odds);
        }

        public StringBuilder fuckUpStringBuilder(StringBuilder stringBuilder) {

            int dropIndex = random.nextInt(stringBuilder.length());
            stringBuilder.deleteCharAt(dropIndex);

            return stringBuilder;
        }

    }

    public class Cloner extends FuckUp {

        public Cloner(float odds) {
            super(odds);
        }

        public StringBuilder fuckUpStringBuilder(StringBuilder stringBuilder) {

            int cloneIndex = random.nextInt(stringBuilder.length());
            stringBuilder.insert(cloneIndex, stringBuilder.charAt(cloneIndex));

            return stringBuilder;
        }

    }

}