import java.io.File;

import CotasDeDisco.ControleDeDisco;

public class Main {
    public static void main(String[] args){
        ControleDeDisco control = new ControleDeDisco();

        File file = new File("usuarios.txt");
        
        try {
            System.out.println(control.readFile(file));   
        } catch (Exception e) {
            System.out.println(e);
        }
        
    }
}