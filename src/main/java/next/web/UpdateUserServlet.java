package next.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import core.db.DataBase;
import next.model.User;

@WebServlet("/user/updateUser")
public class UpdateUserServlet extends HttpServlet {

	private static final long serialVersionUID = 822508446136665165L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String userId = req.getParameter("userId");
		User user = DataBase.findUserById(userId);
		
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		
		if (StringUtils.isNotBlank(name)) {
			user.setName(name);
		}
		if (StringUtils.isNotBlank(email)) {
			user.setEmail(email);
		}
		if (StringUtils.isNotBlank(password)) {
			user.setPassword(password);
		}
		
		DataBase.updateUser(userId, user);
		
		resp.sendRedirect("/user/list");
	}
}
