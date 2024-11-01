// main class
public class Main{

    public static void main(String[] args){

        /**
         * scrambles that got into g-prime
         * 
         * move sequences r done from left to right
         * 
         * [Y', B2, G2, W', Y', G2, B2, Y', R2, W', Y, R', G, B, O'] - [O, G', B', R]
         * [W, Y2, W', R, Y', R, O', G] - [G, O', R, W', R]
         * [B', G', Y2, B', Y2, B2, G2, O', G', R] - [R', G, O, W2, G]
         * [R, Y, W, G, B2, W] - [W', G, Y', W', O]
         * 
         */

        //Cube cube = new Cube("R' W O2 G' Y2 G'");
        Cube cube = new Cube();
        Cube.scramble(cube, 6, 20, true);

        System.out.println(cube);
        
        //System.out.println(CubeAlgorithm.isG_PRIME(cube));
        CubeAlgorithm alg = new CubeAlgorithm(cube);

        System.out.println(alg.solveToG_PRIME(cube));
        if (alg.scramble != null){
            System.out.println(alg.scramble);
            System.out.println(alg.scramble.sequence);
        }

        //System.out.println(Cube.reverseSequence("R' W G2 B2 O O' B' Y' Y2"));
        
            
    }
}