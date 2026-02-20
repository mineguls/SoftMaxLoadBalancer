import java.util.Random;

public class Server {

    private int id;
    private double baseLatency;
    private Random random;

    public Server(int id, double baseLatency) {
        this.id = id;
        this.baseLatency = baseLatency;
        this.random = new Random();
    }

    public double getLatency() {
        double noise = random.nextGaussian() * 10;
        return Math.max(1, baseLatency + noise);
    }

    public void changePerformance(double newLatency) {
        this.baseLatency = newLatency;
    }

    public int getId() {
        return id;
    }
}