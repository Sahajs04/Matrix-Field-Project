
import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

/**
 * A generic class that represents a field. It implements the FlexibleIterable interface. 
 * 
 * @author Sahaj Singh 
 * @version Java 8
 */
public class Field<T> implements FlexibleIterable<T> 
{

/**
 * matrix that holds the field as a 2d array and variables that hold height and width of field 
 * 
 */
private T[][] matrix;
private int height;
private int width;

////////////////////////////////////////////////////////////////
/**
 * Constructor sets the matrix dimensions as well as the height and width of the field 
 * 
 * @param height the height of the field 
 * @param width  the width of the field
 */
public Field(int height, int width)
{
    matrix =(T[][]) new Object[height][width];
    this.height = height;
    this.width = width;
}
////////////////////////////////////////////////////////////////
/**
 * returns the element at the passed row and column
 * @param row the specific row
 * @param col the specific column
 * @return the element at that row and column
 */
public T getElement(int row, int col)
{
    return matrix[row][col];
}
/**
 * Sets the element at a specific row and column
 * 
 * @param row
 * @param col
 * @param el the element u want to set the index at that row and column to
 */
public void setElement(int row, int col, T el)
{
    matrix[row][col] = el;
}
/**
 * Getter method 
 * 
 * @return returns the height of the field
 */
public int getHeight()
{
    return height;
}
/**
 * Getter method 
 * 
 * @return returns the width of the field
 */
public int getWidth()
{
    return width;
}
/**
 * toString overide method
 * 
 * @return returns the field as a string
 * 
 */
public String toString()
{
    String s = "";

    for(int i = 0; i < getHeight(); i++)
    {
        for(int j = 0; j < getWidth(); j++)
        {
            s += (matrix[i][j] + " ");
        }
        s+= '\n';
    }
    return s;
}

/**
 * The default iterator.
 * 
 * Implements the hasNext and next methods inside that iterate over every element in the field
 */
public Iterator<T> iterator()
{

    Iterator<T> iter = new Iterator<T>()
    {
        private int rowCount = 0; 
        private int colCount = 0;

        public boolean hasNext()
        {
            if((rowCount <= getHeight()) && (colCount < getWidth()))
            {
                return true;
            }
            else
            {
                return false;
            }
        }
////////////////////////////////////////////////////////////////////////////////////
public T next()
{
    if(hasNext() == true)
    {   
        if(colCount >= getWidth())
        {
            T element = getElement(rowCount, colCount);
            colCount = 0;
            rowCount ++;
            return element;
            
        }
        else
        {
            T element = getElement(rowCount, colCount);
            colCount++;
            return element;
        }

    }
    else
    {
        throw new NoSuchElementException();
    }
}
//////////////////////////////////////////////////////////////////////////////////////////
        public void remove() 
            {
            throw new UnsupportedOperationException();
            }
    };
    return iter;
}
//////////////////////////////////////////////////////////////////
    /**
     * The only thing that this method does is to create and return a new FieldIterator object.
     * 
     * return returns a FieldIterator object
     */
    @Override
    public Iterator<T> iterator(String iterableObjectName)
    {
        FieldIterator<T> iter = new FieldIterator<T>(this, iterableObjectName);
        return iter;
    }


}




