import java.util.Scanner;
import java.io.*;
public class ConnectFour {
    private char[][] gameBoard = new char[6][7];
    private String playerTurn;
    private char token;
    public ConnectFour() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                gameBoard[i][j] = ' ';
            }
        }
        playerTurn = "Red";
        token = 'R';
    }
    public String getPlayerTurn() {
        return playerTurn;
    }
    public char getToken() {
        return token;
    }
     public void nextTurn() {
        if (playerTurn.equals("Red")) {
            playerTurn = "Yellow";
            token = 'Y';
        } else {
            playerTurn = "Red";
            token = 'R';
        }
     }
     public int nextAvailablePosition(int colNum) {
        int availableRow = 0;
        if (gameBoard[0][colNum] != ' ') {
            return -1;
        } else {
            for (int i = 0; i <= 5; i++) {
                if (gameBoard[i][colNum] == ' ') {
                    availableRow = i;
                }
            }
        }
        return availableRow;
     }
     public void dropPiece(int colNum, char token) throws ColumnFull {
        int row = nextAvailablePosition(colNum);
        if (row == -1) {
            throw new ColumnFull("That column is full try again");
        } else {
            gameBoard[row][colNum] = token;
        }
     }

     @Override
    public String toString() {
         String to_return="  0   1   2   3   4   5  6";
         for(int i=0;i<6;i++) {
             to_return+="\n-----------------------------\n";
             to_return+="| ";
             for(int j=0;j<7;j++) {
                 to_return+=gameBoard[i][j]+" | ";
             }
         }
         to_return+="\n-----------------------------\n";
         return to_return;
    }
    public void saveGame() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter filename:");
        String fileName = sc.nextLine();
        File userFile;
        PrintWriter pw;
        try {
            userFile = new File(fileName);
            pw = new PrintWriter(userFile);
            for (int i = 0; i<6; i++) {
                for (int j = 0; j<7; j++) {
                    pw.println(gameBoard[i][j]+",");
                }
            }
            pw.flush();
            pw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
    public void loadGame() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a filename:");
        String fileName = sc.nextLine();
        try {
            File file = new File(fileName);
            Scanner scan = new Scanner(file);
            int row = 0, col = 0;
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                String[] splitLine = line.split(",");
                for (int i = 0; i< splitLine.length; i++) {
                    char c = splitLine[0].charAt(i);
                    gameBoard[row][col] = c;
                    col++;
                    if (col == 7) {
                        col = 0;
                        row++;
                    }
                }
            }
            scan.close();
        } catch (FileNotFoundException fnfe) {
            System.out.println("Unable to read that file");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
