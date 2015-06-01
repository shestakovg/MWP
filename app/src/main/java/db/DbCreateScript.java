package db;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by g.shestakov on 26.05.2015.
 */
public class DbCreateScript {
    // Create table
    private static String CREATE_BASEPARAMS="create table IF NOT EXISTS baseParams (ParamId text, paramValue text, PRIMARY KEY(ParamId ASC)) ";


    public static  ArrayList<String> getCreateDataBaseScripts()
    {
        ArrayList<String> list = new ArrayList<String>();
        list.add(CREATE_BASEPARAMS);
        return list;
    }

 //Drop table
    private static String DROP_BASEPARAMS = "DROP TABLE IF EXISTS baseParams";

    public static ArrayList<String>  getDropTableScripts()
    {
        ArrayList<String> list = new ArrayList<String>();
        list.add(DROP_BASEPARAMS);
        return list;
    }
}
