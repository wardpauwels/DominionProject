import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
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
    ArrayList<String> playerNames = new ArrayList<String>();;
    JSONObject names;

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        response.setContentType("application/json");
        Writer writer = response.getWriter();

        String operation = request.getParameter("operation");

        switch (operation){
            case "initialize":
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
                System.out.println(names);
                initGame();
                break;
            case "getNames":
                writer.append(names.toString());
                break;


            case "playCard":
                int positionInHand;
                positionInHand = Integer.parseInt(request.getParameter("positionInHand"));
                System.out.println("nummer " + positionInHand+1 +  "gespeeld!");
                useActionCard(positionInHand);
        }
    }

    public void initGame(){
        System.out.println(ammountOfPlayers());
        g = new Game();
        g.createPlayersList(ammountOfPlayers());
        setNames();
    }

    public int ammountOfPlayers() {
        int ammount;
        if (name4 != null){
            playerNames.add(name1);
            playerNames.add(name2);
            playerNames.add(name3);
            playerNames.add(name4);
            ammount = 4;
        } else if (name3 != null){
            playerNames.add(name1);
            playerNames.add(name2);
            playerNames.add(name3);
            ammount = 3;
        } else{
            playerNames.add(name1);
            playerNames.add(name2);
            ammount = 2;
        }
        return ammount;
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
