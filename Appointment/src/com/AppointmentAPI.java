package com;

import com.Appointment;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AppointmentAPI")
public class AppointmentAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Appointment a = new Appointment();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AppointmentAPI() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		/* doGet(request, response); */

		String output = a.insertItem(request.getParameter("p_id"),
				request.getParameter("p_name"),
				request.getParameter("p_age"),
				request.getParameter("type_of_case"),
				request.getParameter("d_name"));

		response.getWriter().write(output);

	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */

	  protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
		  // TODO Auto-generated method stub
	 
	 
	 Map paras = getParasMap(request); 
	 String output = a.updateItem(paras.get("hidItemIDSave").toString(),
//	 paras.get("p_id").toString(),
	 paras.get("p_name").toString(),
	 paras.get("p_age").toString(),
	 paras.get("type_of_case").toString(),
	 paras.get("d_name").toString());
	 response.getWriter().write(output);
	 
	 
	  
	 
	  }


	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		Map paras = getParasMap(request);
		String output = a.deleteItem(paras.get("p_id").toString());
		response.getWriter().write(output);

	}

	private static Map getParasMap(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
			String queryString = scanner.hasNext() ? scanner.useDelimiter("\\A").next() : "";
			scanner.close();
			String[] params = queryString.split("&");
			for (String param : params) {
				String[] p = param.split("=");
				map.put(p[0], p[1]);
			}
		} catch (Exception e) {
		}
		return map;
	}
}
