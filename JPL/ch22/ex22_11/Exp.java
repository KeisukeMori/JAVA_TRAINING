package ch22.ex22_11;

public class Exp extends SimpleTreeNode<String>{

	Exp() {
		super(null, "exp");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

}
