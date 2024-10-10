// this class will be responsible for handling data of a cube piece

public class Piece{

    enum Type{
        CENTER,
        EDGE,
        CORNER
    }

    enum Face{
        RED,
        BLUE,
        WHITE,
        YELLOW,
        ORANGE,
        GREEN
    }

    public Face[] faces;
    public Type type;
    
    
}