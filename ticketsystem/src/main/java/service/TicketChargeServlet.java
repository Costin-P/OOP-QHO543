/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import models.TicketCharge;

@WebServlet(urlPatterns = {"/listTicketCharge", "/editTicketCharge",
    "/updTicketCharge"})
public class TicketChargeServlet extends HttpServlet {

    @Resource(name = "jdbc/postgresUpwork1Conn")
    private DataSource ds;

    private DateTimeFormatter dft = DateTimeFormatter.ofPattern("HH:mm:ss");

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
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>TicketCharge Servlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet TicketCharge1Service at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

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
            case "/listTicketCharge":
                url = "/pages/ticketCharge.jsp";
                try {
                    ArrayList<TicketCharge> ticketCharges = TicketCharge.getTicketChargeList(ds);
                    request.setAttribute("ticketCharges", ticketCharges);
                } catch (Exception e) {
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
            case "/editTicketCharge":
                url = "/pages/editTicketCharge.jsp";
                try {
                    request.setAttribute("action", "edit");
                    TicketCharge tc=TicketCharge.getTicketCharge(ds,
                                    Integer.valueOf(request.getParameter("id")));
        
                    request.setAttribute("ticketCharge",
                            TicketCharge.getTicketCharge(ds,
                                    Integer.valueOf(request.getParameter("id"))));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                request.getRequestDispatcher(url).forward(request, response);
                break;
            case "/updTicketCharge":
                try {
                url = request.getContextPath() + "/listTicketCharge";
                TicketCharge ticketCharge1 = new TicketCharge(
                        Double.valueOf(request.getParameter("pickcharge")),
                        Double.valueOf(request.getParameter("offpickcharge")),
                        LocalTime.parse(request.getParameter("pickfromtime"), dft),
                        LocalTime.parse(request.getParameter("picktotime"), dft)
                );
                ticketCharge1.setId(Integer.valueOf(request.getParameter("id")));
                TicketCharge.updTicketCharge(ds, ticketCharge1);
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
