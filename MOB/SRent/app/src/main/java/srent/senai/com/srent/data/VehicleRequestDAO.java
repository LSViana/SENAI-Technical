package srent.senai.com.srent.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import srent.senai.com.srent.models.Van;
import srent.senai.com.srent.models.VehicleRequest;
import srent.senai.com.srent.models.VehicleType;

public class VehicleRequestDAO extends SQLiteOpenHelper {

    public static String DATE_PATTERN = "dd/MM/yyyy";
    private static SimpleDateFormat sdf = new SimpleDateFormat(VehicleRequestDAO.DATE_PATTERN);

    // It must be static for it to be initialized with the class and not the instance
    private static final String DB_NAME = "srent";
    private static final Integer DB_VERSION = 3;
    private static final String TABLE_NAME = "vehiclerequest";

    private BusDAO busDAO;
    private VanDAO vanDAO;

    public VehicleRequestDAO(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        //
        busDAO = new BusDAO(context);
        vanDAO = new VanDAO(context);
//        onUpgrade(getWritableDatabase(), 2, 3);
//        onCreate(getWritableDatabase());
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = String.format("CREATE TABLE %s (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "vehicleType TEXT NOT NULL, " +
                "vehicleId NUMBER NOT NULL, " +
                "startDate TEXT NOT NULL, " +
                "endDate TEXT NOT NULL, " +
                "passengerAmount NUMBER NOT NULL, " +
                "originAddress TEXT NOT NULL, " +
                "destinyAddress TEXT NOT NULL, " +
                "includesDriver NUMBER NOT NULL" +
                ");", TABLE_NAME);
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sql = String.format("DROP TABLE IF EXISTS %s", TABLE_NAME);
        sqLiteDatabase.execSQL(sql);
    }

    public void insert(VehicleRequest obj) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        //
        cv.put("vehicleType", obj.getVehicleType().toString().toUpperCase());
        cv.put("vehicleId", obj.getVehicle().getId());
        cv.put("startDate", sdf.format(obj.getStartDate()));
        cv.put("endDate", sdf.format(obj.getEndDate()));
        cv.put("passengerAmount", obj.getPassengerAmount());
        cv.put("originAddress", obj.getOriginAddress());
        cv.put("destinyAddress", obj.getDestinyAddress());
        cv.put("includesDriver", obj.getIncludesDriver() ? 1 : 0);
        //
        db.insert(TABLE_NAME, null, cv);
    }

    public void delete (Long id) {
        SQLiteDatabase db = getWritableDatabase();
        //
        db.delete(TABLE_NAME, "id = ?", new String[] { id.toString() });
    }

    public void update(VehicleRequest obj) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        //
        cv.put("vehicleType", obj.getVehicleType().toString().toUpperCase());
        cv.put("vehicleId", obj.getVehicle().getId());
        cv.put("startDate", sdf.format(obj.getStartDate()));
        cv.put("endDate", sdf.format(obj.getEndDate()));
        cv.put("passengerAmount", obj.getPassengerAmount());
        cv.put("originAddress", obj.getOriginAddress());
        cv.put("destinyAddress", obj.getDestinyAddress());
        cv.put("includesDriver", obj.getIncludesDriver() ? 1 : 0);
        //
        db.update(TABLE_NAME, cv, "id = ?", new String[] { obj.getId().toString() });
    }

    public List<VehicleRequest> searchAll() {
        String sql = String.format("SELECT id, vehicleType, vehicleId, startDate, endDate, passengerAmount, originAddress, destinyAddress, includesDriver FROM %s", TABLE_NAME);
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);
        List<VehicleRequest> result = new ArrayList<>();
        //
        while(c.moveToNext()) {
            int columnIndex = 0;
            VehicleRequest vr = new VehicleRequest();
            vr.setId(c.getLong(columnIndex++));
            VehicleType vt = VehicleType.valueOf(c.getString(columnIndex++));
            vr.setVehicleType(vt);
            switch (vt) {
                case BUS:
                    vr.setVehicle(busDAO.search(c.getLong(columnIndex++)));
                    break;
                case VAN:
                    vr.setVehicle(vanDAO.search(c.getLong(columnIndex++)));
                    break;
            }
            try {
                vr.setStartDate(sdf.parse(c.getString(columnIndex++)));
                vr.setEndDate(sdf.parse(c.getString(columnIndex++)));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            vr.setPassengerAmount(c.getInt(columnIndex++));
            vr.setOriginAddress(c.getString(columnIndex++));
            vr.setDestinyAddress(c.getString(columnIndex++));
            vr.setIncludesDriver(c.getInt(columnIndex++) == 0 ? false : true);
            //
            result.add(vr);
        }
        //
        return result;
    }


}
