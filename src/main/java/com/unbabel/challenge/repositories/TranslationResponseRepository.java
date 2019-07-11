package com.unbabel.challenge.repositories;

import com.unbabel.challenge.model.TranslationResponse;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TranslationResponseRepository extends CrudRepository<TranslationResponse,Long> {
}
