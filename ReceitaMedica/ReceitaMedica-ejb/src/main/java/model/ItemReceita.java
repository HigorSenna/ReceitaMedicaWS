package model;

import java.io.Serializable;
import javax.persistence.*;

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

	private String instrucao;

	@Column(name="NM_RECEITA")
	private String nmReceita;

	@Column(name="REG_ANVISA")
	private int regAnvisa;

	private String uso;

	@ManyToOne
	@JoinColumn(name="NUM_RECEITA")
	private ReceitasMedica receitasMedica;

	public ItemReceita() {
	}

	public int getIdItem() {
		return this.idItem;
	}

	public void setIdItem(int idItem) {
		this.idItem = idItem;
	}

	public String getContraIndicacao() {
		return this.contraIndicacao;
	}

	public void setContraIndicacao(String contraIndicacao) {
		this.contraIndicacao = contraIndicacao;
	}

	public String getInstrucao() {
		return this.instrucao;
	}

	public void setInstrucao(String instrucao) {
		this.instrucao = instrucao;
	}

	public String getNmReceita() {
		return this.nmReceita;
	}

	public void setNmReceita(String nmReceita) {
		this.nmReceita = nmReceita;
	}

	public int getRegAnvisa() {
		return this.regAnvisa;
	}

	public void setRegAnvisa(int regAnvisa) {
		this.regAnvisa = regAnvisa;
	}

	public String getUso() {
		return this.uso;
	}

	public void setUso(String uso) {
		this.uso = uso;
	}

	public ReceitasMedica getReceitasMedica() {
		return this.receitasMedica;
	}

	public void setReceitasMedica(ReceitasMedica receitasMedica) {
		this.receitasMedica = receitasMedica;
	}

}