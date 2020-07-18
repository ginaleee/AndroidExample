package com.example.example

import android.os.Bundle
import android.util.Log
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.common.api.Status
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.model.TypeFilter
import com.google.android.libraries.places.api.net.PlacesClient
import com.google.android.libraries.places.widget.AutocompleteSupportFragment
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener

class AddressActivity : AppCompatActivity() {
    companion object {
        private const val API_KEY = ""
        private const val TAG = "AddressActivity"
    }

    private lateinit var placesClient: PlacesClient
    private var streetNumber: String = ""
    private var route: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_address)
        if (!Places.isInitialized()) {
            Places.initialize(applicationContext, API_KEY)
        }

        placesClient = Places.createClient(this)

        setUi()
    }

    private fun setUi() {
        val autocompleteFragment = supportFragmentManager.findFragmentById(R.id.autocomplete_fragment) as AutocompleteSupportFragment?
        autocompleteFragment?.setTypeFilter(TypeFilter.ADDRESS)
        autocompleteFragment?.setPlaceFields(listOf(Place.Field.ID, Place.Field.NAME, Place.Field.ADDRESS_COMPONENTS))
        autocompleteFragment?.setOnPlaceSelectedListener(object : PlaceSelectionListener {
            override fun onPlaceSelected(place: Place) {
                findViewById<EditText>(R.id.street_edit_text).setText(place.address)
                place.addressComponents?.asList()?.forEach {
                    Log.i(TAG, "type: " + it.types)
                    if (it.types.contains("street_number")) {
                        streetNumber = it.name
                    } else if (it.types.contains("route")) {
                        route = it.name
                    } else if (it.types.contains("locality")) {
                        findViewById<EditText>(R.id.city_edit_text).setText(it.name)
                    } else if (it.types.contains("postal_code")) {
                        findViewById<EditText>(R.id.zip_code_edit_text).setText(it.name)
                    }
                }

                findViewById<EditText>(R.id.street_edit_text).setText("$streetNumber $route")
            }

            override fun onError(status: Status) {
                Log.i(TAG, "An error occurred: $status")
            }
        })
    }
}
