public class TrafficControllerSimple implements TrafficController {

    private TrafficRegistrar registrar;
    boolean empty;

    public TrafficControllerSimple(TrafficRegistrar registrar) {
        this.registrar = registrar;
        this.empty = true;
    }

    @Override
    public synchronized void enterRight(Vehicle v) {
        while (!empty) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }
        }
        empty = false;
        this.registrar.registerRight(v);

    }

    @Override
    public synchronized void enterLeft(Vehicle v) {
        while (!empty) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }
        }
        empty = false;
        this.registrar.registerLeft(v);


    }

    @Override
    public synchronized void leaveLeft(Vehicle v) {
        this.registrar.deregisterLeft(v);
        empty = true;
        notifyAll();
    }

    @Override
    public synchronized void leaveRight(Vehicle v) {
        this.registrar.deregisterRight(v);
        empty = true;
        notifyAll();
    }
}
