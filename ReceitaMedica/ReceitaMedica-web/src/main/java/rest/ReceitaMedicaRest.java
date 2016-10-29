package rest;

import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import model.Medico;
import service.MedicoService;

@WebServlet(name = "ReceitaMedicaWS", urlPatterns = { "/receitaMedica" })

public class ReceitaMedicaRest extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Medico medico;
	
	@Inject 
	MedicoService medicoService;

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		
		try (PrintWriter out = response.getWriter()) {          
			JSONArray listaRest = new JSONArray();

			Medico m = new Medico();
			m.setCrmMedico(123456);
			m.setNmMedico("Higor");
			
			JSONObject jo = new JSONObject();
			jo.put("medico", m.getNmMedico());
			jo.put("crm", m.getCrmMedico());
			
			listaRest.put(jo);
			System.out.println(listaRest.toString());

            
		}catch(IOException iex){
			//contete o desenvolvedor
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
		}
	}
	
	private void retornarMedicoTeste(PrintWriter out){
		JSONArray listaRest = new JSONArray();

		Medico m = new Medico();
		m.setCrmMedico(123456);
		m.setNmMedico("Higor");
		
		JSONObject jo = new JSONObject();
		jo.put("medico", m.getNmMedico());
		jo.put("crm", m.getCrmMedico());
		
		listaRest.put(jo);
		out.print(listaRest.toString());
		
	}
	
	
	private void criarMedico(HttpServletRequest request){
		//String nome = request.getParameter("nomeMedico");
		//int CRM = Integer.parseInt(request.getParameter("crm"));
		
		this.medico.setNmMedico("Teste");
		this.medico.setCrmMedico(1234567); //mudar CRM PARA INSERIR, POIS EH CHAVE ESTRANGEIRA
	}	
	
	private void salvar(Medico medico) throws Exception{
		this.medicoService.salvar(medico);
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
