public class Fase4_Vigilante {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("--- INICIANDO FASE 4 (VIGILANTE) ---");

        Temperatura temp = new Temperatura();
        Humedad hum = new Humedad();
        Luz luz = new Luz();
        ProgramadorRiego riego = new ProgramadorRiego();

        Thread hiloTemp = new Thread(temp, "sensor-Temperatura");
        Thread hiloHum = new Thread(hum, "sensor-Humedad");
        Thread hiloLuz = new Thread(luz, "sensor-Luz");

        System.out.println("\nEstados ANTES de arrancar:");
        System.out.println("Temperatura: " + hiloTemp.getState());
        System.out.println("Humedad: " + hiloHum.getState());
        System.out.println("Luz: " + hiloLuz.getState());
        System.out.println("Riego: " + riego.getState());

        Thread vigilante = new Thread(() -> {
            while (true) {
                try {
                    System.out.println("[VIGILANTE] Temp: " + hiloTemp.getState() +
                            " | Hum: " + hiloHum.getState() +
                            " | Luz: " + hiloLuz.getState() +
                            " | Riego: " + riego.getState());
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    System.out.println("Vigilante interrumpido.");
                }
            }
        });

        vigilante.setDaemon(true);
        vigilante.start();

        hiloTemp.start();
        hiloHum.start();
        hiloLuz.start();
        riego.start();

        hiloTemp.join();
        hiloHum.join();
        hiloLuz.join();
        riego.join();

        System.out.println("\nEstados DESPUÉS de terminar:");
        System.out.println("Temperatura: " + hiloTemp.getState());
        System.out.println("Humedad: " + hiloHum.getState());
        System.out.println("Luz: " + hiloLuz.getState());
        System.out.println("Riego: " + riego.getState());

        System.out.println("\nMain ha terminado. El vigilante (demonio) muere automáticamente.");
    }
}