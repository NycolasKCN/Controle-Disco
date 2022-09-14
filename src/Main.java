import CotasDeDisco.ControleDeDisco;

public class Main {
    public static void main(String[] args){

        String fileName = "usuarios.txt";

        ControleDeDisco control = new ControleDeDisco(fileName);

        control.run();
        
    }
}