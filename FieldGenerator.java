
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

/**
 * The class that generates the actual field to be played on 
 * it has two different ways to generate fields
 * 
 * @author Sahaj Singh 
 * @version Java 8
 */
public class FieldGenerator
{

    @SuppressWarnings ("unchecked")
    /**
     * Loads the field data from the filename passed. sets the elements of the matrix equal to the data in the file
     * ass well as the height and width
     * 
     * @param filename the file to be used
     * @return returns a field object containing all the data from the file
     */
    public static Field<Block> loadDataFromFile(String filename) 
    {
        File file = new File(filename);
        Scanner sc;
        try {
            sc = new Scanner(file);

        int rows = 0;
        int col = 0;
        while(sc.hasNextLine())
        {
            String[] line = sc.nextLine().split(" ");
            col = line.length;
            rows++;
        }
        Field field = new Field<Block>(rows, col);


        Scanner scr = new Scanner(file);
        for(int i = 0; i < rows; i++)
        {
            for(int j = 0; j < col; j++)
            {
                String net = scr.next();
                if(net.equals("-"))
                {
                    Obstacle obj = new Obstacle("-");
                    field.setElement(i, j, obj);
                }
                else
                {
                    Passage pass = new Passage(Integer.parseInt(net));
                    field.setElement(i, j, pass);
                }
            }
            if(scr.hasNextLine())
            {
            scr.nextLine();
            }
        }

        return field;
    }
    catch(FileNotFoundException e)
    {
        System.out.print("No file found");
    }
    return null;
    }

    /**
     * Creates a random field based of the integers passed 
     * 
     * @param h the height of the field
     * @param w the width of the field
     * @param l the lowest random number of points that a Passage can have
     * @param m the largest random number of points that a Passage can have
     * @param n is the number of Obstacle objects in the Field.
     * @return returns the field created with the parameters passed
     */
    public static Field<Block> randomIntegers(int h, int w, int l, int m, int n)
    {
        Field field = new Field<Block>(h, w);

        Random random = new Random(42);

        int total = 2 * (h + w);

        //double chance = (double)n / (double)total;

        //int counter = 0;
        for(int i = 0; i < n; i++)
        {
            int row = random.nextInt(h);
            int col = random.nextInt(w);
            Obstacle obstacle = new Obstacle("-");
            field.setElement(row, col, obstacle);
          
        }

        for(int i = 0; i < h; i++)
        {
            for(int j = 0;j < w; j++)
            {
                if(field.getElement(i, j) instanceof Obstacle)
                {
                    continue;
                }
                else 
                {
                    Passage passage = new Passage(random.nextInt(m - l) + l);
                    field.setElement(i,j, passage);
                }
            }
        }
        return field;


    }

}