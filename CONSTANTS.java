import java.util.Random;
import java.lang.Math;

public class CONSTANTS{
  //All Simulation Parameters are here.
  public static String fileName = "DATA_C1_T0";
  public static int secondsInDay = 50; //amount of seconds until food is given
  public static int startingEnergy = 1000;
  public static int startingBacteria = 2; //number of starting bacteria
  public static int enzymeCost = 1; //energy cost to produce 1 enzyme
  public static int foodValue = 1000; //energy given by digesting food
  public static double sMutationChance = 0.1; //probablility of a signalling mutation occuring
  public static double bMutationChance = 0.1; //probablility of a baseline mutation occuring
  public static double signalMutationSize  = 1; //size of a mutation in the signalling pathway
  public static double baselineMutationSize = 1; //size of a mutation in base methyl production.
  public static int numberOfBases = 100; //number of methylatable bases in genome for enzyme.
  public static int replicationEnergy = 6000; //energy level needed to replicate
  public static Random theRandom = new Random(600);
  public static int giveFoodAtTime = 1; //ranges from 0 to the value of

  //time in days until simulation ends
  public static int totalTime = (replicationEnergy - startingEnergy)/(foodValue - secondsInDay*enzymeCost)*1000;
  public static int totalSeconds = totalTime * secondsInDay;

  public static double random(){
    //double randomNum = theRandom.nextDouble();
    double randomNum = Math.random();
    //System.out.println("RANDOM CALLED: " + theRandom.nextDouble());
    return randomNum;
  }
}
