
import java.util.Iterator;

/**
 * A generic interface that extends the Iterable interface.
 * Only has one method that all classes that implement must overide
 */
public interface FlexibleIterable<T> extends Iterable<T>
{
    /**
     * Abstract method. Creates an iterator that iterates only on objects whose datatype name is iterableObjectName
    and skips everything else in the iterable object. 
     * 
     * @param iterableObjectName
     * @return
     */
    public Iterator<T> iterator(String iterableObjectName);
}