public class Fase3_Experimentos {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== EXPERIMENTO A: sin join ===");
        Temperatura temp = new Temperatura();
        Thread hiloTemp = new Thread(temp, "sensor-Temperatura");
        hiloTemp.start();
        System.out.println("Informe inmediato: " + temp.informe());
        hiloTemp.join();
        System.out.println("Informe tras join: " + temp.informe());

        System.out.println("\n=== EXPERIMENTO B: run() en lugar de start() ===");
        ProgramadorRiego riego = new ProgramadorRiego();
        long inicioB = System.currentTimeMillis();
        riego.run();
        long finB = System.currentTimeMillis();
        System.out.println("Tiempo: " + ((finB - inicioB) / 1000) + " segundos");
        System.out.println("Estado del hilo tras run(): " + riego.getState());

        System.out.println("\n=== EXPERIMENTO C: doble start() ===");
        ProgramadorRiego riego2 = new ProgramadorRiego();
        riego2.start();
        riego2.join();
        try {
            riego2.start();
        } catch (IllegalThreadStateException e) {
            System.out.println("Excepción capturada: " + e.toString());
        }
    }
}