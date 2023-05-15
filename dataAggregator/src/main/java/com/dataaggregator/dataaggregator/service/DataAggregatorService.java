package com.dataaggregator.dataaggregator.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.dataaggregator.dataaggregator.entity.ComuneDose;
import com.dataaggregator.dataaggregator.exception.BadRequestException;
import com.dataaggregator.dataaggregator.repository.DataAggregatorRepository;
import com.mongodb.BasicDBObject;


/**
 * This class represents a service for aggregating data about COVID-19 vaccine
 * doses administered in Lombardia.
 */
@Service
public class DataAggregatorService {

	@Autowired
	private DataAggregatorRepository repository;

	@Autowired
	private MongoTemplate mongoTemplate;

	/**
	 * This method is used to add a new ComuneDose object to the database.
	 * 
	 * @param comune A ComuneDose object representing the comune (municipality) in
	 *               Italy to be added to the database.
	 * @throws BadRequestException if a comune with the specified ID already exists
	 *                             in the database.
	 */
	public void addComuneDose(ComuneDose comune) {

		boolean existsById = repository.existsById(comune.getCodice());
		if (existsById) {
			throw new BadRequestException("Comune with ID " + comune.getCodice() + " already exists.");
		}
		repository.insert(comune);
	}

	/**
	 * This method is used to retrieve the total number of first doses of COVID-19
	 * vaccine administered in Lombardia.
	 * 
	 * @return a long value representing the total number of first doses
	 *         administered in Lombardia.
	 * 
	 * @throws BadRequestException if the aggregation operation fails or the result
	 *                             is null.
	 */
	public long getTotalNumberOfOneDose() {

		Aggregation aggregation = Aggregation.newAggregation(Aggregation.group().sum("dose1").as("totalDose1"));

		AggregationResults<BasicDBObject> results = mongoTemplate.aggregate(aggregation, "comuneDose",
				BasicDBObject.class);

		BasicDBObject output = results.getUniqueMappedResult();
		if (output == null) {
			throw new BadRequestException("Bad Request");
		}

		return output.getLong("totalDose1");
	}

	/**
	 * This method is used to retrieve the total number of second doses of COVID-19
	 * vaccine administered in Lombardia.
	 * 
	 * @return a long value representing the total number of second doses
	 *         administered in Lombardia.
	 * 
	 * @throws BadRequestException if the aggregation operation fails or the result
	 *                             is null.
	 */
	public long getTotalNumberOfTwoDoses() {

		Aggregation aggregation = Aggregation.newAggregation(Aggregation.group().sum("dose2").as("totalDose2"));

		AggregationResults<BasicDBObject> results = mongoTemplate.aggregate(aggregation, "comuneDose",
				BasicDBObject.class);
		BasicDBObject output = results.getUniqueMappedResult();
		if (output == null) {
			throw new BadRequestException("Bad Request");
		}

		return output.getLong("totalDose2");
	}

	/**
	 * 
	 * Retrieve the total number of first doses of COVID-19 vaccine administered in
	 * a specific province.
	 * 
	 * @param province a String representing the province initials for which the
	 *                 total number of first doses administered should be retrieved.
	 * 
	 * @return a long value representing the total number of first doses
	 *         administered in the specified province.
	 * 
	 * @throws BadRequestException if the aggregation operation fails or the result
	 *                             is null.
	 */
	public long getTotalNumberOfOneDoseFromProvince(String province) {

		Aggregation aggregation = Aggregation.newAggregation(Aggregation.match(Criteria.where("sigla").is(province)),
				Aggregation.group().sum("dose1").as("totalDose1"));

		AggregationResults<BasicDBObject> results = mongoTemplate.aggregate(aggregation, "comuneDose",
				BasicDBObject.class);
		BasicDBObject output = results.getUniqueMappedResult();

		if (output == null) {
			throw new BadRequestException("Bad Request");
		}

		return output.getLong("totalDose1");

	}

	/**
	 * 
	 * Retrieve the total number of second doses of COVID-19 vaccine administered in
	 * a specific province.
	 * 
	 * @param province a String representing the province initials for which the
	 *                 total number of second doses administered should be
	 *                 retrieved.
	 * 
	 * @return a long value representing the total number of second doses
	 *         administered in the specified province.
	 * 
	 * @throws BadRequestException if the aggregation operation fails or the result
	 *                             is null.
	 */
	public long getTotalNumberOfTwoDosesFromProvince(String province) {

		Aggregation aggregation = Aggregation.newAggregation(Aggregation.match(Criteria.where("sigla").is(province)),
				Aggregation.group().sum("dose2").as("totalDose2"));

		AggregationResults<BasicDBObject> results = mongoTemplate.aggregate(aggregation, "comuneDose",
				BasicDBObject.class);
		BasicDBObject output = results.getUniqueMappedResult();

		if (output == null) {
			throw new BadRequestException("Bad Request");
		}

		return output.getLong("totalDose2");
	}

	/**
	 * 
	 * This method returns a list of {@link ComuneDose} objects sorted by the number
	 * of second doses received, in descending order.
	 * 
	 * @return a list of {@link ComuneDose} objects, sorted by the number of second
	 *         doses received.
	 */
	public List<ComuneDose> getComunesOrderedByTwoDoses() {

		Query query = new Query();

		query.with(Sort.by("dose2").descending());

		return mongoTemplate.find(query, ComuneDose.class);
	}

	/**
	 * This method returns a list of {@link ComuneDose} objects from a specific
	 * province, sorted by the number of second doses received, in descending order.
	 * 
	 * @param province the province for which to retrieve the {@link ComuneDose}
	 *                 objects.
	 * @return a list of {@link ComuneDose} objects from the specified province,
	 *         sorted by the number of second doses received.
	 */
	public List<ComuneDose> getComunesOrderedByTwoDosesFromProvince(String province) {

		Query query = new Query(Criteria.where("sigla").is(province));

		query.with(Sort.by("dose2").descending());

		return mongoTemplate.find(query, ComuneDose.class);
	}

	/**
	 * Retrieves the comune with the highest number of first doses administered.
	 * 
	 * @return ComuneDose object with the highest number of first doses.
	 */
	public ComuneDose getComuneWithMoreOneDose() {

		Query query = new Query();
		query.limit(1);
		query.with(Sort.by("dose1").descending());

		return mongoTemplate.findOne(query, ComuneDose.class);
	}

	/**
	 * Retrieves the comune with the highest number of second doses administered.
	 * 
	 * @return ComuneDose object with the highest number of second doses.
	 */
	public ComuneDose getComuneWithMoreTwoDoses() {

		Query query = new Query();
		query.limit(1);
		query.with(Sort.by("dose2").descending());

		return mongoTemplate.findOne(query, ComuneDose.class);
	}

	/**
	 * Retrieves the ComuneDose object with the smallest value of first doses
	 * administered.
	 * 
	 * @return The ComuneDose object with the smallest value of first doses
	 *         administered.
	 */
	public ComuneDose getComuneWithLessOneDose() {

		Query query = new Query();
		query.limit(1);
		query.with(Sort.by("dose1").ascending());

		return mongoTemplate.findOne(query, ComuneDose.class);
	}

	/**
	 * Retrieves the ComuneDose object with the smallest value of second doses
	 * administered.
	 * 
	 * @return The ComuneDose object with the smallest value of second doses
	 *         administered.
	 */
	public ComuneDose getComuneWithLessTwoDoses() {

		Query query = new Query();
		query.limit(1);
		query.with(Sort.by("dose2").ascending());

		return mongoTemplate.findOne(query, ComuneDose.class);
	}

	/**
	 * Retrieves the ComuneDose object with the biggest value of first doses
	 * administered by a specific province.
	 * 
	 * @param province the province for which to retrieve the {@link ComuneDose}
	 *                 objects.
	 * 
	 * @return The ComuneDose object with the biggest value of first doses
	 *         administered.
	 */
	public ComuneDose getComuneWithMoreOneDoseFromProvince(String province) {

		Query query = new Query(Criteria.where("sigla").is(province));
		query.limit(1);
		query.with(Sort.by("dose1").descending());

		return mongoTemplate.findOne(query, ComuneDose.class);
	}

	/**
	 * Retrieves the ComuneDose object with the biggest value of second doses
	 * administered by a specific province.
	 * 
	 * @param province the province for which to retrieve the {@link ComuneDose}
	 *                 objects.
	 * 
	 * @return The ComuneDose object with the biggest value of second doses
	 *         administered.
	 */
	public ComuneDose getComuneWithMoreTwoDosesFromProvince(String province) {

		Query query = new Query(Criteria.where("sigla").is(province));
		query.limit(1);
		query.with(Sort.by("dose2").descending());

		return mongoTemplate.findOne(query, ComuneDose.class);
	}

}
