import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ConnectFour game = new ConnectFour();
        int value;
        do {
            System.out.println(game);
            System.out.println("Which column would "+game.getPlayerTurn()+" like to go in(7 to save, 8 to load, 9 to quit)");
            value = sc.nextInt();
            if (value == 0 || value==1 || value==2 || value==3 ||value==4 || value==5 || value==6) {
                try {
                    game.dropPiece(value, game.getToken());
                    game.nextTurn();
                } catch (ColumnFull e) {
                    System.out.println(e.getMessage());
                }
            } else if (value == 7) {
                game.saveGame();
            } else if (value == 8) {
                game.loadGame();
            } else if (value == 9) {

            }
        } while (value < 9);
    }
}