import java.util.*;

public class Main {
    public static void main(String[] args) {
        for (int t = 5; t <= 10; t++) {

            int n = (int) Math.pow(2, t);

            // calculamos los límites máximos para k y los valores según las fórmulas del PDF.
            int maxK = (int) Math.pow(2, t - 2);
            int maxValor = (int) Math.pow(2, t + 2);

            // fijamos semilla para que al volver a correr el programa, salgan los mismos números aleatorios.
            StdRandom.setSeed(777666);

            String nombreArchivo = "archivo_" + n + ".csv";
            Out salidaCsv = new Out(nombreArchivo); // usamos Out.java como pide el PDF

            System.out.println("Procesando tamaño n = " + n + "...");
            salidaCsv.println("Iteracion,Tiempo_Metodo1,Tiempo_Metodo2,Tiempo_Metodo3");

            // para cada tamaño n, generamos 100 arreglos distintos.
            for (int j = 0; j < 100; j++) {

                // generamos k aleatorio en el rango [1, 2^(t-2)].
                int k = StdRandom.uniformInt(1, maxK + 1);

                // generamos n valores únicos en el rango [0, 2^(t+2)].
                // usamos HashSet porque es una estructura que no permite elementos duplicados automáticamente.
                Set<Integer> numerosUnicos = new HashSet<>();
                while (numerosUnicos.size() < n){
                    numerosUnicos.add(StdRandom.uniformInt(0, maxValor + 1));
                }

                // convertimos el Set a una Lista y la desordenamos para que sea un caso verdaderamente aleatorio.
                List<Integer> listaOriginal = new ArrayList<>(numerosUnicos);
                Collections.shuffle(listaOriginal);

                // hacemos copias de la lista original ANTES de medir el tiempo.
                // esto es vital porque los métodos de Result.java ordenan la lista (pValores.sort(null)).
                // si no le pasamos una copia fresca a cada metodo , el metodo 2 y 3 recibiran una lista ya ordenada
                // copiarlas fuera del cronómetro evita medir el tiempo de copiado.
                List<Integer> listaParaMetodo1 = new ArrayList<>(listaOriginal);
                List<Integer> listaParaMetodo2 = new ArrayList<>(listaOriginal);
                List<Integer> listaParaMetodo3 = new ArrayList<>(listaOriginal);

                // medicion metodo 1
                StopwatchCPU timer1 = new StopwatchCPU();
                Result.cuentaPares(n, listaParaMetodo1, k);
                // multiplicamos por 1000.0 porque StopwatchCPU entrega segundos, y el laboratorio pide milisegundos.
                double t1 = timer1.elapsedTime() * 1000.0;

                // medicion metodo 2
                StopwatchCPU timer2 = new StopwatchCPU();
                Result.cuentaPares2(n, listaParaMetodo2, k);
                double t2 = timer2.elapsedTime() * 1000.0;

                // medicion metodo 3
                StopwatchCPU timer3 = new StopwatchCPU();
                Result.cuentaPares3(n, listaParaMetodo3, k);
                double t3 = timer3.elapsedTime() * 1000.0;

                // guardamos los tiempos de esta iteración con 6 decimales para no perder precisión.
                salidaCsv.printf("%d, %.6f, %.6f, %.6f\n", j, t1, t2, t3);
            }
            // cerramos el archivo para que se guarde correctamente en el disco duro.
            salidaCsv.close();
        }
    }
}