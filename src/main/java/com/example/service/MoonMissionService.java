package com.example.service;

import com.example.model.MoonMission;
import com.example.repository.MoonMissionRepository;
import com.example.repository.RepositoryException;

import java.util.List;
import java.util.Optional;

/**
 * Service layer for managing moon missions. Wraps MoonMissionRepository
 * and handles exceptions by throwing RepositoryException.
 */
public class MoonMissionService {
    private final MoonMissionRepository repo;

    /**
     * Constructs the service with a given repository.
     *
     * @param repo the {@link MoonMissionRepository} to use for data access
     */
    public MoonMissionService(MoonMissionRepository repo) {
        this.repo = repo;
    }

    /**
     * Lists all moon missions in the repository.
     *
     * @return a {@link List} of {@link MoonMission} objects
     * @throws RepositoryException if an error occurs while retrieving missions
     */
    public List<MoonMission> listMissions() {
        try {
            return repo.listMissions();
        } catch (Exception e) {
            throw new RepositoryException("Error listing missions", e);
        }
    }

    /**
     * Retrieves a moon mission by its ID.
     *
     * @param id the ID of the mission to fetch
     * @return an {@link Optional} containing the {@link MoonMission} if found, otherwise empty
     * @throws RepositoryException if an error occurs while fetching the mission
     */
    public Optional<MoonMission> getMissionById(int id) {
        try {
            return repo.getMissionById(id);
        } catch (Exception e) {
            throw new RepositoryException("Error fetching mission by ID", e);
        }
    }

    /**
     * Counts the number of moon missions that occurred in a specific year.
     *
     * @param year the year to count missions for
     * @return the number of missions in the given year
     * @throws RepositoryException if an error occurs while counting missions
     */
    public int countMissionsByYear(int year) {
        try {
            return repo.countMissionsByYear(year);
        } catch (Exception e) {
            throw new RepositoryException("Error counting missions by year", e);
        }
    }
}