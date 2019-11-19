import memberDao.membroDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.ExecutionException;


@SuppressWarnings("serial")
@WebServlet(name = "HammerDELETE", value="/DeleteMembro")

public class HammerDELETE extends HttpServlet {
    String reqDelete, respDao;
    memberDao.membroDao membroDao;

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);

        reqDelete = req.getParameter("CPF");

        try {
            respDao  = membroDao.deleteMembroDB(reqDelete);
            System.out.println(respDao);

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
