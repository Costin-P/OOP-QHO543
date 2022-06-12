
package service;

import java.io.IOException;
import java.io.PrintWriter;
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

@WebServlet(urlPatterns = {"/getStationByZone", "/getGateByStation", "/getTicketMachineByGate"})
public class TcketMachineServlet extends HttpServlet {

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
            out.println("<title>Servlet AjaxService</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AjaxServlet at " + request.getContextPath() + "</h1>");
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
        processRequest(request, response);
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
        String userPath = request.getServletPath();
        System.out.println(userPath);
        String url = "";
        switch (userPath) {
            case "/getStationByZone":
                response.setContentType("text/html;charset=UTF-8");
                try (PrintWriter out = response.getWriter()) {
                    ZoneModel zone = ZoneModel.getZoneDetails(ds,
                            Integer.valueOf(request.getParameter("zoneid")));
                    ArrayList<Station> list = Station.getStationsByZone(ds, zone);
                    StringBuilder sb = new StringBuilder();
                    sb.append("<option>Select Station</option>");
                    for (Station e : list) {
                        sb.append("<option value='").append(e.getStationname()).append("'>")
                                .append(e.getStationname())
                                .append("</option>");
                    }
                    out.print(sb.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "/getGateByStation":
                response.setContentType("text/html;charset=UTF-8");
                try (PrintWriter out = response.getWriter()) {
                    Station station=Station.getStation(ds, 
                            Integer.valueOf(request.getParameter("zoneid")), 
                            request.getParameter("stationname"));
                    System.out.println(station.toString());
                    ArrayList<GateModel> gates = GateModel.findAllGateByStnName(ds, station);
                    StringBuilder sb = new StringBuilder();
                    sb.append("<option>Select Gate</option>");
                    for (GateModel gate : gates) {
                        sb.append("<option value='").append(gate.getGateno()).append("'>")
                                .append(gate.getGateno())
                                .append("</option>");
                    }
                    out.print(sb.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "/getTicketMachineByGate":
                response.setContentType("text/html;charset=UTF-8");
                try (PrintWriter out = response.getWriter()) {
                    GateModel gate=GateModel.gateDetails(ds, 
                            Integer.valueOf(request.getParameter("zoneid")), 
                            request.getParameter("stationname"), 
                            request.getParameter("gateno"));
                    ArrayList<TicketMachine> list = TicketMachine.findAllTktMachineByGate(ds, gate);
                    StringBuilder sb = new StringBuilder();
                    sb.append("<option>Select TicketMachine</option>");
                    for (TicketMachine e : list) {
                        sb.append("<option value='").append(e.getMachineid()).append("'>")
                                .append(e.getMachineid())
                                .append("</option>");
                    }
                    out.print(sb.toString());
                } catch (Exception e) {
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
