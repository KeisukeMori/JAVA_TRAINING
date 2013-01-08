/*
 * Copyright (C) 2012 RICOH Co., Ltd. All rights reserved.
 */

package threadpool;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

/**
 * This is a Test class for ThreadPool class.
 * This class is written with JUnit 4.
 */
public class TestThreadPool {

	/**
	 * Simple counter task which counts the number of invocation of run() method.
	 */
	private static class CounterTask implements Runnable {
		private int runCount = 0;
		
//		@Override
		public synchronized void run() {
			runCount++;
			System.out.println(runCount);
			notifyAll();
		}
		
		synchronized int waitForRunCount(int count) {
			while (this.runCount < count) {
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			return runCount;
		}
	}
	
	/**
	 * A simple latch task whose run() method will wait for other threads would
	 * execute the run() method until the expected number of run() invocations reached.
	 */
	private static class LatchTask implements Runnable {
		private final int latchCount;
		private int currentCount = 0;
		
		LatchTask(int count) {
			this.latchCount = count;
		}
		
		public synchronized void run() {
			currentCount++;
			notifyAll();
			while (currentCount < latchCount) {
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
		synchronized void waitForLatchCount() {
			while (currentCount < latchCount) {
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testConstructorIllegalArgumentFirst() {
		new ThreadPool(0, 1);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testConstructorIllegalArgumentSecond() {
		new ThreadPool(1, 0);
	}
	
	@Test
	public void testStartAndStop() {
		ThreadPool tp = new ThreadPool(1,1);
		tp.start();
		tp.stop();
	}
	
	@Test(expected=IllegalStateException.class)
	public void testStopBeforeStart() {
		ThreadPool tp = new ThreadPool(1,1);
		tp.stop();
	}
	
	@Test(expected=IllegalStateException.class)
	public void testRestartWithoutStop() {
		ThreadPool tp = new ThreadPool(1,1);
		tp.start();
		tp.start();
	}
	
	@Test(expected=NullPointerException.class)
	public void testDispatchNullArgument() {
		ThreadPool tp = new ThreadPool(1,1);
		tp.start();
		tp.dispatch(null);
	}
	
	@Test(expected=IllegalStateException.class)
	public void testDispatchBeforeStart() {
		ThreadPool tp = new ThreadPool(1,1);
		CounterTask t = new CounterTask();
		tp.dispatch(t);
	}
	@Test
	public void testSimpleDispatch() {
		ThreadPool tp = new ThreadPool(1,1);
		tp.start();
		CounterTask t = new CounterTask();
		tp.dispatch(t);
		t.waitForRunCount(1);
		tp.stop();
	}
	
	@Test
	public void testSimpleRepeatedDispatch() {
		ThreadPool tp = new ThreadPool(1,1);
		tp.start();
		CounterTask t = new CounterTask();
		
		for (int i = 0; i < 10; i++)
			tp.dispatch(t);
		
		t.waitForRunCount(10);
		tp.stop();
	}
	
	@Test
	public void testComplexRepeatedDispatch() {
		ThreadPool tp = new ThreadPool(10,10);
		tp.start();
		CounterTask t = new CounterTask();
		
		for (int i = 0; i < 1000; i++)
			tp.dispatch(t);
		
		t.waitForRunCount(1000);
		tp.stop();
	}
	
	@Test
	public void testComplexRepeatedDispatch2() {
		ThreadPool tp = new ThreadPool(10,10);
		tp.start();
		CounterTask[] tasks = new CounterTask[10];
		for (int i = 0; i < tasks.length; i++)
			tasks[i] = new CounterTask();
		
		for (int i = 0; i < 100; i++) {
			for (CounterTask t: tasks)
				tp.dispatch(t);
		}
		
		for (CounterTask t: tasks)
			t.waitForRunCount(100);
				
		tp.stop();
	}
	
	@Test
	public void testLatchSimpleDispatch() {
		final int numberOfThreads = 10;
		ThreadPool tp = new ThreadPool(10,numberOfThreads);
		tp.start();
		LatchTask t = new LatchTask(numberOfThreads);

		for (int i = 0; i < numberOfThreads; i++)
			tp.dispatch(t);
		
		t.waitForLatchCount();
		tp.stop();
	}
	
	@Test
	public void testLatchComplexDispatch() {
		final int numberOfThreads = 10;
		ThreadPool tp = new ThreadPool(10,numberOfThreads);
		tp.start();
		
		LatchTask[] tasks = new LatchTask[10];
		for (int i = 0; i < tasks.length; i++) 
			tasks[i] = new LatchTask(numberOfThreads);
		
		for (LatchTask t: tasks) {
			for (int i = 0; i < numberOfThreads; i++) {
				tp.dispatch(t);
			}
		}
		
		for (LatchTask t: tasks)
			t.waitForLatchCount();
				
		tp.stop();
	}
	
	@Test
	public void testNumberOfThreads() {
		final Set<Thread> threads = Collections.synchronizedSet(new HashSet<Thread>());
		Runnable task = new Runnable() {
//			@Override
			public void run() {
				threads.add(Thread.currentThread());
				try {
					Thread.sleep(500); // wait for a while
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		
		final int numberOfThreads = 10;
		ThreadPool tp = new ThreadPool(10,numberOfThreads);
		tp.start();
		for (int i = 0; i < numberOfThreads; i++)
			tp.dispatch(task);
		
		// By the specification, stop() will wait for the terminations 
		// of all threads.
		tp.stop();
		
		assertEquals(numberOfThreads, threads.size());
	}
	
	@Test
	public void testTerminationOfThreads() {
		final List<Thread> threads = Collections.synchronizedList(new ArrayList<Thread>());
		Runnable task = new Runnable() {
//			@Override
			public void run() {
				threads.add(Thread.currentThread());
				try {
					Thread.sleep(500); // wait for a while
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		final int numberOfThreads = 10;
		ThreadPool tp = new ThreadPool(10,numberOfThreads);
		tp.start();
		for (int i = 0; i < numberOfThreads; i++)
			tp.dispatch(task);
		// By the specification, stop() will wait for the terminations 
		// of all threads.
		tp.stop();
		
		assertEquals(numberOfThreads, threads.size());
		for (Thread t: threads)
			assertFalse(t.isAlive());
	}
}
