import java.util.*;

// this class will be responsible for handling the data of a cube.
public class Cube {

    /*
                WHITE
        RED     BLUE    ORANGE  GREEN
                YELLOW

        26 TOTAL PIECES
        6 CENTERS << these will be constant
        12 EDGES
        8 CORNERS

        how we will represent the cube

        >>>>CUBE_MAP<<<<

                    w0  w1  w2
                    w3  w   w4
                    w6  w7  w8                      
        r0  r1  r2  b0  b1  b2  o0  o1  o2  g0  g1  g2
        r3  r   r5  b3  b   b5  o3  o   o5  g3  g   g5
        r6  r7  r8  b6  b7  b8  o6  o7  o8  g6  g7  g8
                    y0  y1  y2
                    y3  y   y5
                    y6  y7  y8

        this acts like an unwrapped version of the 3D cube, like UV Unwrapping
        ...
        EXCEPT for the fact that GREEN is flipped then if it were like a paper cutout, bc/ the first column of GREEN
        will STILL correspond with the first column of WHITE.
    */

    public final int NUM_FACES = 6;
    public final int PIECES_PER_FACE = 9; // # of pieces on ONE face

    public enum Color{
        RED { @Override public String toString(){ return "R"; } },
        BLUE { @Override public String toString(){ return "B"; } },
        WHITE { @Override public String toString(){ return "W"; } },
        YELLOW { @Override public String toString(){ return "Y"; } },
        ORANGE { @Override public String toString(){ return "O"; } },
        GREEN { @Override public String toString(){ return "G"; } };

        public static Color fromInt(int idx){
            switch (idx){
                case 0: return RED;
                case 1: return BLUE;
                case 2: return WHITE;
                case 3: return YELLOW;
                case 4: return ORANGE;
                case 5: return GREEN;
                default: return null;
            }
        }
    }

    // the KEY will represent the side color aka the center color BC CENTER COLOR SHALL STAY CONSISTENT
    // the VALUE will be the pieces on this face according to the "CUBE_MAP"
    public Map<Color, Color[]> matrix;

    // make default cube
    public Cube(){
        matrix = makeDefault();
    }

    public void toDefault(){
        matrix = makeDefault();
    }

    // converts this object into a solved cube
    private Map<Color, Color[]> makeDefault(){

        Map<Color, Color[]> cube = new HashMap<Color, Color[]>();

        Color[] colorOrder = {Color.RED, Color.BLUE, Color.WHITE, Color.YELLOW, Color.ORANGE, Color.GREEN};

        // make faces
        Color[] face; // buffer
        for (int i = 0; i < NUM_FACES; i++){
            face = fullColorFace(colorOrder[i]);
            cube.put(colorOrder[i], face);
        }

        return cube;
    }

    // make 3x3 color array of same color
    private Color[] fullColorFace(Color faceColor){

        Color[] face = new Color[PIECES_PER_FACE];

        for (int i = 0; i < PIECES_PER_FACE; i++){
            face[i] = faceColor;
        }

        return face;
    }

    // changes the cube map based on what face was turned
    // clockwise is considered looking at a face on the CUBE_MAP and rotating it clockwise
    public void turn(Color faceToturn, boolean clockwise){
        // i'm not smart enough to make code reusable for all 12 different turns

        faceturn(faceToturn, clockwise);
        switch (faceToturn){

            // CORNER SWAPPING = "alpha" and "beta" refer to the different sides of the corner, for r2 = "1:w6 & 2:b0", for r8 = "1:b6 & 2: y0"  
            case BLUE:
                // CORNERS
                ArraySwap.swapFour(matrix.get(Color.RED), 2, matrix.get(Color.WHITE), 8, matrix.get(Color.ORANGE), 6, matrix.get(Color.YELLOW), 0, clockwise); // alpha
                ArraySwap.swapFour(matrix.get(Color.WHITE), 6, matrix.get(Color.ORANGE), 0, matrix.get(Color.YELLOW), 2, matrix.get(Color.RED), 8, clockwise); // beta

                // EDGES
                ArraySwap.swapFour(matrix.get(Color.WHITE), 7, matrix.get(Color.ORANGE), 3, matrix.get(Color.YELLOW), 1, matrix.get(Color.RED), 5, clockwise);
                break;
            case GREEN:
                // CORNERS
                ArraySwap.swapFour(matrix.get(Color.ORANGE), 2, matrix.get(Color.WHITE), 0, matrix.get(Color.RED), 6, matrix.get(Color.YELLOW), 8, clockwise); // alpha
                ArraySwap.swapFour(matrix.get(Color.WHITE), 2, matrix.get(Color.RED), 0, matrix.get(Color.YELLOW), 6, matrix.get(Color.ORANGE), 8, clockwise); // beta

                // EDGES
                ArraySwap.swapFour(matrix.get(Color.WHITE), 1, matrix.get(Color.RED), 3, matrix.get(Color.YELLOW), 7, matrix.get(Color.ORANGE), 5, clockwise);
                break;
            case ORANGE:
                // CORNERS
                ArraySwap.swapFour(matrix.get(Color.BLUE), 2, matrix.get(Color.WHITE), 2, matrix.get(Color.GREEN), 6, matrix.get(Color.YELLOW), 2, clockwise); // alpha
                ArraySwap.swapFour(matrix.get(Color.WHITE), 8, matrix.get(Color.GREEN), 0, matrix.get(Color.YELLOW), 8, matrix.get(Color.BLUE), 8, clockwise); // beta

                // EDGES
                ArraySwap.swapFour(matrix.get(Color.WHITE), 5, matrix.get(Color.GREEN), 3, matrix.get(Color.YELLOW), 5, matrix.get(Color.BLUE), 5, clockwise);
                break;
            case RED:
                // CORNERS
                ArraySwap.swapFour(matrix.get(Color.GREEN), 2, matrix.get(Color.WHITE), 6, matrix.get(Color.BLUE), 6, matrix.get(Color.YELLOW), 6, clockwise); // alpha
                ArraySwap.swapFour(matrix.get(Color.WHITE), 0, matrix.get(Color.BLUE), 0, matrix.get(Color.YELLOW), 0, matrix.get(Color.GREEN), 8, clockwise); // beta

                // EDGES
                ArraySwap.swapFour(matrix.get(Color.WHITE), 3, matrix.get(Color.BLUE), 3, matrix.get(Color.YELLOW), 3, matrix.get(Color.GREEN), 5, clockwise);
                break;
            case WHITE:
                // CORNERS
                ArraySwap.swapFour(matrix.get(Color.RED), 0, matrix.get(Color.GREEN), 0, matrix.get(Color.ORANGE), 0, matrix.get(Color.BLUE), 0, clockwise); // alpha
                ArraySwap.swapFour(matrix.get(Color.GREEN), 2, matrix.get(Color.ORANGE), 2, matrix.get(Color.BLUE), 2, matrix.get(Color.RED), 2, clockwise); // beta

                // EDGES
                ArraySwap.swapFour(matrix.get(Color.GREEN), 1, matrix.get(Color.ORANGE), 1, matrix.get(Color.BLUE), 1, matrix.get(Color.RED), 1, clockwise);
                break;
            case YELLOW:
                // CORNERS
                ArraySwap.swapFour(matrix.get(Color.RED), 8, matrix.get(Color.BLUE), 8, matrix.get(Color.ORANGE), 8, matrix.get(Color.GREEN), 8, clockwise); // alpha
                ArraySwap.swapFour(matrix.get(Color.BLUE), 6, matrix.get(Color.ORANGE), 6, matrix.get(Color.GREEN), 6, matrix.get(Color.RED), 6, clockwise); // beta

                // EDGES
                ArraySwap.swapFour(matrix.get(Color.BLUE), 7, matrix.get(Color.ORANGE), 7, matrix.get(Color.GREEN), 7, matrix.get(Color.RED), 7, clockwise);
                break;

        }
    }

    // turns just the face...
    private void faceturn(Color faceToturn, boolean clockwise){
        Color[] face = matrix.get(faceToturn);

        ArraySwap.swapFour(face, 0, 2, 8, 6, clockwise);
        ArraySwap.swapFour(face, 1, 5, 7, 3, clockwise);
    }

    public void scramble(String sequence){
        scramble(sequence.split(" ", 0));
    }

    public void scramble(String[] sequence){

    }
    
    public void scramble(){

        // scramble sequence
        String scrambleSeq = "";

        int numturns = (int)(16 * Math.random()) + 5; // 10-20 moves

        while (numturns > 0){
            numturns--;

            int turnFace = (int)(6 * Math.random());
            int turnType = (int)(3 * Math.random());
            Color face = Color.fromInt(turnFace);

            scrambleSeq += face.toString();


            switch (turnType){
                case 0:
                    turn(face, true);
                    break;
                case 1:
                    scrambleSeq += "\'";
                    turn(face, false);
                    break;
                case 2:
                    scrambleSeq += "2";
                    turn(face, true);
                    turn(face, true);
                    break;
            }

            scrambleSeq += " ";
        }

        System.out.println(scrambleSeq);
    }

    @Override public String toString(){

        return getCubeMap();
    }

    private String getCubeMap(){
        // MAKES THE VISUAL CUBE MAP
        String msg = "";

        // white
        Color[] white = matrix.get(Color.WHITE);
        for (int w = 0; w < 3; w++){
            msg = msg + "\t" + white[3*w] + " " + white[3*w+1] + " " + white[3*w+2] + "\n";
        }

        // other colors
        Color[] red = matrix.get(Color.RED);
        Color[] blue = matrix.get(Color.BLUE);
        Color[] orange = matrix.get(Color.ORANGE);
        Color[] green = matrix.get(Color.GREEN);
        String redS = "", blueS = "", orangeS = "", greenS = "";
        for (int c = 0; c < 3; c++){

            redS = red[3*c].toString() + " " + red[3*c+1] + " " + red[3*c+2];
            blueS = blue[3*c].toString() + " " + blue[3*c+1] + " " + blue[3*c+2];
            orangeS = orange[3*c].toString() + " " + orange[3*c+1] + " " + orange[3*c+2];
            greenS = green[3*c].toString() + " " + green[3*c+1] + " " + green[3*c+2];
            
            msg = msg + redS + "\t" + blueS + "\t" + orangeS + "\t" + greenS + "\n";
        }

        // yellow
        Color[] yellow = matrix.get(Color.YELLOW);
        for (int y = 0; y < 3; y++){
            
            msg = msg + "\t" + yellow[3*y] + " " + yellow[3*y+1] + " " + yellow[3*y+2] + "\n";
        }

        return msg;
    }

}