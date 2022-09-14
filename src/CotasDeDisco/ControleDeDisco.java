package CotasDeDisco;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ControleDeDisco {

    private String inputFileName;
    private String outputFileName;
    private ArrayList<ArrayList<String>> users;
    private long totalOcupedSpace;

    /**
     * 
     * @param inputFile Caminho ou nome do arquivo que contém os usuarios
     * @param outputFileName Caminho ou nome do arquivo de saida dos dados
     */
    public ControleDeDisco(String inputFile, String outputFileName) {
        this.outputFileName = outputFileName;
        this.inputFileName = inputFile;

        this.users = new ArrayList<>();
        this.totalOcupedSpace = 0l;
    }

    public ControleDeDisco(String inputFile) {
        this(inputFile, "relatorio.txt");
    }

    public void run() {
        this.readFile();
        for (ArrayList<String> user : users) {
            System.out.println(user.get(0) + " " + user.get(1));
        }

        this.sumOcupedSpace();
        System.out.println("Essa é a soma total de bytes: " + this.totalOcupedSpace);
    }

    private void readFile() {
        Scanner reader = null;
        try {
            reader = new Scanner(new FileReader(this.inputFileName));

            while (reader.hasNextLine()) {
                String[] user = reader.nextLine().split("\\s+");
                ArrayList<String> userArray = new ArrayList<>();
                userArray.add(user[0]);
                userArray.add(user[1]);

                this.users.add(userArray);
            }

        } catch (FileNotFoundException e) {
            System.out.println(e.getStackTrace());

        } finally {
            if (reader != null) {
                reader.close();
            }
        }
    }

    private void writeFile() {
        
    }

    private void sumOcupedSpace() {
        long sum = 0l;
        for (List<String> user : this.users) {
            sum += Long.parseLong(user.get(1));
        }

        this.totalOcupedSpace = sum;
    }

    public double bytesToMegabytes(long bytes) {
        return bytes * Math.pow(10, -6);
    }
}
