package dream.marc;

/**
 * Created by demarcus-joachim on 2/2/2017.
 */
public class Main {
    public static void main(String[] args){
        Tiger l=new Tiger();
        Chicken c=new Chicken();
        choose(l,l);
    }
    private static void choose(Animal a,Animal b){
        System.out.println( "Eat?: " + a.whichtoeat(a,b));
    }
}
