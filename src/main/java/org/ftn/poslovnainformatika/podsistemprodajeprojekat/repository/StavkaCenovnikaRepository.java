package org.ftn.poslovnainformatika.podsistemprodajeprojekat.repository;

import org.ftn.poslovnainformatika.podsistemprodajeprojekat.model.StavkaCenovnika;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "stavka-cenovnika", path = "stavke-cenovnika")
public interface StavkaCenovnikaRepository extends PagingAndSortingRepository<StavkaCenovnika, String> {
}
