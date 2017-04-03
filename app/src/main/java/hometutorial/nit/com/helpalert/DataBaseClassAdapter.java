package hometutorial.nit.com.helpalert;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static hometutorial.nit.com.helpalert.DataBaseClassAdapter.DataBaseClass.TABLE_NAME;


/**
 * Created by nareshit on 22-Sep-16.
 */
public class DataBaseClassAdapter {
    DataBaseClass dataBaseClass ;
     String personemail,personpwd;

    public  DataBaseClassAdapter(Context context)
    {
         dataBaseClass=new DataBaseClass(context);
    }


    public  long insertData(String FirstName , String LastName, String FullNmae, String Password, String ConfirmPwd,String email)

    {

        SQLiteDatabase sqLiteDatabase=dataBaseClass.getWritableDatabase();

        ContentValues contentValues=new ContentValues();
        contentValues.put(DataBaseClass.FIRSTNAME,FirstName);
        contentValues.put(DataBaseClass.LASTNAME,LastName);
        contentValues.put(DataBaseClass.FULLNAME,FullNmae);

        contentValues.put(DataBaseClass.PWD, Password );
        contentValues.put(DataBaseClass.CPWD, ConfirmPwd  );

        contentValues.put(DataBaseClass.EMAIL,email);

      long id=sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
        return id;

    }
    //for showing data  for main class
    public  boolean isAuthenticated(String email,String password){
        SQLiteDatabase sqLiteDatabase= dataBaseClass.getWritableDatabase();

        String[] columns={DataBaseClass.EMAIL,DataBaseClass.PWD};
       // String[] selectionArgs={email,password};
        Cursor cursor= sqLiteDatabase.query
                (DataBaseClass.TABLE_NAME,columns,"email=? And Password=?",new String[]{email,password},null,null,null) ;

        Boolean f=false;

        if (cursor.moveToNext()) {
            f=true;
        }
        return f;

    }






    //================================================================================
    static class DataBaseClass  extends SQLiteOpenHelper
    {
        private static final String DATABASE_NAME="LOGINDB";
        static final String TABLE_NAME="SINGUP";
        private static final String UID="_id";
        private static final int DATABASE_VERSION=6;
        private static final String FIRSTNAME="FirstName";
        private static final String LASTNAME="LastName";
        private static final String FULLNAME="FullNmae";
        private static final String PWD="Password";
        private static final String CPWD="ConfirmPwd";
        private static final String EMAIL="email";


        private static final String CREATE_TABLE="CREATE TABLE "+TABLE_NAME+"(" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+FIRSTNAME+" VARCHAR(255),"+LASTNAME+" VARCHAR(255),"+FULLNAME+" VARCHAR(255),"+PWD+" VARCHAR(255),"+CPWD+" VARCHAR(255),"+EMAIL+" VARCHAR(255));";

        // private static final String PASSWORD="Password";
        //private static final String DROP_TABLE="DROP TABLE IF EXIST "+TABLE_NAME+"";
        private static final String DROP_TABLE= "DROP TABLE IF EXISTS " + TABLE_NAME;
        private Context context;


        public DataBaseClass(Context context) {
            super(context,DATABASE_NAME, null, DATABASE_VERSION);
            this.context=context;
            //Message.message(context, "constructor is called");
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {


            try  {
                sqLiteDatabase.execSQL(CREATE_TABLE);
               // Message.message(context, "onCreate  is called");
             }
            catch (SQLException e)
            {
                Message.message(context, ""+e);


            }

        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            //DELETE TABLE IF EXIST "P_INFO_TABLE" ;
              //String DROP_TABLE= "DROP TABLE IF EXIST"+TABLE_NAME;

            try  {
                sqLiteDatabase.execSQL(DROP_TABLE);
                onCreate(sqLiteDatabase);
              // Message.message(context, "onUpgrade is called");

            }
            catch (SQLException e)
            {
                Message.message(context, ""+e);
            }

        }
    }

}

