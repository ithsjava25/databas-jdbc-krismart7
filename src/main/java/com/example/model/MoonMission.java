package com.example.model;

import java.sql.Date;

public record MoonMission(
        int missionId,
        String spacecraft,
        Date launchDate,
        String carrierRocket,
        String operator,
        String missionType,
        String outcome
) {

    @Override
    public String toString() {
        return "MoonMission: " +
                "missionId: " + missionId +
                ", spacecraft: " + spacecraft +
                ", launchDate: " + launchDate +
                ", carrierRocket: " + carrierRocket +
                ", operator: " + operator +
                ", missionType: " + missionType +
                ", outcome: " + outcome;
    }
}
