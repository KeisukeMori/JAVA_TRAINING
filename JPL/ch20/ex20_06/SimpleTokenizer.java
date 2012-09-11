package ch20.ex20_06;

import java.io.IOException;
import java.io.Reader;
import java.io.StreamTokenizer;
import java.util.HashMap;
import java.util.Map;

import static java.io.StreamTokenizer.*;

public class SimpleTokenizer {

	private HashMap <String, Integer> variables = new HashMap <String, Integer>();
	private State state;
	private String opObj;
	private Operand operand;
	
	public SimpleTokenizer(){
		state = State.WAITING;
	}
	
	enum Operand {
		PLUS("+"){
			public int calc(int left, int right){
				return left + right;
			}
		},
		MINUS("-"){
			public int calc(int left, int right){
				return left - right;
			}
		},
		EQUAL("="){
			public int calc(int left, int right){
				return right;
			}
		};
		private String str;
		private Operand(String op){
			this.str = op;
		}
		@Override
		public String toString(){
			return str;
		}
		public char toChar(){
			return str.toCharArray()[0];
		}
		public abstract int calc(int left, int right);
	}
	
	private static enum State {
		OP, NUM, WAITING, QUIT
	}
	
	public void printResult(){
		for(Map.Entry<String, Integer> entry : variables.entrySet()){
			System.out.println(entry.getKey() + " : " + entry.getValue());
		}
	}
	
	public void analyze(Reader reader) throws IOException{
		StreamTokenizer tokenizer = new StreamTokenizer(reader);
		
		int tokenKind = TT_EOF;
		while((tokenKind = tokenizer.nextToken()) != TT_EOF){
			switch(tokenKind){
			case TT_NUMBER:
				nextState((int)tokenizer.nval);
				break;
			case TT_WORD:
				String value = tokenizer.sval;
				nextState(value);
				break;
			default:
				Character op = (char)tokenizer.ttype;
				if(Operand.EQUAL.toChar() == op){
					nextState(Operand.EQUAL);
				}
				else if (Operand.MINUS.toChar() == op){
					nextState(Operand.MINUS);
				}
				else if (Operand.PLUS.toChar() == op){
					nextState(Operand.PLUS);
				}
				break;
			}
		}
	}
	
	private void nextState(int value){
		switch(state){
		case NUM:
			int result = operand.calc(variables.get(opObj), value);
			variables.put(opObj, result);
			state = State.WAITING;
			break;
		default:
			throw new IllegalArgumentException("parse failed! :" + value);
		}
	}
	private void nextState(String variable){
		switch(state){
		case WAITING:
			if(!variables.containsKey(variable)){
				variables.put(variable, 0);
			}
			opObj = variable;
			state = State.OP;
			break;
		default:
			throw new IllegalArgumentException("parse failed! :" + variable);
		}
	}
	private void nextState(Operand op){
		switch(state){
		case OP:
			this.operand = op;
			state = State.NUM;
			break;
		default:
			throw new IllegalArgumentException("parse failed! :" + op);
		}
	}
}
