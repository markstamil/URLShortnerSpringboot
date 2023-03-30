package com.example.SampleAES.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.SampleAES.Model.StoreURL;

@Repository
public interface URLPostAndGetRepo extends MongoRepository<StoreURL, Integer> {

	@Query(value="{'Shortened_URL':?0}")
	public List<StoreURL> findByShortened_URL(String url);
}
