package controllers.reaction;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Reaction;
import models.Report;
import utils.DBUtil;

/**
 * Servlet implementation class GoodShow
 */
@WebServlet("/reactions/good/show")
public class GoodShow extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public GoodShow() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        Report rp = em.find(Report.class, Integer.parseInt(request.getParameter("id")));
        Reaction ra=em.find(Reaction.class, Integer.parseInt(request.getParameter("id")));

        //Report report_id = (Employee)request.getSession().getAttribute("login_employee");

        List<Reaction> getAllGood=em.createNamedQuery("getMyAllGood",Reaction.class)
                .setParameter("report", rp)
                .getResultList();

        em.close();

        request.setAttribute("report", rp);
        request.setAttribute("reaction",ra);
        request.setAttribute("getAllGood", getAllGood);
        request.setAttribute("_token", request.getSession().getId());

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/reactions/show.jsp");
        rd.forward(request,response);
    }

}
