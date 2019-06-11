package container;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.sql.SQLException;
import java.sql.Statement;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UpdateBD
 */
@WebServlet("/UpdateBD")
public class UpdateBD extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateBD() {
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
		String [] nombres=null;
		String [] usuarios = null;
		String [] emails = null;
		String[] ID = null;
		
		Statement stmt=null;
	
		Connection con = null;
		
		 if (request.getParameterValues("nombre")!= null) {
	    	   nombres =  request.getParameterValues("nombre");
	    	  }
	      
	      if (request.getParameterValues("usuario")!= null) {
	    	  usuarios =request.getParameterValues("usuario");
	    	  }
	
	      if (request.getParameterValues("email")!= null) {
	    	  emails =request.getParameterValues("email");
	    	  }
	      if (request.getParameterValues("ID")!= null) {
	    	   ID =  request.getParameterValues("ID");
	    	  }
	    
		
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
	      out.println("BD abierta UpdateBD.java");
	     
	      
//updatea  la tabla
	    
	      String sql=null;
	   
	   
	      String nombreInput = request.getParameter("nombre");
	      String usuarioInput = request.getParameter("usuario");
	      String emailInput = request.getParameter("email");
	      String idInput = request.getParameter("ID");
	   
	      if (nombreInput==""||usuarioInput==""||emailInput=="" ) {
	    	  paginaDeError(response);
		}
	      else {
	    	  // sql="INSERT INTO usuariostabla (id,full_name, usuario, email) VALUES ('11','"+nombreInput+"','"+usuarioInput+"','"+emailInput+"')";
	 	     sql="UPDATE usuariostabla SET full_name= '"+nombreInput+"' , usuario= '"+usuarioInput+"' , email= '"+emailInput+"'WHERE id='"+idInput+ "'";
	    
		}
	      
	      
	      
	 
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
		  out.append("<title>VALORES ACTUALIZADOS</title>");
		  out.append("</head>"); 
		  out.append("<body>"); 
		  		  
		  for ( int i = 0; i < nombres.length; i++ ) {
			  	out.append("<br>");
				out.append("<h1>ACTUALIZADOS A LA DB:</h1>");
				out.println("<br>");
				out.append("<br>");
				
				out.append("<table id=tablaBD>"); 
				out.append("<tr>");
				out.append("<th>"); out.append("ID" ); out.append("</th>");  
				out.append("<th>"); out.append("Nombre" ); out.append("</th>");  
				out.append("<th>"); out.append("Usuario" ); out.append("</th>");  
				out.append("<th>"); out.append("Email" ); out.append("</th>");  
				
				out.append("</tr>"); 
				
				out.append("<tr>");
				out.append("<td>");out.append(ID[i]);out.append("</td>"); 
				out.append("<td>"); out.append(nombres[i]);out.append("</td>"); 
				out.append("<td>");out.append(usuarios[i]);out.append("</td>"); 
				out.append("<td>");out.append(emails[i]);out.append("</td>"); 
				out.append("</tr>"); 
				
				out.append("</table>"); 
				
				
				out.append("<br>");
				out.append("<form action=MostrarBD method=post name=\"formulario\">");
				out.append("<input type=\"submit\" value=\"Mostrar BD\" class=\"button\">");
				out.append("</form>");
				out.append("<br>");
				 out.append(" <a href=index.html color=white><input type=button class=button value=Inicio></a>");
				out.append("<br>");
		}
		  out.append("</body>");
		   out.append("</html>"); 
		   out.flush(); 
		   out.close();
		doGet(request, response);
	}
	private void paginaDeError(HttpServletResponse response) throws IOException {
		
		PrintWriter out = response.getWriter(); 
		response.setContentType("text/html"); response.setCharacterEncoding("UTF-8");
		 out.append("<!DOCTYPE html>");
		  out.append("<html>"); 
		  out.append("<head>");
		  out.append("<meta http-equiv=Refresh content=2;url=MostrarBD>");
		  out.append("<link rel=stylesheet type=text/css href=indexstyle.css>");	 
		  out.append("<title>VALORES ACTUALIZADOS</title>");
		  out.append("</head>"); 
		  out.append("<body>"); 
		 
		  out.append("<br>");  out.append("<br>");  out.append("<br>");  out.append("<br>"); 
		  out.println("<h1 class=camposVacios>ALGUN CAMPO ESTA VACIO. Volviendo a la tabla...</h1>");
//		  out.append("<form action=MostrarBD method=post name=\"formulario\">");
//			out.append("<input type=\"submit\" value=\"Mostrar BD\" class=\"button\">");
//			out.append("</form>");
			 out.append("<script src=crearFormulario.js></script>"); 
			 out.append("</body>"); 
			 out.append("</html>"); 
	}

}
