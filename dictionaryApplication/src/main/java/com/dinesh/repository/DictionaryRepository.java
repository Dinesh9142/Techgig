package com.dinesh.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dinesh.model.Dictionary;

/**
 * Created by Dinesh
 */
@Repository
public interface DictionaryRepository extends CrudRepository<Dictionary, Long>{
	 @Query("select a.searchword from Dictionary a where a.searchword like %?1")
	 List<String> findByName(String searchword);
}
