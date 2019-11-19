import memberDao.membroDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.ExecutionException;

@SuppressWarnings("serial")
@WebServlet(name = "HammerREAD", value="/ReadMembro")

public class HammerREAD extends HttpServlet {
String reqREAD;
memberDao.membroDao membroDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
        PrintWriter out = resp.getWriter();
        try {
            reqREAD =  membroDao.readMembroDB(req.getParameter("CPF"));
            System.out.println(reqREAD);
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            out.print(reqREAD);
            out.flush();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
