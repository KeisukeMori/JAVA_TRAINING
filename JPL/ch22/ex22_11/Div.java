package ch22.ex22_11;

public class Div extends SimpleTreeNode<String> {

	Div(SimpleTreeNode<String> parent) {
		super(parent, "/");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
	
}
