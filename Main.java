// main class
public class Main{

    public static void main(String[] args){

        Cube cube = new Cube();
        
        cube.scramble("R\'");

        System.out.println(cube);
        
        System.out.println(CubeAlgorithm.isG_PRIME(cube));
        
            
    }
}