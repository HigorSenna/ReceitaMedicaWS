package model;

import java.io.Serializable;

public class ReciboReceita implements Serializable{	
	
	private static final long serialVersionUID = 1L;
	
	private String crmMedico;
	private String cpfPaciente;
	private int numReceita;
	private String data;
	private String msg;
	
	public ReciboReceita(String crmMedico, String cpfPaciente, int numReceita, String data, String msg) {
		this.crmMedico = crmMedico;
		this.cpfPaciente = cpfPaciente;
		this.numReceita = numReceita;
		this.data = data;
		this.msg = msg;
	}
	
	public String getCrmMedico() {
		return crmMedico;
	}
	public void setCrmMedico(String crmMedico) {
		this.crmMedico = crmMedico;
	}
	public String getCpfPaciente() {
		return cpfPaciente;
	}
	public void setCpfPaciente(String cpfPaciente) {
		this.cpfPaciente = cpfPaciente;
	}
	public int getNumReceita() {
		return numReceita;
	}
	public void setNumReceita(int numReceita) {
		this.numReceita = numReceita;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
}
