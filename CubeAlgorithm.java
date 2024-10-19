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

        // Check 4 corner triples


        return true;
    }

    // This method helps identify G_PRIME.  Checks if a face has only yellow or white or both colors.
    private boolean isYellowWhite(Color[] face){
        int size = face.length;

        for (int i = 0; i < size; i++){
            // if not yellow or white...
            if (!(face[i].toString().equals("W") || face[i].toString().equals("Y"))){
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

        

        

        // Identify type of property to check for...



    }

    /**
     * @description Returns true when the associated corner of the cube instance matches the alpha beta colors.  Returns false otherwise.
     * Will help identify G_PRIME.
     * 
     * @preconditions
     * The yellow & white face on @param matrix must already be checked as to be fully yellow or white or both.
     * idx must be in-between 0-8 inclusive.
     * 
     * @param matrix the cube instance
     * @param alpha defined exactly as followsChargeProperty()
     * @param beta defined exactly as followsChargeProperty()
     * @param idx defines the location of which corner to contest on @param face
     * @param face The yellow or white face where this corner is on @param matrix
     * @return true when the associated corner has this alpha & beta color. false otherwise.
     */
    private boolean hasAlphaBeta(Map<Color, Color[]> matrix, Color alpha, Color beta, int idx, Color face){

        // Check valid index.
        if (!(idx == 0 || idx == 2 || idx == 6 || idx == 8)) return false;

        // Get the colors of the contested corner...
        Color _alpha = null, _beta = null;
        if (face.equals(Color.YELLOW)){

            switch(idx){
                case 0:
                    _alpha = matrix.get(Color.RED)[0];
                    _beta = matrix.get(Color.GREEN)[2];
                    break;
                case 2:
                    _alpha =  matrix.get(Color.ORANGE)[2];
                    _beta = matrix.get(Color.GREEN)[0];
                    break;
                case 6:
                    _alpha = matrix.get(Color.RED)[2];
                    _beta = matrix.get(Color.BLUE)[0];
                    break;
                case 8:
                    _alpha = matrix.get(Color.ORANGE)[0];
                    _beta = matrix.get(Color.BLUE)[2];
                    break;
            }
        } else if (face.equals(Color.WHITE)){
            switch(idx){
                case 0:
                    _alpha = matrix.get(Color.RED)[8];
                    _beta = matrix.get(Color.BLUE)[6];
                    break;
                case 2:
                    _alpha =  matrix.get(Color.ORANGE)[6];
                    _beta = matrix.get(Color.BLUE)[8];
                    break;
                case 6:
                    _alpha = matrix.get(Color.RED)[6];
                    _beta = matrix.get(Color.GREEN)[8];
                    break;
                case 8:
                    _alpha = matrix.get(Color.ORANGE)[8];
                    _beta = matrix.get(Color.GREEN)[6];
                    break;
            }
        } else {
            return false;
        }

        if (_alpha == null || _beta == null) return false;

        return _alpha.equals(alpha) && _beta.equals(beta);
    }
}