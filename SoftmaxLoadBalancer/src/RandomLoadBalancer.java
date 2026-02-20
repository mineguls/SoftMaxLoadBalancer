import java.util.Random;

public class RandomLoadBalancer {

    private int serverCount;
    private Random random;

    public RandomLoadBalancer(int serverCount) {
        this.serverCount = serverCount;
        this.random = new Random();
    }

    public int selectServer() {
        return random.nextInt(serverCount);
    }
}