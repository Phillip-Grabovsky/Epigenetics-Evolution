import java.lang.Math;


public class Bacterium {
  //private static Random theRandom = new Random();
  private int energyLevel;
  private double[] genome = new double[2];
  // genome: 1st number is the base methylation
  // genome: 2nd number is the coefficent which relates
  //           the  time of day to methylation.
  private boolean enzyme = true;

  public Bacterium(int startingEnergy){
    energyLevel = startingEnergy;
    genome = new double[]{0,0};
  }

  public Bacterium(Bacterium Parent){
    //inherit epigenetic system from parents
    genome = Parent.passDown();

    //do some mutations to epigenetics
    double s = CONSTANTS.random();
    double b = CONSTANTS.random();
    boolean signalMutation =  s < CONSTANTS.sMutationChance;
    boolean baselineMutation = b < CONSTANTS.bMutationChance;
    if(signalMutation){
      boolean plusMinus = CONSTANTS.random() < 0.5;
      if(plusMinus){
        genome[1] -= CONSTANTS.signalMutationSize;
      }
      else{
        genome[1] += CONSTANTS.signalMutationSize;
      }
    }

    if(baselineMutation){
      boolean plusMinus = CONSTANTS.random() < 0.5;
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

    double blockProbability = (totalMethyl / CONSTANTS.numberOfBases);
    double transcribeProbability = 1 - blockProbability;
    boolean transcribe = CONSTANTS.random() < transcribeProbability;

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
    return new double[]{genome[0], genome[1]};
  }

  public int getEnergy(){
    return energyLevel;
  }
}
