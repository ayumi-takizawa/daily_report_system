package controllers.reports;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Employee;
import models.Reaction;
import models.Report;
import utils.DBUtil;

/**
 * Servlet implementation class ReportsShowServlet
 */
@WebServlet("/reports/show")
public class ReportsShowServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportsShowServlet() {
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
        Employee login_employee = (Employee)request.getSession().getAttribute("login_employee");

        long good_count = (long)em.createNamedQuery("getGoodCount", Long.class)
                .setParameter("report", rp)
                .getSingleResult();

        List<Reaction> goodCheck=em.createNamedQuery("checkGood_flag",Reaction.class)
                .setParameter("employee", login_employee)
                .setParameter("report",rp)
                .getResultList();


        em.close();


        request.setAttribute("goodCheck",goodCheck);
        request.setAttribute("reaction", ra);
        request.setAttribute("report", rp);
        request.setAttribute("good_count",good_count);
        request.setAttribute("_token", request.getSession().getId());

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/reports/show.jsp");
        rd.forward(request,response);
    }

}
