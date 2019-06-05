import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main{
  public static List<Bacterium> culture = new ArrayList<Bacterium>();
  public static int seed = 0;

  public static void main(String[] args){
    //INITIALIZE CULUTRE
    for(int i = 0; i < CONSTANTS.startingBacteria; i++){
      culture.add(new Bacterium(CONSTANTS.startingEnergy, i));
    }


    //RUN SIMULATION
    for(int t = 0; t < CONSTANTS.totalSeconds; t++){
      updateBacteria(t%50);
      if(t % CONSTANTS.secondsToFood == 0){
        addFood();
        replicate();
        dailyUpdate(t);
      }
      if(culture.size() > 10000){
        killHalf();
      }
    }

    //DISPLAY GENOME DISTRIBUTIONS
    for(Bacterium b : culture){
      System.out.println(b.passDown()[0] + ", " + b.passDown()[1]);
    }

  }

  public static void updateBacteria(int t){
    List<Bacterium> removeList = new ArrayList<Bacterium>();

    for(Bacterium b : culture){
      b.transcribe(t%50);
      if(b.getEnergy() < 0){
        removeList.add(b);
      }
    }

    for(Bacterium b : removeList){
      culture.remove(b);
    }
  }

  public static void addFood(){
    for(Bacterium b : culture){
      b.takeFood();
    }
  }

  public static void replicate(){
    List<Bacterium> removeList = new ArrayList<Bacterium>();
    List<Bacterium> addList = new ArrayList<Bacterium>();

    for(Bacterium b : culture){
      seed+=2;
      if(b.getEnergy() > CONSTANTS.replicationEnergy){
        //create 2 new bacteria descended from parent, kill parent.
        addList.add(new Bacterium(b, seed));
        addList.add(new Bacterium(b, seed+1));
        removeList.add(b);
      }
    }

    for(Bacterium b : addList){
      culture.add(b);
    }

    for(Bacterium b : removeList){
      culture.remove(b);
    }
  }

  public static void dailyUpdate(int t){
    System.out.println("============UPDATE: Day " + t/50 + "============");
    System.out.println("Total Number: " + culture.size());
    System.out.println("-=-=-=-=-=-genomes-=-=-=-=-=-=-=");
    for(Bacterium b : culture){
      System.out.println(b.passDown()[0] + ", " + b.passDown()[1]);
    }
  }

  public static void killHalf(){
    Random random = new Random();
    List<Bacterium> removeList = new ArrayList<Bacterium>();
    for(Bacterium b : culture){
      if(random.nextDouble() < 0.5){
        removeList.add(b);
      }
    }

    for(Bacterium b : removeList){
      culture.remove(b);
    }
  }

}
