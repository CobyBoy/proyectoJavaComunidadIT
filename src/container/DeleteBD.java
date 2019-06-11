package container;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DeleteBD
 */
@WebServlet("/DeleteBD")
public class DeleteBD extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteBD() {
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

		
		PrintWriter out = response.getWriter(); 
//Conexión
		
	      try {
	         Class.forName("org.postgresql.Driver");
	         con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/javausers","postgres", "postgres");
	      } 
	      catch (Exception e) {
	         e.printStackTrace();
	         System.err.println(e.getClass().getName()+": "+e.getMessage());
	         System.exit(0);
	      }
	      out.println("BD abierta DeleteBD.java");
	      out.println("<br>");
	      
//borra de  la tabla
	    
	      String sql=null;
	      String showSQL=null;
	      String idInput = request.getParameter("ID");
	   
	      sql="DELETE FROM usuariostabla WHERE id="+idInput+"";
	      showSQL="SELECT full_name, usuario, email FROM usuariostabla WHERE id="+idInput+"";
	      
	      try {stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT full_name, usuario, email FROM usuariostabla WHERE id="+idInput+"");
			
			String nombre =null;
	    	String usuario=null;
	    	String email = null;
			
	    	out.println("<br>");
	    	out.append("<h1>ESTOS SON LOS ELEMENTOS BORRADOS</h1>");
	    	out.println("<br>");
	    	out.append("<br>");
//Tabla	    	
	   	 out.append("<table id=tablaBD>");
    	 out.append("<tr>");
    	 out.append("<th>ID");out.append("</th>");
    	 out.append("<th>NOMBRE"); out.append("</th>");
    	 out.append("<th>USUARIO"); out.append("</th>");
    	 out.append("<th>EMAIL"); out.append("</th>");
    	 out.append("</tr>");	
    	 
    	while(rs.next()){
    		 nombre = rs.getString("full_name");
    		 usuario = rs.getString("usuario");
    	
	    	 email = rs.getString("email");
	    	 
	    	 out.append("<tr>");
	    	 out.append("<td>");out.append(idInput); out.append("</td>");
	    	 out.append("<td>");out.println( nombre);out.append("</td>");
	    	 out.append("<td>");out.println(usuario);out.append("</td>");
	    	 out.append("<td>");out.println(email);out.append("</td>");
	    	 out.append("</tr>");
    	
    	}
    	out.append("</table>");
			
			rs.close();
	    	stmt.close();
		} catch (SQLException e1) {
			out.println("SQL Exception: " + e1.getMessage());
			e1.printStackTrace();
		}
	   
   
	      try {
	    	  stmt = con.createStatement();
			stmt.execute(sql);
			
		} catch (SQLException e) {
			// Informamos al usuario de los errores SQL que se han producido
	    	out.println("SQL Exception: " + e.getMessage());
			e.printStackTrace();
		}
	   
	      
	      try {stmt = con.createStatement();
			stmt.execute(showSQL);
		} catch (SQLException e) {
			out.println("SQL Exception: " + e.getMessage());
			e.printStackTrace();
		}
	      
	      

		  response.setContentType("text/html"); response.setCharacterEncoding("UTF-8");
		  
		 
		  
		  out.append("<!DOCTYPE html>");
		  out.append("<html>"); 
		  out.append("<head>");
		  out.append("<link rel=stylesheet type=text/css href=indexstyle.css>");	 
		  out.append("<title>Valores ELIMINADOS</title>");
		  out.append("</head>"); 
		  out.append("<body>"); 
		  		  
//FORM MOSTRAR			  
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
