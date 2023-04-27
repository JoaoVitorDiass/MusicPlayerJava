/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.lang.reflect.Array;
import java.util.ArrayList;

/*
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
*/

import com.google.gson.Gson; 
import com.google.gson.GsonBuilder;  
import javax.servlet.annotation.MultipartConfig;

@WebServlet(name = "ServletPesquisaMusica", urlPatterns = {"/ServletPesquisaMusica"})
@MultipartConfig(
    location="/", 
    fileSizeThreshold=1024*1024,    // 1MB *      
    maxFileSize=1024*1024*100,      // 100MB **
    maxRequestSize=1024*1024*10*10  // 100MB ***
)
public class ServletPesquisaMusica extends HttpServlet {
  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String chave = request.getParameter("pesquisa");
        ArrayList arrayMusicas;
        arrayMusicas = new ArrayList<>();
        Gson gson = new GsonBuilder().create();
        
        try (PrintWriter out = response.getWriter()) {
            File pastaweb = new File(getServletContext().getRealPath("/")+"/musicas_recebidas");
            
            Musica msc;
            String[] exploded;
            
            for (File file : pastaweb.listFiles()) {
                if(file.isFile()){
                   //json.put(file.getAbsolutePath(),file.getName().toString());
                    if(file.getName().toUpperCase().contains(chave.toUpperCase())) {
                    exploded = file.getName().split("_");
                    msc = new Musica(file.getName(),exploded[2],exploded[1],exploded[0]);
                    arrayMusicas.add(msc);
                    }
                }
            }
            out.println(gson.toJson(arrayMusicas));
            //os.write(input, 0, input.length);
        }
        catch(Exception e){
            response.getWriter().println("Erro: "+e.getMessage());
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
