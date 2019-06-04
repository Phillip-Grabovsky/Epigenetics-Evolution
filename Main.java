import java.util.Arrays;
import java.util.List;

public class Main{
  public static List culture = new ArrayList()<Bacterium>;


  public static main(String[] args){
    //INITIALIZE CULUTRE
    for(int i = 0; i < CONSTANTS.startingBacteria; i++){
      culture.add(new Bacterium(startingEnergy));
    }


    //RUN SIMULATION
    for(int t = 0; t < CONSTANTS.totalSeconds; t++){
      updateBacteria(t%50);
      if(t % CONSTANTS.secondsToFoodFood == 0){
        addFood();
        replicate();
      }
    }
  }

  public static updateBacteria(int t){
    for(Bacterium b : culture){
      b.transcribe(t);
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
