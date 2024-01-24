public abstract class FuckUp {
    
    float odds;
    
    public FuckUp (float odds) {
        this.odds = odds;
    }
    
    public String fuckUpString(String string) {
        return fuckUpStringBuilder(new StringBuilder(string)).toString();
    }
    
    public StringBuilder tryToFuckUpStringBuilder(StringBuilder stringBuilder, float roll) {
        if(roll < odds) {
            return fuckUpStringBuilder(stringBuilder);
        }
        return stringBuilder;
    }
    
    public abstract StringBuilder fuckUpStringBuilder(StringBuilder stringBuilder);
    
}
