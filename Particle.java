import java.Math*;
import java.util.*;

public class Particle
{	
	
    private Array<double> velocity;
	private Array<double> position; 
    private Array<int> pBestPos;
	
    private double personalBest;
    private double neighborhoodBest;
    private double currValue;
    
	private Random rand = new Random();

    private Array<Particle> myNeighbors;

    public Particle(Array<double> v, Array<double> p) {

        try {
            velocity = v;
            position = p;
        } catch (Exception* e) {
            System.out.println("Error: Sudden change in dimensionality.");
            System.exit(0);
        }
        
    }

    public void setVel(Array<double> velComps)
    {
        try {
            velocity = velComps;
        } catch (Exception* e) {
            System.out.println("Error: Sudden change in dimensionality.");
            System.exit(0);
        }
    }

    public void setPos(Array<double> posCoords)
    {	
        try {
            position = posCoords;
        } catch (Exception* e) {
            System.out.println("Error: Sudden change in dimensionality.");
            System.exit(0);
        }
    }

    public Array<double> getVel(){
    	return velocity;
    }

   	public Array<double> getPos(){
   		return position;
    }

    public Array<int> setPBestPos(Array<int> a){
        pBestPos = a;
    }

    public void getPBestPos(){
        return pBestPos;
    }

    public void setPBest(double pbest){
    	personalBest = pbest;
    }

    public double getPBest(){
        return personalBest;
    }

    public void addNeighbor(Particle p){
        myNeighbors.add(p);
    }

    public void removeNeighbor(Particle p){
        myNeighbors.remove(p);
    }

    public double getNBest(){
        return neighborhoodBest;
    }

    public void updateNBest(){
        for(p:myNeighbors){
            if(p.getVal() < neighborhoodBest)
                neighborhoodBest = p.getVal();
        }
    }

    //NOTE: "being neighbors," under this logic, does NOT have to be a "two-way street."
    public Bool isNeighborOf(Particle p){
        if(Arrays.asList(myNeighbors).contains(p))
            return true;
        else return false;
    }

    public Array<Particle> getNeighborhood(){
        return myNeighbors;
    }

    public double getVal(){
        return currValue;
    }

    public void setVal(double val){
        currVal = val;
    }
}