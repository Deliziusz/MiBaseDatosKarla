package zombie.deliziusz.mibasedatoskarla;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MiProveedorConteido extends ContentProvider {

    SQLiteDatabase _sqliteDB ;
    Context _ctx;

    // Creación de los Uris
    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        uriMatcher.addURI("zombie.deliziusz.mibasedatoskarla", "contactos", 1);
        uriMatcher.addURI("zombie.deliziusz.mibasedatoskarla", "contactos/#", 2);
        uriMatcher.addURI("zombie.deliziusz.mibasedatoskarla", "contactos/*", 3);
    }

    @Override
    public boolean onCreate() {
        _sqliteDB = new MiDB(this.getContext()).getWritableDatabase();
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String orderby) {
        Cursor c  = null;
        switch (uriMatcher.match(uri)){
            case 1:
                c = _sqliteDB.query(MiDB.TABLE_NAME_CONTACTOS,
                        projection,
                        null,
                        null,
                        null,
                        null, null );
                break;
            case 2:
                c =_sqliteDB.query(MiDB.TABLE_NAME_CONTACTOS ,
                        projection,
                        MiDB.COLUMNS_NAME_CONTACTO[0] + "=?",
                        new String[]{uri.getLastPathSegment()},
                        null,null,null
                );
                break;
        }
        return c;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        String resul = "";

        switch (uriMatcher.match(uri)){
            case 1:
                resul = "vnd.android.cursor.dir/vnd." +
                        "zombie.deliziusz.mibasedatoskarla.contactos";
                break;

            case 2:
                resul = "vnd.android.cursor.item/vnd. " +
                        "zombie.deliziusz.mibasedatoskarla.contactos";
                break;
        }
        return resul;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        Long id  = null;
        switch (uriMatcher.match(uri)){
            case 1:
                id = _sqliteDB.insert(MiDB.TABLE_NAME_CONTACTOS,
                        null, contentValues);
                break;
        }
        return    Uri.parse(uri.toString()
                + "/" +
                String.valueOf(id)) ;

    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        int z=  _sqliteDB.delete(MiDB.TABLE_NAME_CONTACTOS, MiDB.COLUMNS_NAME_CONTACTO[0] + "=?", new String[]{s});
        return z;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        int a=0;
        a =  _sqliteDB.update(MiDB.TABLE_NAME_CONTACTOS,contentValues,MiDB.COLUMNS_NAME_CONTACTO[0] + "=?",new String[]{s});
        return a;
    }
}
