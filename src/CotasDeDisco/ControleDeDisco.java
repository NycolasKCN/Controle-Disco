package CotasDeDisco;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ControleDeDisco {
    private String caminhoArquivoEntrada;

    public ControleDeDisco() {
        this("");
    }

    public ControleDeDisco(String caminhoArquivo) {
        this.caminhoArquivoEntrada = caminhoArquivo;
    }

    public ArrayList<String[]> readFile(File file) throws FileNotFoundException {
        Scanner reader = new Scanner(file);
        ArrayList<String[]> usersList = new ArrayList<>();

        while (reader.hasNextLine()) {
            // \\s+ é um regex para remover os espaços em branco
            String[] user = reader.nextLine().split("\\s+");
            usersList.add(user);
        }

        reader.close();
        return usersList;
    }

    public long sumOcupedSpace(List<String[]> usersList) {
        long sum = 0l;

        for (String[] user : usersList) {
            sum += Long.parseLong(user[1]);
        }

        return sum;
    }
}
