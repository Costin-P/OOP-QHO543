
package service;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import models.GateModel;
import models.Station;
import models.TicketMachine;
import models.ZoneModel;

@WebServlet(urlPatterns = {"/listTicketMachine", "/addTicketMachine", "/editTicketMachine",
    "/insTicketMachine", "/updTicketMachine", "/delTicketMachine"})
public class TicketMachineServlet extends HttpServlet {

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
            out.println("<title>Servlet TicketMachineService</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet TicketMachineService at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request service request
     * @param response service response
     * @throws ServletException if a service-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userPath = request.getServletPath();
        System.out.println(userPath);
        String url = "";
        switch (userPath) {
            case "/listTicketMachine":
                url = "/pages/listTicketMachine.jsp";
                try {
                    ArrayList<TicketMachine> ticketMachines = TicketMachine.getTktMachinesDetails(ds);
                    request.setAttribute("ticketMachines", ticketMachines);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                request.getRequestDispatcher(url).forward(request, response);
                break;
            case "/addTicketMachine":
                url = "/pages/addEditTicketMachine.jsp";
                request.setAttribute("action", "add");
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
        String userPath = request.getServletPath();
        System.out.println(userPath);
        String url = "";
        switch (userPath) {
            case "/editTicketMachine":
                url = "/pages/addEditTicketMachine.jsp";
                try {
                    request.setAttribute("action", "edit");
                    TicketMachine ticketMachine = TicketMachine.getTicketMachineDetails(ds,
                            request.getParameter("machineid"));
                    ZoneModel zone=ZoneModel.getZoneDetails(ds, ticketMachine.getzonenumber());
                    Station station=Station.getStation(ds, ticketMachine.getzonenumber(), ticketMachine.getStationname());
                    request.setAttribute("ticketMachine", ticketMachine);
                    request.setAttribute("zones", ZoneModel.getZoneList(ds));
                    request.setAttribute("stations", Station.getStationsByZone(ds, zone));
                    request.setAttribute("gates", GateModel.findAllGateByStnName(ds, station));
                    
                    
                    
                    
                    
                    
                    
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                request.getRequestDispatcher(url).forward(request, response);
                break;
            case "/insTicketMachine":
                try {
                url = request.getContextPath() + "/listTicketMachine";
                TicketMachine ticketMachine = new TicketMachine(
                        request.getParameter("machineid"),
                        Integer.valueOf(request.getParameter("zonenumber")),
                        request.getParameter("stationname"));
                TicketMachine.insertTktMachine(ds, ticketMachine);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            response.sendRedirect(url);
            break;
            case "/updTicketMachine":
                try {
                url = request.getContextPath() + "/listTicketMachine";
                TicketMachine ticketMachine = new TicketMachine(
                        request.getParameter("machineid"),
                        Integer.valueOf(request.getParameter("zonenumber")),
                        request.getParameter("stationname"),
                        request.getParameter("gateno"));
                ticketMachine.setId(Integer.valueOf(request.getParameter("id")));
                TicketMachine.updateTktMachine(ds, ticketMachine);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            response.sendRedirect(url);
            break;
            case "/delTicketMachine":
                url = request.getContextPath() + "/listTicketMachine";
                try {
                    TicketMachine.deleteTktMachine(ds, 
                            TicketMachine.getTicketMachineDetails(ds, request.getParameter("machineid")));
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
     * Returns a short description of the service.
     *
     * @return a String containing service description
     */
    @Override
    public String getServiceInfo() {
        return "Short description";
    }// </editor-fold>

}
