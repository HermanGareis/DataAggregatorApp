package com.dataaggregator.dataaggregator.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.dataaggregator.dataaggregator.entity.ComuneDose;

/**
 * A repository interface for CRUD operations on ComuneDose entities in MongoDB.
 * Extends the MongoRepository interface to inherit basic MongoDB operations.
 */
public interface DataAggregatorRepository extends MongoRepository<ComuneDose, String> {

}
