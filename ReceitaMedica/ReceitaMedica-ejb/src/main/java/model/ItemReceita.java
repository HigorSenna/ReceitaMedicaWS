package model;

import java.io.Serializable;
import javax.persistence.*;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@Table(name="itens_receitas")
public class ItemReceita implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_ITEM")
	private int idItem;

	@Column(name="CONTRA_INDICACAO")
	private String contraIndicacao;

	@Column(name="INSTRUCAO")
	private String instrucao;

	@Column(name="NM_RECEITA")
	private String nmReceita;

	@Column(name="REG_ANVISA")
	private int regAnvisa;
	
	@Column(name = "USO")
	private String uso;
	
	@Column(name = "STATUS")
	private String status;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="NUM_RECEITA")
	private ReceitasMedica receitasMedica;

	public int getIdItem() {
		return idItem;
	}

	public void setIdItem(int idItem) {
		this.idItem = idItem;
	}

	public String getContraIndicacao() {
		return contraIndicacao;
	}

	public void setContraIndicacao(String contraIndicacao) {
		this.contraIndicacao = contraIndicacao;
	}

	public String getInstrucao() {
		return instrucao;
	}

	public void setInstrucao(String instrucao) {
		this.instrucao = instrucao;
	}

	public String getNmReceita() {
		return nmReceita;
	}

	public void setNmReceita(String nmReceita) {
		this.nmReceita = nmReceita;
	}

	public int getRegAnvisa() {
		return regAnvisa;
	}

	public void setRegAnvisa(int regAnvisa) {
		this.regAnvisa = regAnvisa;
	}

	public String getUso() {
		return uso;
	}

	public void setUso(String uso) {
		this.uso = uso;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public ReceitasMedica getReceitasMedica() {
		return receitasMedica;
	}

	public void setReceitasMedica(ReceitasMedica receitasMedica) {
		this.receitasMedica = receitasMedica;
	}
}