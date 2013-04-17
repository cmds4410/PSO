public class Particle
{
    private Array<double> velocity;
    private Array<double> position;
    private double personalBest;
    
    public Particle(double v, double p) {
        
        velocity = v;
        position = p;
        
    }

    public void setVel(Array<double> velComps)
    {

    }

    public void setPos(Array<double> posCoords)
    {

    }

    public Array<double> getVel(){
    	return velocity;
    }

   	public Array<double> getPos(){
   		return position;
    }

    public setPBest(double pbest){
    	
    }

    
}