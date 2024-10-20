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

        // -= CHECK NON-CORNER TRIPLE PROPERTIES =-

        // *check yellow-white gamma properties*
        Piece[] topAndBottom = {Piece.R0G2W0, Piece.G1W1, Piece.O2G0W2, Piece.R1W3, Piece.O1W5, Piece.R2B0W6, Piece.B1W7, Piece.O0B2W8,
                                Piece.R6G8Y6, Piece.G7Y7, Piece.O8G6Y8, Piece.R7Y3, Piece.O7Y5, Piece.R8B6Y0, Piece.B7Y1, Piece.O6B8Y2};
        // for each top-bottom side of the yellow and white faces...
        for (Piece p : topAndBottom){
            Color gamma = instance.getGamma(p);
            // if the gamma of this piece is not yellow, white, or nothing... return false
            if (!(gamma == null || gamma == Color.YELLOW || gamma == Color.WHITE)) return false;
        }



        // -= CHECK 4 CORNER-TRIPLE PROPERTIES =-

        // check if middle section edges have matching alphas and betas throughout
        Piece[] middle = {Piece.R3G5, Piece.O5G3, Piece.R5B3, Piece.O3B5};
        for (int e = 0; e < middle.length; e++){
            // Check alpha
            Color alpha = instance.getAlpha(middle[e]);
            if (!(alpha == Color.RED || alpha == Color.ORANGE)){ return false; }
            // Check beta
            Color beta = instance.getBeta(middle[e]);
            if (!(beta == Color.BLUE || beta == Color.GREEN)){ return false; }
        }

        // *check positive-negative properties*

        // store each triplet corners
        Piece[] redGreen = new Piece[2];
        Piece[] redBlue = new Piece[2];
        Piece[] orangeBlue = new Piece[2];
        Piece[] orangeGreen = new Piece[2];
        int rg = 0, rb = 0, ob = 0, og = 0;

        // Identify locations of triplets.  Divised into corner triple groups RED-GREEN, RED-BLUE, ORANGE-GREEN, ORANGE-BLUE.
        Piece[] corners = {Piece.R0G2W0, Piece.O2G0W2, Piece.R2B0W6, Piece.O0B2W8, Piece.R6G8Y6, Piece.O8G6Y8, Piece.R8B6Y0, Piece.O6B8Y2};
        // for each corner of the cube... identify & store which corners are apart of which corner triple group...
        for (int c = 0; c < corners.length; c++){
            // get colors of the contested corner...
            Color alpha = instance.getAlpha(corners[c]);
            Color beta = instance.getBeta(corners[c]);

            // Classify this corner based off of their alpha & beta colors, and store it into the associated group...
            if (alpha == Color.RED || beta == Color.RED){
                if (alpha == Color.BLUE || beta == Color.BLUE){
                    if (rb > 1) return false; // failsafe... should generally never happen
                    redBlue[rb] = corners[c]; // store variable
                    rb++; // store another one later...
                } else if (alpha == Color.GREEN || beta == Color.GREEN){
                    if (rg > 1) return false;
                    redGreen[rg] = corners[c];
                    rg++;
                }
            } else if (alpha == Color.ORANGE || beta == Color.ORANGE){
                if (alpha == Color.BLUE || beta == Color.BLUE){
                    if (ob > 1) return false;
                    orangeBlue[ob] = corners[c];
                    ob++;
                } else if (alpha == Color.GREEN || beta == Color.GREEN){
                    if (og > 1) return false;
                    orangeGreen[og] = corners[c];
                    og++;
                }
            }
        }

        // if triplets weren't successful... return false
        if (!(rg == 2 && rb == 2 && ob == 2 && og == 2)) return false; // also shouldn't generally happen... but failsafe regardless

        // if any of the corner triples does not follow its charge property... return false
        if (!(followsChargeProperty(instance, redGreen[0], redGreen[1]) && followsChargeProperty(instance, redBlue[0], redBlue[1])
            && followsChargeProperty(instance, orangeBlue[0], orangeBlue[1]) && followsChargeProperty(instance, orangeGreen[0], orangeGreen[1]))) return false;
        // else... return true because all properties came out to be true!
        
        return true;
    }

    /**
     * @description
     * This method helps identify G_PRIME.  Checks if this corner triple has a negative or positive property defined in the function.
     * 
     * @preconditions
     * @param alphaC and @param betaC must ALREADY be checked to be apart of the same corner triple.  They're gammas must also already be checked to be yellow for one, and white for the ohter.
     * This allow us to say that the gamma of both pieces ON THE CUBE are ALWAYS different, to avoid further amibguity of checking this property again when it has been checked in isG_PRIME().
     * 
     * @param instance the cube where this contested triple is on...
     * @param alphaC corner1 of triple (order doesn't matter)
     * @param betaC corner2 of triple (order doesn't matter)
     * @return Returns true if this triple follows their associated charge property.  False otherwise.
     **/ 
    private boolean followsChargeProperty(Cube instance, Piece alphaC, Piece betaC){

        /**
         * Check positive property = When alphaC & betaC are on the same face on the same side, or on opposing faces on opp. sides
         * ex. corners are right next to eachother... or corners are diagonally across the cube
         * Check negative property = When alphaC & betaC are on opposing faces but same side, or same face & opp. sides
         * ex. corners are diagonal from eachother on any same face
         **/

        // Identify type of property to check for...
        Color a_alpha = Piece.getAlpha(alphaC); // Piece.getAlpha(Piece p) differs from Cube.object.getAlpha(Piece p) as  Piece.getAlpha(Piece p) gets p's original alpha color on the solved cube.
        Color a_beta = Piece.getBeta(alphaC);
        Color a_gamma = Piece.getGamma(alphaC); // Piece.getGamma(Piece p) returns the original yellow-white color of p on the solved cube.

        Color b_alpha = Piece.getAlpha(betaC);
        Color b_beta = Piece.getBeta(betaC);
        Color b_gamma = Piece.getGamma(betaC);

        boolean positive = true;

        // CHECK if this triple is a NEGATIVE property...

        // check if one side is the same, and the other 2 are different...
        if (a_alpha == b_alpha){
            if (a_beta != b_beta && a_gamma != b_gamma){ positive = false; }
        } else if (a_beta == b_beta){
            if (a_alpha != b_alpha && a_gamma != b_gamma){ positive = false; }
        } else if (a_gamma == b_gamma){
            if (a_alpha != b_alpha && a_beta != b_beta){ positive = false; }
        }
        // if not... then this is a positive property


        // SEE if this triple FOLLOWS its corresponding +/- property on the instance...
        a_alpha = instance.getAlpha(alphaC); // change these variables to store the alpha & betas colors that it is CURRENTLY on @param Cube instance.
        a_beta = instance.getBeta(alphaC);

        b_alpha = instance.getAlpha(betaC);
        b_beta = instance.getBeta(betaC);
        // Check if the instance follows the +/- property accordingly to be apart of G_PRIME...
        if (positive){
            // if alphas are not the same as eachother or betas are not the same as easchother... return false as it does not follow the positive property
            if (a_alpha != b_alpha || a_beta != b_beta) return false;
        } else {
            // if alphas are the same as eachother or betas are the same as easchother... return false as it does not follow the negative property.
            if (a_alpha == b_alpha || a_beta == b_beta) return false;
        }
        // else... return true!

        return true;
    }
}