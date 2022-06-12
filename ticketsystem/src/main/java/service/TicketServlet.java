
package service;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import models.Ticket;
import models.TicketCharge;
import models.ZoneModel;

@WebServlet(initParams = {@WebInitParam(name = "logFilePath", value = "/home/costa/NetBeansProjects/logupwork.log")},
        urlPatterns = {"/bookTicket", "/getTicketDetails", "/buyTicket"})
public class TicketServlet extends HttpServlet {

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
            out.println("<title>Servlet TicketServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet TicketServlet at " + request.getContextPath() + "</h1>");
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
            case "/bookTicket":
                url = "/pages/bookTicket.jsp";
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
     * @param request service request
     * @param response service response
     * @throws ServletException if a service-specific error occurs
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
            case "/getTicketDetails":
                url = "/pages/ticketDetails.jsp";
                Integer fzoneid = Integer.valueOf(request.getParameter("fzoneid")),
                 tzoneid = Integer.valueOf(request.getParameter("tzoneid"));
                request.setAttribute("fzoneid", fzoneid);
                request.setAttribute("tzoneid", tzoneid);
                request.setAttribute("fstationname", request.getParameter("fstationname"));
                request.setAttribute("fgateno", request.getParameter("fgateno"));
                request.setAttribute("machineid", request.getParameter("machineid"));
                request.setAttribute("tstationname", request.getParameter("tstationname"));
                LocalDate d = LocalDate.parse(request.getParameter("d"), dateFormat);
                LocalTime t = LocalTime.parse(request.getParameter("t"), timeFormat);
                request.setAttribute("d", d);
                request.setAttribute("t", t);
                Integer zonecount = Math.abs(fzoneid - tzoneid) + 1;
                request.setAttribute("zonecount", zonecount);
                try {
                    String tickettype = TicketCharge.getTicketType(ds, d, t);
                    Double price = TicketCharge.getTicketPrice(ds, tickettype, zonecount);
                    LocalDateTime validdt = LocalDateTime.of(d, t).plusMinutes(zonecount * 30).plusMinutes(15);
                    request.setAttribute("tickettype", tickettype);
                    request.setAttribute("price", price);
                    request.setAttribute("validdt", validdt);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                request.getRequestDispatcher(url).forward(request, response);
                break;
            case "/buyTicket":
                url = "/pages/finalTicket.jsp";
                Ticket ticket = new Ticket(
                        Integer.valueOf(request.getParameter("fzoneid")),
                        request.getParameter("fstationname"),
                        request.getParameter("machineid"),
                        Integer.valueOf(request.getParameter("tzoneid")),
                        request.getParameter("tstationname"),
                        LocalDateTime.of(LocalDate.parse(request.getParameter("d")), LocalTime.parse(request.getParameter("t"))),
                        request.getParameter("tickettype"),
                        Integer.parseInt(request.getParameter("zonecount")),
                        LocalDateTime.parse(request.getParameter("validdt")),
                        Double.valueOf(request.getParameter("price"))
                );
                try {
                    int id = Ticket.saveTicket(ds, ticket);
                    request.setAttribute("ticket", Ticket.getTicket(ds, id));
                    //request.setAttribute("id", id);
                    Logger logger = Logger.getLogger("TicketLog");
                    FileHandler fh = new FileHandler(getInitParameter("logFilePath"));
                    logger.addHandler(fh);
                    SimpleFormatter formatter = new SimpleFormatter();
                    fh.setFormatter(formatter);
                    logger.info(Ticket.getTicket(ds, id).toString());
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
     * Returns a short description of the service.
     *
     * @return a String containing service description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
