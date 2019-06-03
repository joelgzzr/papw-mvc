/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import clases.AuthController;
import models.Database;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Set;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.User;
import com.oreilly.servlet.*;
import java.io.FileInputStream;

@WebServlet(name = "RegisterServlet", urlPatterns = {"/register"})
public class RegisterServlet extends HttpServlet {
    
    User registerUser = null;
    User loginUser = null;
    String username;
    String fullname;
    String email;
    String password;
    String phone = "";
    String address = "";
    File avatar = null;
    File cover = null;
    FileInputStream avatarStream;
    FileInputStream coverStream;
    
    RequestDispatcher rd = null;

    boolean success;

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
        /* TODO output your page here. You may use following sample code. */
        String path = getServletContext().getRealPath("/img/");
        MultipartRequest req = new MultipartRequest(request, path, 10485760);

        if(req.getParameter("button") != null){
            username = req.getParameter("username");
            fullname = req.getParameter("fullname");
            email = req.getParameter("email");
            password = req.getParameter("password");
            if(req.getParameter("phone") != null){
                phone = req.getParameter("phone");
            }
            if(req.getParameter("address") != null){
                address = req.getParameter("address");
            }
            avatar = req.getFile("avatar");
            cover = req.getFile("cover");
            avatarStream = new FileInputStream(avatar);
            coverStream = new FileInputStream(cover);

            registerUser = new User(username, fullname, email, password, phone, address);
            registerUser.setAvatar(avatarStream);
            registerUser.setCover(coverStream);
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
        processRequest(request, response);
        
        AuthController auth = new AuthController();
        
        if(registerUser != null){
            success = auth.register(registerUser);
            if(success){
                loginUser = auth.getUserFromDatabase(email);
                if(loginUser != null){
                    request.setAttribute("user", loginUser);
                }
            }
            rd = request.getRequestDispatcher("register.jsp");
        }
        
        rd.forward(request, response);
        
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
