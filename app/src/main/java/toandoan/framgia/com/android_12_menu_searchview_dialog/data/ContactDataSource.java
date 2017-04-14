package toandoan.framgia.com.android_12_menu_searchview_dialog.data;

import java.util.List;
import toandoan.framgia.com.android_12_menu_searchview_dialog.data.model.Contact;

/**
 * Created by framgia on 14/04/2017.
 */

public interface ContactDataSource {
    boolean insertContact(Contact contact);

    List<Contact> getContact();

    Contact getContactById(int id);

    boolean updateContact(Contact contact);

    List<Contact> getContactByName(String name);
}
