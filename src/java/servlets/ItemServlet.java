/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import clases.ItemController;
import com.oreilly.servlet.MultipartRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Item;
import models.User;

@WebServlet(name = "ItemServlet", urlPatterns = {"/items"})
public class ItemServlet extends HttpServlet {
    
    String name;
    String description;
    int category;
    String price;
    String stock;
    File img1;
    File img2;
    File img3;
    FileInputStream imgStream1;
    FileInputStream imgStream2;
    FileInputStream imgStream3;
    File video;
    FileInputStream videoStream;
    int draft;
    
    Item newItem;
    User itemUser;
    
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
        
        HttpSession webSession = request.getSession(true);
        itemUser = (User)webSession.getAttribute("user");
        
        String path = getServletContext().getRealPath("/img/");
        MultipartRequest req = new MultipartRequest(request, path, 10485760);

        if(req.getParameter("button") != null){
            name = req.getParameter("name");
            description = req.getParameter("description");
            category = Integer.parseInt(req.getParameter("category"));
            price = req.getParameter("price");
            stock = req.getParameter("stock");
            img1 = req.getFile("img1");
            img2 = req.getFile("img2");
            img3 = req.getFile("img3");
            video = req.getFile("video");
            if(req.getParameterValues("draft") != null){
                draft = Integer.parseInt(req.getParameterValues("draft")[0]);
            } else {
                draft = 0;
            }
            imgStream1 = new FileInputStream(img1);
            imgStream2 = new FileInputStream(img2);
            imgStream3 = new FileInputStream(img3);
            videoStream = new FileInputStream(video);
            
            newItem = new Item(itemUser.getId(), name, description, category, price, stock, draft);
            newItem.setImg1(imgStream1);
            newItem.setImg2(imgStream2);
            newItem.setImg3(imgStream3);
            newItem.setVideo(videoStream);
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
        
        ItemController ic = new ItemController();
        
        if(newItem != null){
            success = ic.addItemToDatabase(newItem);
            if(success){
                response.sendRedirect("agregar.jsp");
            }
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
