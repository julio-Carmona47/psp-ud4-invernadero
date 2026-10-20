import java.util.concurrent.ThreadLocalRandom;

public abstract class Sensor implements Runnable {
    protected String nombre;
    protected String unidad;
    protected int periodo;
    protected int numLecturas;
    protected double umbralMin;
    protected double umbralMax;


    protected int lecturasHechas = 0;
    protected double sumaValores = 0;
    protected double valorMin = Double.MAX_VALUE;
    protected double valorMax = Double.MIN_VALUE;
    protected int alertas = 0;

    public Sensor(String nombre, String unidad, int periodo, int numLecturas, double umbralMin, double umbralMax) {
        this.nombre = nombre;
        this.unidad = unidad;
        this.periodo = periodo;
        this.numLecturas = numLecturas;
        this.umbralMin = umbralMin;
        this.umbralMax = umbralMax;
    }

    public abstract double medir();

    @Override
    public void run() {
        for (int i = 0; i < numLecturas; i++) {
            double valor = medir();


            lecturasHechas++;
            sumaValores += valor;
            if (valor < valorMin) valorMin = valor;
            if (valor > valorMax) valorMax = valor;

            String textoAlerta = "";
            if (valor < umbralMin || valor > umbralMax) {
                textoAlerta = " ALERTA";
                alertas++;
            }

            String hora = java.time.LocalTime.now().toString().substring(0, 8);
            System.out.printf("[%s] [%s] %s: %.2f %s%s%n",
                    hora,
                    Thread.currentThread().getName(),
                    nombre,
                    valor,
                    unidad,
                    textoAlerta);


            try {
                Thread.sleep(periodo);
            } catch (InterruptedException e) {
                System.out.println(nombre + " interrumpido.");
            }
        }
    }

    public String informe() {
        double media = lecturasHechas > 0 ? sumaValores / lecturasHechas : 0;
        return String.format("%s -> Media: %.2f %s | Mín: %.2f | Máx: %.2f | Alertas: %d",
                nombre, media, unidad, valorMin, valorMax, alertas);
    }
}