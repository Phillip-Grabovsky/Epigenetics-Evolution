import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Main{
  public static List<Bacterium> culture = new ArrayList<Bacterium>();
  public static int seed = 0;

  public static void main(String[] args){
    //INITIALIZE CULUTRE
    for(int i = 0; i < CONSTANTS.startingBacteria; i++){
      culture.add(new Bacterium(CONSTANTS.startingEnergy));
    }


    //RUN SIMULATION
    for(int t = 0; t < CONSTANTS.totalSeconds; t++){
      updateBacteria(t%CONSTANTS.secondsInDay);
      if(t % CONSTANTS.secondsInDay == CONSTANTS.giveFoodAtTime){
        addFood();
        replicate();
        dailyUpdate(t);
      }
      if(culture.size() > 10000){
        killHalf();
      }
    }

    //DISPLAY GENOME DISTRIBUTIONS
    String newLine = System.getProperty("line.separator");
    try {
  		FileOutputStream fos = new FileOutputStream("C:\\Users\\bob\\Documents\\GitHub\\Epigenetics-Evolution\\"  + CONSTANTS.fileName + ".txt");
  		DataOutputStream dos = new DataOutputStream(fos);
  		for(Bacterium b : culture){
  				dos.writeUTF(Double.toString(b.passDown()[0]));
  				dos.writeUTF(", ");
          dos.writeUTF(Double.toString(b.passDown()[1]));
          dos.writeUTF("\n");
  			}
  		dos.close();
  	}
		catch (IOException e) {
			System.out.println("IOException : " + e);
		}

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
        addList.add(new Bacterium(b));
        addList.add(new Bacterium(b));
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
    /*for(Bacterium b : culture){
      System.out.println(b.passDown()[0] + ", " + b.passDown()[1]);
    }*/
  }

  public static void killHalf(){
    List<Bacterium> removeList = new ArrayList<Bacterium>();
    for(Bacterium b : culture){
      if(CONSTANTS.random() < 0.5){
        removeList.add(b);
      }
    }

    for(Bacterium b : removeList){
      culture.remove(b);
    }
  }

}
