
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

import models.Station;
import models.ZoneModel;

@WebServlet(urlPatterns = {"/listStation", "/addStation", "/editStation", "/insStation", "/updStation", "/delStation"})
public class StationServlet extends HttpServlet {

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
            out.println("<title>Servlet StationService</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet StationService at " + request.getContextPath() + "</h1>");
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
            case "/listStation":
                url = "/pages/listStation.jsp";
                try {
                    ArrayList<Station> list = Station.getStationList(ds);
                    request.setAttribute("stations", list);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                request.getRequestDispatcher(url).forward(request, response);
                break;
            case "/addStation":
                url = "/pages/addEditStation.jsp";
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
            case "/editStation":
                url = "/pages/addEditStation.jsp";
                try {
                    request.setAttribute("action", "edit");
                    Station station = Station.getStation(ds,
                            Integer.valueOf(request.getParameter("zoneid")),
                            request.getParameter("stationname"));
                    request.setAttribute("station", station);
                    request.setAttribute("zones", ZoneModel.getZoneList(ds));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                request.getRequestDispatcher(url).forward(request, response);
                break;
            case "/insStation":
                try {
                url = request.getContextPath() + "/listStation";
                Station station = new Station(
                        Integer.valueOf(request.getParameter("zoneid")),
                        request.getParameter("stationname"));
                Station.insStation(ds, station);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            response.sendRedirect(url);
            break;
            case "/updStation":
                try {
                url = request.getContextPath() + "/listStation";
                Station station = new Station(
                        Integer.valueOf(request.getParameter("zoneid")),
                        request.getParameter("stationname"));
                station.setId(Integer.valueOf(request.getParameter("id")));
                Station.updStation(ds, station);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            response.sendRedirect(url);
            break;
            case "/delStation":
                url = request.getContextPath() + "/listStation";
                try {
                    Station station = Station.getStation(ds,
                            Integer.valueOf(request.getParameter("zoneid")),
                            request.getParameter("stationname"));
                    Station.delStation(ds, station);
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
