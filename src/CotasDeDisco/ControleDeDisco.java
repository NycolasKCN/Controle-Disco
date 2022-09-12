package CotasDeDisco;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ControleDeDisco {
    private File file;
    private List<List<String>> users;

    public ControleDeDisco(File file) {
        this.file = file;
    }   
    
    public void run() throws FileNotFoundException {
        this.users = readFile(this.file);

        long ocupedSpace = sumOcupedSpace(users);
        

    }

    public List<List<String>> readFile(File file) throws FileNotFoundException {
        Scanner reader = new Scanner(file);
        List<List<String>> usersList = new ArrayList<>();

        while (reader.hasNextLine()) {
            List<String> user = new ArrayList<>();
            // \\s+ é um regex para remover os espaços em branco
            String[] user2 = reader.nextLine().split("\\s+");
            user.add(user2[0]);
            user.add(user2[1]);
            usersList.add(user);
        }

        reader.close();
        return usersList;
    }

    public long sumOcupedSpace(List<List<String>> usersList) {
        long sum = 0l;

        for (List<String> user : usersList) {
            sum += Long.parseLong(user.get(1));
        }

        return sum;
    }

    public double bytesToMegabytes(long bytes) {
        return bytes * Math.pow(10, -6);
    }
}
