package source;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

import java.io.IOException;

@WebServlet(name = "registration", value = "/registration")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService=UserService.getUserService();
	
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().append("served at: ").append(request.getContextPath());
        System.out.println("doGet");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("doPost");

        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        userService.saveUser(new User(firstname,lastname,email,password));
        HttpSession session=request.getSession(true);
        
        session.setAttribute("userEmail", email);
        session.setAttribute("userFirstname", firstname);
        
        request.getRequestDispatcher("cabinet.jsp").forward(request,response);
    }
}