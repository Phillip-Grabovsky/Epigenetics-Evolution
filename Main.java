import java.util.Arrays;
import java.util.List;

public class Main{
  //CONSTANTS
  private static int secondsToFood = 50; //amount of seconds until food is given
  private static int totalTime = 100 //time in days until simulation ends
  private static int startingBacteria = 2;
  private static List culture = new ArrayList()<Bacterium>;
  private static int totalSeconds = totalTime * secondsToFood;



  public static main(String[] args){
    //INITIALIZE CULUTRE
    for(int i = 0; i < startingBacteria; i++){
      culture.add(new Bacterium(startingEnergy));
    }


    //RUN SIMULATION
    for(int t = 0; t < totalSeconds; t++){
      updateBacteria(t%50);
      if(t % secondsToFood == 0){
        addFood();
        replicate();
      }
    }
  }

  public static updateBacteria(int t){
    for(Bacterium b : culture){
      b.transcribe();
    }
  }

  public static addFood(){
    for(Bacterium b : culture){
      b.takeFood();
    }
  }

  public static replicate(){
    for(Bacterium b : culture){
      if(b.sufficentEnergy){
        //create 2 new bacteria descended from parent, kill parent.
        culture.add(new Bacterium(b));
        culture.add(new Bacterium(b));
        culture.remove(b);
      }
    }
  }

}
