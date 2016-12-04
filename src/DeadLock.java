class Demo {
    public synchronized void a(Demo demo) {
        System.out.println("a");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        demo.b(); //获取b的同步锁，造成死锁
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
