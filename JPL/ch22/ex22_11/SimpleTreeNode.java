package ch22.ex22_11;

public abstract class SimpleTreeNode<V> implements Acceptable{
	private SimpleTreeNode<V> parent;
	private SimpleTreeNode<?> left;
	private SimpleTreeNode<?> right;
	private final V value;
	SimpleTreeNode(SimpleTreeNode<V> parent, V value){
		this.parent = parent;
		this.value = value;
	}
	public V get(){
		return this.value;
	}
	public void setRight(SimpleTreeNode<?> right){
		this.right = right;
	}
	public void setLeft(SimpleTreeNode<?> left){
		this.left = left;
	}
	public SimpleTreeNode<?> left(){
		return left;
	}
	public SimpleTreeNode<?> right(){
		return right;
	}
	public abstract void accept(Visitor visitor);
}
