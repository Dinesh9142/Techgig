package com.dinesh.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dinesh.dao.DictionaryDAO;
import com.dinesh.model.Dictionary;
import com.dinesh.repository.DictionaryRepository;
import com.dinesh.utils.Response;

@Service
public class CommonService {
	private static final Logger logger = Logger.getLogger(CommonService.class);
	
	@Autowired
	DictionaryRepository lDictionaryRepository;
	
	@Autowired
	DictionaryDAO lDictionaryDAO;
	
	@Transactional
	public Response addDictoniary(String fileName) {
		Response lResponse = new Response();
		logger.info("Input File Name: "+fileName);
		Set<String> wordsToBeAddedInDic = new HashSet<>();
		File file = new File(fileName);
		if (file.exists()) {
			try (FileReader fr = new FileReader(file); BufferedReader br = new BufferedReader(fr)) {
				String line;
				while ((line = br.readLine()) != null) {
					// process the line
					wordsToBeAddedInDic.addAll(Arrays.asList(line.split(" ")).stream().map(x->x.toLowerCase().trim().replaceAll("[^a-zA-Z0-9]+",""))
							.filter(x -> x != null && !x.trim().isEmpty()).collect(Collectors.toSet()));
				}

				// If new word list is available, validate those words with existing words and add new word in db
				Set<String> newWordToAdd = new HashSet<>();
				if (!wordsToBeAddedInDic.isEmpty()) {
					List<Dictionary> existingDics = (ArrayList) lDictionaryRepository.findAll();

					if (existingDics != null && !existingDics.isEmpty()) {
						Set<String> existingWords = existingDics.stream().map(x-> x.getSearchWord()).collect(Collectors.toSet());
						// Remove the existing words from the addition list by comparing it with db data
						Collection<String> newWords = CollectionUtils.subtract(wordsToBeAddedInDic, existingWords);
						if(newWords!=null && !newWords.isEmpty()) {
							newWordToAdd.addAll(newWords);
						}
					}else {
						newWordToAdd = wordsToBeAddedInDic;
					}
					
					if (newWordToAdd != null && !newWordToAdd.isEmpty()) {
						newWordToAdd.stream()
							.filter(x -> x != null && !x.toString().isEmpty())
							.forEach(word -> {
								Dictionary lDictionary = new Dictionary();
								lDictionary.setSearchWord(word);
								lDictionaryRepository.save(lDictionary);
						});
					}
				}
				lResponse.setStatus(true);
				lResponse.setMessages("Words in the File added into DB successfully");
			} catch (Exception e) {
				logger.info("Error occured in addDictoniary() ", e);
				lResponse.setStatus(false);
				lResponse.setErrorMessages("Error occured in addDictoniary() " + e);
			}
		} else {
			lResponse.setStatus(false);
			lResponse.setErrorMessages("Please provide valid File name or path to load dictionary data");
		}
		return lResponse;
	}
	
	@Transactional
	public Response getDictionaryCount() {
		List<String> dicList = (List)lDictionaryRepository.findAll();
		Response lResponse = new Response();
		lResponse.setStatus(true);
		lResponse.setData(dicList);
		return lResponse;
	}
	
	@Transactional
	public Response isWordExists(String searchWord) {

		Response lResponse = new Response();
		if (searchWord == null || searchWord.isEmpty()) {
			lResponse.setStatus(false);
			lResponse.setErrorMessages("Please provide valid word to search.");
		} else if (searchWord != null && !searchWord.isEmpty()) {
			List<String> existingDics = lDictionaryDAO.findByName(searchWord.toLowerCase());
			if (existingDics != null && !existingDics.isEmpty()) {
				lResponse.setStatus(true);
				lResponse.setMessages("Is " + searchWord + " word available in db: " + Boolean.TRUE);
			} else {
				lResponse.setStatus(true);
				lResponse.setMessages("Is " + searchWord + " word available in db: " + Boolean.FALSE);
			}
		}
		return lResponse;
	}
}
