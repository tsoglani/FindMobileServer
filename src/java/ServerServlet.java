/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Nikos
 */

public class ServerServlet extends HttpServlet {

    public ServerServlet() {

    }

    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        doPost(req, res);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse response)
            throws ServletException, IOException {
        FindMobile.sendToAllIpInNetwork();
        response.setContentType("text/html");
        // New location to be redirected
        String site = new String(req.getRequestURI().substring(0,req.getRequestURI().length()-14));
        System.out.println(req.getRequestURI());
        response.setStatus(response.SC_MOVED_TEMPORARILY);
        response.setHeader("Location", site);

    }
}
