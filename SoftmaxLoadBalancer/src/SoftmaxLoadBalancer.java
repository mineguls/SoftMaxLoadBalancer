import java.util.Random;

public class SoftmaxLoadBalancer {

    private double[] Q;
    private double temperature;
    private double alpha;
    private Random random;

    public SoftmaxLoadBalancer(int serverCount, double temperature, double alpha) {
        this.Q = new double[serverCount];
        this.temperature = temperature;
        this.alpha = alpha;
        this.random = new Random();
    }

    public int selectServer() {
        double[] probabilities = new double[Q.length];
        double sum = 0;

        for (int i = 0; i < Q.length; i++) {
            probabilities[i] = Math.exp(Q[i] / temperature);
            sum += probabilities[i];
        }

        for (int i = 0; i < probabilities.length; i++) {
            probabilities[i] /= sum;
        }

        double r = random.nextDouble();
        double cumulative = 0;

        for (int i = 0; i < probabilities.length; i++) {
            cumulative += probabilities[i];
            if (r <= cumulative) {
                return i;
            }
        }

        return probabilities.length - 1;
    }

    public void update(int serverIndex, double reward) {
        Q[serverIndex] = Q[serverIndex] + alpha * (reward - Q[serverIndex]);
    }

    public double[] getQValues() {
        return Q;
    }
}