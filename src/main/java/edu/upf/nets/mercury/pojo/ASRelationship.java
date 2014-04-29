package edu.upf.nets.mercury.pojo;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "asrelationships")
public class ASRelationship {
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
	private String id;
	@Indexed
	private int as0;
	@Indexed
	private int as1;
	private int relationship; //-1 customer, 0 peer, 1 provider, 2 sibling, 10 not found, 3 ixp
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getAs0() {
		return as0;
	}
	public void setAs0(int as0) {
		this.as0 = as0;
	}
	public int getAs1() {
		return as1;
	}
	public void setAs1(int as1) {
		this.as1 = as1;
	}
	public int getRelationship() {
		return relationship;
	}
	public void setRelationship(int relationship) {
		this.relationship = relationship;
	}
	
	
	
	

}
