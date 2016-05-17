import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WebServlet extends HttpServlet {

    private int count;
    private int ammountOfPlayers = 2;
    private String player1 = null;
    private String player2 = null;
    private String player3 = null;
    private String player4 = null;


    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        getServletContext().log("init() called");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        player1 = request.getParameter("player1");
        player2 = request.getParameter("player2");
        player3 = request.getParameter("player3");
        player4 = request.getParameter("player4");
        //ammountOfPlayers = request.getParameter(ammountOfPlayers);

        response.getWriter().write("<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <link rel=\"stylesheet\" type=\"text/css\" href=\"assets/css/reset.css\" />\n" +
                "    <link rel=\"stylesheet\" type=\"text/css\" href=\"assets/css/Layout.css\" />\n" +
                "    <link rel=\"stylesheet\" type=\"text/css\" href=\"assets/css/Baraja/baraja.css\" />\n" +
                "    <link rel=\"stylesheet\" type=\"text/css\" href=\"assets/css/Baraja/custom.css\" />\n" +
                "    <link rel=\"stylesheet\" type=\"text/css\" href=\"assets/css/Baraja/demo.css\" />\n" +
                "    <link rel=\"stylesheet\" type=\"text/css\" href=\"assets/css/DominionBoard.css\" />\n" +
                "    <meta name=\"author\" content=\"Dylan Van Kerrebroeck\"/>\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
                "    <title>Dominion</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<div id=\"wrapper\">\n" +
                "    <div id=\"bigCard\"></div>\n" +
                "    <div id=\"header\">\n");

                if (player4 != null){
                    response.getWriter().write("<div id=\"four_players\">\n" +
                            "            <div class=\"player_one_name\">\n" +
                            "                <p>" + player1 + "</p>\n" +
                            "            </div>\n" +
                            "            <div class=\"player_two_name\">\n" +
                            "                <p>" + player2 + "</p>\n" +
                            "            </div>\n" +
                            "            <div class=\"player_third_name\">\n" +
                            "                <p>" + player3 + "</p>\n" +
                            "            </div>\n" +
                            "            <div class=\"player_fourth_name\">\n" +
                            "                <p>" + player4 + "</p>\n" +
                            "            </div>\n" +
                            "        </div>\n");
                } else if (player3 != null){
                response.getWriter().write("<div id='three_players'><div class='player_one_name'><p>" + player1 + "</p></div><div class='player_two_name'><p>" + player2 + "</p></div><div class='player_third_name'><p>" + player3 + "</p></div></div>");
                } else if (player2 != null){
                    response.getWriter().write("<div id='two_players'><p class='player_one_name'>" + player1 + "</p><p class='player_two_name'>" + player2 + "</p></div></div>");
                }

                response.getWriter().write("</div>\n" +
                "\n" +
                "    <div id=\"content\">\n" +
                "        <div id=\"actioncards_on_table\">\n" +
                "            <ul class=\"actioncards_on_table_print\">\n" +
                "            </ul>\n" +
                "        </div>\n" +
                "        <div id=\"cards_on_board\">\n" +
                "            <div id=\"victory_cards\">\n" +
                "                <ul>\n" +
                "                    <li>\n" +
                "                        <p class=\"counteronsmallcards\">0</p>\n" +
                "                        <img src=\"assets/images/Small%20Cards/province.jpg\" title=\"Province + 6 points\" alt=\"Province\"/>\n" +
                "                    </li>\n" +
                "                    <li>\n" +
                "                        <p class=\"counteronsmallcards\">0</p>\n" +
                "                        <img src=\"assets/images/Small%20Cards/duchy.jpg\" title=\"Duchy + 3 points\" alt=\"Duchy\"/>\n" +
                "                    </li>\n" +
                "                    <li>\n" +
                "                        <p class=\"counteronsmallcards\">0</p>\n" +
                "                        <img src=\"assets/images/Small%20Cards/estate.jpg\" title=\"Estate + 1 point\" alt=\"Estate\"/>\n" +
                "                    </li>\n" +
                "                    <li>\n" +
                "                        <p class=\"counteronsmallcards\">0</p>\n" +
                "                        <img src=\"assets/images/Small%20Cards/curse.jpg\" title=\"Curse - 1 point\" alt=\"Curse\"/>\n" +
                "                    </li>\n" +
                "                </ul>\n" +
                "            </div>\n" +
                "            <div id=\"money_cards\">\n" +
                "                <ul>\n" +
                "                    <li>\n" +
                "                        <p class=\"counteronsmallcards\">0</p>\n" +
                "                        <img src=\"assets/images/Small%20Cards/gold.jpg\" alt=\"Gold\" title=\"Gold + 3 coins\"/>\n" +
                "                    </li>\n" +
                "                    <li>\n" +
                "                        <p class=\"counteronsmallcards\">0</p>\n" +
                "                        <img src=\"assets/images/Small%20Cards/silver.jpg\" alt=\"Silver\" title=\"Silver + 2 coins\"/>\n" +
                "                    </li>\n" +
                "                    <li>\n" +
                "                        <p class=\"counteronsmallcards\">0</p>\n" +
                "                        <img src=\"assets/images/Small%20Cards/copper.jpg\" alt=\"Copper\" title=\"Copper + 1 coin\"/>\n" +
                "                    </li>\n" +
                "                </ul>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "        <div id=\"playedcards_on_table\">\n" +
                "            <ul>\n" +
                "\n" +
                "            </ul>\n" +
                "        </div>\n" +
                "\n" +
                "        <div id=\"handcards_on_table\">\n" +
                "            <div id=\"deck_on_table\">\n" +
                "\n" +
                "            </div>\n" +
                "            <div id=\"discard_pile_on_table\">\n" +
                "                <div id=\"playedcards_on_discard_pile\">\n" +
                "                    <img src=\"assets/images/Big%20cards/bureaucrat.jpg\" alt=\"\" title=\"\"/>\n" +
                "                </div>\n" +
                "                <div id=\"toplaycards_on_discard_pile\">\n" +
                "                    <p id=\"backcardcounter\">0</p>\n" +
                "                    <img src=\"assets/images/Big%20cards/back.jpg\" alt=\"\" title=\"\"/>\n" +
                "                </div>\n" +
                "\n" +
                "            </div>\n" +
                "            <div id=\"hand_on_table\">\n" +
                "                <div class=\"baraja-demo\">\n" +
                "                    <ul id=\"baraja-el\" class=\"baraja-container\">\n" +
                "                        <li><img src=\"assets/images/Big%20cards/adventurer.jpg\"/> </li>\n" +
                "                        <li><img src=\"assets/images/Big%20cards/bureaucrat.jpg\"/> </li>\n" +
                "                        <li><img src=\"assets/images/Big%20cards/gold.jpg\"/> </li>\n" +
                "                        <li><img src=\"assets/images/Big%20cards/silver.jpg\"/> </li>\n" +
                "                        <li><img src=\"assets/images/Big%20cards/copper.jpg\"/> </li>\n" +
                "                        <li><img src=\"assets/images/Big%20cards/copper.jpg\"/> </li>\n" +
                "                        <li><img src=\"assets/images/Big%20cards/copper.jpg\"/> </li>\n" +
                "                        <li><img src=\"assets/images/Big%20cards/copper.jpg\"/> </li>\n" +
                "                        <li><img src=\"assets/images/Big%20cards/copper.jpg\"/> </li>\n" +
                "                        <li><img src=\"assets/images/Big%20cards/copper.jpg\"/> </li>\n" +
                "                        <li><img src=\"assets/images/Big%20cards/copper.jpg\"/> </li>\n" +
                "                        <li><img src=\"assets/images/Big%20cards/copper.jpg\"/> </li>\n" +
                "                        <li><img src=\"assets/images/Big%20cards/copper.jpg\"/> </li>\n" +
                "                        <li><img src=\"assets/images/Big%20cards/copper.jpg\"/> </li>\n" +
                "                    </ul>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "            <div id=\"trash_pile_on_table\">\n" +
                "                <!--<img src=\"assets/images/exit_button.jpg\" title=\"exit the game\" alt=\"exit\"/>-->\n" +
                "                <img src=\"assets/images/trash_pile_filled.png\" title=\"Trash Pile\" alt=\"Trash Pile\"/>\n" +
                "\n" +
                "            </div>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "    <div id=\"footer\">\n" +
                "        <div id=\"footer_container\">\n" +
                "            <div id=\"left_side_footer\">\n" +
                "                <div class=\"Actions\">\n" +
                "                    <p>Actions</p>\n" +
                "                    <p id=\"Amount_Of_Actions\" style=\"padding:2px 14px 2px 14px\">0</p>\n" +
                "                </div>\n" +
                "                <div class=\"Buys\">\n" +
                "                    <p>Buys</p>\n" +
                "                    <p id=\"Amount_Of_Buys\" style=\"padding:2px 14px 2px 14px\">0</p>\n" +
                "                </div>\n" +
                "                <div class=\"Coins\">\n" +
                "                    <p>Coins</p>\n" +
                "                    <p id=\"Amount_Of_Coins\" style=\"padding:2px 14px 2px 14px\">0</p>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "            <div id=\"center_side_footer\">\n" +
                "                <button id=\"opencards\">Open hand</button>\n" +
                "                <button id=\"closecards\">Close hand</button>\n" +
                "            </div>\n" +
                "            <div id=\"right_side_footer\">\n" +
                "                <p>Currently playing: </p>\n" +
                "                <p id=\"Current_Playing\">Player Dominion</p>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</div>\n" +
                "<script src=\"assets/js/jquery.min.js\"></script>\n" +
                "<script src=\"assets/js/script.js\"></script>\n" +
                "<script src=\"assets/css/Baraja/js/modernizr.custom.79639.js\"></script>\n" +
                "<script src=\"assets/css/Baraja/js/jquery.baraja.js\"></script>\n" +
                "</body>\n" +
                "</html>");
    }

    @Override
    public void destroy() {
        getServletContext().log("destroy() called");
    }

}