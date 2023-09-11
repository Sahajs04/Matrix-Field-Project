import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

/**
 * A generic class that provides an iterator for the Field. It implements the Iterator interface.
 * The purpose of this iterator is to provide an alternative to the default iteration. Based on the value of the
 * argument iterableObjectName this iterator will not iterate over the entire Field but it will restrict
 * itself to Blocks of specific datatype
 * 
 * @author Sahaj Singh
 */
public class FieldIterator<T> implements Iterator<T>
{
    /**
     * Instance variables
     */
    private String iterableObjectName;//if it isnt you go to the next one 
    private int row; 
    private int column ;
    private Field<T> field;

    /**
     * Constructor for the FieldIterator class 
     * Sets the field and iterable object name and initializes the rows and columns to 0
     * 
     * @param field the current field
     * @param iterableObjectName the object to iterate over
     */
    public FieldIterator(Field<T> field, String iterableObjectName)
    {
        this.field = field; 

        this.iterableObjectName = iterableObjectName;

        column = 0;
        row = 0;
    }

    /**
     * Checks if the field has another object of type iterableObject left 
     * 
     * @return returns true if it has a object of type iterable object left and false if it does not
     */
    public boolean hasNext()
     {
        while((row < field.getHeight()))
        {
            if(field.getElement(row, column).getClass().getName().equals(iterableObjectName))
            {
                return true;
            }
            else
            {
                column++;
                if(column >= field.getWidth() - 1)
                {
                    row++;
                    column = 0;
                }
            }
        }
        return false;
     }
    
    /**
     * Goes to the next itterable object
     * If there are no itterable objects left throws NoSuch element exception
     * 
     * @return returns the next iterable object
     */
    public T next() 
    {
        if(hasNext() == true)
        {   
            T e = field.getElement(row, column);
            if(column == field.getWidth())
            {
                row++;
                column = 0;
            }
            else
            {
                column++;
            }
            return e;
        }
        else
        {
        throw new NoSuchElementException(); 
        }
}
/**
 * not a supported method 
 * throws unspopported operation exception 
 * 
 */
public void remove()
{
    throw new UnsupportedOperationException();
}


}