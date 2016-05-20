import java.io.*;
import java.util.ArrayList;
import javax.servlet.*;
import javax.servlet.http.*;

import org.json.JSONArray;
import org.json.JSONObject;



//@WebServlet(name = "BoardServlet")
public class BoardServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    String name1;
    String name2;
    Game g = new Game();
    ArrayList<String> playerNames;

    public void doGet (HttpServletRequest request,HttpServletResponse response)
            throws ServletException, IOException
    {
        response.setContentType("application/json");
        Writer writer = response.getWriter();

        String operation = request.getParameter("operation");

        switch (operation){
            case "initialize":
                name1 = request.getParameter("name1");
                name2 = request.getParameter("name2");
                JSONObject names = new JSONObject();
                names.put("name1", name1);
                names.put("name2", name2);
                writer.append(names.toString());
                initGame();
                JSONObject cards = new JSONObject();
                for(int i = 0; i < g.allPlayers.get(g.player).getHandSize();i++){
                    cards.put("kaart"+ i,g.allPlayers.get(g.player).getCardOnPosInHand(i).getName());
                }
                System.out.println(cards);
                writer.append(cards.toString());
                break;

            case "playCard":
                int positionInHand;
                positionInHand = Integer.parseInt(request.getParameter("positionInHand"));
                System.out.println("nummer " + positionInHand+1 +  "gespeeld!");
                useActionCard(positionInHand);


        }
    }

    private void initGame(){

        int amountOfPlayers = countAmountOfPlayers();
        g.createPlayersList(amountOfPlayers);
        putNamesIntoArrayList();
        setNames();



    }
    private void putNamesIntoArrayList(){

    }
    private int countAmountOfPlayers(){
        return 2; //TODO input via website/gebruiker ipv 2
    };

    private void nextTurn(){
        g.nextTurn();
        //todo showNextPlayer GASTEN GEEN IDEE JAVASCRIPT DINGEN






    }

    private void setNames(){
        for (int i=0;i<playerNames.size();i++){
            g.allPlayers.get(i).setName(playerNames.get(i));
        }
    }
    private void useActionCard(int positionInHand){
        g.setDecisionOfPlayerPosition(positionInHand);
        g.playActionCard();


    }
    private void endTurn(){
        g.endTurn();
        //TODO ANIMATION VOOR NIEUW GEMAAKT HAND OPVRAAGBAAR VIA g.returnHand(g.allPlayers.get(player)

    }
    private void endPhase(){
        //TODO ANIMATION VOOR BUYPHASE

    }

    private void buyCard(int positionOnTheBoard, String typeOfToBeBoughtCard){
        g.setDecisionOfPlayerPosition(positionOnTheBoard);
        g.setDecisionOfPlayerType(typeOfToBeBoughtCard);
        g.buyCard();
        g.checkIfFinished();
    }


}
