package ch22.ex22_11;

public class Num extends SimpleTreeNode<Integer> {

	Num(SimpleTreeNode<Integer> parent, int val) {
		super(parent, val);
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

}
