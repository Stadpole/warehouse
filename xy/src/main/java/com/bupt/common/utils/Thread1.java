package com.bupt.common.utils;

 public class Thread1 implements Runnable {

 private String name;
 private Object prev;
 private Object self;

 private Thread1(String name, Object prev, Object self) {
 this.name = name;
 this.prev = prev;
 this.self = self;
 }

 @Override
 public void run() {
 int count = 10;
 while (count > 0) {
 synchronized (prev) {
 synchronized (self) {
 System.out.print(name);
 count--;

 self.notify();
 }
 try {
 prev.wait();
 } catch (InterruptedException e) {
 e.printStackTrace();
 }
 }

 }
 }

 public static void main(String[] args) throws Exception {
 Object a = new Object();
 Object b = new Object();
 Object c = new Object();
     Thread1 pa = new Thread1("A", c, a);
     Thread1 pb = new Thread1("B", a, b);
     Thread1 pc = new Thread1("C", b, c);


 new Thread(pa).start();
 Thread.sleep(100);  //确保按顺序A、B、C执行
 new Thread(pb).start();
 Thread.sleep(100);
 new Thread(pc).start();
 Thread.sleep(100);
 }
 }


