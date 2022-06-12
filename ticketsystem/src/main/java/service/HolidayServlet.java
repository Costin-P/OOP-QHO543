
package service;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import models.GateModel;
import models.HolidayModel;
import models.ZoneModel;

@WebServlet(urlPatterns = {"/listHoliday", "/addHoliday", "/editHoliday",
    "/insHoliday", "/updHoliday", "/delHoliday"})
public class HolidayServlet extends HttpServlet {

    @Resource(name = "jdbc/postgresUpwork1Conn")
    private DataSource ds;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet HolidayServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet HolidayServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userPath = request.getServletPath();
        System.out.println(userPath);
        String url = "";
        switch (userPath) {
            case "/listHoliday":
                url = "/pages/listHoliday.jsp";
                try {
                    ArrayList<HolidayModel> holidays = HolidayModel.getAllHolidays(ds);
                    request.setAttribute("holidays", holidays);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                request.getRequestDispatcher(url).forward(request, response);
                break;
            case "/addHoliday":
                url = "/pages/addEditHoliday.jsp";
                request.setAttribute("action", "add");
                request.getRequestDispatcher(url).forward(request, response);
                break;
            default:
                break;
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String userPath = request.getServletPath();
        System.out.println(userPath);
        String url = "";
        switch (userPath) {
            case "/editHoliday":
                url = "/pages/addEditHoliday.jsp";
                try {
                    request.setAttribute("action", "edit");
                    HolidayModel holiday = HolidayModel.getHoliday(ds,
                            LocalDate.parse(request.getParameter("d"), dateFormat));
                    request.setAttribute("holiday", holiday);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                request.getRequestDispatcher(url).forward(request, response);
                break;
            case "/insHoliday":
                try {
                url = request.getContextPath() + "/listHoliday";
                HolidayModel holiday = new HolidayModel(
                        LocalDate.parse(request.getParameter("d"), dateFormat),
                        request.getParameter("remark"));
                HolidayModel.insertHoliday(ds, holiday);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            response.sendRedirect(url);
            break;
            case "/updHoliday":
                try {
                url = request.getContextPath() + "/listHoliday";
                HolidayModel holiday = new HolidayModel(
                        LocalDate.parse(request.getParameter("d"), dateFormat),
                        request.getParameter("remark"));
                HolidayModel.updateHoliday(ds, holiday);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            response.sendRedirect(url);
            break;
            case "/delHoliday":
                url = request.getContextPath() + "/listHoliday";
                try {
                    HolidayModel holiday = new HolidayModel(
                        LocalDate.parse(request.getParameter("d"), dateFormat),
                        request.getParameter("remark"));
                    HolidayModel.deleteHoliday(ds, holiday);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                response.sendRedirect(url);
                break;
            default:
                break;
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
