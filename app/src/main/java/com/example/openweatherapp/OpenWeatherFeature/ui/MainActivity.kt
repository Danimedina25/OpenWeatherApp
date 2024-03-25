package com.example.openweatherapp.OpenWeatherFeature.ui

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import com.example.openweatherapp.OpenWeatherFeature.domain.model.OpenWeather
import com.example.openweatherapp.OpenWeatherFeature.domain.util.Constants
import com.example.openweatherapp.R
import com.example.openweatherapp.databinding.ActivityMainBinding
import com.google.android.gms.common.api.Status
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.AutocompleteSupportFragment
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), OnMapReadyCallback{

    private lateinit var binding: ActivityMainBinding
    private val mainActivityViewModel :MainActivityViewModel by viewModels()
    private var mGoogleMap: GoogleMap? = null
    private lateinit var lastLocation: Location
    private lateinit var fusedLocationClient:FusedLocationProviderClient
    private lateinit var autocompleteFragment: AutocompleteSupportFragment
    private lateinit var currentLocationPlace: LatLng

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        supportActionBar!!.setTitle("Consulta el clima de tu ciudad")
        val view = binding.root
        setContentView(view)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        //initialize and show map
        val mapFragment = supportFragmentManager.findFragmentById(binding.fragmentContainer.id) as SupportMapFragment
        mapFragment.getMapAsync(this)

        setAutocompleteSearchOnMaps()
        setupObservers()

        binding.buttonConsultarClima.setOnClickListener {
            getDataWeatherFromPlace()
        }
    }

    private fun setAutocompleteSearchOnMaps(){
        Places.initialize(applicationContext, getString(R.string.google_maps_api_key))
        autocompleteFragment = supportFragmentManager.findFragmentById(binding.autoComplete.id) as AutocompleteSupportFragment
        autocompleteFragment.setPlaceFields(listOf(Place.Field.ID, Place.Field.ADDRESS, Place.Field.LAT_LNG))
        autocompleteFragment.setOnPlaceSelectedListener(object: PlaceSelectionListener{
            override fun onError(status: Status) {
                when(status) {
                    Status.RESULT_CANCELED -> binding.buttonConsultarClima.visibility = View.GONE
                    else -> Toast.makeText(
                        applicationContext,
                        "Ocurrió un error en la búsqueda ${status.status.toString()}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onPlaceSelected(place: Place) {
                val latLng = place.latLng!!
                zoomOnMap(latLng)
                currentLocationPlace = latLng
                binding.cardviewInfoWeather.visibility = View.GONE
                binding.buttonConsultarClima.visibility = View.VISIBLE
            }
        })
    }

    private fun setupObservers(){
        mainActivityViewModel.openWeatherResult.observe(this, Observer {
            Log.d("result", Gson().toJson(it))
            showDataWeather(it)
        })
        mainActivityViewModel.openWeatherErrorMessage.observe(this, Observer {
            Log.d("result", Gson().toJson(it))
            binding.cardviewInfoWeather.visibility = View.GONE
            Toast.makeText(applicationContext, it.toString(), Toast.LENGTH_SHORT).show()
        })

        mainActivityViewModel.isLoading.observe(this, Observer {
            if (it)
                binding.loading.visibility = View.VISIBLE
            else
                binding.loading.visibility = View.GONE
        })
    }

    private fun getDataWeatherFromPlace(){
        mainActivityViewModel.getWeatherByPlace(currentLocationPlace.latitude, currentLocationPlace.longitude)
    }

    @SuppressLint("SetTextI18n")
    private fun showDataWeather(openWeather: OpenWeather){
        binding.cardviewInfoWeather.visibility = View.VISIBLE
        binding.buttonConsultarClima.visibility = View.GONE
        binding.tvNamePlace.text = "${openWeather.name}, ${openWeather.sys.country}"
        binding.tvLatLng.text = "${openWeather.coord.lat}, ${openWeather.coord.lon}"
        val temp = convertKelvinToCelsius(openWeather.main.temp).toString().substringBefore(".") + "°C"
        binding.tvTemperatura.text = temp
        binding.tvHumedad.text = "humedad: ${openWeather.main.humidity}%"
    }

    private fun convertKelvinToCelsius(temp: Double): Double{
        return temp-273.15
    }

    private fun zoomOnMap(latLng: LatLng){
        val newLatLngZoom = CameraUpdateFactory.newLatLngZoom(latLng, 12f)
        mGoogleMap?.animateCamera(newLatLngZoom)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mGoogleMap = googleMap
        mGoogleMap!!.uiSettings.isZoomControlsEnabled = true
        setupMap()
    }

    private fun setupMap(){
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION), Constants.LOCATION_REQUEST_CODE)
            return
        }
        mGoogleMap!!.isMyLocationEnabled = true
        fusedLocationClient.lastLocation.addOnSuccessListener(this) { location ->
            if(location != null){
                lastLocation = location
                val currentLatLong = LatLng(location.latitude, location.longitude)
                mGoogleMap!!.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLong, 12f))
            }
        }
    }
}