package com.ftable21.android.weather.data.db.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.ftable21.android.weather.data.db.LocationMsg;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "LOCATION_MSG".
*/
public class LocationMsgDao extends AbstractDao<LocationMsg, Void> {

    public static final String TABLENAME = "LOCATION_MSG";

    /**
     * Properties of entity LocationMsg.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Addr = new Property(0, String.class, "addr", false, "ADDR");
        public final static Property Country = new Property(1, String.class, "country", false, "COUNTRY");
        public final static Property Province = new Property(2, String.class, "province", false, "PROVINCE");
        public final static Property City = new Property(3, String.class, "city", false, "CITY");
        public final static Property District = new Property(4, String.class, "district", false, "DISTRICT");
        public final static Property Street = new Property(5, String.class, "street", false, "STREET");
    }


    public LocationMsgDao(DaoConfig config) {
        super(config);
    }
    
    public LocationMsgDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"LOCATION_MSG\" (" + //
                "\"ADDR\" TEXT," + // 0: addr
                "\"COUNTRY\" TEXT," + // 1: country
                "\"PROVINCE\" TEXT," + // 2: province
                "\"CITY\" TEXT," + // 3: city
                "\"DISTRICT\" TEXT," + // 4: district
                "\"STREET\" TEXT);"); // 5: street
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"LOCATION_MSG\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, LocationMsg entity) {
        stmt.clearBindings();
 
        String addr = entity.getAddr();
        if (addr != null) {
            stmt.bindString(1, addr);
        }
 
        String country = entity.getCountry();
        if (country != null) {
            stmt.bindString(2, country);
        }
 
        String province = entity.getProvince();
        if (province != null) {
            stmt.bindString(3, province);
        }
 
        String city = entity.getCity();
        if (city != null) {
            stmt.bindString(4, city);
        }
 
        String district = entity.getDistrict();
        if (district != null) {
            stmt.bindString(5, district);
        }
 
        String street = entity.getStreet();
        if (street != null) {
            stmt.bindString(6, street);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, LocationMsg entity) {
        stmt.clearBindings();
 
        String addr = entity.getAddr();
        if (addr != null) {
            stmt.bindString(1, addr);
        }
 
        String country = entity.getCountry();
        if (country != null) {
            stmt.bindString(2, country);
        }
 
        String province = entity.getProvince();
        if (province != null) {
            stmt.bindString(3, province);
        }
 
        String city = entity.getCity();
        if (city != null) {
            stmt.bindString(4, city);
        }
 
        String district = entity.getDistrict();
        if (district != null) {
            stmt.bindString(5, district);
        }
 
        String street = entity.getStreet();
        if (street != null) {
            stmt.bindString(6, street);
        }
    }

    @Override
    public Void readKey(Cursor cursor, int offset) {
        return null;
    }    

    @Override
    public LocationMsg readEntity(Cursor cursor, int offset) {
        LocationMsg entity = new LocationMsg( //
            cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0), // addr
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // country
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // province
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // city
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // district
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5) // street
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, LocationMsg entity, int offset) {
        entity.setAddr(cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0));
        entity.setCountry(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setProvince(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setCity(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setDistrict(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setStreet(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
     }
    
    @Override
    protected final Void updateKeyAfterInsert(LocationMsg entity, long rowId) {
        // Unsupported or missing PK type
        return null;
    }
    
    @Override
    public Void getKey(LocationMsg entity) {
        return null;
    }

    @Override
    public boolean hasKey(LocationMsg entity) {
        // TODO
        return false;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
