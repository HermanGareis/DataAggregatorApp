package com.dataaggregator.dataaggregator.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dataaggregator.dataaggregator.entity.ComuneDose;
import com.dataaggregator.dataaggregator.service.DataAggregatorService;

import lombok.AllArgsConstructor;

/**
 * This class represents a RESTful web service that provides information about
 * COVID-19 vaccine doses administered in various comunes (municipalities) in
 * Italy. It is annotated with @RestController to indicate that it is a RESTful
 * web service, and @RequestMapping with the "api/comunes" path to specify the
 * base URI for all HTTP requests handled by this controller.
 */
@RestController
@RequestMapping(path = "api/comunes")
@AllArgsConstructor
public class DataAggregatorController {

	@Autowired
	DataAggregatorService service;

	/**
	 * This method is used to handle the HTTP GET request for retrieving the total
	 * number of individuals who have received a single dose of the COVID-19
	 * vaccine.
	 * 
	 * @return The total number of individuals who have received a single dose of
	 *         the COVID-19 vaccine.
	 */
	@RequestMapping(value = "/getTotalNumberOfOneDose")
	public long getTotalNumberOfOneDose() {

		return service.getTotalNumberOfOneDose();
	}

	/**
	 * This method is used to handle the HTTP GET request for retrieving the total
	 * number of individuals who have received two doses of the COVID-19 vaccine.
	 * 
	 * @return The total number of individuals who have received two dose of the
	 *         COVID-19 vaccine.
	 */
	@RequestMapping(value = "/getTotalNumberOfTwoDoses")
	public long getTotalNumberOfTwoDoses() {

		return service.getTotalNumberOfTwoDoses();
	}

	/**
	 * This method is used to handle the HTTP GET request for retrieving the total
	 * number of individuals who have received a single doses of the COVID-19
	 * vaccine from a specific province in Italy.
	 * 
	 * @param siglaProvince The province code (sigla) for which the total number of
	 *                      individuals who have received a single doses of the
	 *                      COVID-19 vaccine is to be retrieved.
	 * @return The total number of individuals who have received two doses of the
	 *         COVID-19 vaccine from the specified province in Italy.
	 */
	@RequestMapping(value = "/getTotalNumberOfOneDoseFromProvince/{siglaProvince}")
	public long getTotalNumberOfOneDoseFromProvince(@PathVariable("siglaProvince") String siglaProvince) {

		return service.getTotalNumberOfOneDoseFromProvince(siglaProvince);
	}

	/**
	 * This method is used to handle the HTTP GET request for retrieving the total
	 * number of individuals who have received two doses of the COVID-19 vaccine
	 * from a specific province in Italy.
	 * 
	 * @param siglaProvince The province code (sigla) for which the total number of
	 *                      individuals who have received two doses of the COVID-19
	 *                      vaccine is to be retrieved.
	 * @return The total number of individuals who have received two doses of the
	 *         COVID-19 vaccine from the specified province in Italy.
	 */
	@RequestMapping(value = "/getTotalNumberOfTwoDosesFromProvince/{siglaProvince}")
	public long getTotalNumberOfTwoDosesFromProvince(@PathVariable("siglaProvince") String siglaProvince) {

		return service.getTotalNumberOfTwoDosesFromProvince(siglaProvince);
	}

	/**
	 * This method is used to handle the HTTP GET request for retrieving a list of
	 * all the comunes in Italy, sorted by the total number of individuals who have
	 * received two doses of the COVID-19 vaccine in descending order.
	 * 
	 * @return A list of ComuneDose objects representing each comune in Italy,
	 *         sorted by the total number of individuals who have received two doses
	 *         of the COVID-19 vaccine in descending order.
	 */
	@RequestMapping(value = "/getComunesOrderedByTwoDoses")
	public List<ComuneDose> getComunesOrderedByTwoDoses() {

		return service.getComunesOrderedByTwoDoses();
	}

	/**
	 * This method is used to handle the HTTP GET request for retrieving a list of
	 * all the comunes from a specific province in Italy, sorted by the total number
	 * of individuals who have received two doses of the COVID-19 vaccine in
	 * descending order.
	 * 
	 * @param siglaProvince The province code (sigla) for which the list of comunes
	 *                      is to be retrieved and sorted.
	 * @return A list of ComuneDose objects representing each comune from the
	 *         specified province in Italy, sorted by the total number of
	 *         individuals who have received two doses of the COVID-19 vaccine in
	 *         descending order.
	 */
	@RequestMapping(value = "/getComunesOrderedByTwoDosesFromProvince/{siglaProvince}")
	public List<ComuneDose> getComunesOrderedByTwoDosesFromProvince(
			@PathVariable("siglaProvince") String siglaProvince) {

		return service.getComunesOrderedByTwoDosesFromProvince(siglaProvince);
	}

	/**
	 * This method is used to handle the HTTP GET request for retrieving the comune
	 * in Italy with the highest number of individuals who have received a single
	 * dose of the COVID-19 vaccine.
	 * 
	 * @return A ComuneDose object representing the comune in Italy with the highest
	 *         number of individuals who have received a single dose of the COVID-19
	 *         vaccine.
	 */
	@RequestMapping(value = "/getComuneWithMoreOneDose")
	public ComuneDose getComuneWithMoreOneDose() {

		return service.getComuneWithMoreOneDose();
	}

	/**
	 * This method is used to handle the HTTP GET request for retrieving the comune
	 * in Italy with the highest number of individuals who have received two doses
	 * of the COVID-19 vaccine.
	 * 
	 * @return A ComuneDose object representing the comune in Italy with the highest
	 *         number of individuals who have received two doses of the COVID-19
	 *         vaccine.
	 */
	@RequestMapping(value = "/getComuneWithMoreTwoDoses")
	public ComuneDose getComuneWithMoreTwoDoses() {

		return service.getComuneWithMoreTwoDoses();
	}

	/**
	 * This method is used to handle the HTTP GET request for retrieving the comune
	 * in Italy with the lowest number of individuals who have received a single
	 * dose of the COVID-19 vaccine.
	 * 
	 * @return A ComuneDose object representing the comune in Italy with the lowest
	 *         number of individuals who have received a single dose of the COVID-19
	 *         vaccine.
	 */
	@RequestMapping(value = "/getComuneWithLessOneDose")
	public ComuneDose getComuneWithLessOneDose() {

		return service.getComuneWithLessOneDose();
	}

	/**
	 * This method is used to handle the HTTP GET request for retrieving the comune
	 * from a specific province in Italy with the highest number of individuals who
	 * have received a single dose of the COVID-19 vaccine.
	 * 
	 * @param siglaProvince The province code (sigla) for which the comune with the
	 *                      highest number of individuals who have received a single
	 *                      dose of the COVID-19 vaccine is to be retrieved.
	 * @return A ComuneDose object representing the comune from the specified
	 *         province in Italy with the highest number of individuals who have
	 *         received a single dose of the COVID-19 vaccine.
	 */
	@RequestMapping(value = "/getComuneWithMoreOneDoseFromProvince/{siglaProvince}")
	public ComuneDose getComuneWithMoreOneDoseFromProvince(@PathVariable("siglaProvince") String siglaProvince) {

		return service.getComuneWithMoreOneDoseFromProvince(siglaProvince);
	}

	/**
	 * This method is used to handle the HTTP GET request for retrieving the comune
	 * from a specific province in Italy with the highest number of individuals who
	 * have received two doses of the COVID-19 vaccine.
	 * 
	 * @param siglaProvince The province code (sigla) for which the comune with the
	 *                      highest number of individuals who have received two
	 *                      doses of the COVID-19 vaccine is to be retrieved.
	 * @return A ComuneDose object representing the comune from the specified
	 *         province in Italy with the highest number of individuals who have
	 *         received two doses of the COVID-19 vaccine.
	 */
	@RequestMapping(value = "/getComuneWithMoreTwoDosesFromProvince/{siglaProvince}")
	public ComuneDose getComuneWithMoreTwoDosesFromProvince(@PathVariable("siglaProvince") String siglaProvince) {

		return service.getComuneWithMoreTwoDosesFromProvince(siglaProvince);
	}

}
