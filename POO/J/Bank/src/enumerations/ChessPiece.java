package enumerations;

// Creating an enumerations
public enum ChessPiece {
	KING(1), QUEEN(2), ROOK(3), BISHOP(4), KNIGHT(5), PAWN(6);
	public int index;
	ChessPiece(int index) {
		this.index = index;
	}
}
