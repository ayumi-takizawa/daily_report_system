package controllers.reaction;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
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
 * Servlet implementation class GoodDeleteServlet
 */
@WebServlet("/reactions/good/reset")
public class GoodResetServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public GoodResetServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token = (String)request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())) {
            EntityManager em = DBUtil.createEntityManager();

            Report rp = em.find(Report.class, Integer.parseInt(request.getParameter("report_id")));

            Employee login_employee = (Employee)request.getSession().getAttribute("login_employee");
            List<Reaction> goodCheck=em.createNamedQuery("checkGood_flag",Reaction.class)
                    .setParameter("employee", login_employee)
                    .setParameter("report",rp)
                    .getResultList();

            Reaction ra=goodCheck.get(0);
            ra.setGood_flag(0);

            em.getTransaction().begin();
            em.getTransaction().commit();
            em.close();

            // report/indexのページにリダイレクト
            response.sendRedirect(request.getContextPath()+"/reports/index");
        }
    }
}
