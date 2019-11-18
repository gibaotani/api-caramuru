
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.SimpleTimeZone;
import java.util.concurrent.ExecutionException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import memberDao.membroDao;
import Object.membro;


@SuppressWarnings("serial")
@WebServlet(name = "HammerPOST", value="/AddMembro")

public class HammerPOST extends HttpServlet{
    membroDao membroDao;
    String json;


    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException   {
        PrintWriter out = resp.getWriter();
        membro membro = new membro();
        membro.setNome(req.getParameter("nome"));
        membro.setCPF(req.getParameter("CPF"));
        String reqDate =  req.getParameter("data_nascimento");
        try {
            Date date = new SimpleDateFormat("dd/MM/yyyy").parse(reqDate);
            membro.setDate(date);
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("Não foi possivel converter a Data");
        }
        membro.setLink_foto(req.getParameter("link_foto"));
        membro.setRegistro_UEB(req.getParameter("resgistro_ueb"));

        try {
            json = membroDao.addMembroDB(membro);
            out.println(json);
        } catch (ExecutionException e) {
            System.out.println("Não foi possivel adiconar o membro");
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("Não foi possivel adiconar o membro");
        }
    }

}
