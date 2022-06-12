
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
import models.ZoneModel;


@WebServlet(urlPatterns = {"/listGate", "/addGate", "/editGate",
    "/insGate", "/updGate", "/delGate"})
public class GateService extends HttpServlet {

    @Resource(name = "jdbc/postgresUpwork1Conn")
    private DataSource ds;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request service request
     * @param response service response
     * @throws ServletException if a service+-specific error occurs
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
            out.println("<title>Servlet StationGateService</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet StationGateService at " + request.getContextPath() + "</h1>");
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
            case "/listGate":
                url = "/pages/listGate.jsp";
                try {
                    ArrayList<GateModel> gates = GateModel.getGateDetails(ds);
                    request.setAttribute("gates", gates);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                request.getRequestDispatcher(url).forward(request, response);
                break;
            case "/addGate":
                url = "/pages/addEditGate.jsp";
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
            case "/editGate":
                url = "/pages/addEditGate.jsp";
                try {
                    request.setAttribute("action", "edit");
                    GateModel gate = GateModel.gateDetails(ds,
                            Integer.valueOf(request.getParameter("zonenumber")),
                            request.getParameter("stationname")                          
                            request.setAttribute("gate", gate);
                }  catch (SQLException e) {
                    e.printStackTrace(); {
                }
                request.getRequestDispatcher(url).forward(request, response);
                break;            
               GateModel.insertGateData(ds, gate);
            } 
            response.sendRedirect(url);
            break;
            case "/updGate":
                try {
                url = request.getContextPath() + "/listGate";
                GateModel gate = new GateModel(
                        Integer.valueOf(request.getParameter("zonenumber")),
                        request.getParameter("stationname"),
                gate.setId(Integer.valueOf(request.getParameter("number"));
                GateModel.updateGateData(ds, gate);
            } catch (SQLException e) {
                e.printStackTrace();
            
            response.sendRedirect(url);
            break;
            case "/delGate":
                url = request.getContextPath() + "/listGate";
                else {
                    GateModel gate = GateModel.gateDetails(ds,
                        Integer.valueOf(request.getParameter("zonenumber")),
                        request.getParameter("stationname"),
                      





