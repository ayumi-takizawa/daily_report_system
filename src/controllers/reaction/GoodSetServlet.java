package controllers.reaction;

import java.io.IOException;

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
 * Servlet implementation class GoodCountServlet
 */
@WebServlet("/reactions/good/set")
public class GoodSetServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public GoodSetServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token = (String)request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())) {
            EntityManager em = DBUtil.createEntityManager();

            Reaction ra=new Reaction();//新規登録
            ra.setEmployee((Employee)request.getSession().getAttribute("login_employee"));
            Report rp = em.find(Report.class, Integer.parseInt(request.getParameter("report_id")));
            ra.setReport(rp);
            ra.setGood_flag(1);

         //リクエストスコープにセットして送る
         em.getTransaction().begin();
         em.persist(ra);//新規登録
         em.getTransaction().commit();
         em.close();
         request.setAttribute("report_id", rp);//追記
         request.setAttribute("_token", request.getSession().getId());//

      // report/indexのページにリダイレクト
         response.sendRedirect(request.getContextPath()+"/reports/index");
    }
    }
}

