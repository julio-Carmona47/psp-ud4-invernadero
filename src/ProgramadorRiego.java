public class ProgramadorRiego extends Thread {
    private int ciclos = 5;
    private double litrosTotales = 0;

    public ProgramadorRiego() {

        super("riego");
    }

    @Override
    public void run() {
        for (int i = 1; i <= ciclos; i++) {
            litrosTotales += 2.5;
            System.out.println("[" + getName() + "] Ciclo " + i + ": Válvula ABIERTA. Litros: " + litrosTotales);

            try {
                Thread.sleep(700);
            } catch (InterruptedException e) {
                System.out.println("Riego interrumpido.");
            }

            System.out.println("[" + getName() + "] Ciclo " + i + ": Válvula cerrada.");
            try {
                Thread.sleep(700);
            } catch (InterruptedException e) {
                System.out.println("Riego interrumpido.");
            }
        }
    }

    public String informe() {
        return "Riego -> Ciclos completados: " + ciclos + " | Total litros: " + litrosTotales + " L";
    }
}