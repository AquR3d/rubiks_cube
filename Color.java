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

    public static Color fromString(String idx){
        switch (idx){
            case "R": return RED;
            case "B": return BLUE;
            case "W": return WHITE;
            case "Y": return YELLOW;
            case "O": return ORANGE;
            case "G": return GREEN;
            default: return null;
        }
    }

    public boolean equals(Color other){
        return this.toString().equals(other.toString());
    }
}