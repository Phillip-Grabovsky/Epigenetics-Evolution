import java.util.Random;


public class Bacterium {
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
    Random myRandom = new Random();
    boolean signalMutation = myRandom.nextDouble() < CONSTANTS.sMutationChance;
    boolean baselineMutation = myRandom.nextDouble() < CONSTANTS.bMutationChance;
    if(signalMutation){
      boolean plusMinus = myRandom.nextDouble() < 0.5;
      if(plusMinus){
        genome[1] -= CONSTANTS.signalMutationSize;
      }
      else{
        genome[1] += CONSTANTS.signalMutationSize;
      }
    }

    if(baselineMutation){
      boolean plusMinus = myRandom.nextDouble() < 0.5;
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

    double attachedMethyl;
    if(totalMethyl > CONSTANTS.numberOfBases) {
      attachedMethyl = CONSTANTS.numberOfBases;
    }
    else if(totalMethyl < 0){
      attachedMethyl = 0;
    }
    else{
      attachedMethyl = totalMethyl;
    }

    double blockProbability = (attachedMethyl / CONSTANTS.numberOfBases);
    double transcribeProbability = 1 - blockProbability;
    Random myRandom = new Random();
    boolean transcribe = myRandom.nextDouble() < transcribeProbability;

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
