package br.ifms.cx.petrastreio.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Coordenada {
	@Id
	@SequenceGenerator(sequenceName = "seq_coordenada", name = "gen_seq_coordenada", allocationSize = 1, initialValue = 1)
	@GeneratedValue(generator = "gen_seq_coordenada", strategy = GenerationType.SEQUENCE)

	private Long id;
	private String latitude;
	private String longitude;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
}
