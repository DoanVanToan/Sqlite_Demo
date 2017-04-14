package toandoan.framgia.com.android_12_menu_searchview_dialog.data;

import android.content.Context;
import java.util.List;
import toandoan.framgia.com.android_12_menu_searchview_dialog.data.local.ContactLocalDataSource;
import toandoan.framgia.com.android_12_menu_searchview_dialog.data.model.Contact;

/**
 * Created by framgia on 14/04/2017.
 */

public class ContactRepository implements ContactDataSource {
    private static ContactRepository sRepository;
    private ContactDataSource mLocalDataSource;

    public static ContactRepository getInstance(Context context) {
        if (sRepository == null) {
            sRepository = new ContactRepository(ContactLocalDataSource.getInstance(context));
        }
        return sRepository;
    }

    public ContactRepository(ContactDataSource localDataSource) {
        mLocalDataSource = localDataSource;
    }

    @Override
    public boolean insertContact(Contact contact) {
        return mLocalDataSource.insertContact(contact);
    }

    @Override
    public List<Contact> getContact() {
        return mLocalDataSource.getContact();
    }

    @Override
    public Contact getContactById(int id) {
        return mLocalDataSource.getContactById(id);
    }

    @Override
    public boolean updateContact(Contact contact) {
        return mLocalDataSource.updateContact(contact);
    }

    @Override
    public List<Contact> getContactByName(String name) {
        return mLocalDataSource.getContactByName(name);
    }
}
