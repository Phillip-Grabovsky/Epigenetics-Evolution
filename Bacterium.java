import java.lang.Math;
import java.util.Random;


public class Bacterium {
  //private static Random theRandom = new Random();
  private Random transcriber;
  private Random mutator;
  private int energyLevel;
  private double[] genome = new double[2];
  // genome: 1st number is the base methylation
  // genome: 2nd number is the coefficent which relates
  //           the  time of day to methylation.
  private boolean enzyme = true;

  public Bacterium(int startingEnergy, int seed){
    energyLevel = startingEnergy;
    genome = new double[]{0,0};
    transcriber = new Random(seed);
    mutator = new Random(seed+11);
  }

  public Bacterium(Bacterium Parent, int seed){
    //inherit epigenetic system from parents
    genome = Parent.passDown();
    transcriber = new Random(seed);
    mutator = new Random(seed+11);

    //do some mutations to epigenetics
    double sNumber = mutator.nextDouble();
    double bNumber = mutator.nextDouble();
    System.out.println(sNumber);
    System.out.println(bNumber);
    System.out.println("-----");
    boolean signalMutation =  sNumber < CONSTANTS.sMutationChance;
    boolean baselineMutation = bNumber < CONSTANTS.bMutationChance;
    if(signalMutation){
      boolean plusMinus = mutator.nextDouble() < 0.5;
      if(plusMinus){
        genome[1] -= CONSTANTS.signalMutationSize;
      }
      else{
        genome[1] += CONSTANTS.signalMutationSize;
      }
    }

    if(baselineMutation){
      boolean plusMinus = mutator.nextDouble() < 0.5;
      if(plusMinus){
        genome[0] -= CONSTANTS.baselineMutationSize;
      }
      else{
        genome[0] += CONSTANTS.baselineMutationSize;
      }
    }

    //set starting energy
    energyLevel = CONSTANTS.startingEnergy;
  }

  public void transcribe(int t){
    int timeOfDayMolecules = t;
    double startingMethyl = genome[0];
    double addedMethyl = t*genome[1];
    double totalMethyl = startingMethyl + addedMethyl;

    int attachedMethyl;
    if(totalMethyl > CONSTANTS.numberOfBases) {
      attachedMethyl = CONSTANTS.numberOfBases;
    }
    else if(totalMethyl < 0){
      attachedMethyl = 0;
    }
    else{
      attachedMethyl = (int)Math.round(totalMethyl);
    }

    double blockProbability = (attachedMethyl / CONSTANTS.numberOfBases);
    double transcribeProbability = 1 - blockProbability;
    boolean transcribe = transcriber.nextDouble() < transcribeProbability;

    if(transcribe){
      energyLevel -= CONSTANTS.enzymeCost;
      enzyme = true;
    }
    else{
      enzyme = false;
    }
  }

  public void takeFood(){
    if(enzyme){
      energyLevel += CONSTANTS.foodValue;
    }
  }

  public double[] passDown(){
    return genome;
  }

  public int getEnergy(){
    return energyLevel;
  }
}
