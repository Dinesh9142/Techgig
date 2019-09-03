package com.dinesh.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dinesh.repository.DictionaryRepository;

@Component
public class DictionaryDAO implements IDictionary {

	@Autowired
	DictionaryRepository lDictionaryRepository;

	@Override
	public List<String> findByName(String searchword) {
		return (List<String>) lDictionaryRepository.findByName(searchword);
	}

}
