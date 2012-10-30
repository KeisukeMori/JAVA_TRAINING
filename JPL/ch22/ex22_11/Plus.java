package ch22.ex22_11;

public class Plus extends SimpleTreeNode<String> {

	Plus(SimpleTreeNode<String> parent) {
		super(parent, "+");
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
		
	}

}
