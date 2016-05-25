package yaroslavromanyuta.com.ua.weatherforecast.application;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import yaroslavromanyuta.com.ua.weatherforecast.forecast.greenDaoModel.DaoMaster;
import yaroslavromanyuta.com.ua.weatherforecast.forecast.greenDaoModel.DaoSession;

/**
 * Created by Yaroslav on 25.05.2016.
 */
public class ApplicationWithDaoSession extends Application {

    DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this,"lisosiky-db",null);
        SQLiteDatabase database = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(database);
        daoSession = daoMaster.newSession();
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }
}
