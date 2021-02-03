package org.ftn.poslovnainformatika.podsistemprodajeprojekat.repository;

import org.ftn.poslovnainformatika.podsistemprodajeprojekat.model.JedinicaMere;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "jedinica-mere", path = "jedinice-mere")
public interface JedinicaMereRepository extends PagingAndSortingRepository<JedinicaMere, String> {
}
