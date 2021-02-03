package org.ftn.poslovnainformatika.podsistemprodajeprojekat.repository;

import org.ftn.poslovnainformatika.podsistemprodajeprojekat.model.PoslovnaGodina;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "poslovna-godina", path = "poslovne-godine")
public interface PoslovnaGodinaRepository extends PagingAndSortingRepository<PoslovnaGodina, String> {
}
