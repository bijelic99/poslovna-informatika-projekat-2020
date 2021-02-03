package org.ftn.poslovnainformatika.podsistemprodajeprojekat.repository;

import org.ftn.poslovnainformatika.podsistemprodajeprojekat.model.GrupaRobeIliUsluge;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "grupa-robe-ili-usluge", path = "grupe-robe-ili-usluge")
public interface GrupaRobeIliUslugeRepository extends PagingAndSortingRepository<GrupaRobeIliUsluge, String> {

}
