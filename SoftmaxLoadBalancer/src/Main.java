public class Main {

    public static void main(String[] args) {

        int K = 3;
        int requests = 10000;

        Server[] servers = new Server[K];
        servers[0] = new Server(0, 80);
        servers[1] = new Server(1, 40);
        servers[2] = new Server(2, 120);

        Simulation simulation = new Simulation(servers, requests);

        simulation.runRandomSimulation();
        simulation.runSoftmaxSimulation();
    }
}