package com.thread.atomicity;

//import java.util.concurrent.CountDownLatch;
//import java.util.concurrent.locks.Lock;
//import java.util.concurrent.locks.ReentrantLock;

/**
 * 并发的原子性问题
 * @author sunli
 *
 */
public class AtomicityProblem {

    public static int count = 0;
    
	static int num = 100;
	// static Lock lock1 = new ReentrantLock();
	// static CountDownLatch cdl = new CountDownLatch(num);
	public static void main(String[] args) {
		
		for(int i=0;i<num;i++){
			Thread td = new Thread(new Runnable() {
				@Override
				public void run() {
//					lock1.lock();
					count++;
//					lock1.unlock();
//					cdl.countDown();
				}
			});
			td.start();
		}
//		try {
//			cdl.await();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		System.out.println(count);
		
	}
}
