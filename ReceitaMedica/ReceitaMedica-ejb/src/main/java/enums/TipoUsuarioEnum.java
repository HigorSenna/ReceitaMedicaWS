package enums;

public enum TipoUsuarioEnum {
	
	MEDICO("MEDICO","M"),
	PACIENTE("PACIENTE","P");
	
	private String label;
	private String valor;
	
	private TipoUsuarioEnum(String label, String valor) {
		this.label = label;
		this.valor = valor;
	}
	
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
}
