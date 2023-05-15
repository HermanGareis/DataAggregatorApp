package com.dataaggregator.dataaggregator.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * A class representing the vaccination data of a municipality.
 * 
 * The data includes the municipality's code, name, province, region's acronym,
 * 
 * and the number of administered doses for the first, second, booster, and
 * recall doses.
 * 
 * Overrides the toString method to return
 * 
 * a string representation of the object's fields.
 * 
 * This class is annotated with the Spring Data annotation "@Document" to
 * indicate
 * 
 * that it is a document that can be stored in a MongoDB database collection.
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document
public class ComuneDose {

	@Id
	private String codice;
	private String comune;
	private String provincia;
	private String sigla;
	private int dose1;
	private int dose2;
	private int booster;
	private int richiamo;

	@Override
	public String toString() {
		return "{" + "codice='" + codice + '\'' + ", comune='" + comune + '\'' + ", provincia='" + provincia + '\''
				+ ", sigla='" + sigla + '\'' + ", dose1='" + dose1 + '\'' + ", dose2='" + dose2 + '\'' + ", booster='"
				+ booster + '\'' + ", richiamo='" + richiamo + '\'' + '}';
	}

}
