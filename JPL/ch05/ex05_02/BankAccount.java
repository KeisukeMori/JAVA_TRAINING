package ch05.ex05_02;

public class BankAccount {
	private long number; // 口座番号
	private long balance; // 残高
    private Action lastAct; // 最後に行われた処理
    
	private History history = new History();	// 履歴
    private final static int HISTORY_SIZE = 10; // 履歴の最大件数

	
    public BankAccount(long number, long balance) {
        this.number = number;
        this.balance = balance;
    }

	
	public class Action {
		private String act; // 処理
		private long amount; // 金額

		Action(String act, long amount) {
			this.act = act;
			this.amount = amount;
		}

		@Override
		public String toString() {
			return number + " : " + act + " " + amount;
		}
	}

	/**
     * 本クラスは、BankAccountクラスに依存しているためにネストしたクラスにすべき
     * また、historyに対して直接アクセスすべきではないためstaticは望ましくない
	 * @author keisukemori
	 *
	 */
	public class History {
		private Action[] actionHistory = new Action[HISTORY_SIZE];
		private int currentIndex = -1;
		private int index = 0;

		public Action next(){
			if(currentIndex ++ < actionHistory.length - 1) {
				return actionHistory[currentIndex];
			} else {
				//リストの最後はnull
				return null;
			}
		}

		public void add(Action act) {
			if(index < HISTORY_SIZE) {
				actionHistory[index] = act;
			} else {
				// 大きかった場合、先頭の要素を削除し前に詰める
				for(int i = 0; i < actionHistory.length - 1; i++) {
					actionHistory[i] = actionHistory[i+1];
				}
				actionHistory[actionHistory.length - 1] = act;
			}
			index++;
		}

		public void show() {
			System.out.println(this.next());
		}
	}
	/**
	 * 預金
	 * @param amount
	 */
	public void deposit(long amount) {
		balance += amount;
		lastAct = this.new Action("deposit", amount);
		this.history.add(lastAct);
	}
	/**
	 * 引き出し
	 * @param amount
	 */
	public void withdraw(long amount) {
		balance -= amount;
        lastAct = this.new Action("withdraw", amount);
		this.history.add(lastAct);
	}
	
	public History history() {
		return this.history;
	}

    public long getBalance() {
        return balance;
    }
    
    public Action getLastAction() {
        return lastAct;
    }

	
	public static void main(String args[]){
		BankAccount bankAccount = new BankAccount(55555, 1000);
		bankAccount.deposit(1000);
		bankAccount.deposit(2000);
		bankAccount.deposit(3000);
		bankAccount.withdraw(4000);
		bankAccount.deposit(5000);
		bankAccount.deposit(6000);
		bankAccount.withdraw(7000);
		bankAccount.deposit(8000);
		bankAccount.deposit(9000);
		bankAccount.deposit(10000);
		bankAccount.withdraw(11000);
		bankAccount.deposit(12000);
		System.out.println(bankAccount.history().next());
		System.out.println(bankAccount.history().next());
		System.out.println(bankAccount.history().next());
		System.out.println(bankAccount.history().next());
		System.out.println(bankAccount.history().next());
		System.out.println(bankAccount.history().next());
		System.out.println(bankAccount.history().next());
		System.out.println(bankAccount.history().next());
		System.out.println(bankAccount.history().next());
		System.out.println(bankAccount.history().next());
		System.out.println(bankAccount.history().next());
	}	
}
