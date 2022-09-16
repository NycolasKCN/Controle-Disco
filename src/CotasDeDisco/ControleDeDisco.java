package CotasDeDisco;

import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ControleDeDisco {

    private String inputFileName;
    private String outputFileName;
    private ArrayList<ArrayList<String>> users;
    private double totalOcupedSpace;
    private double media;

    /**
     * 
     * @param inputFile      Caminho ou nome do arquivo que contém os usuarios
     * @param outputFileName Caminho ou nome do arquivo de saida dos dados
     */
    public ControleDeDisco(String inputFile, String outputFileName) {
        this.outputFileName = outputFileName;
        this.inputFileName = inputFile;

        this.users = new ArrayList<>();
        this.totalOcupedSpace = 0l;
        this.media = 0;
    }

    public ControleDeDisco(String inputFile) {
        this(inputFile, "relatorio.txt");
    }

    public void run() {
        this.readFile();
        this.sumOcupedSpace();
        this.userPercentage();
        this.writeFile();
    }

    private void readFile() {
        // try with resources - try com recursos
        try (Scanner reader = new Scanner(new FileReader(this.inputFileName))) {

            while (reader.hasNextLine()) {
                String[] user = reader.nextLine().split("\\s+");
                ArrayList<String> userArray = new ArrayList<>();
                userArray.add(user[0]);
                userArray.add(user[1]);

                this.users.add(userArray);
            }

        } catch (FileNotFoundException e) {
            System.out.println(e.getStackTrace());
        }
    }

    private void writeFile() {
        // try with resources - try com recursos
        try (PrintWriter pw = new PrintWriter(new FileWriter(this.outputFileName))) {

            pw.println(String.format("%-24s %s", "ACME inc.", "Uso de espaco em disco pelos usuarios"));
            pw.println(line("==", 40));
            pw.println(String.format("%-5s %-18s %10s %15s", "id.", "usuarios", "Espaco Utilizado", "% de uso"));
            pw.println();

            for (int i = 0; i < this.users.size(); i++) {
                String user = this.users.get(i).get(0);
                double space = this.bytesToMegabytes(Long.parseLong(this.users.get(i).get(1)));
                double percentage = Double.parseDouble(this.users.get(i).get(2));
                pw.println(String.format("%-5d %-18s %10.2f  MB %14.2f %%", i + 1, user, space, percentage));
            }

            pw.println();
            pw.println(String.format("Espaco medio ocupado: %.2f MB", this.media));
            pw.println(String.format("Espaco total ocupado: %.2f MB", this.totalOcupedSpace));

        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /**
     * Repete uma string e a retorna
     * 
     * @param c     String que será repetida
     * @param width quantidade de vezes que a String será repetida
     * @return Retorna o caractere ou string {@code c} repetido.
     */
    private String line(String c, int width) {
        String line = "";
        for (int i = 0; i < width; i++) {
            line += c;
        }
        return line;
    }

    private void userPercentage() {
        for (ArrayList<String> user : this.users) {
            double user_usage = this.bytesToMegabytes(Long.parseLong(user.get(1)));
            double user_percentage = (user_usage / this.totalOcupedSpace) * 100;
            user.add(2, "" + user_percentage + "");
        }

        this.media = this.totalOcupedSpace / this.users.size();
    }

    private void sumOcupedSpace() {
        long sum = 0l;
        for (List<String> user : this.users) {
            sum += Long.parseLong(user.get(1));
        }

        this.totalOcupedSpace = this.bytesToMegabytes(sum);
    }

    public double bytesToMegabytes(long bytes) {
        return bytes * Math.pow(10, -6);
    }
}
