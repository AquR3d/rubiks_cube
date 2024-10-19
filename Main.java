// main class
public class Main{

    public static void main(String[] args){

        Cube cube = new Cube();
        
        //cube.scramble();s
        cube.scramble(5);
        cube.turn("R2");

        System.out.println(cube);

        
            
    }
}