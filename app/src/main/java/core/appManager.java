package core;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import db.DbOpenHelper;

/**
 * Created by g.shestakov on 26.05.2015.
 */
public class appManager {
    private static appManager ourInstance ; //= new appManager();

    public AppSettings appSetupInstance ;
    public static appManager getOurInstance() {
        if (ourInstance == null)
        {
//            Exception ex = new Exception("appManager not exist");
//
//            throw ex;
        }
        return ourInstance;
    }

    public static appManager getOurInstance(Context context) {
        if (ourInstance == null)
        {
            // Create the instance
            ourInstance = new appManager(context);
        }
        return ourInstance;
    }

    public static void setOurInstance(appManager ourInstance) {
        appManager.ourInstance = ourInstance;
    }

    public Context getCurrentContext() {
        return currentContext;
    }

    public void setCurrentContext(Context currentContext) {
        this.currentContext = currentContext;
    }

    private Context currentContext;

    private appManager() {
        this.appSetupInstance = new AppSettings();

    }

    private appManager(Context context) {
        this.currentContext = context;
        this.appSetupInstance = new AppSettings();
        loadSettingsFromDb();
    }
    private void loadSettingsFromDb()
    {
        this.appSetupInstance.readSetup(getCurrentContext());
    }
//    public static void initInstance()
//    {
//        if (ourInstance == null)
//        {
//            // Create the instance
//            ourInstance = new appManager();
//        }
//    }
}
