package controller;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by scheldejonas on 09/02/2017.
 */
@WebServlet(name = "FrontPageServlet", urlPatterns = "/index")
public class FrontPageServlet extends javax.servlet.http.HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        throw new NotImplementedException();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processGet(request,response);
    }

    private void processGet(HttpServletRequest request, HttpServletResponse response) {
        System.out.printf("Request Line | Path Info: %s \n",request.getPathInfo() );

        System.out.printf("Request Line | Servlet Path: %s \n",request.getServletPath() );
        System.out.printf("Request Line | Session Creation Time: %s \n",request.getSession().getCreationTime() );
        System.out.printf("Request Line | Session id: %s \n",request.getSession().getId() );
        System.out.printf("Request Line | Session Last Accessed Time: %s \n",request.getSession().getLastAccessedTime() );
        System.out.printf("Request Line | Session Max Inactive Interval: %s \n",request.getSession().getMaxInactiveInterval() );
        System.out.printf("Request Line | Session Servlet Context Path: %s \n",request.getSession().getServletContext().getContextPath() );
        System.out.printf("Request Line | Local Addresss: %s \n",request.getLocalAddr() );
        System.out.printf("Request Line | Is Requested Session id From Cookie: %s \n",request.isRequestedSessionIdFromCookie() );
        System.out.printf("Request Line | Is Requested Session id From Url: %s \n",request.isRequestedSessionIdFromURL() );
        System.out.printf("Request Line | Is Requested Session id Valid: %s \n",request.isRequestedSessionIdValid() );
        System.out.printf("Request Line | Path Info: %s \n",request.getPathInfo() );

        System.out.printf("Request Line | Protocol: %s \n",request.getProtocol() );
        System.out.printf("Request Line | Request URI: %s \n",request.getRequestURI() );
        System.out.printf("Request Line | Context Path: %s \n",request.getContextPath() );
        System.out.printf("Request Line | Auth Type: %s \n",request.getAuthType() );
        System.out.printf("Request Line | Local Port: %s \n",request.getLocalPort() );
        System.out.printf("Request Line | Method: %s \n",request.getMethod() );
        System.out.printf("Header | Host: %s \n",request.getHeader("host") );
        System.out.printf("Header | Connection: %s \n",request.getHeader("connection") );
        System.out.printf("Header | cache-control: %s \n",request.getHeader("cache-control") );
        System.out.printf("Header | Upgrade-Insecure-Requests: %s \n",request.getHeader("Upgrade-Insecure-Requests") );
        System.out.printf("Header | User-Agent: %s \n",request.getHeader("user-agent") );
        System.out.printf("Header | Accept: %s \n",request.getHeader("accept") );
        System.out.printf("Header | Accept-Encoding: %s \n",request.getHeader("accept-encoding") );
        System.out.printf("Header | Accept-Language: %s \n",request.getHeader("accept-language") );
        System.out.printf("Header | Cookie: %s \n",request.getHeader("cookie") );

        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/index_two.jsp");
        try {
            requestDispatcher.forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
