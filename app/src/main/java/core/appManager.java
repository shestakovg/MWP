package core;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import db.DbOpenHelper;

/**
 * Created by g.shestakov on 26.05.2015.
 */
public class appManager {
    private static appManager ourInstance ; //= new appManager();

    private AppSettings appSetupInstance ;
    public static appManager getOurInstance() {
        if (ourInstance == null)
        {
            // Create the instance
            ourInstance = new appManager();
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
        this.appSetupInstance = new AppSettings();
        this.currentContext = context;
        loadSettingsFromDb();
    }
    private void loadSettingsFromDb()
    {
        DbOpenHelper dbOpenHelper = new DbOpenHelper(this.currentContext);
        SQLiteDatabase db= dbOpenHelper.getReadableDatabase();

        db.close();
    }
    public static void initInstance()
    {
        if (ourInstance == null)
        {
            // Create the instance
            ourInstance = new appManager();
        }
    }
}
