// All 27 Piece classifications for each piece on the cube.  Will be based on the above cube map.
    // Identification string goes in order of red-orange side, blue-green side, and white-yellow side.
    public enum Piece{
        R0G2W0,
        G1W1,
        O2G0W2,
        R1W3,
        W4, // center
        O1W5,
        R2B0W6,
        B1W7,
        O0B2W8,
        R3G5,
        G4, // center
        O5G3,
        R4, // center
        NULL, // the core... dk why i put an enum for this
        O4, // center
        R5B3,
        B4, // center
        O3B5,
        R6G8Y6,
        G7Y7,
        O8G6Y8,
        R7Y3,
        Y4, // center
        O7Y5,
        R8B6Y0,
        B7Y1,
        O6B8Y2
    }