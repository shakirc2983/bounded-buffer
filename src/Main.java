public class Main {

    public static void main(String[] args) throws InterruptedException {
        MemoryBuffer sharedBuffer = new MemoryBuffer(5);
        Producer producer = new Producer(sharedBuffer);
        Consumer consumer = new Consumer(sharedBuffer);

        producer.start();
        consumer.start();
    }
}