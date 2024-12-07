import java.util.*;

public class CFOPAlg extends CubeAlgorithm {

    public CFOPAlg(Cube other){
        super(other);
    }

    public boolean solveUsingCFOP(Cube instance) {
        // step 1: solve the cross
        if (!solveCross(instance)) {
            return false;
        }

        // step 2: solve the first two layers (F2L)
        if (!solveF2L(instance)) {
            return false;
        }

        // step 3: orient the last layer (OLL)
        if (!solveOLL(instance)) {
            return false;
        }

        // step 4: Permute the last layer (PLL)
        if (!solvePLL(instance)) {
            return false;
        }

        // if all steps succeed, set the sequence and return true
        this.sequence = instance.sequence;
        return true;
    }

    /**
     * solves the cross on the cube
     * returns true if successful, false if it fails to make progress
     */

    public boolean solveCross(Cube instance) {
        // Placeholder for the logic to solve the cross.
        // Implement actual cross-solving logic here.

        // Solve each edge of the cross
        String totalSeq = "";
        int crosses = 0;
        while(true){

            crosses = crossEdged(instance);
            if (crosses >= 4) break;

            // get solve to another part of the cross
            String seq = solveToCross(instance, crosses+1); // add method of breadth searcth to find solve

            // do solve
            Cube.scramble(instance, seq); // crosses should automatically update after

            totalSeq += seq + " ";
        }

        // orientate appropriately to centers
        int times = 0;
        while(true){
            
            String seq = "";

            // if centers are orientated
            if (crossCompleted(instance)){
                switch(times){
                    case 1: seq += "W"; break;
                    case 2: seq += "W2"; break;
                    case 3: seq += "W'"; break;
                }
                totalSeq += seq + " ";
                break;
            }

            times++;
            Cube.turn(instance, Color.WHITE, true);
        }

        System.out.println(totalSeq);

        return true;
    }

    // solve a certain amount of white piece edges
    private String solveToCross(Cube instance, int cross){

        if (crossEdged(instance) == cross) return null;

        // SOLVING SETUP
        instance.sequence = "";
        instance.prev = null; // IMPORTANT

        // QUEUES THAT REPRESENT POSSIBLE PERMUTAITON OF GPRIME AND SOLVED IN n MOVES
        LinkedList<Cube> q = new LinkedList<>();
        q.addLast(instance);

        // SETUP VAIRABLES
        int qItr = 0; // n for GPRIME

        Cube copy = null;

        // BREADTH-FIRST SEARCH ACROSS PERMUTATIONS
        while (!q.isEmpty() && q.size() < maxInstances && qItr < 5){

            // controls n permutations
            // clear out the queue of n permutations...
            while (!q.isEmpty() && q.size() < maxInstances && q.peek().sequence.split(" ").length <= qItr){
                // get first in line to be iterated
                copy = q.poll();

                // this method might be optimized later to find shorter solution where cfop edges can be solved during another one's

                // check for cross edges
                if (crossEdged(copy) == cross) return copy.sequence;

                // add instances from this copy
                addinstances(copy, q, false, false, null);
            }
            qItr++;

        }

        // according to my theory on solving the cross, this should NEVER return null...
        System.out.println("Something went wrong.  Returned null.");
        return null;
    }

    // returns the number of edges that are in the correct order for the cross, regardless of oritentation away from their center colors
    private int crossEdged(Cube instance){
        // num is between 1-4
        // checks if that many white cross edges have been solved

        // get alpha & beta
        // white-cross in order from yellow on top
        Color[] correctOrder = {Color.RED, Color.GREEN, Color.ORANGE, Color.BLUE};
        // get the white face
        Color[] order = {Cube.getAlpha(instance, Piece.R1W3), Cube.getBeta(instance, Piece.G1W1), Cube.getAlpha(instance, Piece.O1W5), Cube.getBeta(instance, Piece.B1W7)};
        Color[] gamma = {Cube.getGamma(instance, Piece.R1W3), Cube.getGamma(instance, Piece.G1W1), Cube.getGamma(instance, Piece.O1W5), Cube.getGamma(instance, Piece.B1W7)};

        // check for similarities
        int crosses = 0;
        int maxCrosses = 0;

        // use a cycling method to find the max matches
        for (int i = 0; i < 4; i++){

            crosses = 0;
            // check gamma if white
            if (gamma[0] == Color.WHITE && order[0] == correctOrder[i%4]) crosses++;
            if (gamma[1] == Color.WHITE && order[1] == correctOrder[(i+1)%4]) crosses++;
            if (gamma[2] == Color.WHITE && order[2] == correctOrder[(i+2)%4]) crosses++;
            if (gamma[3] == Color.WHITE && order[3] == correctOrder[(i+3)%4]) crosses++;

            if (maxCrosses < crosses) maxCrosses = crosses;
        }

        return maxCrosses;
    }

    private boolean crossCompleted(Cube instance){

        // check each edge piece
        if (Cube.getGamma(instance, Piece.R1W3) != Color.WHITE || Cube.getAlpha(instance, Piece.R1W3) != Color.RED) return false;
        if (Cube.getGamma(instance, Piece.G1W1) != Color.WHITE || Cube.getBeta(instance, Piece.G1W1) != Color.GREEN) return false;
        if (Cube.getGamma(instance, Piece.O1W5) != Color.WHITE || Cube.getAlpha(instance, Piece.O1W5) != Color.ORANGE) return false;
        if (Cube.getGamma(instance, Piece.B1W7) != Color.WHITE || Cube.getBeta(instance, Piece.B1W7) != Color.BLUE) return false;

        return true;
    }

    // I might consider putting this in the Cube class instead.
    private boolean hasColor(Cube instance, Piece p, Color color){

        // if any of the faces are colored "color", return true.. false if none of the faces are colored "color"
        if (Cube.getAlpha(instance, p) == color) return true;
        if (Cube.getBeta(instance, p) == color) return true;
        if (Cube.getGamma(instance, p) == color) return true;

        return false;
    }

    private Color[] getPairType(Cube instance, Piece p){

        Color[] type = new Color[2];

        Color[] colors = {Cube.getAlpha(instance, p), Cube.getBeta(instance, p), Cube.getGamma(instance, p)};

        for (Color c : colors){
            if (c == Color.RED) type[0] = Color.RED;
            if (c == Color.ORANGE) type[0] = Color.ORANGE;
            if (c == Color.BLUE) type[1] = Color.BLUE;
            if (c == Color.GREEN) type[1] = Color.GREEN;
        }

        return type;
    }

    // checks the yellow face for any existing pairs
    private Piece[] pairOnTop(Cube instance, int pairs){

        // check if cross has been maintained
        if (!crossCompleted(instance)) return null;
        if (pairs(instance) != pairs) return null; // make sure current structure was maintained

        // first check for white colors on yellow corners
        Piece[] corners = {Piece.R8B6Y0, Piece.O6B8Y2, Piece.O8G6Y8, Piece.R6G8Y6};
        Piece[] edges = {Piece.R7Y3, Piece.B7Y1, Piece.O7Y5, Piece.G7Y7};

        for (Piece c : corners){

            if (hasColor(instance, c, Color.WHITE)){
                // identify pair-colors
                Color[] type = getPairType(instance, c);

                // check if any edges match type
                for (Piece e : edges){
                    if (Arrays.equals(type, getPairType(instance, e))){
                        // found one!!

                        // return locations
                        Piece[] pieceLocs = {c, e};

                        return pieceLocs; // we should return the pair type
                    }
                }

            }
        }

        return null;
    }

    private int pairs(Cube instance){

        // precondition of cross being completed
        if (!crossCompleted(instance)) return 0;

        return 0;
    }

    /**
     * solves the first two layers (F2L) on the cube
     * returns true if successful, false if it fails to make progress
     */
    private boolean solveF2L(Cube instance) {
        // Placeholder for the logic to solve F2L.
        // Implement actual F2L-solving logic here.

        // preconditions: cross has to be done
        // Solve each pair of F2L
        String totalSeq = "";
        int pairs = 0;
        while(true){

            pairs = pairs(instance);
            if (pairs >= 4) break;

            // get solve to another part of pairs
            String seq = solveToF2L(instance, pairs+1); // add method of breadth searcth to find solve

            // do solve
            Cube.scramble(instance, seq); // pairs should automatically update after

            totalSeq += seq + " ";
        }

        System.out.println(totalSeq);

        return true;
    }

    private String solveToF2L(Cube instance, int pairs){
        
        if (pairs(instance) == pairs) return null;

        // SOLVING SETUP
        instance.sequence = "";
        instance.prev = null; // IMPORTANT

        // QUEUES THAT REPRESENT POSSIBLE PERMUTAITON OF GPRIME AND SOLVED IN n MOVES
        LinkedList<Cube> q = new LinkedList<>();
        q.addLast(instance);

        // SETUP VAIRABLES
        int qItr = 0; // n for GPRIME

        Cube copy = null;

        // BREADTH-FIRST SEARCH ACROSS PERMUTATIONS
        while (!q.isEmpty() && q.size() < maxInstances && qItr < 6){

            // controls n permutations
            // clear out the queue of n permutations...
            while (!q.isEmpty() && q.size() < maxInstances && q.peek().sequence.split(" ").length <= qItr){
                // get first in line to be iterated
                copy = q.poll();

                // can be optimized but idk where to start

                // check if pair on top w/o curr structure being broken
                Piece[] type = pairOnTop(copy, pairs);
                if (type != null){

                    // identify orientation

                    // get orientation sequence

                    // return that sequence with current sequence
                    return copy.sequence; // + orientation sequence
                }

                // add instances from this copy
                addinstances(copy, q, false, false, Color.WHITE);
            }
            qItr++;

        }

        // according to my theory on solving the cross, this should NEVER return null...
        System.out.println("Something went wrong.  Returned null.");
        return null;
    }

    /**
     * solves the orientation of the last layer (OLL) on the cube
     * returns true if successful, false if it fails to make progress
     */

    private boolean solveOLL(Cube instance) {
        // Placeholder for the logic to solve OLL.
        // Implement actual OLL-solving logic here.
        return false;
    }

    /**
     * solves the permutation of the last layer (PLL) on the cube
     * returns true if successful, false if it fails to make progress
     */

    private boolean solvePLL(Cube instance) {
        // Placeholder for the logic to solve PLL.
        // Implement actual PLL-solving logic here.
        return false;
    }
}