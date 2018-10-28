package com.ftable21.android.weather.data.db.dao;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.ftable21.android.weather.data.db.LocationMsg;

import com.ftable21.android.weather.data.db.dao.LocationMsgDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig locationMsgDaoConfig;

    private final LocationMsgDao locationMsgDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        locationMsgDaoConfig = daoConfigMap.get(LocationMsgDao.class).clone();
        locationMsgDaoConfig.initIdentityScope(type);

        locationMsgDao = new LocationMsgDao(locationMsgDaoConfig, this);

        registerDao(LocationMsg.class, locationMsgDao);
    }
    
    public void clear() {
        locationMsgDaoConfig.clearIdentityScope();
    }

    public LocationMsgDao getLocationMsgDao() {
        return locationMsgDao;
    }

}
