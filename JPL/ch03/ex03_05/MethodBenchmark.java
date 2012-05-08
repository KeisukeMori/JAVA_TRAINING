package ch03.ex03_05;

public class MethodBenchmark extends Benchmark{
	private int loopCounter;

	void benchmark(){
		for(int i = 0; i < loopCounter; i++) {
		}
	}
	
	public void setLoopCounter (int loopCounter) {
		this.loopCounter = loopCounter;
	}

	public static void main(String[] args) {
		//        int count = Integer.parseInt(args[0]);
		int count = 400;
		MethodBenchmark benchmark = new MethodBenchmark();
		//        benchmark.loopCounter = Integer.parseInt(args[1]);
		benchmark.loopCounter = 500;

		long time = benchmark.repeat(count);
		System.out.println(
				benchmark.loopCounter + " loop " 
				+ count + " methods in " + time + " milliseconds");
	}

}
