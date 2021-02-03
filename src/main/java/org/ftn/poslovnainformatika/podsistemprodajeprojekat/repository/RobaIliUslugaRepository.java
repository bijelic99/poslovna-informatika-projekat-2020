package org.ftn.poslovnainformatika.podsistemprodajeprojekat.repository;

import org.ftn.poslovnainformatika.podsistemprodajeprojekat.model.RobaIliUsluga;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "roba-ili-usluga", path = "roba-ili-usluge")
public interface RobaIliUslugaRepository extends PagingAndSortingRepository<RobaIliUsluga, String> {
}
