public class Main
{
    public static void main(String[] args)
    {
        for(int i = 5; i<= 10; i++)
        {
            int n = (int) Math.pow(2, i);
            StdRandom.setSeed(77700);
            String Archivo = "archivo_" + n + ".csv";
            Out salidaCsv = new Out(Archivo);
            System.out.println("Tamaño n = " + n + "t = " + i + " ");
            for (int j = 0; j<100; j++)
            {
                
            }
        }
    }
}
