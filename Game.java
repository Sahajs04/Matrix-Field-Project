import java.util.Scanner;


import java.io.File;
import java.util.Iterator;
import java.util.ArrayList;
/** 
* This class represents the game running 
*it also contains the main method
*
*@author Sahaj Singh
*@version java 8
*/
public class Game
{
    /**
     * Recursivley goes through and calculates the best starting point 
     * 
     * @param board the field
     * @return returns a object of type TwoValues conaining the index and points of the best starting point 
     */
    public static TwoValues bestStartingPoint(Field<Block> board)
    {
        int max = 0;
        int col = 0;
        int temp = 0;
        for(int i = 0; i < board.getWidth(); i++)
        {
            temp = helperMethod(board, 0,i );
            if(temp > max)
            {
                max = temp;
                col = i;
            }
        }
        TwoValues two = new TwoValues();

        two.startingColumn = col;
        two.totalPoints = max;
        return two;

    }
    /**
     * Calculates the best route to the end of the field based on the starting point given
     * 
     * @param board the field
     * @param col the column to iterate from
     * @return
     */
    public static ArrayList<Block> bestRoute(Field<Block> board, int col)
    {
            TwoValues startingPoint = bestStartingPoint(board);
            int row = 0;
            int column = col;
            int max;
            ArrayList<Block> route = new ArrayList<Block>();
            route.add(board.getElement(row, column));
            while(row < board.getHeight() - 1)
            {
            if((column == 0) || (board.getElement(row, column -1) instanceof Obstacle))
            {
             if(board.getElement(row + 1, column) instanceof Obstacle)
            {
                route.add(board.getElement( row + 1, column + 1));
                row++; 
                column++;
                continue;
            }
            else if(board.getElement(row + 1, column + 1) instanceof Obstacle)
            {
                route.add(board.getElement(row + 1, column));
                row++;
                continue;
            }
            else
            {
                int points = Math.max( board.getElement(row +1, column).getValue(),board.getElement( row +1, column + 1).getValue());
                if(board.getElement(row +1, column).getValue() > board.getElement( row +1, column + 1).getValue())
                {
                    route.add(board.getElement(row + 1, column));
                    row++;
                    continue;
                }
                else
                {
                    route.add(board.getElement(row + 1, column + 1));
                    row++;
                    column++;
                    continue;
                }
            }
        }
        else if((column == board.getWidth()-1) || board.getElement(row + 1, column + 1) instanceof Obstacle)
        {
            if(board.getElement(row + 1, column) instanceof Obstacle)
            {
               route.add(board.getElement(row + 1, column - 1));
               row ++; 
               column--;
               continue;
            }
            else if(board.getElement(row + 1, column -1) instanceof Obstacle)
            {
                route.add(board.getElement(row + 1, column));
                row++;
                continue;

            }
            else
            {
                int points = Math.max(board.getElement(row +1, column).getValue(), board.getElement( row +1, column -1).getValue());
                if(board.getElement(row +1, column).getValue() > board.getElement( row +1, column -1).getValue())
                {
                    route.add(board.getElement( row +1, column));
                    row++;
                    continue;
                }
                else
                {
                    route.add(board.getElement( row + 1, column -1));
                    row++;
                    column++;
                    continue;
                }
            }

        }
        else 
        {
            if(board.getElement(row +1, column) instanceof Obstacle)
            {
                int total = Math.max(board.getElement(row +1, column+1).getValue(), board.getElement( row +1, column -1).getValue());
                if(board.getElement(row +1, column+1).getValue() > board.getElement( row +1, column -1).getValue())
                {
                   route.add(board.getElement( row +1, column +1));
                   row++;
                   column++;
                   continue;
                }
                else 
                {
                    route.add(board.getElement(row +1, column -1));
                    row++; 
                    column--;
                    continue;
                }
            }
            else 
            {
                int points = Math.max(Math.max(board.getElement(row +1, column+1).getValue(), board.getElement( row +1, column -1).getValue()), board.getElement(row +1, column).getValue());

                if(points == board.getElement(row +1, column+1).getValue() )
                {
                    route.add(board.getElement(row + 1, column + 1));
                    row++; 
                    column++;
                    continue;
                }
                else if(points == board.getElement( row +1, column -1).getValue())
                {
                    route.add(board.getElement(row +1, column -1));
                    row++; 
                    column--;
                    continue;
                }
                else
                {
                    route.add(board.getElement(row + 1, column));
                    row++;
                    continue;
                }
            }
        }
    } 
    return route;      
}   

    /**
     * helper method to find the best starting point recursivley calls itself to calculate the total points
     * @param field the field being used 
     * @param row the current row
     * @param column the current column
     * @return
     */
    private static int helperMethod(Field<Block> field, int row, int column)
    {
        if((row >= field.getHeight() -1) || column >= field.getWidth() -1) 
        {
            return 0;
            
        }
        int maxPoints = field.getElement(row, column).getValue();
        if((column == 0) || field.getElement(row, column -1) instanceof Obstacle)
        {
            if(field.getElement(row + 1, column) instanceof Obstacle)
            {
                maxPoints += field.getElement(row + 1, column + 1).getValue();
                maxPoints += helperMethod(field,row + 1, column );
            }
            else if(field.getElement(row + 1, column + 1) instanceof Obstacle)
            {
                maxPoints += field.getElement(row + 1, column + 1).getValue();
                maxPoints += helperMethod(field, row +1 , column +1);
            }
            else
            {
                maxPoints += Math.max(field.getElement(row +1, column).getValue(), field.getElement( row +1, column + 1).getValue());
                if(field.getElement(row +1, column).getValue() > field.getElement( row +1, column + 1).getValue())
                {
                    maxPoints += helperMethod(field, row +1, column);
                }
                else
                {
                    maxPoints += helperMethod(field, row +1, column +1);
                }
            }
        }

        else if((column == field.getWidth()-1) || field.getElement(row + 1, column + 1) instanceof Obstacle)
        {
            if(field.getElement(row + 1, column) instanceof Obstacle)
            {
                maxPoints +=  field.getElement(row + 1, column -1).getValue();
                maxPoints += helperMethod(field, row + 1, column -1);
            }
            else if(field.getElement(row + 1, column -1) instanceof Obstacle)
            {
                maxPoints += field.getElement(row + 1, column).getValue();
                maxPoints += helperMethod(field, row +1, column);

            }
            else
            {
                maxPoints += Math.max(field.getElement(row +1, column).getValue(), field.getElement( row +1, column -1).getValue());
                if(field.getElement(row +1, column).getValue() > field.getElement( row +1, column -1).getValue())
                {
                    maxPoints += helperMethod(field, row +1, column);
                }
                else
                {
                    maxPoints += helperMethod(field, row + 1, column -1);
                }
            }

        }
        else 
        {
            if(field.getElement(row +1, column) instanceof Obstacle)
            {
                maxPoints += Math.max(field.getElement(row +1, column+1).getValue(), field.getElement( row +1, column -1).getValue());
                if(field.getElement(row +1, column+1).getValue() > field.getElement( row +1, column -1).getValue())
                {
                    maxPoints += helperMethod(field, row +1, column +1);
                }
                else 
                {
                    maxPoints += helperMethod(field, row +1, column -1);
                }
            }
            else 
            {
                int max = Math.max(Math.max(field.getElement(row +1, column+1).getValue(), field.getElement( row +1, column -1).getValue()), field.getElement(row +1, column).getValue());
                maxPoints += max;
                if(max == field.getElement(row +1, column+1).getValue() )
                {
                    maxPoints += helperMethod(field, row + 1, column +1);
                }
                else if(max == field.getElement( row +1, column -1).getValue())
                {
                    maxPoints += helperMethod(field, row +1, column -1);
                }
                else
                {
                    maxPoints += helperMethod(field, row + 1, column);
                }
            }
        }
        return maxPoints;
    }

    /**
    static nested class serving as a vehicle to return two values from a method
    */
    public static class TwoValues
    {
        public int startingColumn;
        public int totalPoints;
    }

    public static void main(String args[])
    {
        /**
        command line arguments validation
        */
        
        if (args.length != 1)
        {
            System.err.println("Usage: java " + Game.class.getName() + " <filename>");
            return;
        }
        /**
        example of loading data from a file
        */
        Field<Block> field = FieldGenerator.loadDataFromFile(args[0]);

        /**
        example of generating a random Field
        */
       //Field<Block> field = FieldGenerator.randomIntegers(8,11,0,9,10);
        
        /**
        print the whole field
        */
        System.out.println(field);

        /**
        Example of running a foreach loop
        This will invoke the default iterator (the one using the anonymous inner class)
        */
        for(Block b:field)
            System.out.println(b);

        /**
        Example of running a while loop
        This will invoke the overloaded iterator (the one using the FieldIterator class)
        */
        Iterator<Block> it = field.iterator("Passage");
//      Iterator<Block> it = field.iterator("Obstacle"); // same thing for Obstacle objects
        while(it.hasNext())
            System.out.println(it.next());

        /**
        find the best starting point and print the results
        */
        TwoValues br = bestStartingPoint(field);
        System.out.println("Best starting point is in column " + br.startingColumn + " and the total points collected from this route is " + br.totalPoints);

        /**
        find the best route and print it
        */
      ArrayList<Block> al = bestRoute(field, br.startingColumn);
        for (Block b : al)
           System.out.println(b);
    }

}