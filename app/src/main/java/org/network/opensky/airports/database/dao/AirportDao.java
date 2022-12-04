package org.network.opensky.airports.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import org.network.opensky.airports.database.entities.AirportEntity;

import java.util.List;

@Dao
public interface AirportDao {
    @Query("SELECT * FROM airports")
    LiveData<List<AirportEntity>> loadAllAirports();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<AirportEntity> airports);

    @Query("select * from airports where id = :airportId")
    LiveData<AirportEntity> loadAirport(int airportId);

    @Query("select * from airports where id = :airportId")
    AirportEntity loadAirportSync(int airportId);

    @Query("select * from airports where icao = :icao")
    LiveData<AirportEntity> loadAirportByICAO(String icao);

    @Query("SELECT airports.* FROM airports JOIN airportsFts ON (airports.id = airportsFts.rowid) "
            + "WHERE airportsFts MATCH :query")
    LiveData<List<AirportEntity>> searchAllAirports(String query);

}