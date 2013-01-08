/*
 * Copyright (C) 2012 RICOH Co., Ltd. All rights reserved.
 */

package threadpool;

import java.util.ArrayList;
import java.util.List;


/**
 * Simple Thread Pool class.
 *
 * This class can be used to dispatch an Runnable object to
 * be exectued by a thread.
 *
 * [Instruction]
 *  Implement one constructor and three methods.
 *  Don't forget to write a Test program to test this class. 
 *  Pay attention to @throws tags in the javadoc.
 *  If needed, you can put "synchronized" keyword to methods.
 *  All classes for implementation must be private inside this class.
 *  Don't use java.util.concurrent package.
 */
public class ThreadPool {
	private volatile Pool = ; 
	//private volatile boolean isAlive = false; 
	private ArrayList taskQueue;
	/**
	 * Constructs ThreadPool.
	 *
	 * @param queueSize the max size of queue
	 * @param numberOfThreads the number of threads in this pool.
	 *
	 * @throws IllegalArgumentException if either queueSize or numberOfThreads
	 *         is less than 1
	 */
	public ThreadPool(int queueSize, int numberOfThreads) {
		//    	throw new AssertionError("Not Implemented Yet");
		if (queueSize < 1) { 
			throw new IllegalArgumentException("queueSize less than 1");
		}

		if (numberOfThreads < 1) { 
			throw new IllegalArgumentException("numberOfThreads less than 1");
		}

		taskQueue = new ArrayList(numberOfThreads);
		for (int i = 0; i < numberOfThreads; i++) {
			taskQueue.add(new Thread());
		}
	}

	/**
	 * Starts threads.
	 *
	 * @throws IllegalStateException if threads has been already started.
	 */
	public synchronized void start() {

		if (isAlive) {
			throw new IllegalStateException("threads has been already started");
		}
		System.out.println("start.");
		isAlive = true;
	}   

	/**
	 * Stop all threads and wait for their terminations.
	 *
	 * @throws IllegalStateException if threads has not been started.
	 */
	public synchronized void stop() {
		if (!isAlive) {
			throw new IllegalStateException("threads has not been started");
		} else {
			isAlive = false;
			taskQueue.clear();
			System.out.println("stop.");
		}
	}

	/**
	 * Executes the specified Runnable object, using a thread in the pool.
	 * run() method will be invoked in the thread.
	 * 
	 * @param runnable Runnable object whose run() method will be invoked.
	 *
	 * @throws NullPointerException if runnable is null.
	 * @throws IllegalStateException if this pool has not been started yet.
	 */
	public synchronized void dispatch(Runnable runnable) {
		if (runnable == null) {
			throw new NullPointerException("runnable is null.");
		}
		if (!isAlive) {
			throw new IllegalStateException("this pool has not been started yet.");
		}

		
		
		for (int i = 0; i < taskQueue.size(); i++) {
			if (taskQueue.get(i) instanceof Thread) {
				((Thread) taskQueue.get(i)).run();
			}    			
		}
		runnable.run();
	}
	public void run() {
		for(;;) {
			Runnable r;
			synchronized (queue) {
				while(queue.isEmpty) {
					if (state = Poolstate.stop) {
						return;
					}
				}
				try {
					queue.wait();
				} catch (InterruptedException e){
					e.getStackTrace();
				}
			}
			// sta
		}
	}
}

