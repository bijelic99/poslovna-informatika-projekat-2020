package org.ftn.poslovnainformatika.podsistemprodajeprojekat.repository;

import org.ftn.poslovnainformatika.podsistemprodajeprojekat.model.PDVStopa;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "pdv-stopa", path = "pdv-stope")
public interface PDVStopaRepository extends PagingAndSortingRepository<PDVStopa, String> {
}
