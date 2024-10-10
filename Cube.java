// this class will be responsible for handling the data of a cube.
import java.util.*;

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

                    w   w   w
                    w   w   w
                    w   w   w
        r   r   r   b   b   b   o   o   o   g   g   g
        r   r   r   b   b   b   o   o   o   g   g   g
        r   r   r   b   b   b   o   o   o   g   g   g
                    y   y   y
                    y   y   y
                    y   y   y
    */

    public final int NUM_FACES = 6;
    public final int PIECES_PER_FACE = 9; // # of pieces on ONE face


    public enum Color{
        RED,
        BLUE,
        WHITE,
        YELLOW,
        ORANGE,
        GREEN
    }

    // the KEY will represent the side color aka the center color BC CENTER COLOR SHALL STAY CONSISTENT
    // the VALUE will be the pieces on this face according to the "CUBE_MAP"
    public Map<Color, Color[]> matrix;

    // make default cube
    public Cube(){
        matrix = MakeDefault();
    }

    // converts this object into a solved cube
    private Map<Color, Color[]> MakeDefault(){

        Map<Color, Color[]> cube = new HashMap<Color, Color[]>();

        Color[] colorOrder = {Color.RED, Color.BLUE, Color.WHITE, Color.YELLOW, Color.ORANGE, Color.GREEN};

        // make faces
        Color[] face; // buffer
        for (int i = 0; i < NUM_FACES; i++){
            face = FullColorFace(colorOrder[i]);
            cube.put(colorOrder[i], face);
        }

        return cube;
    }

    // make 3x3 color array of same color
    private Color[] FullColorFace(Color faceColor){

        Color[] face = new Color[PIECES_PER_FACE];

        for (int i = 0; i < PIECES_PER_FACE; i++){
            face[i] = faceColor;
        }

        return face;
    }

}