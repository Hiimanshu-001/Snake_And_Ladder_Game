import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
public class SnakeAndLadder {

    final static int WINPOINT = 100;
    static Map<Integer, Integer> snake = new HashMap<>();
    static Map<Integer, Integer> ladder = new HashMap<>();

    {
        snake.put(99, 41);
        snake.put(89, 53);
        snake.put(76, 58);
        snake.put(66, 45);
        snake.put(54, 31);
        snake.put(43, 18);
        snake.put(40, 3);
        snake.put(27, 5);


        ladder.put(4, 25);
        ladder.put(13, 46);
        ladder.put(33, 49);
        ladder.put(50, 69);
        ladder.put(62, 81);
        ladder.put(74, 92);
    }

    //dice roll: on rolling dice you will get any number in between 1 to 6 included
    public int rollDice() {
      return (int)Math.floor(Math.random()*6 + 1);
//      return 6;
    }

    /*calculatePlayerPosition will calculate the new position of
    *the player after it has moved certain distance
     */
    public int calculatePlayerPosition(int playerPosition, int diceValue) {
        int playerNewPosition=playerPosition+diceValue;

        if (playerNewPosition > WINPOINT)
            return playerPosition;

        if (null !=snake.get(playerNewPosition)) {
            System.out.println("Oops..swallowed by the snake..");
            System.out.println("You fell from " + playerNewPosition + " to " + snake.get(playerNewPosition));
            playerNewPosition=snake.get(playerNewPosition);
        }

        if (null !=ladder.get(playerNewPosition)) {
            System.out.println("YAY! climbing the ladder... from " + playerNewPosition + " to " + ladder.get(playerNewPosition));
            playerNewPosition=ladder.get(playerNewPosition);
        }

        return playerNewPosition;
    }

    public boolean isWin(int playerPosition) {
        return WINPOINT==playerPosition;
    }

    public void startGame() {
        int player1Position=0, player2Position=0;
        int currentPlayer=-1;
        Scanner scan= new Scanner(System.in);
        String rPressed;
        int diceValue = 0;
        int count = 0;
        do {
            System.out.println(currentPlayer == -1
                    ? "\n\nFirst player's turn" : "\n\nSecond player's turn");
            System.out.println("Press 'r' to roll Dice");
            rPressed=scan.next();
            diceValue=rollDice();

            if(diceValue == 6){
                count++;
                if(count==3){
                    System.out.println("Damn, you got 6 for 3rd time straight.");
                    if(currentPlayer == -1){
                        System.out.println("In hurry are you? Take that, you are off where you started ;(");
                        player1Position -= 12;
                        System.out.println("Player 1 is back at where he started");
                        System.out.println("Player 1 position is now : " + player1Position);
                    }else{
                        System.out.println("In hurry are you? Take that you are off where you started ;(.");
                        player2Position -= 12;
                        System.out.println("Player 2 is back at where it started");
                        System.out.println("Player 2 position is now : " + player2Position);
                    }

                    currentPlayer = -currentPlayer;
                    count = 0;
                    continue;
                }
            }


            if (currentPlayer==-1) {
                player1Position= calculatePlayerPosition(player1Position, diceValue);
                System.out.println("Player 1 can move " + diceValue + " positions ahead.");
                System.out.println("First Player Position:"+player1Position);
                System.out.println("Second Player Position:"+player2Position);
                System.out.println("-------------------------");
                if (isWin(player1Position)) {
                    System.out.println("Congratulations! First player won");
                    return;
                }

            } else {
                player2Position = calculatePlayerPosition(player2Position, diceValue);
                System.out.println("Player 2 can move " + diceValue + " positions ahead.");
                System.out.println("First Player Position:"+player1Position);
                System.out.println("Second Player Position:"+player2Position);
                System.out.println("-------------------------");
                if (isWin(player2Position)) {
                    System.out.println("Congratulations! Second player won");
                    return;
                }
            }

            if(diceValue == 6){
                System.out.println("You got 6 on your dice. You can move once again :D");
            }else {
                currentPlayer = -currentPlayer;
                count = 0;
            }
        } while ("r".equals(rPressed));
    }
}
