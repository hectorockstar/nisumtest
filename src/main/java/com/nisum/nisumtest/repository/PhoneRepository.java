package com.nisum.nisumtest.repository;

import com.nisum.nisumtest.model.Phone;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface PhoneRepository extends CrudRepository<Phone, UUID> {
}
