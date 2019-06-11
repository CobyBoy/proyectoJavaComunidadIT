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
 * Servlet implementation class MostrarBD
 */
@WebServlet("/MostrarBD")
public class MostrarBD extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MostrarBD() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	Statement stmt=null;	
	Connection con = null;
	PrintWriter out = response.getWriter(); 
	String nombreDB ="jdbc:postgresql://localhost:5432/javausers";
	
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
	      out.println("BD abierta MostrarBD.java");
	      out.println("<br>");
 //
//Imprime toda la tabla de usuarios
	      
	      out.append("BD ACTUAL:"+nombreDB);
	      out.append("<br>");
	     
	      out.println("<br>");
	      try{
		    	stmt = con.createStatement();
		    	ResultSet rs = stmt.executeQuery("SELECT * FROM usuariostabla ORDER BY id");
		   // Resultado obtenidos en la consulta SQL
		    	String nombre =null;
		    	int id=0 ;
		    	String usuario=null;
		    	String email = null;
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
		    		 id = rs.getInt("id");
			    	 email = rs.getString("email");
			    	 
			    	 out.append("<tr>");
			    	 out.append("<td>");out.println(id );out.append("</td>");
			    	 out.append("<td>");out.println( nombre);out.append("</td>");
			    	 out.append("<td>");out.println(usuario);out.append("</td>");
			    	 out.append("<td>");out.println(email);out.append("</td>");
			    	 out.append("</tr>");
		    	
		    	}
		    	out.append("</table>");
//Fin de Tabla		    	
		    	
		    
		    	rs.close();
		    	stmt.close();
		    	
		    	}catch(SQLException se){
		    // Informamos al usuario de los errores SQL que se han producido
		    	out.println("SQL Exception: " + se.getMessage());
		    	se.printStackTrace(System.out);
		    	}
	      
		  response.setContentType("text/html"); response.setCharacterEncoding("UTF-8");
		  
		 
		  
		  out.append("<!DOCTYPE html>");
		  out.append("<html>"); 
		  out.append("<head>");
		  out.append("<link rel=stylesheet type=text/css href=indexstyle.css>");	 
		  out.append("<title>BASE DE DATOS</title>");
		  out.append("</head>"); 
		  out.append("<body>"); 
		  out.append("<br>");
		  out.append(" <a href=index.html><input type=button class=button value=\"Inicio\"></a>");
//formulario insert		  
		  out.append( "<form action=InsertBD method=post name=formulario>");
		  out.append("<div id=datos>");
		  out.append("Nombre completo: <br>");
		  out.append("<input type=text name=nombre placeholder=\"Inserte su nombre \"><br>");
		  out.append("Nombre de usuario<br>");
		  out.append("<input type=text name=usuario placeholder=\"Inserte su nombre de usuario (long=10)\"><br>");
		  out.append("E-mail:<br>");
		  out.append("<input type=text name=email placeholder=\"Inserte su e-mail\"><br>");
		  out.append("	ID:<br>");
		  out.append("<input type=number name=ID min=1 max =10 value=1><br>");

		  out.append("</div>");
		  out.append("	<div id=nuevoInsert></div>");
		 	
		  out.append("<input type=button value=\"Agregar Entrada \" onclick='crearInput()'class=\"button\" >");
		  out.append("<input type=reset value=Reset Valores class=\"button\"> ");
		 		
		  out.append("	<input type=submit value=\"INSERT INTO BD\" class=\"button\">");
//formulario INSERT ALL		  
		  out.append("<input type=submit formaction=InsertALL  value=\"INSERT ALL\" class=button>");
//formulario UPDATE
		  out.append("<input type=submit formaction=UpdateBD  value=\"UPDATE\" class=button>");
		  out.append("<input type=submit formaction=UpdateALL  value=\"UPDATE ALL\" class=button>");
		  out.append("</form>");
		  
//formulario delete
		  out.append("<form action=DeleteBD method=post name=formulario>");
		  out.append("	<div id=datos>");
				
		  out.append("	ID:<input type=number name=ID min=1 max =10 value=1><br>");
		  out.append("		</div>");
		  out.append("			<input type=submit value=\"DELETE FROM\"  BD class=\"button\">");
//formulario delete all
		  out.append("<input type=submit formaction=DeleteALL  value=\"DELETE ALL\" class=button>");
		  out.append("	</form>");
		  
		  out.append("<script src=crearFormulario.js></script>");
		  out.append("</body>");
		  out.append("</html>"); 
		  out.flush(); 
		  out.close();
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
