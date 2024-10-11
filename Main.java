// main class

public class Main{

    public static void main(String[] args){
        System.out.println("amongus");

        Cube cube = new Cube();

        System.out.println(cube);

        cube.Scramble();

        System.out.println(cube);

        /*
        // showcase all 18 permutations
        for (Cube.Color c : Cube.Color.values()){

            cube.Turn(c, true);
            System.out.println(c);
            System.out.println(cube);
            cube.ToDefault();

            cube.Turn(c, false);
            System.out.println(c + "\'");
            System.out.println(cube);
            cube.ToDefault();

            cube.Turn(c, true);
            cube.Turn(c, true);
            System.out.println(c + "2");
            System.out.println(cube);
            cube.ToDefault();
        }*/

        
    }
}