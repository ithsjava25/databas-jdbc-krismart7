package com.example;

import java.util.List;
import java.util.Optional;

public interface MoonMissionRepository {
    List<MoonMission> findAll();
    Optional<MoonMission> findById(long missionId);
    long create(MoonMission mission);
    boolean updateSpacecraft(long missionId, String newSpacecraft);
    boolean delete(long missionId);
}
