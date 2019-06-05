public class CONSTANTS{
  //All Simulation Parameters are here.
  public static int secondsToFood = 50; //amount of seconds until food is given
  public static int totalTime = 100; //time in days until simulation ends
  public static int startingEnergy = 100;
  public static int startingBacteria = 2; //number of starting bacteria
  public static int totalSeconds = totalTime * secondsToFood;
  public static int enzymeCost = 1; //energy cost to produce 1 enzyme
  public static int foodValue = 60; //energy given by digesting food
  public static int mitosisLevel; //energy needed to begin reproduction
  public static double sMutationChance = 0.1; //probablility of a signalling mutation occuring
  public static double bMutationChance = 0.1; //probablility of a baseline mutation occuring
  public static double signalMutationSize  = 0.1; //size of a mutation in the signalling pathway
  public static double baselineMutationSize = 0.1; //size of a mutation in base methyl production.
  public static int numberOfBases = 100; //number of methylatable bases in genome for enzyme.
  public static int replicationEnergy = 200; //energy level needed to replicate
}
