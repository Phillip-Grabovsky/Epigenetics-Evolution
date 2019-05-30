import java.util.Arrays;
import java.util.List;

public class Main{
  //CONSTANTS
  private static int secondsToFood = 50; //amount of seconds until food is given
  private static int totalTime = 100 //time in days until simulation ends
  private static int startingBacteria = 2;
  private static List culture = new ArrayList()<Bacterium>;
  private static int totalSeconds = totalTime * secondsToFood;


  //INITIALIZE CULUTRE
  culture.add(new Bacterium());
  culture.add(new Bacterium());


  //RUN SIMULATION
  for(int t = 0; t < totalSeconds; t++){
    updateBacteria();
    if(t % secondsToFood == 0){
      addFood();
      replicate();
    }
  }

}
