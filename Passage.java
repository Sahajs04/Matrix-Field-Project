/** 
* The Passage class represents a block that contains a passage
*It extends the block class
*
*@author Sahaj Singh
*@version java 8
*/
public class Passage extends Block
{
    /**
     * The value of the passage
     * 
     */
    private int value;
    /**
     * Sets the value of the passage 
     * 
     * @param value
     */
    public Passage(int value)
    {
        this.value = value;
    }

    /**
     * Simply returns the value of the passage as a string
     */
    public String toString()
    {
        return "" + value;
    }
    /**
     * Getter method for the value of the passage
     */
    public int getValue()
    {
        return value;
    }

}