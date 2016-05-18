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
    String operation;
    Writer writer;
    JSONObject names;

    public void doGet (HttpServletRequest request,HttpServletResponse response)
            throws ServletException, IOException
    {
        response.setContentType("application/json");
        writer = response.getWriter();
        operation = request.getParameter("operation");

        switch (operation){
            case "initialize":
                initGame(request);
                break;
            case "getNames":
                writer.append(names.toString());
                break;
        }
    }

    public void initGame(HttpServletRequest request) throws IOException {
        name1 = request.getParameter("name1");
        name2 = request.getParameter("name2");
        names = new JSONObject();
        names.append("name1", name1);
        names.append("name2", name2);
        writer.append(names.toString());
    }
}
