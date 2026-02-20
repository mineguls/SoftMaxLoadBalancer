public class Simulation {

    private Server[] servers;
    private int requestCount;

    public Simulation(Server[] servers, int requestCount) {
        this.servers = servers;
        this.requestCount = requestCount;
    }

    public void runSoftmaxSimulation() {
        SoftmaxLoadBalancer lb = new SoftmaxLoadBalancer(servers.length, 0.5, 0.1);

        double totalLatency = 0;
        int[] selectionCount = new int[servers.length];

        for (int t = 0; t < requestCount; t++) {

            // ortam değişimi
            if (t == requestCount / 2) {
                System.out.println("Environment changed!");
                servers[0].changePerformance(30);
                servers[1].changePerformance(90);
                servers[2].changePerformance(70);
            }

            int serverIndex = lb.selectServer();
            selectionCount[serverIndex]++;

            double latency = servers[serverIndex].getLatency();
            totalLatency += latency;

            double reward = 1.0 / latency;
            lb.update(serverIndex, reward);
        }

        System.out.println("\nSoftmax Results");
        System.out.println("Average Latency: " + totalLatency / requestCount);

        for (int i = 0; i < selectionCount.length; i++) {
            System.out.println("Server " + i + " selected: " + selectionCount[i]);
        }
    }

    public void runRandomSimulation() {
        RandomLoadBalancer lb = new RandomLoadBalancer(servers.length);

        double totalLatency = 0;

        for (int t = 0; t < requestCount; t++) {

            int serverIndex = lb.selectServer();
            double latency = servers[serverIndex].getLatency();
            totalLatency += latency;
        }

        System.out.println("\nRandom Results");
        System.out.println("Average Latency: " + totalLatency / requestCount);
    }
}