package com.example.demo.cases.multithreads;

import java.util.Random;
import java.util.concurrent.Phaser;

public class PhaserTest {

    public static void main(String[] args) {
        MyPhaser phaser = new MyPhaser(4);
        for(int i=0; i<4; i++){
            new Thread(()->{
                System.out.println(Thread.currentThread().getName());
                phaser.arriveAndAwaitAdvance();
                System.out.println(phaser.getPhase());
                try {
                    Thread.sleep((long)(new Random().nextInt()*10));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    phaser.arriveAndAwaitAdvance();
                    phaser.getPhase();
                }
            }).start();
        }
    }
}

class MyPhaser extends Phaser{

    public MyPhaser(int parties){
        super(parties);
    }

    @Override
    protected boolean onAdvance(int phase, int registeredParties) {
        System.out.println("phase:"+phase+"-parties:"+registeredParties);
        return super.onAdvance(phase, registeredParties);
    }
}
