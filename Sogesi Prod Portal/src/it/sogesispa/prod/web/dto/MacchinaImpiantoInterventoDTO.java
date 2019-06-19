package it.sogesispa.prod.web.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MacchinaImpiantoInterventoDTO implements Serializable{

	public class ImpiantoInterventoDTO
	{
		private String descrizioneImpianto;
		private List<AttivitaDTO> list = new ArrayList<>();
		public String getDescrizioneImpianto() {
			return descrizioneImpianto;
		}
		public void setDescrizioneImpianto(String descrizioneImpianto) {
			this.descrizioneImpianto = descrizioneImpianto;
		}
		public List<AttivitaDTO> getList() {
			return list;
		}
		public void setList(List<AttivitaDTO> list) {
			this.list = list;
		}
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = -2595394065889586730L;
	
	private List<ImpiantoInterventoDTO> lista = new ArrayList<>();
	private String nomeMacchina;
	private String tipologiaMacchina;
	private String nomeStabilimento;
	private String nomeLinea;
	public String getNomeMacchina() {
		return nomeMacchina;
	}
	public void setNomeMacchina(String nomeMacchina) {
		this.nomeMacchina = nomeMacchina;
	}
	public List<ImpiantoInterventoDTO> getLista() {
		return lista;
	}
	public void setLista(List<ImpiantoInterventoDTO> lista) {
		this.lista = lista;
	}
	public String getNomeStabilimento() {
		return nomeStabilimento;
	}
	public void setNomeStabilimento(String nomeStabilimento) {
		this.nomeStabilimento = nomeStabilimento;
	}
	public String getNomeLinea() {
		return nomeLinea;
	}
	public void setNomeLinea(String nomeLinea) {
		this.nomeLinea = nomeLinea;
	}
	public String getTipologiaMacchina() {
		return tipologiaMacchina;
	}
	public void setTipologiaMacchina(String tipologiaMacchina) {
		this.tipologiaMacchina = tipologiaMacchina;
	}
	
	
}
