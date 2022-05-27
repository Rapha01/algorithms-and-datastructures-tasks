public interface PriorityQueue <T extends Comparable<T>> {

	public void insert(T e);

	public T removeFirst();

	public boolean isEmpty();
}