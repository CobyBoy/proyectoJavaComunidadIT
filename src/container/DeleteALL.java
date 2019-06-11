package container;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DeleteALL
 */
@WebServlet("/DeleteALL")
public class DeleteALL extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteALL() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Statement stmt=null;
		Connection con = null;
		String nombreDB ="jdbc:postgresql://localhost:5432/javausers";

		
		PrintWriter out = response.getWriter(); 
//Conexión
		
	      try {
	         Class.forName("org.postgresql.Driver");
	         con = DriverManager.getConnection(nombreDB,"postgres", "postgres");
	      } 
	      catch (Exception e) {
	         e.printStackTrace();
	         System.err.println(e.getClass().getName()+": "+e.getMessage());
	         System.exit(0);
	      }
	      out.println("BD abierta DeleteALL.java");
	      out.println("<br>");
	      
//borra la tabla
	    
	    String sql=null;
	
	   
	      sql="DELETE FROM usuariostabla ";
	      out.println(nombreDB);
	    	out.println("<br>");
	    	out.append("<br>");
	    	out.append("<h1 class=camposVacios>TODOS LOS ELEMENTOS DE LA TABLA BORRADOS </h1>");
	    	
   
	      try {
	    	  stmt = con.createStatement();
			stmt.execute(sql);
			
		} catch (SQLException e) {
			// Informamos al usuario de los errores SQL que se han producido
	    	out.println("SQL Exception: " + e.getMessage());
			e.printStackTrace();
		}

		  response.setContentType("text/html"); response.setCharacterEncoding("UTF-8");
		  
		 
		  
		  out.append("<!DOCTYPE html>");
		  out.append("<html>"); 
		  out.append("<head>");
		  out.append("<link rel=stylesheet type=text/css href=indexstyle.css>");	 
		  out.append("<title>TABLA ELIMINADA</title>");
		  out.append("</head>"); 
		  out.append("<body>"); 
		  		  
			  
				out.append("<form action=MostrarBD method=post name=\"formulario\">");
				out.append("<input type=submit value=\"Mostrar BD\" class=\"button\">");
				out.append("</form>");
				out.append("<br>");
				 out.append(" <a href=index.html color=white><input type=button class=button value=Inicio></a>");
				out.append("<br>");
	//	}
		  out.append("</body>");
		   out.append("</html>"); 
		   out.flush(); 
		   out.close();
		doGet(request, response);
	}

}
