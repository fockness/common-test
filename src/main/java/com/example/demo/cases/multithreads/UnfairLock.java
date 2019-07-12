package com.example.demo.cases.multithreads;

import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;

/**
 * 手写ReentrantLock的UnfairLock
 */
public class UnfairLock implements Lock {

    private AtomicReference<Thread> owner = new AtomicReference<>();
    private List<Thread> waitingList = new ArrayList<>();

    @Override
    public void lock() {
        while(!owner.compareAndSet(null, Thread.currentThread())){
            //没有抢到锁的线程加入等待队列中
            waitingList.add(Thread.currentThread());
            //挂起线程
            LockSupport.park();
            //执行到这步说明线程被唤醒了，去除等待队列的该线程
            waitingList.remove(Thread.currentThread());
        }
    }

    @Override
    public void unlock() {
        while(!owner.compareAndSet(Thread.currentThread(), null)){
            if(CollectionUtils.isNotEmpty(waitingList)){
                waitingList.forEach(i->LockSupport.unpark(i));
            }
        }
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
