import java.util.Scanner;

/**
 * This class is a text-based implementation of the classic Tic-Tac-Toe game. 
 * The user has a choice of playing against a friend or the computer.
 * The player1 is always X, and player2 or the computer is O.
 * To win the game, you must place your given letter in three in a row, three in a column or three in diagonal.
 * 
 * Date: 6/12/2018
 * Version: 1.0 
 * @author Joshua Johnston 
 * 
 */

public class TicTacToe {
	
	public static void main(String[] args){
		
		Scanner input = new Scanner(System.in);
		
		int count = 0;
		int row = 0;
		int column = 0;
		
		char turn = 'X';        //Player1 is always X and plays first		
		
		char[][] gameBoard = new char[3][3];		
		
		int option = menu(input);
		drawBoard(gameBoard);
		
		while(true){			
			
			System.out.print("Enter a row (0, 1, or 2) for player " + (turn == 'X' ? "X" : "O" ) + ": ");
			row = getUserInput(input, option, turn);
			System.out.print(option == 2 && turn == 'O' ? row + "\n" : ""); //Print the computer's row number
			
			System.out.print("Enter a column (0, 1, or 2) for player " + (turn == 'X' ? "X" : "O" ) + ": ");
			column = getUserInput(input, option, turn);
			System.out.print(option == 2 && turn == 'O' ? column + "\n" : "");  //Print the computer's column number
			
			if(fillBoard(gameBoard, turn, row, column) == -1){   //If the cell is not empty then back to top of the loop
				continue;                                    //for the same player to try again.
			}
			else
				count++;
			
			drawBoard(gameBoard);
			
			if(count == 9){                            //every cell is filled
				System.out.println("It's a draw!");
				System.out.println();
				count = 0;
				option = menu(input);
				
				if(option != 3){
					resetBoard(gameBoard);
					drawBoard(gameBoard);
				}
			}
			else if(check(gameBoard, turn)){      //Winning condition
				System.out.println("The player " + turn + " is the winner!");
				System.out.println();
				count = 0;
				option = menu(input);
				
				if(option != 3){
					resetBoard(gameBoard);
					drawBoard(gameBoard);
				}
			}
			else 
				turn = (turn == 'X')? 'O' : 'X';   //Next player's turn			
		}		
		
	}//end of main()
//-----------------------------------------------------------------------------------
/**
 * This method displays the game menu, and prompts the user for input.
 * If the user enters a number out of bounds, an error message is printed, and asks for user input again until it's valid.
 *  
 * @param  input : Scanner
 * @return option : int
 */
	public static int menu(Scanner input){
		
		int option = 0;
		boolean notValid = true;
		
		do{
			System.out.println("Tic Tac Toe");
			System.out.println("1) player1 vs player2");
			System.out.println("2) player1 vs computer");
			System.out.println("3) Exit");
			System.out.print("Enter 1, 2, or 3: ");
			
			option = input.nextInt();
			
			if(option < 1 || option > 3){
				System.out.println("Error: the number " + option + " is invalid.");				
				
			}
			else
				notValid = false;
			
		}while(notValid);
		
		if(option == 3){
			System.out.println("Exiting the program...");
			System.exit(0);
		}
		
		return option;
	}//end of menu()
//-----------------------------------------------------------------------------------
/**
 * This method prints the game board out.
 * 	
 * @param board : char[][]
 */
	public static void drawBoard(char[][] board){
		
		for(int i = 0; i < board.length; i++){
			System.out.println("-------------");
			for(int j = 0; j < board[i].length; j++){
				System.out.print("| " + board[i][j] + " ");
			}
			System.out.println("|");
		}
		System.out.println("-------------");
		
	}//end of drawBoard()
//-----------------------------------------------------------------------------------
/**
 * This method prompts the user for input or generates a random number between 0-2 
 * depending on if the user is playing against a friend or the computer. If the user enters a number out of bounds
 * an error message is printed, and asks for new input until the input is valid.
 * 	
 * @param input : Scanner
 * @param option : int
 * @param player : char
 * @return number : int
 */
	public static int getUserInput(Scanner input, int option, char player){
		
		int temp = 0;		
		boolean notValid = true;
		
		if(option == 1 || player == 'X'){                    //Getting input from player1 or player2
			do{
				temp = input.nextInt();
				
				if(temp < 0 || temp > 2){
					System.out.println("Error: the number " + option + " is invalid.");	
				}
				else
					notValid = false;
			}while(notValid);
			
			return temp;			
		}
		else{
			return (int)(Math.random() * 3);               //Getting random number for the computer player
		}
		
		
	}//end of getUserInput()
//-----------------------------------------------------------------------------------
/**
 * This method checks to see if the player wins or not.
 * It checks to see if three of player's letter are in a column, in a row, or diagonal, and
 * if so then return true else return false.
 *  
 * @param board : char[][]
 * @param player : char
 * @return true or false
 */
	public static boolean check(char[][] board, char player){
		
		//Vertical		
		for(int column = 0; column < board.length; column++){
			if(board[0][column] == player && board[1][column] == player && board[2][column] == player){
				return true;
			}
		}
		
		//Horizontal
		for(int row = 0; row < board.length; row++){
			if(board[row][0] == player && board[row][1] == player && board[row][2] == player){
				return true;
			}
			
		}
		
		//Diagonal
		if((board[0][0] == player && board[1][1] == player && board[2][2] == player)
                    || (board[0][2] == player && board[1][1] == player && board[2][0] == player)){
			
			return true;
		}
		
		return false;		
	}//end of check()
//-----------------------------------------------------------------------------------
/**
 * This method inserts the player's letter into the game board.
 * If the cell is empty then the player's letter gets inserted else it will print an error message and return a -1 for error.
 *  	
 * @param board : char[][]
 * @param player : char
 * @param row : int
 * @param column : int
 * @return o or -1 : int
 */
	public static int fillBoard(char[][] board, char player, int row, int column){
		
		if(board[row][column] == 0){
			board[row][column] = player;
		}
		else{
			System.out.println("Error: not an empty cell");
			return -1;
		}
		return 0;
	}//end of fillBoard()
//-----------------------------------------------------------------------------------
/**
 * This method resets the game board back to an empty board.
 * 
 * @param board : char[][]
 */
	public static void resetBoard(char[][] board){
		
		for(int i = 0; i< board.length; i++){
			for(int j = 0; j < board[i].length; j++){
				board[i][j] = 0;
			}
		}
	}//end of resetBoard()
//-----------------------------------------------------------------------------------
	
/////////////////////////////////////////////////////////////////////////////////////
}//end of TicTacToe Class
