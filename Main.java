import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

public class Main{
  public static List<Bacterium> culture = new ArrayList<Bacterium>();

  public static void main(String[] args){
    //INITIALIZE CULUTRE
    for(int i = 0; i < CONSTANTS.startingBacteria; i++){
      culture.add(new Bacterium(CONSTANTS.startingEnergy));
    }


    //RUN SIMULATION
    for(int t = 0; t < CONSTANTS.totalSeconds; t++){
      updateBacteria(t%50);
      if(t % CONSTANTS.secondsToFood == 0){
        addFood();
        replicate();
        dailyUpdate(t);
      }
    }
  }

  public static void updateBacteria(int t){
    for(Bacterium b : culture){
      b.transcribe(t%50);
      if(b.getEnergy() < 0){
        culture.remove(b);
      }
    }
  }

  public static void addFood(){
    for(Bacterium b : culture){
      b.takeFood();
    }
  }

  public static void replicate(){
    List<Bacterium> removeList = new ArrayList<Bacterium>();

    for(Bacterium b : culture){
      if(b.getEnergy() > CONSTANTS.replicationEnergy){
        //create 2 new bacteria descended from parent, kill parent.
        culture.add(new Bacterium(b));
        culture.add(new Bacterium(b));
        removeList.add(b);
      }
    }
    for(Bacterium b : removeList){
      culture.remove(b);
    }
  }

  public static void dailyUpdate(int t){
    System.out.println("============UPDATE: Day " + t/50 + "============");
    System.out.println("Total Number: " + culture.size());

  }

}
