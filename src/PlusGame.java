import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONObject;


public class PlusGame {

    public static void main(String[] args) {
        GameManager plusGame = new GameManager();
        plusGame.play();
    }
    // D2coding
}

class GameManager {  // 게임 전반을 운영하는 클래스

    private GameMaker gameMaker = new GameMaker();       // 문제를 출제하는 클래스
    private GamePlayer gamePlayer = new GamePlayer();    // 문제를 풀고 채점하는 클래스
    private GameRecord gameRecord = new GameRecord();    // 결과를 저장하고 불러오는 클래스
    private Scanner screenInput = new Scanner(System.in);

    private static final int COMMAND_STARTGAME = 1;
    private static final int COMMAND_VIEWRESULT = 2;
    private static final int COMMAND_EXIT = 3;


    public void play() {
        printMessage("덧셈게임에 오신 것을 환영합니다");
        while(true){
            printMainUI();
            gameRecord.loadRecord();  // 파일에서 저장된 점수 데이터를 읽어 옴

            try {
                int command = screenInput.nextInt();

                if(command == COMMAND_STARTGAME){
                    JSONArray questions = gameMaker.makeGame();
                    gamePlayer.play(questions);

                    JSONObject result = gamePlayer.getResult();
                    gameRecord.saveResult(result);

                    printMessage("게임을 완료하였습니다.");



                }else if(command == COMMAND_VIEWRESULT){
                    String resultString = gameRecord.getRecord();
                    printMessage(resultString);
                    System.out.println("[Enter]를 치세요." );
                    screenInput.nextLine();
                    screenInput.nextLine();

                }else if(command == COMMAND_EXIT){
                    printMessage("게임을 종료합니다.");
                    gameRecord.saveRecord();
                    break;
                }

            } catch (Exception e) {
                printMessage("Error - 입력값 에러");
                screenInput.nextLine();   //  예외가 발생하면 버퍼를 비움   screenInput = new Scanner(System.in);
            }
        }
    }

    private void printMainUI(){
        // 메인 UI 출력
        System.out.println("==========================================================");
        System.out.println("1. 게임시작 " );
        System.out.println("2. 점수보기 " );
        System.out.println("3. 종료 " );
        System.out.println("==========================================================");
        System.out.print("Command> ");
    }

    private void printMessage(String str){
        System.out.println(str);
    }

}

