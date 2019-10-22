package lyl.test.demo201910;

public class JoinTest {
    public static void main(String[] args) throws InterruptedException {
        Thread thread1= new Thread(()->{
            try {
                Thread.sleep(9000);//子线程处理中
                System.out.println("子线程处理完成");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread1.start();
        thread1.join();
        System.out.println("主线程结束");
    }
}
