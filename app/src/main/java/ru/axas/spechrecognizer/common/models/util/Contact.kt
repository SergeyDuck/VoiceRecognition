package ru.axas.contacts.common.models.util

import android.annotation.SuppressLint
import android.content.ContentProviderOperation
import android.content.ContentProviderResult
import android.content.ContentResolver
import android.content.ContentUris
import android.content.Context
import android.database.Cursor
import android.provider.Contacts.OrganizationColumns.COMPANY
import android.provider.ContactsContract
import ru.axas.contacts.common.models.logger.LogCustom
import ru.axas.contacts.network.model.Contact


private const val CONTACT_ID = ContactsContract.Contacts._ID
private const val DISPLAY_NAME = ContactsContract.Contacts.DISPLAY_NAME

private const val LAST_NAME = ContactsContract.Contacts.DISPLAY_NAME_ALTERNATIVE

private const val HAS_PHONE_NUMBER = ContactsContract.Contacts.HAS_PHONE_NUMBER
private const val PHONE_NUMBER = ContactsContract.CommonDataKinds.Phone.NUMBER
private const val PHONE_CONTACT_ID = ContactsContract.CommonDataKinds.Phone.CONTACT_ID


@SuppressLint("Range")
fun getContacts(context: Context): List<Contact> {
    val contacts = mutableListOf<Contact>()
    val contentResolver: ContentResolver = context.contentResolver
    val cursor: Cursor? = contentResolver.query(
        ContactsContract.Contacts.CONTENT_URI,
        null,
        null,
        null,
        null
    )

    if (cursor != null && cursor.count > 0) {
        while (cursor.moveToNext()) {
            val contactId =
                cursor.getString(cursor.getColumnIndex(CONTACT_ID)) ?: ""
            val firstname =
                cursor.getString(cursor.getColumnIndex(DISPLAY_NAME)) ?: ""
            val lastName =
                cursor.getString(cursor.getColumnIndex(LAST_NAME)) ?: ""
            val company =
                cursor.getString(cursor.getColumnIndex(COMPANY)) ?: ""

            if (cursor.getInt(cursor.getColumnIndex(HAS_PHONE_NUMBER)) > 0) {
                val phoneCursor: Cursor? = contentResolver.query(
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    null,
                    "$PHONE_CONTACT_ID = ?",
                    arrayOf(contactId),
                    null
                )

                if (phoneCursor != null && phoneCursor.moveToFirst()) {
                    val phoneNumber =
                        phoneCursor.getString(phoneCursor.getColumnIndex(PHONE_NUMBER))

                    val listName = firstname.split(" ")

                    val firstN = listName.getOrNull(0) ?: ""
                    val lastN = listName.getOrNull(1) ?: ""
                    val familyN = listName.getOrNull(2)

                    contacts.add(
                        Contact(
                            phone = phoneNumber,
                            firstName = firstN,
                            lastName = familyN ?: lastN,
                            company = company
                        )
                    )
                    phoneCursor.close()
                }
            }
        }
        cursor.close()
    }

    return contacts
}


fun addContact(
    context: Context,
    list: List<Contact>
) {
    val contentResolver: ContentResolver = context.contentResolver

    list.forEach { contact ->
        // Создаем новую запись контакта
        val ops = ArrayList<ContentProviderOperation>()

        ops.add(
            ContentProviderOperation.newInsert(ContactsContract.RawContacts.CONTENT_URI)
                .withValue(ContactsContract.RawContacts.ACCOUNT_TYPE, null)
                .withValue(ContactsContract.RawContacts.ACCOUNT_NAME, null)
                .build()
        )

        // Добавляем имя
        ops.add(
            ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                .withValue(
                    ContactsContract.Data.MIMETYPE,
                    ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE
                )
                .withValue(
                    ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME,
                    contact.firstName
                )
                .withValue(
                    ContactsContract.CommonDataKinds.StructuredName.FAMILY_NAME,
                    contact.lastName
                )
                .build()
        )

        // Добавляем номер телефона
        ops.add(
            ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                .withValue(
                    ContactsContract.Data.MIMETYPE,
                    ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE
                )
                .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, contact.phone)
                .withValue(
                    ContactsContract.CommonDataKinds.Phone.TYPE,
                    ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE
                )
                .build()
        )

        // Добавляем компанию
        ops.add(
            ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                .withValue(
                    ContactsContract.Data.MIMETYPE,
                    ContactsContract.CommonDataKinds.Organization.CONTENT_ITEM_TYPE
                )
                .withValue(ContactsContract.CommonDataKinds.Organization.COMPANY, contact.company)
                .build()
        )

        try {
            // Применяем операции
            val results: Array<ContentProviderResult> =
                contentResolver.applyBatch(ContactsContract.AUTHORITY, ops)

            // Проверяем результаты
            LogCustom.logD("addContact_${contact.firstName} is ${results.isNotEmpty()}")
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}

@SuppressLint("Range")
fun deleteAllContacts(context: Context) {
    val contentResolver: ContentResolver = context.contentResolver

    val cursor = contentResolver.query(
        ContactsContract.Contacts.CONTENT_URI,
        null,
        null,
        null,
        null
    )

    cursor?.use { contacts ->
        while (contacts.moveToNext()) {
            val id = contacts.getString(contacts.getColumnIndex(ContactsContract.Contacts._ID))
            val deleteUri =
                ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, id.toLong())
            contentResolver.delete(deleteUri, null, null)
        }
    }
}
