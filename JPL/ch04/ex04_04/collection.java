package ch04.ex04_04;

interface Iterable<T> {
	
}

interface Collection<E> extends Iterable<E> {
	
}

interface List<E> extends Collection<E> {
	
}

interface Queue<E> extends Collection<E> {
	
}

interface BlockingQueue<E> extends Queue<E> {
	
}

interface BlockingDeque<E> extends BlockingQueue<E>, Deque<E> {
	
}

interface Deque<E> extends Queue<E> {
	
}

interface Set<E> extends Collection<E> {
	
}

interface SortedSet<E> extends Set<E> {
	
}

interface NavigableSet<E> extends SortedSet<E> {
	
}

