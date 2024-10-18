import java.util.*;

/***
 * This class will handle ALL algorithm logic for solving a cube, or possibly
 * returning a sequence of moves that reach a desired outcome of a certain
 * permutation.
 * 
 * The purpose of this class is to return a sequence of moves to do on
 * the specified Cube instantiated within it.
 */
public class CubeAlgorithm {
    private Cube solved;
    private Cube scramble;
    

    public CubeAlgorithm(Cube other){
        scramble = other;
        solved = new Cube();
    }

    public String[] solve(){

        // gimme scramble

        return null;
    }

    public boolean isG_PRIME(Cube instance){

        Map<Color, Color[]> matrix = instance.getMatrix();

        // check 4 edge triples
        Color[] yellow = matrix.get(Color.YELLOW);
        Color[] white = matrix.get(Color.WHITE);


        // check 4 corner triples


        return true;
    }
}