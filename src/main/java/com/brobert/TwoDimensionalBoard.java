package com.brobert;

public interface TwoDimensionalBoard {

	int height();



	int width();





	enum Token {
		X, O, EMPTY;
	}



	void placePiece(int x, int y, Token pieceType);



	void printBoard();



	boolean isFull();



	Token at(int x, int y);



	boolean enoughInARow(Token computer);
}
