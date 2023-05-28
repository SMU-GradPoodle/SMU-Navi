package smu.poodle.smnavi.map.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import smu.poodle.smnavi.map.domain.station.BusStation;
import smu.poodle.smnavi.map.domain.station.SubwayStation;
import smu.poodle.smnavi.map.domain.station.Waypoint;

import java.util.List;
import java.util.Optional;

public interface SubwayStationRepository extends JpaRepository<SubwayStation, Long> {

    Optional<SubwayStation> findFirstByLineNameAndStationId(String lineName, Integer stationId);

//    @EntityGraph(attributePaths = {"fullPat/**/hs", "fullPaths.subPaths", "fullPaths.subPaths.subPath"})
    List<Waypoint> findAllByStationId(Integer stationId);
}