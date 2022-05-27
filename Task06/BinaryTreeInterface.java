/**
 * An interface to describe a binary search tree.
 * 
 * @author Dominik
 * 
 * @param <T>
 *            the data type to store in this tree.
 */
public interface BinaryTreeInterface<T extends Comparable<T>> {

        /**
         * Look up data in the tree.
         * 
         * @return the requested object, or null if not existent
         */
        public T search(T data);

        /**
         * Add data to the tree.
         */
        public void insert(T data);

        /**
         * Remove data from the tree, if existent.
         */
        public void remove(T data);

}

