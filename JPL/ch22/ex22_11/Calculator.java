package ch22.ex22_11;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class Calculator {

	private enum Token{
		NUM, PLUS, MINUS, MUL, DIV
	}
	
	private Queue<Map.Entry<Token, SimpleTreeNode<?>>> analyzed = new LinkedBlockingQueue<Map.Entry<Token, SimpleTreeNode<?>>>();
	
	private SimpleTreeNode<?> exp(Queue<Map.Entry<Token, SimpleTreeNode<?>>> tokens){
		SimpleTreeNode<String> root = new Exp();
		SimpleTreeNode<?> nl = Num(tokens);
		SimpleTreeNode<?> nr = PlusOrMinus(tokens);
		root.setLeft(nl);
		root.setRight(nr);
		return root;
	}
	
	private SimpleTreeNode<?> PlusOrMinus(Queue<Map.Entry<Token, SimpleTreeNode<?>>> tokens){
		SimpleTreeNode<?> n = null;
		while(tokens.peek().getKey() == Token.PLUS || tokens.peek().getKey() == Token.MINUS){
			n = tokens.poll().getValue();
			n.setLeft(PlusOrMinus(tokens));
			n.setRight(DivOrMul(tokens));
		}
		return n;
	}
	
	private SimpleTreeNode<?> DivOrMul(Queue<Map.Entry<Token, SimpleTreeNode<?>>> tokens){
		SimpleTreeNode<?> n = null;
		while(tokens.peek().getKey() == Token.DIV || tokens.peek().getKey() == Token.MUL){
			n = tokens.poll().getValue();
			n.setLeft(DivOrMul(tokens));
			n.setRight(Num(tokens));
		}
		return n;
	}
	
	private SimpleTreeNode<?> Num(Queue<Map.Entry<Token, SimpleTreeNode<?>>> tokens){
		return tokens.poll().getValue();
	}
}
