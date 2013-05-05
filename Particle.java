import java.util.*;

public class Particle
{	
    
	private int index; //used as identifier
	
    private List velocity; //doubles
	private List position; //doubles
    private List pBestPos; //doubles
	private List nBestPos; //doubles
	
    private double personalBest;
    private double neighborhoodBest;
    private double currValue;
    
	private Random rand = new Random();

    private List<Particle> myNeighbors;

    // double both
    public Particle(int i, List v, List p) {
		
		// initialize values
        try {
			index = i;
            velocity = v;
            position = p;
			nBestPos = p;
			pBestPos = p;
            personalBest = Double.MAX_VALUE;
			
            // initialize lists
			myNeighbors = new ArrayList<Particle>();
            
            
        } catch (Exception e) {
            //catch an error resulting from passing less velocity/position components than expected
            System.exit(0);
        }

    }
    
	//getter for index, returns int
	public int getIndex(){
		return index;
	}
	
    //setter for the velocities 
    //double
    public void setVel(List velComps)
    {
        try {
            velocity = velComps;

        } catch (Exception e) {
            System.exit(0);
        }
    }
    
    //setter for the positions
    //double
    public void setPos(List posCoords)
      {
          try {
              position = posCoords;

          } catch (Exception e) {
              System.exit(0);
          }
      }
                
    //double
    public List getVel(){
    	return velocity;
    }

    //double
   	public List getPos(){
   		return position;
    }

    //set the personal best position
    //int? then double param
    public void setPBestPos(List a){
        pBestPos = a;
    }

    public List getPBestPos(){
        return pBestPos;
    }

	//set neighborhood best position
    public void setNBestPos(List a){
        nBestPos = a;
    }

    public List getNBestPos(){
        return nBestPos;
    }

    public void setPBest(double pbest){
    	personalBest = pbest;
    }

    public double getPBest(){
        return personalBest;
    }
	public void setNBest(double nbest){
    	neighborhoodBest = nbest;
    }

    public double getNBest(){
        return neighborhoodBest;
    }

    public void addNeighbor(Particle p){
        myNeighbors.add(p);
    }

    public void clearNeighbors(){
        myNeighbors.clear();
    }

    public void updateNBest(){
        for(Particle p : myNeighbors){
            if(p.getVal() < neighborhoodBest)
                neighborhoodBest = p.getVal();
        }
    }

    public List<Particle> getNeighborhood(){
        return myNeighbors;
    }

    public double getVal(){
        return currValue;
    }

    public void setVal(double val){
        currValue = val;
    }
}