package enums;

public enum StatusItemEnum {
	ATIVO("A","ATIVO"),
	VENDIDO("V","VENDIDO");
	
	private String valor;
	private String label;
	
	StatusItemEnum(String valor,String label){
		this.valor = valor;
		this.label = label;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
}
