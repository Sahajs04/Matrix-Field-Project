
/** 
* The Obstacle class represents a block that is a obstacle
*It extends the block class
*
*@author Sahaj Singh
*@version java 8
*/
public class Obstacle extends Block
{   /**
    *The label of the obstacle
     */
    private String label;
    /**
     * Constructor for the obstacle sets the label
     * 
     * @param label
     */
    public Obstacle(String label)
    {
        this.label = label;
    }
    /**
     * toString for the obstacle returns the label
     */
    public String toString()
    {
        return label;
    }
    /**
     * Returns the value of a obstacle (0)
     * 
     */
    public int getValue()
    {
       return 0;
    }

    

}