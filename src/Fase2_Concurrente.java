public class Fase2_Concurrente {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("--- INICIANDO FASE 2 (CONCURRENTE) ---");
        long inicio = System.currentTimeMillis();

        Temperatura temp = new Temperatura();
        Humedad hum = new Humedad();
        Luz luz = new Luz();
        ProgramadorRiego riego = new ProgramadorRiego();

        Thread hiloTemp = new Thread(temp, "sensor-Temperatura");
        Thread hiloHum = new Thread(hum, "sensor-Humedad");
        Thread hiloLuz = new Thread(luz, "sensor-Luz");

        hiloTemp.start();
        hiloHum.start();
        hiloLuz.start();
        riego.start();

        hiloTemp.join();
        hiloHum.join();
        hiloLuz.join();
        riego.join();

        long fin = System.currentTimeMillis();
        long tiempoTotal = (fin - inicio) / 1000;

        System.out.println("\n--- INFORMES (FASE 2) ---");
        System.out.println(temp.informe());
        System.out.println(hum.informe());
        System.out.println(luz.informe());
        System.out.println(riego.informe());

        System.out.println("\nTiempo total concurrente: " + tiempoTotal + " segundos.");
    }
}