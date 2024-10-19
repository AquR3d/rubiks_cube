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

        // Check 4 edge triples by checking white and yellow
        Color[] yellow = matrix.get(Color.YELLOW);
        Color[] white = matrix.get(Color.WHITE);

        // if neither of these are yellow-white
        if (!(isYellowWhite(yellow) && isYellowWhite(white))) return false;

        // check if middle section edges have matching alphas and betas throughout
        Piece[] middle = {Piece.R3G5, Piece.O5G3, Piece.R5B3, Piece.O3B5};
        for (Piece p : middle){
            // Check alpha
            Color alpha = instance.getAlpha(p);
            if (!(alpha == Color.RED || alpha == Color.ORANGE)){ return false; }
            // Check beta
            Color beta = instance.getBeta(p);
            if (!(beta == Color.BLUE || beta == Color.GREEN)){ return false; }
        }

        // Check 4 corner triples


        return true;
    }

    // This method helps identify G_PRIME.  Checks if a face has only yellow or white or both colors.
    private boolean isYellowWhite(Color[] face){
        int size = face.length;

        for (int i = 0; i < size; i++){
            // if not yellow or white...
            if (!(face[i] == Color.WHITE || face[i] == Color.YELLOW)){
                return false;
            }
        }

        return true;
    }

    /**
     * @description
     * This method helps identify G_PRIME.  Checks if this corner triple has a negative or positive property defined in the function.
     * 
     * @preconditions
     * The yellow & white face on @param matrix must already be checked as to be fully yellow or white or both.
     * 
     * @return
     * Will return true if it follows the negative or positive property for G_PRIME.
     * returns false otherwise...
     * 
     * A corner triple is defined by the 2 colors it shares across 3 pieces.  In this function, that will be defined
     * by @param alpha @param beta...  The alpha color will be RED or ORANGE, but the beta will be BLUE or GREEN.
     * ex. YELLOW-GREEN-ORANGE(corner/alphaC) & GREEN-ORANGE(edge) & WHITE-GREEN-ORANGE(corner/betaC) is a corner triple.
     **/ 
    private boolean followsChargeProperty(Map<Color, Color[]> matrix, Color alpha, Color beta){

        /**
         * Check positive property = When alphaC & betaC are on the same face on the same side, or on opposing faces on opp. sides
         * ex. corners are right next to eachother... or corners are diagonally across the cube
         * Check negative property = When alphaC & betaC are on opposing faces but same side, or same face & opp. sides
         * ex. corners are diagonal from eachother on any same face
         **/

        // Find alphaC & betaC... location will be defined by the yellow & white face
        Color[] yellow = matrix.get(Color.YELLOW);
        Color[] white = matrix.get(Color.WHITE);

        // Define parameters for corner locations.
        int i = 0;
        int[] indices = {-1, -1}; // corner 1 & 2
        Color[] faces = {null, null}; // corner 1 & 2

        // Get corners
        
        

        // Identify type of property to check for...



    }
}