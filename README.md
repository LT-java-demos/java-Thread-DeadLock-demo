#java-Thread-DeadLock-demo

##同步锁

在方法定义时加入 `synchronized` 修饰符即添加了同步锁，当多个线程同时准备调用该方法时，只有一个线程可以获取到同步锁，进行调用，其他线程会变成阻塞状态停止运行，直到该线程执行方法完毕后退出释放锁，其他线程会按刚才的规则再次竞争获取同步锁并执行。

--------------

- 一个不恰当使用同步方法引起死锁的例子

```java
class Demo {
    public synchronized void a(Demo demo) {
        System.out.println("a");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("ok");
    }

    public synchronized void b() {
        System.out.println("b");
    }
}

public class DeadLock {
    public static void main(String[] args) {
        final Demo d1 = new Demo();
        final Demo d2 = new Demo();
        new Thread() {
            @Override
            public void run() {
                d1.a(d2);
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                d2.a(d1);
            }
        }.start();
    }
}
```

两个线程各自拥有一个同步锁，并尝试获取对方的同步锁。


