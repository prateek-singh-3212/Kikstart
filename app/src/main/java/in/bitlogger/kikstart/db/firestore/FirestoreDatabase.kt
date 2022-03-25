package `in`.bitlogger.kikstart.db.firestore

import `in`.bitlogger.kikstart.interfaces.CoroutineDataPassCallbacks
import com.google.firebase.firestore.FirebaseFirestore
import timber.log.Timber

object FirestoreDatabase {

    /**
     * Adds the NEW data to firestore.
     * @param documentURI It should be in even multiple.
     * @param data Data you want add in database.
     * CHECK THE INTERNET ACCESS BEFORE CALLING.
     * */
    fun addDataToFirestore(documentURI: String, data: Map<String, Any>) {
        FirebaseFirestore.getInstance()
            .document(documentURI)
            .set(data)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    Timber.d("Data added to firestore successfully")
                }else {
                    Timber.d("Failed to add data to firestore. ${it.exception}")
                }
            }
    }

    /**
     * UPDATES the data to firestore.
     * @param documentURI It should be in even multiple.
     * @param data Data you want update in database.
     * CHECK THE INTERNET ACCESS BEFORE CALLING.
     * */
    fun updateDataToFirestore(documentURI: String, data: Map<String, Any>) {
        FirebaseFirestore.getInstance()
            .document(documentURI)
            .update(data)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    Timber.d("Data updated to firestore successfully")
                }else {
                    Timber.d("Failed to update data to firestore. ${it.exception}")
                }
            }
    }

    fun getDataFromFirestore(documentURI: String, callbacks: CoroutineDataPassCallbacks) {
        callbacks.isDataLoading(true)

        FirebaseFirestore.getInstance()
            .document(documentURI)
            .get()
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    callbacks.isDataLoading(false)
                    callbacks.onLoadComplete(it.result.data)
                } else {
                    callbacks.isDataLoading(false)
                    callbacks.onLoadFailed("404", "Unable to fetch data.")
                }
            }
    }
}