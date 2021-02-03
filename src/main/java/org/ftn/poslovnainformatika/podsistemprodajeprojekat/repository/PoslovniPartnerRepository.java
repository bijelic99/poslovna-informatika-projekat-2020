package org.ftn.poslovnainformatika.podsistemprodajeprojekat.repository;

import org.ftn.poslovnainformatika.podsistemprodajeprojekat.model.PoslovnaGodina;
import org.ftn.poslovnainformatika.podsistemprodajeprojekat.model.PoslovniPartner;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "poslovni-partner", path = "poslovni-partneri")
public interface PoslovniPartnerRepository extends PagingAndSortingRepository<PoslovniPartner, String> {
}
