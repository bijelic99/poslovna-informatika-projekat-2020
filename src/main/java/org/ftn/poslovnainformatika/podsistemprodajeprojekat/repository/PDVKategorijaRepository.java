package org.ftn.poslovnainformatika.podsistemprodajeprojekat.repository;

import org.ftn.poslovnainformatika.podsistemprodajeprojekat.model.PDVKategorija;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "pdv-kategorija", path = "pdv-kategorije")
public interface PDVKategorijaRepository extends PagingAndSortingRepository<PDVKategorija, String> {
}
