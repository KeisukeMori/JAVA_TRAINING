package ch03.ex03_05;

abstract class Benchmark {
    abstract void benchmark();
    
    public final long repeat(int count) {
        long start = System.nanoTime();
        for (int i = 0; i < count; i++) {
            benchmark();
        }
        return System.nanoTime() - start;
    }
}

