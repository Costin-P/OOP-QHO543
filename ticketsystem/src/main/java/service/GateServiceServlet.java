
package service;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import models.Ticket;
import models.ZoneModel;


@WebServlet(urlPatterns = {"/gateService", "/checkTicketValidity"})
public class GateServiceServlet extends HttpServlet {
    
    @Resource(name = "jdbc/postgresUpwork1Conn")
    private DataSource ds;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request service request
     * @param response service response
     * @throws ServletException if a service-specific error occurs
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
            out.println("<title>Servlet GateServiceServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet GateServiceServlet at " + request.getContextPath() + "</h1>");
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
            case "/gateService":
                url = "pages/gateService.jsp";
                try {
                    request.setAttribute("zones", ZoneModel.getZoneList(ds));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
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
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd"),
                timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");
        String userPath = request.getServletPath();
        System.out.println(userPath);
        String url = "";
        switch (userPath) {
            case "/checkTicketValidity":
                url = request.getContextPath() + "/gateService?message=";
                try {
                    Integer zoneid = Integer.parseInt(request.getParameter("zoneid"));
                    String stationname = request.getParameter("stationname");
                    String gateno = request.getParameter("gateno");
                    Ticket ticket = Ticket.getTicket(ds,
                            Integer.parseInt(request.getParameter("id")));
                    String movement = request.getParameter("movement");
                    LocalDateTime mdt = LocalDateTime.of(LocalDate.parse(request.getParameter("md"), dateFormat),
                            LocalTime.parse(request.getParameter("mt"), timeFormat));
                    Boolean validTicket = false;
                    if (movement.equals("entry")) {
                        if (ticket.getFstationname().equals(stationname)
                                && ticket.getDt().until(mdt, ChronoUnit.MINUTES) <= 15) {
                            validTicket = true;
                        }
                    } else if (movement.equals("exit")) {
                        Integer fzoneid = ticket.getFzoneid(),
                                tzoneid = ticket.getTzoneid();
                        if (((zoneid >= fzoneid && zoneid <= tzoneid) || (zoneid >= tzoneid && zoneid <= fzoneid))
                                && mdt.isAfter(ticket.getDt()) && mdt.isBefore(ticket.getValiddt())) {
                            validTicket = true;
                        }
                    }
                    response.sendRedirect(url += validTicket);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
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
