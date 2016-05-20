import java.io.*;
import java.lang.reflect.Array;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.*;

import static java.lang.Integer.parseInt;


//@WebServlet(name = "BoardServlet")
public class BoardServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    String name1;
    String name2;
    String name3;
    String name4;
    Game g;
    ArrayList<String> playerNames;
    JSONObject names;
    JSONObject cards = new JSONObject();
    String[] cardNames;
    JSONObject actionCards = new JSONObject();
    Card[] actionCardsOnBoard;


    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        response.setContentType("application/json");
        Writer writer = response.getWriter();

        String action = request.getParameter("action");

        switch (action){
            case "init":
                name1 = request.getParameter("name1");
                name2 = request.getParameter("name2");
                name3 = request.getParameter("name3");
                name4 = request.getParameter("name4");
                names = new JSONObject();
                names.append("name1", name1);
                names.append("name2", name2);
                names.append("name3", name3);
                names.append("name4", name4);

                writer.append(names.toString());
                System.out.println("Test");
                System.out.println(names);
                //System.out.println("amount"+countAmountOfPlayers());
                //System.out.println("s1");
                g = new Game();
                System.out.println("s2");
                g.createPlayersList(2);
                System.out.println("s3");
                cardNames = new String[g.allPlayers.get(g.player).getHandSize()];
                for(int i = 0; i < g.allPlayers.get(g.player).getHandSize();i++){
                    /*if(i == g.allPlayers.get(g.player).getHandSize()-1){
                    cards.put("cardName",g.allPlayers.get(g.player).getCardOnPosInHand(i).getName());}
                    else{
                        cards.put("cardName",g.allPlayers.get(g.player).getCardOnPosInHand(i).getName()+",");
                    }*/
                    cardNames[i] = g.allPlayers.get(g.player).getCardOnPosInHand(i).getName();

                }
                cards.put("CardNames",cardNames);

                System.out.println(cards);
                writer.append(cards.toString());
                System.out.println(cards);


                break;
            case "getCards":
                //writer.append(names.toString());
                writer.append(cards.toString());
                break;


            case "playCard":
                int positionInHand;
                positionInHand = Integer.parseInt(request.getParameter("positionInHand"));
                System.out.println("nummer " + positionInHand+  "gespeeld!");
                useActionCard(positionInHand);
                break;
            case "updateHand":
                cardNames = new String[g.allPlayers.get(g.player).getHandSize()];
                for(int i = 0; i < g.allPlayers.get(g.player).getHandSize();i++){
                    cardNames[i] = g.allPlayers.get(g.player).getCardOnPosInHand(i).getName();
                }
                cards.put("CardNames",cardNames);
                System.out.println(cards);
                writer.append(cards.toString());

                break;
            case "updateActionBoard":
                actionCardsOnBoard = new Card[g.actionCardsOnBoard.size()];
                for(int i = 0; i < g.actionCardsOnBoard.size();i++){
                    actionCardsOnBoard[i] = g.actionCardsOnBoard.get(i);
                }
                actionCards.put("actionCardsOnBoard",actionCardsOnBoard);
                System.out.println(actionCards);
                writer.append(actionCards.toString());
                g.printActionCards();



        }
    }

    public void initGame(){
        System.out.println("amount"+countAmountOfPlayers());
        System.out.println("s1");
        g = new Game();
        System.out.println("s2");
        g.createPlayersList(countAmountOfPlayers());
        System.out.println("s3");
        setNames();
    }


    private int countAmountOfPlayers() {
        if (name4 != null){
            playerNames.add(name1);
            playerNames.add(name2);
            playerNames.add(name3);
            playerNames.add(name4);
            return 4;
        } else if (name3 != null){
            playerNames.add(name1);
            playerNames.add(name2);
            playerNames.add(name3);
            return 3;
        } else{
            playerNames.add(name1);
            playerNames.add(name2);
            return 2;
        }
    }


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
