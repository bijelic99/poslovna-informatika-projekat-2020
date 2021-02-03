package org.ftn.poslovnainformatika.podsistemprodajeprojekat.repository;

import org.ftn.poslovnainformatika.podsistemprodajeprojekat.model.StavkaFakture;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "stavka-fakture", path = "stavke-fakture")
public interface StavkaFaktureRepository extends PagingAndSortingRepository<StavkaFakture, String> {
}
