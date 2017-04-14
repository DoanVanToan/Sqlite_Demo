package toandoan.framgia.com.android_12_menu_searchview_dialog.data.local;

import android.provider.BaseColumns;

/**
 * Created by framgia on 14/04/2017.
 */

public class ContactContract {
    public ContactContract() {
    }

    public static class ContactEntry implements BaseColumns {
        public static final String TABLE_NAME = "tbl_contact";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_PHONE_NUMBER = "phone";
        public static final String COLUMN_ADDRESS = "address";
    }
}

