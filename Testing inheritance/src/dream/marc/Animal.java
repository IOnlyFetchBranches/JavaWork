package dream.marc;


public abstract class Animal {
    public abstract String whichtoeat(Animal a,Animal b);
    protected abstract String getSize();
    protected abstract String isNice();
    //String name=this.getClass().toString();
     String getName(){
        return this.getClass().getSimpleName();
    }
}

 class Tiger extends Animal implements Edible{
     public String getName(){
         return "Tiger";
     }
     public String whichtoeat(Animal A,Animal B){
         if (A.getSize().contains("b") && B.getSize().contains("b")){ return "Don't mess with either";}
         if(A.getSize().contains("big") &&  B.getSize().contains("small")){
             return B.getName();
         }
         else{
             return A.getName();
         }
     }
    public String howtoeat(){
        return "don't.";
    }
    public String getSize(){
        return "big";
    }

    @Override
    public String isNice() {
        return "I will kill you!";
    }
}
class Lion extends Animal implements Edible{

    public String whichtoeat(Animal A,Animal B){
        if (A.getSize().contains("big") && B.getSize().contains("big")){ return "Don't mess with either";}
        if(A.getSize().contains("big") &&  B.getSize().contains("small")){
            return B.getName();
        }
        else{
            return A.getName();
        }
    }
    public String howtoeat(){
        return "you can try lol, I aint";
    }
    public String getSize(){
        return "big";
    }

    @Override
    public String isNice() {
        return "I will kill you! (faster)";
    }


}

class Chicken extends Animal implements Edible{

    public String whichtoeat(Animal A,Animal B){
        if ((A.getSize().contains("b") && B.getSize().contains("b") || (A.getSize().contains("B") && B.getSize().contains("B")))){ return "Don't mess with either";}
        if(A.getSize().contains("b") &&  B.getSize().contains("s")){
            return B.getName();
        }
        else{
            return A.getName();
        }
    }
    public String howtoeat(){
        return "Fry Me";
    }
    public String getSize(){
        return "small";
    }

    @Override
    public String isNice() {
        return "I can be Nice at times.";
    }

}
