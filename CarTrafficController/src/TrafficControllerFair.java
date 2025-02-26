import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;


public class TrafficControllerFair implements TrafficController {
    private TrafficRegistrar registrar;
    private ReentrantLock lock;
    private Condition condition;
    private boolean empty;

    public TrafficControllerFair(TrafficRegistrar registrar) {
        this.registrar = registrar;
        this.lock = new ReentrantLock(true);
        this.condition = lock.newCondition();
        this.empty = true;
    }

    @Override
    public void enterRight(Vehicle v) {
        lock.lock();
        try {
            while(!empty) {
                condition.await();
            }
            empty = false;
            this.registrar.registerRight(v);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void enterLeft(Vehicle v) {
        lock.lock();
        try {
            while(!empty) {
                condition.await();
            }
            empty = false;
            this.registrar.registerLeft(v);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void leaveLeft(Vehicle v) {
        try {
            this.registrar.deregisterLeft(v);
            empty = true;
            condition.signalAll();
        }
        finally {
            lock.unlock();
        }

    }

    @Override
    public void leaveRight(Vehicle v)  {
        try {
            this.registrar.deregisterRight(v);
            empty = true;
            condition.signalAll();
        }
        finally {
            lock.unlock();
        }
    }
}
