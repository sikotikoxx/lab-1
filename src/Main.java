import java.util.*;

public class Main
{
    public static void main(String[] args)
    {
        for(int i = 5; i <= 10; i++)
        { // i = tamaño
            int n = (int) Math.pow(2, i);
            // Calcular los límites máximos según la fórmula del PDF
            int maxK = (int) Math.pow(2, i - 2);     // Límite para k
            int maxValor = (int) Math.pow(2, i + 2); // Límite para los valores
            StdRandom.setSeed(777666);

            String Archivo = "archivo_" + n + ".csv";
            Out salidaCsv = new Out(Archivo);

            System.out.println("Tamaño n = " + n);
            salidaCsv.println("Iteracion,Tiempo_Metodo1,Tiempo_Metodo2,Tiempo_Metodo3");

            for (int j = 0; j < 100; j++)
            {
                // genera k aleatorio en el rango [1, 2^(t-2)]
                int k = StdRandom.uniformInt(1, maxK + 1);

                // genera n valores unicos en el rango [0, 2^(t+2)]
                // Usamos HashSet porque funciona como un "guardia" que rechaza números repetidos
                Set<Integer> numerosUnicos = new HashSet<>();
                while (numerosUnicos.size() < n)
                {
                    numerosUnicos.add(StdRandom.uniformInt(0, maxValor + 1));
                }

                // pasamos los números unicos a una lista y los desordenamos
                List<Integer> lista = new ArrayList<>(numerosUnicos);
                Collections.shuffle(lista);

                // Medir metodo 1
                StopwatchCPU timer1 = new StopwatchCPU();
                for(int p = 0; p < 1000; p++)
                {
                    // pasamos una copia de la lista (new ArrayList) porque Result.java la ordena (asi en cada metodo)
                    Result.cuentaPares(n, new ArrayList<>(lista), k);
                }
                double t1 = timer1.elapsedTime();

                // Medir metodo 2
                StopwatchCPU timer2 = new StopwatchCPU();
                for(int p = 0; p < 1000; p++)
                {
                    Result.cuentaPares2(n, new ArrayList<>(lista), k);
                }
                double t2 = timer2.elapsedTime();

                // Medir metodo 3
                StopwatchCPU timer3 = new StopwatchCPU();
                for(int p = 0; p < 1000; p++)
                {
                    Result.cuentaPares3(n, new ArrayList<>(lista), k);
                }
                double t3 = timer3.elapsedTime();

                // Guardar resultados en el CSV (guardando hasta 6 decimales)
                salidaCsv.printf("%d, %.6f, %.6f, %.6f\n", j, t1, t2, t3);
            }
            salidaCsv.close();
        }
    }
}
