import java.io.*;
import java.util.ArrayList;
import javax.servlet.*;
import javax.servlet.http.*;
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
                names.append("name1", name1);
                names.append("name2", name2);
                writer.append(names.toString());
                initGame();
                break;

        }
    }

    private void initGame(){

        int amountOfPlayers = countAmountOfPlayers();
        g.createPlayersList(amountOfPlayers);
        setNames();


    }
    private int countAmountOfPlayers(){
        return 2; //TODO input via website/gebruiker ipv 2
    };

    private void firstTurn(){

    }
    private void setNames(){
        for (int i=0;i<playerNames.size();i++){
            g.allPlayers.get(i).setName(playerNames.get(i));
        }
    }
}
