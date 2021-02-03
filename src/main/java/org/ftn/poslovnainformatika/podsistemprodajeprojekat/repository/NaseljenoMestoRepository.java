package org.ftn.poslovnainformatika.podsistemprodajeprojekat.repository;

import org.ftn.poslovnainformatika.podsistemprodajeprojekat.model.NaseljenoMesto;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "naseljeno-mesto", path = "naseljena-mesta")
public interface NaseljenoMestoRepository extends PagingAndSortingRepository<NaseljenoMesto, String> {
}
