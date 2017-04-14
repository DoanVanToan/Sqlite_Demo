package toandoan.framgia.com.android_12_menu_searchview_dialog.data.local;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;
import toandoan.framgia.com.android_12_menu_searchview_dialog.data.ContactDataSource;
import toandoan.framgia.com.android_12_menu_searchview_dialog.data.model.Contact;

/**
 * Created by framgia on 14/04/2017.
 */

public class ContactLocalDataSource extends DatabaseHelper implements ContactDataSource {
    private static ContactDataSource sSource;

    public static ContactDataSource getInstance(Context context) {
        if (sSource == null) {
            sSource = new ContactLocalDataSource(context);
        }
        return sSource;
    }

    public ContactLocalDataSource(Context context) {
        super(context);
    }

    @Override
    public boolean insertContact(Contact contact) {
        if (contact == null) return false;
        SQLiteDatabase db = getWritableDatabase();
        long result = db.insert(ContactContract.ContactEntry.TABLE_NAME, null,
                contact.getContentValues());
        return result != -1;
    }

    @Override
    public List<Contact> getContact() {
        List<Contact> contacts = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                ContactContract.ContactEntry._ID, ContactContract.ContactEntry.COLUMN_NAME,
                ContactContract.ContactEntry.COLUMN_PHONE_NUMBER,
                ContactContract.ContactEntry.COLUMN_ADDRESS
        };

        Cursor cursor = db.query(ContactContract.ContactEntry.TABLE_NAME,// The table to query
                projection,                                 // The columns to return
                null,                                       // The columns for the WHERE clause
                null,                                       // The values for the WHERE clause
                null,                                       // don't group the rows
                null,                                       // don't filter by row groups
                null                                        // The sort order
        );
        if (cursor != null && cursor.moveToFirst()) {
            contacts = new ArrayList<>();
            do {
                contacts.add(new Contact(cursor));
            } while (cursor.moveToNext());
        }
        if (cursor != null) {
            cursor.close();
        }
        db.close();
        return contacts;
    }

    @Override
    public Contact getContactById(int id) {
        Contact contact = null;
        SQLiteDatabase db = getReadableDatabase();
        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                ContactContract.ContactEntry._ID, ContactContract.ContactEntry.COLUMN_NAME,
                ContactContract.ContactEntry.COLUMN_PHONE_NUMBER,
                ContactContract.ContactEntry.COLUMN_ADDRESS
        };

        String selection = ContactContract.ContactEntry._ID + "=?";
        String[] selectionArgs = { String.valueOf(id) };

        Cursor cursor = db.query(ContactContract.ContactEntry.TABLE_NAME,// The table to query
                projection,                                 // The columns to return
                selection,                                  // The columns for the WHERE clause
                selectionArgs,                              // The values for the WHERE clause
                null,                                       // don't group the rows
                null,                                       // don't filter by row groups
                null                                        // The sort order
        );
        if (cursor != null && cursor.moveToFirst()) {
            contact = new Contact(cursor);
        }
        if (cursor != null) {
            cursor.close();
        }
        db.close();
        return contact;
    }

    @Override
    public boolean updateContact(Contact contact) {
        if (contact == null) return false;
        SQLiteDatabase db = getWritableDatabase();
        String whereClause = ContactContract.ContactEntry._ID + "=?";
        String[] whereArgs = { String.valueOf(contact.getID()) };
        long result = db.update(ContactContract.ContactEntry.TABLE_NAME, contact.getContentValues(),
                whereClause, whereArgs);
        return result != -1;
    }

    @Override
    public List<Contact> getContactByName(String name) {
        List<Contact> contacts = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                ContactContract.ContactEntry._ID, ContactContract.ContactEntry.COLUMN_NAME,
                ContactContract.ContactEntry.COLUMN_PHONE_NUMBER,
                ContactContract.ContactEntry.COLUMN_ADDRESS
        };

        String whereClause = ContactContract.ContactEntry.COLUMN_NAME + " LIKE ?";
        String[] whereArgs = { name };

        Cursor cursor = db.query(ContactContract.ContactEntry.TABLE_NAME,// The table to query
                projection,                                 // The columns to return
                whereClause,                                       // The columns for the WHERE clause
                whereArgs,                                       // The values for the WHERE clause
                null,                                       // don't group the rows
                null,                                       // don't filter by row groups
                null                                        // The sort order
        );
        if (cursor != null && cursor.moveToFirst()) {
            contacts = new ArrayList<>();
            do {
                contacts.add(new Contact(cursor));
            } while (cursor.moveToNext());
        }
        if (cursor != null) {
            cursor.close();
        }
        db.close();
        return contacts;
    }
}
