/**
 *
 */
package com.brobert;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.brobert.TwoDimensionalBoard.Token;

/**
 * @author brobert
 *
 */
public abstract class Game {

	private TwoDimensionalBoard board;

	private Token computer, human;

	public int turns;



	public void human(Token human) {
		this.human = human;
		if (human == Token.X) {
			computer = Token.O;
		} else {
			computer = Token.X;
		}
	}



	public Token getHuman() {
		return human;
	}



	public Token getComputer() {
		return computer;
	}



	/**
	 * @return
	 */
	public boolean isOver() {
		if (board.enoughInARow(computer)) {
			System.out.println("Computer Won!");
			boolean humanWon = board.enoughInARow(computer);
			System.exit(0);
			return true;
		}
		if (board.enoughInARow(human)) {
			System.out.println("Human Won!");
			boolean humanWon = board.enoughInARow(human);
			System.exit(0);
			return true;
		}
		if (board.isFull()) {
			System.out.println("Stalemate.");
			System.exit(0);
			return true;
		}
		return false;
	}



	public int getInt(Scanner scanner) {
		boolean isValid = false;
		int entry = 0;
		while (isValid == false) {
			try {
				entry = scanner.nextInt();
				isValid = true;
				if (entry > (board.width()) || entry < 1) {
					isValid = false;
					System.out.println("Integer is out of bounds of the width of the board: " + board.width());
				}
			} catch (InputMismatchException e) {
				System.out.println("Invalid integer please try again...");
				scanner.next();
				isValid = false;
			}
		}
		return entry;

	}



	public abstract boolean isValidCoordinate(int x, int y);



	public abstract void printTurn(Token square, Coordinate coordinate);



	public TwoDimensionalBoard getBoard() {
		return board;
	}



	public void setBoard(TwoDimensionalBoard board) {
		this.board = board;
	}



	public abstract void start();



	public abstract void playerTurn(Scanner scanner);



	public void computerTurn(ComputerPlayer player) {
		Coordinate computerPlay = player.play(getBoard());
		getBoard().placePiece(computerPlay.x, computerPlay.y, getComputer());
		printTurn(getComputer(), computerPlay);
		turns++;
	}
}
