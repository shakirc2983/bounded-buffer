
public class Consumer extends Thread {
    private MemoryBuffer memoryBuffer;

    public Consumer(MemoryBuffer memoryBuffer) {
        this.memoryBuffer = memoryBuffer;
    }

    public void consume() throws InterruptedException {
        memoryBuffer.consumeData();
    }

    public void run() {
        while (true) {
            try {
                this.consume();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
