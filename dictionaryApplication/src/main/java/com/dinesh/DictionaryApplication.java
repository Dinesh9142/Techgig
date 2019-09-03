package com.dinesh;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.dinesh.annotation.LogMethodCall;
import com.dinesh.repository.DictionaryRepository;

@SpringBootApplication
public class DictionaryApplication implements CommandLineRunner {

	
	@Autowired
    DictionaryRepository lDictionaryRepository;

    public static void main(String[] args) {
		SpringApplication.run(DictionaryApplication.class, args);
	}


    @Override
    public void run(String... strings) throws Exception {

    }


}
