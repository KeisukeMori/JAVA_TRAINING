package ch22.ex22_11;

public class Mul extends SimpleTreeNode<String> {

	Mul(SimpleTreeNode<String> parent) {
		super(parent, "*");
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

}
