package controller.servlets.support;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@WebServlet(name = "LoadServlet", urlPatterns = "/load/*")
public class LoadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String filename = "D://SiteImages" + request.getPathInfo();

        ServletContext sc = getServletContext();

        String mime = sc.getMimeType(filename);

        response.setContentType(mime);

        File file = new File(filename);
        response.setContentLength((int)file.length());

        FileInputStream fis = new FileInputStream(file);

        BufferedInputStream bis = new BufferedInputStream(fis);

        OutputStream os = response.getOutputStream();

        BufferedOutputStream bos = new BufferedOutputStream(os);

        int a;
        while ((a = bis.read()) != -1) {
            bos.write(a);
        }
        bos.flush();
        bos.close();
        bis.close();
        fis.close();
        os.close();
    }
}
