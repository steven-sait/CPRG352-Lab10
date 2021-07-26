package filters;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.User;
import services.AccountService;

public class AdminFilter implements Filter
{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter( ServletRequest request, ServletResponse response, FilterChain chain ) throws IOException, ServletException
    {
        // AuthenticationFilter is called prior to AdminFilter, so we can assume the user is authenticated.
        HttpServletRequest httpRequest = (HttpServletRequest)request;
        HttpSession session = httpRequest.getSession();       
        AccountService account_serv = new AccountService();
        
        User user = account_serv.FindUser( (String)session.getAttribute( "email" ) );    
        if( user == null )
            return;
        
        // User is not an admin
        if( user.getRole().getRoleId() != 1 )
        {
            ((HttpServletResponse)response).sendRedirect("notes");
            return;
        }
        
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {}
}
