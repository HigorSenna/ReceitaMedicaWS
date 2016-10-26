package rest;

import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.MedicoDAO;
import model.Medico;

@WebServlet(name = "ReceitaMedicaWS", urlPatterns = { "/servico" })
public class MedicoRest extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Medico medico;
	
	@Inject
	private MedicoDAO medicoDAO;

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		
		try (PrintWriter out = response.getWriter()) {          
			
            criarMedico(request);            
            salvar(medico);
            
            out.println("Medico inserido com sucesso!!");
            
		}catch(IOException iex){
			//contete o desenvolvedor
		}
		catch(Exception ex){
			//erro ao inserir
		}
	}
	
	private void criarMedico(HttpServletRequest request){
		String nome = request.getParameter("nomeMedico");
		int CRM = Integer.parseInt(request.getParameter("crm"));
		
		this.medico.setNmMedico(nome);
		this.medico.setCrmMedico(CRM);
	}
	
	private void salvar(Medico medico) throws Exception{
		this.medicoDAO.salvar(medico);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	@Override
	public String getServletInfo() {
		return "Short description";
	}
}
