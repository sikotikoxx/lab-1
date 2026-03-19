import java.util.*;
public class pruebas {
    public static void main(String[] args) {
        for(int i = 5; i <= 10; i++) {
            int n = (int) Math.pow(2, i);
            int k = 2; // Ejemplo: buscamos pares con diferencia de 2
            StdRandom.setSeed(777666);

            String Archivo = "archivo_" + n + ".csv";
            Out salidaCsv = new Out(Archivo);

            System.out.println("Tamaño n = " + n);
            salidaCsv.println("Iteracion,Tiempo_Metodo1,Tiempo_Metodo2,Tiempo_Metodo3");

            for (int j = 0; j < 100; j++) {
                // 1. Generar datos aleatorios para la prueba
                List<Integer> lista = new ArrayList<>();
                for(int m = 0; m < n; m++) {
                    lista.add(StdRandom.uniformInt(1000000));
                }

                // 2. Medir Método 1 (cuentaPares)
                StopwatchCPU timer1 = new StopwatchCPU();
                for(int p =0 ; p<1000;p++)
                {
                Result.cuentaPares(n, new ArrayList<>(lista), k);
                }
                double t1 = timer1.elapsedTime();

                // 3. Medir Método 2 (cuentaPares2)
                StopwatchCPU timer2 = new StopwatchCPU();
                for(int p =0 ; p<1000;p++){
                Result.cuentaPares2(n, new ArrayList<>(lista), k);}
                double t2 = timer2.elapsedTime();

                // 4. Medir Método 3 (cuentaPares3)
                StopwatchCPU timer3 = new StopwatchCPU();
                for(int p =0 ; p<1000;p++){
                Result.cuentaPares3(n, new ArrayList<>(lista), k);}
                double t3 = timer3.elapsedTime();

                // Guardar resultados en el CSV
                salidaCsv.printf("%d, %.10f, %.10f, %.10f\n", j, t1, t2, t3);
            }
            salidaCsv.close();
        }
    }
}
