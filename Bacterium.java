

public class Bacterium {
  private int energyLevel;
  private double[] genome = new double[2];
  // genome: 1st number is the base methylation
  // genome: 2nd number is the coefficent which relates
  //           the  time of day to methylation.

  public Bacterium(int startingEnergy){
    energyLevel = startingEnergy;
    genome = new double[]{0,0};
  }

  public Bacterium(Bacterium Parent){
    //inherit epigenetic system from parents
    genome = Parent.passDown();

    //do slight mutations.
  }

  public boolean transcribe(t){
    int timeOfDayMolecules = t;
    double startingMethyl = genome[0];
    double addedMethyl = t*genome[1];
  }

  public double[] passDown(){
    return genome;
  }


}
