import memberDao.membroDao;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

@SuppressWarnings("serial")
@WebServlet(name = "HammerUPDATE", value="/UpdateMembro")

public class HammerUPDATE extends HttpServlet {
    String reqPut;
    memberDao.membroDao membroDao;
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);

        try {
            reqPut =  membroDao.updateMembroDB(req.getParameter("CPF"),
                    req.getParameter("campo"),req.getParameter("alt"));
            System.out.println(reqPut);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
