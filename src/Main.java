import java.io.File;
import java.util.List;

import CotasDeDisco.ControleDeDisco;

public class Main {
    public static void main(String[] args){

        File file = new File("usuarios.txt");

        ControleDeDisco control = new ControleDeDisco(file);

        try {
            for (List<String> user : control.readFile(file)){
                System.out.println(user.get(0) + user.get(1));
            }
            System.out.println(control.sumOcupedSpace(control.readFile(file)));   
        } catch (Exception e) {
            System.out.println(e);
        }
        
    }
}