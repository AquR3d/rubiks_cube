// main class
public class Main{

    public static void main(String[] args){

        Cube cube = new Cube("B O' B2 Y' W B' W' R Y' W2 O Y W2 W W2 G Y' R B");
        Cube cube2 = new Cube("B O' B' B' Y' W B' W' R Y' W2 O Y W G Y' R B' B2");
        Cube cube3 = new Cube(true);

        System.out.println(cube);
        System.out.println(cube2);
        System.out.println(cube3);

        //cube3.scramble();

        System.out.println(cube.equals(cube2));

        //System.out.println(cube3);

        /*
        // showcase all 18 permutations
        for (Cube.Color c : Cube.Color.values()){

            cube.turn(c, true);
            System.out.println(c);
            System.out.println(cube);
            cube.toDefault();

            cube.turn(c, false);
            System.out.println(c + "\'");
            System.out.println(cube);
            cube.toDefault();

            cube.turn(c, true);
            cube.turn(c, true);
            System.out.println(c + "2");
            System.out.println(cube);
            cube.toDefault();
        }*/

        
    }
}