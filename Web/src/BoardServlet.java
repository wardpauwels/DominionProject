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
    int activePlayer;
    ArrayList<String> playerNames;
    JSONObject names;
    JSONObject cleanNames;
    JSONObject cards = new JSONObject();
    String[] cardNames;
    JSONObject actionCards = new JSONObject();
    JSONObject victoryCards = new JSONObject();
    Card[] cardsOnBoard;
    Player[] player;
    JSONObject treasureCards = new JSONObject();
    JSONObject CAB = new JSONObject();
    JSONObject currentlyPlayingPlayer = new JSONObject();
    int positionOnBoard;
    JSONObject thiefOrSpyArray = new JSONObject();
    boolean finished = false;
    JSONObject gameOver = new JSONObject();


    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        Writer writer = response.getWriter();

        String action = request.getParameter("action");

        switch (action) {
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
                playerNames = new ArrayList<>();
                g.createPlayersList(countAmountOfPlayers());
                setNames();
                activePlayer = 0;

                cardNames = new String[g.allPlayers.get(g.player).getHandSize()];
                for (int i = 0; i < g.allPlayers.get(g.player).getHandSize(); i++) {
                    /*if(i == g.allPlayers.get(g.player).getHandSize()-1){
                    cards.put("cardName",g.allPlayers.get(g.player).getCardOnPosInHand(i).getName());}
                    else{
                        cards.put("cardName",g.allPlayers.get(g.player).getCardOnPosInHand(i).getName()+",");
                    }*/
                    cardNames[i] = g.allPlayers.get(g.player).getCardOnPosInHand(i).getName();
                }
                cards.put("CardNames", cardNames);

                System.out.println(cards);
                writer.append(cards.toString());
                System.out.println(cards);
                break;

            case "getCards":
                writer.append(cards.toString());
                break;

            case "getNames":
                cleanNames = new JSONObject();
                for (int i = 0; i < g.allPlayers.size(); i++) {
                    cleanNames.put("name" + (i + 1), g.allPlayers.get(i).getName());
                }
                cleanNames.put("amount", countAmountOfPlayers());
                writer.append(cleanNames.toString());
                break;

            case "playCard":
                int positionInHand;
                positionInHand = Integer.parseInt(request.getParameter("positionInHand"));
                System.out.println("nummer " + positionInHand + "gespeeld!");
                if (g.currentPhase == -1) {
                    g.activateMilitiaCurse();
                    if (g.allPlayers.get(g.player).getHandSize() == 3) {
                        g.currentPhase = 0;
                        g.allPlayers.get(g.player).cursedByMilitia = false;
                    }
                } else if (g.currentPhase == 0) {
                    boolean throneRoom=false;
                    throneRoom = Boolean.parseBoolean(request.getParameter("throneRoom"));
                    positionInHand = Integer.parseInt(request.getParameter("positionInHand"));
                    if (g.discardingCards()) {
                        discardingCards(positionInHand);
                    }
                    if (g.trashingCards()) {
                        trashingCards(positionInHand);
                    }


                    System.out.println("nummer " + positionInHand + "gespeeld!");
                    if (g.allPlayers.get(g.player).getCardOnPosInHand(positionInHand).getName().toLowerCase().equalsIgnoreCase("militia")) {
                        g.setDecisionOfPlayerPosition(positionInHand);
                        g.playMilitia();
                    }


                    if (throneRoom) {

                        if (!g.allPlayers.get(g.player).getCardOnPosInHand(positionInHand).getName().equals("Throne Room")) {
                            g.setDecisionOfPlayerPosition(positionInHand);
                            g.useThroneRoom(g.player);
                            g.allPlayers.get(g.player).moveCardFromHandToDiscard(positionInHand);
                            g.allPlayers.get(g.player).moveCardFromHandToDiscard(g.allPlayers.get(g.player).scanHandForCardWithName("Throne Room"));
                            throneRoom = false;
                        }
                    }
                    else {
                        System.out.println("nummer " + positionInHand + "gespeeld!");
                        useActionCard(positionInHand);
                    }
                }
                break;




                    /*if (g.allPlayers.get(positionInHand).getNumber() == 13 || g.allPlayers.get(positionInHand).getNumber() == 14) {
                        thiefOrSpyPlayed();
                    } else {
                        System.out.println("nummer " + positionInHand + "gespeeld!");
                        useActionCard(positionInHand);
                    }*/




            case "playThiefOrSpy":
                break;

            case "updateHand":
                cardNames = new String[g.allPlayers.get(g.player).getHandSize()];
                for (int i = 0; i < g.allPlayers.get(g.player).getHandSize(); i++) {
                    cardNames[i] = g.allPlayers.get(g.player).getCardOnPosInHand(i).getName();
                }
                cards.put("CardNames", cardNames);
                g.allPlayers.get(g.getPlayer()).printDeck();
                System.out.println("--------------------");
                g.allPlayers.get(g.getPlayer()).printDiscardPile();
                System.out.println(cards);
                writer.append(cards.toString());
                break;

            case "updateActionBoard":
                cardsOnBoard = new Card[g.actionCardsOnBoard.size()];
                for (int i = 0; i < g.actionCardsOnBoard.size(); i++) {
                    cardsOnBoard[i] = g.actionCardsOnBoard.get(i);
                }
                actionCards.put("actionCardsOnBoard", cardsOnBoard);
                System.out.println(actionCards);
                writer.append(actionCards.toString());
                g.printActionCards();
                break;

            case "updateVictoryBoard":
                cardsOnBoard = new Card[g.victoryCardTable.getSize()];

                for (int i = 0; i < g.victoryCardTable.getSize(); i++) {

                    cardsOnBoard[i] = g.victoryCardTable.getCardOnPos(i);
                }
                victoryCards.put("victoryCardsOnBoard", cardsOnBoard);
                System.out.println(victoryCards);
                writer.append(victoryCards.toString());
                g.printVictoryCards();
                break;

            case "updateTreasureBoard":
                cardsOnBoard = new Card[g.treasureCardTable.getSize()];

                for (int i = 0; i < g.treasureCardTable.getSize(); i++) {

                    cardsOnBoard[i] = g.treasureCardTable.getCardOnPos(i);
                }
                treasureCards.put("treasureCardsOnBoard", cardsOnBoard);
                System.out.println(treasureCards);
                writer.append(treasureCards.toString());
                g.printTreasureCards();
                break;

            case "updateCoinsActionsBuys":

                int[] coinsActionsBuys = new int[3];
                coinsActionsBuys[0] = g.getAmountOfCoinsOfPlayer();
                coinsActionsBuys[1] = g.returnAmountOfActionsRemaining();
                coinsActionsBuys[2] = g.returnRemainingBuys();
                CAB.put("coinsActionsBuys", coinsActionsBuys);
                writer.append(CAB.toString());
                break;

            case "updatePlayer":
                player = new Player[1];
                player[0] = g.allPlayers.get(g.getPlayer());

                currentlyPlayingPlayer.put("activePlayer", player);
                writer.append(currentlyPlayingPlayer.toString());
                break;


            case "buyActionCard":
                if (g.currentPhase == 1 || g.actionToBuyCard) {

                    int positionOnBoard;
                    positionOnBoard = Integer.parseInt(request.getParameter("positionOnBoard"));
                    int pos = g.returnPositionOnBoardForCardWithNumber(positionOnBoard);
                    buyCard(pos, "action");
                    System.out.println("kaart " + pos + " gekocht!");
                    g.actionToBuyCard = false;
                } else {
                    System.out.println("Er kan geen kaart gekocht worden in de actie fase");
                }
                if (g.checkIfFinished()) {
                    runGameIsDone();
                }

                break;
            case "buyVictoryCard":

                if (g.currentPhase == 1 || g.actionToBuyCard) {
                    positionOnBoard = Integer.parseInt(request.getParameter("positionOnBoard"));
                    buyCard(positionOnBoard - 1, "victory");
                    System.out.println("kaart " + positionOnBoard + " gekocht!");
                    g.actionToBuyCard = false;
                } else {
                    System.out.println("Er kan geen kaart gekocht worden in de actie fase");
                }
                if (g.checkIfFinished()) {
                    runGameIsDone();
                }
                break;

            case "buyTreasureCard":
                if (g.currentPhase == 1 || g.actionToBuyCard) {
                    positionOnBoard = Integer.parseInt(request.getParameter("positionOnBoard"));
                    buyCard(positionOnBoard - 1, "treasure");
                    System.out.println("kaart " + positionOnBoard + " gekocht!");
                    g.actionToBuyCard = false;
                } else {
                    System.out.println("Er kan geen kaart gekocht worden in de actie fase");
                }
                if (g.checkIfFinished()) {
                    runGameIsDone();
                }

                break;

            case "endTurn":
                System.out.println("Einde beurt voor speler: " + g.getPlayerName(g.player));
                g.nextPlayer();
                g.endTurn();
                g.printHand(g.allPlayers.get(g.player));
                System.out.println("Nieuwe speler: " + g.getPlayerName(g.player));
                g.resetAmountOfActions();
                g.resetPhase();
                break;
            case "checkMilitia":
                JSONObject militiaObj = new JSONObject();
                militiaObj.put("militiaCheck", g.allPlayers.get(g.player).cursedByMilitia);
                writer.append(militiaObj.toString());
                if (g.allPlayers.get(g.player).cursedByMilitia) {
                    g.currentPhase = -1;
                }
                break;

            case "endPhase":

                g.endPhase();
                break;
            case "checkIfFinished":
                if (finished) {
                    gameOver.put("gameIsDone", 1);
                } else {
                    gameOver.put("gameIsDone", -1);
                }
                writer.append(gameOver.toString());
                break;


        }
    }

    public void initGame() {
        System.out.println("amount" + countAmountOfPlayers());
        System.out.println("s1");
        g = new Game();
        System.out.println("s2");
        g.createPlayersList(countAmountOfPlayers());
        System.out.println("s3");
        setNames();


    }


    private int countAmountOfPlayers() {
        if (name4 != null) {
            playerNames.add(name1);
            playerNames.add(name2);
            playerNames.add(name3);
            playerNames.add(name4);
            return 4;
        } else if (name3 != null) {
            playerNames.add(name1);
            playerNames.add(name2);
            playerNames.add(name3);
            return 3;
        } else {
            playerNames.add(name1);
            playerNames.add(name2);
            return 2;
        }
    }


    private void nextTurn() {
        g.nextTurn();
        g.currentPhase = 0;
        //todo showNextPlayer GASTEN GEEN IDEE JAVASCRIPT DINGEN

    }

    private void discardingCards(int positionInHand) {
        g.amountOfCardsToBeDiscarded -= 1;
        g.selectedCard = g.allPlayers.get(activePlayer).getCardOnPosInHand(positionInHand);
        g.moveCardFromHandToDiscardPilePosition(positionInHand, g.allPlayers.get(activePlayer));
        switch (g.currentAction) {
            case "remodel":
                g.changeCoinsToCostOfCardPlusTwo();
                break;
            case "cellar":
                g.allPlayers.get(activePlayer).addCardFromDeckToHand();
                break;

        }
    }

    private void trashingCards(int positionInHand) {
        g.amountOfCardsToBeTrashed -= 1;
        g.selectedCard = g.allPlayers.get(activePlayer).getCardOnPosInHand(positionInHand);
        g.allPlayers.get(activePlayer).removeCardFromHand(positionInHand);

        switch (g.currentAction) {
            case "moneylender":
                g.moneyLenderCopperFound(activePlayer);
                break;
            case "chapel":
                break;
        }

    }

    private void setNames() {
        for (int i = 0; i < playerNames.size(); i++) {
            g.allPlayers.get(i).setName(playerNames.get(i));
        }
    }

    private void thiefOrSpyPlayed() {

    }

    private void useActionCard(int positionInHand) {
        g.setDecisionOfPlayerPosition(positionInHand);
        g.playActionCard();
    }

    private void endTurn() {
        g.endTurn();

        //TODO ANIMATION VOOR NIEUW GEMAAKT HAND OPVRAAGBAAR VIA g.returnHand(g.allPlayers.get(player)

    }

    private void endPhase() {
        g.nextPhase();
        //TODO ANIMATION VOOR BUYPHASE

    }

    private void runGameIsDone() {
        finished = true;

    }

    private void buyCard(int positionOnTheBoard, String typeOfToBeBoughtCard) {
        if (g.returnRemainingBuys() >= 1) {
            g.setDecisionOfPlayerPosition(positionOnTheBoard);
            g.setDecisionOfPlayerType(typeOfToBeBoughtCard);
            g.buyCard();
            g.checkIfFinished();
        }
    }

}
