package LIS.Controller;

import LIS.Command._00_Main;
import LIS.Command._01_Register;
import LIS.Command._02_Login;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = {"*.do"})
public class Controller extends HttpServlet {

	private Map<String, Object> commandMap = new HashMap<>();

	public void init() {
		commandMap.put("/main.do", new _00_Main());
		commandMap.put("/register.do", new _01_Register());
		commandMap.put("/login.do", new _02_Login());
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		requestPro(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		requestPro(request, response);
	}

	private void requestPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String view = "";
		CommandAction com = null;
		try {
			String command = request.getRequestURI();
			if(command.indexOf(request.getContextPath()) == 0) {
				command = command.substring(request.getContextPath().length());
			}
			com = (CommandAction)commandMap.get(command);
			view = com.requestPro(request, response);
		}catch(Exception e) {
			e.printStackTrace();
		}

		request.setAttribute("content", view);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/00_index.jsp");
		dispatcher.forward(request, response);
	}
}
