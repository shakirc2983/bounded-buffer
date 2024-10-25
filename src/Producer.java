public class Producer extends Thread{
    private MemoryBuffer memoryBuffer;

    public Producer(MemoryBuffer memoryBuffer) {
        this.memoryBuffer = memoryBuffer;
    }

    public void produce() throws InterruptedException {
        memoryBuffer.produceData();
    }

    public void run() {
        while (true) {
            try {
                this.produce();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
