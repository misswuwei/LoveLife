package Date;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import Date.info;

import Date.infoDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig infoDaoConfig;

    private final infoDao infoDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        infoDaoConfig = daoConfigMap.get(infoDao.class).clone();
        infoDaoConfig.initIdentityScope(type);

        infoDao = new infoDao(infoDaoConfig, this);

        registerDao(info.class, infoDao);
    }
    
    public void clear() {
        infoDaoConfig.clearIdentityScope();
    }

    public infoDao getInfoDao() {
        return infoDao;
    }

}
