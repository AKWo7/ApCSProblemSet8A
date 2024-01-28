import java.util.Scanner;
//Initialize board, print board, validInput?, validMove?, switch players, check win.

public class TicTacToe
{
    private static final int size = 3;
    private static char[][] board = new char[size][size];
    private static int round = 1;
    private static char currentPlayer = 'X';

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        while(true){
            initializeBoard();
            printBoard();
            while(true){
                System.out.println("Round " + round+ ":");
                System.out.println(currentPlayer + ", make your move (row,col): ");
                String input = scanner.nextLine();
                if (isValidInput(input)){
                    int[] move = parseMove(input);
                    if(move != null && isValidMove(move[0],move[1])){
                        makeMove(move[0],move[1]);
                        printBoard();                        
                        if (checkWin()){
                            System.out.println("PLAYER " + currentPlayer + " WON!");
                            break;
                        }
                        else if (round == size * size) {//gone for 9 rounds and no more moves can be made
                            System.out.println("It's a draw!");
                            break;
                        }
                        switchPlayer();
                        round++;
                    }
                    else{
                        System.out.println("Invalid move. Please try again.");
                    }
                }
                else{
                    System.out.println("Invalid move. Please use the format row,column");
                }
            }
            System.out.print("Play again? Y/N ");
            String playAgain = scanner.nextLine().toLowerCase();
            if (!playAgain.equals("yes") && !playAgain.equals("y")){
                System.out.println("Thanks for playing!");
                break;
            }
            
        }

    }

    private static void initializeBoard(){
        for(int i = 0; i<size; i++){
            for (int j = 0; j < size;j++){
                board[i][j] = '_';
            }
        }
        round = 1;
        currentPlayer = 'X';
    }

    private static void printBoard(){
        System.out.println("");
        for (int i = 0; i<size; i++){
            System.out.print("| ");
            for (int j = 0; j<size; j++){
                System.out.print(board[i][j] + " | ");
            }
            System.out.println("");
        }
    }

    private static int parseCoords(String coords){
        int result = 0;
        for (char c : coords.toCharArray()){
            if(!Character.isDigit(c)){
                return -1;
            }
            result = result * 10 + (c - '0');
        }
        return result -1; //adjusting to 0 index
    }

    private static boolean isValidInput(String input){
        String[] coord = input.split(",");
        if (coord.length == 2){
            int row = parseCoords(coord[0]);
            int col = parseCoords(coord[1]);
            return row >= 0 && row < size && col >= 0 && col < size;
        }
        return false;
    }
    
    private static int[] parseMove(String input){
        String[] coord = input.split(",");
        int row = parseCoords(coord[0]);
        int col = parseCoords(coord[1]);
        if (row >= 0 && col >= 0){
            return new int[]{row, col};
        }
        else{
            return null;
        }
    }
    
    private static boolean isValidMove(int row, int col){
        return board[row][col] == '_';
    }
    
    private static void makeMove(int row, int col){
        board[row][col] = currentPlayer;
    }
    
    private static void switchPlayer(){
        if (currentPlayer == 'X'){
            currentPlayer = 'O';
        }
        else {
            currentPlayer = 'X';
        }
    }
    
    private static boolean checkWin(){
        for (int i = 0; i <size; i++){
            if (board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer){
                return true;
            }
            if (board[0][i] == currentPlayer && board[1][i] == currentPlayer && board[2][i] == currentPlayer){
                return true;
            }
        }
        
        if (board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer){
                return true;
            }
            
        if (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer){
                return true;
        }
        
        return false;
    }
    
}