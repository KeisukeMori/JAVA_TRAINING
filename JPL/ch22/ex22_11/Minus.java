package ch22.ex22_11;

public class Minus extends SimpleTreeNode<String>{

	Minus(SimpleTreeNode<String> parent) {
		super(parent, "-");
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

}
