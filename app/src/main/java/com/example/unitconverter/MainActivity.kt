package com.example.unitconverter

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        val spinnerType: Spinner = findViewById(R.id.spinnerType)
        val conversionTypes = arrayOf("Length", "Weight", "Volumn", "Speed", "Temperature", "Area")
        val spinnerFrom: Spinner = findViewById(R.id.spinnerFrom)
        val spinnerTo: Spinner = findViewById(R.id.spinnerTo)

        val etFrom: EditText = findViewById(R.id.etFrom)
        val textTo: TextView = findViewById(R.id.textTo)

        var from = ""
        var to = ""

        val fromTypesLength = arrayOf("km", "miles", "yard", "m", "feets", "inch", "cm", "mm")
        val fromTypesWeights = arrayOf("tons", "quintal", "kg", "pounds", "gm", "mg")
        val fromTypesVolumn = arrayOf("L", "gallons", "ml")
        val fromTypesSpeed = arrayOf("km/hr", "knots", "miles/hr", "km/sec", "m/sec", "m/hr")
        val fromTypesTemperature = arrayOf("Celcius", "Fahrenheit", "Kelvin")
        val fromTypesArea = arrayOf(
            "km sq.",
            "mile sq.",
            "yard sq.",
            "feet sq.",
            "m sq.",
            "inch sq.",
            "cm sq.",
            "mm sq."
        )


        val toTypesLengths = arrayOf("km", "miles", "yard", "m", "feets", "inch", "cm", "mm")
        val toTypesWeights = arrayOf("tons", "quintal", "kg", "pounds", "gm", "mg")
        val toTypesVolumn = arrayOf("L", "gallons", "ml")
        val toTypesSpeed = arrayOf("km/hr", "knots", "miles/hr", "km/sec", "m/sec", "m/hr")
        val toTypesTemperature = arrayOf("Celcius", "Fahrenheit", "Kelvin")
        val toTypesArea = arrayOf(
            "km sq.",
            "mile sq.",
            "yard sq.",
            "feet sq.",
            "m sq.",
            "inch sq.",
            "cm sq.",
            "mm sq."
        )

        var finalFromType = arrayOf<String>()
        var finalToType = arrayOf<String>()

        //Implementing adapter on Conversion Type Spinner
        val arrayAdpType =
            ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, conversionTypes)
        spinnerType.adapter = arrayAdpType
        spinnerType.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                when (conversionTypes[position]) {
                    "Length" -> {
                        finalFromType =
                            fromTypesLength.copyOf()      // copyOf() is used to copy the elements of one array into another
                        finalToType = toTypesLengths.copyOf()
                    }
                    "Speed" -> {
                        finalFromType = fromTypesSpeed.copyOf()
                        finalToType = toTypesSpeed.copyOf()

                    }
                    "Volumn" -> {
                        finalFromType = fromTypesVolumn.copyOf()
                        finalToType = toTypesVolumn.copyOf()

                    }
                    "Weight" -> {
                        finalFromType = fromTypesWeights.copyOf()
                        finalToType = toTypesWeights.copyOf()

                    }
                    "Temperature" -> {
                        finalFromType = fromTypesTemperature.copyOf()
                        finalToType = fromTypesTemperature.copyOf()
                    }
                    "Area" -> {
                        finalFromType = fromTypesArea.copyOf()
                        finalToType = fromTypesArea.copyOf()
                    }
                }

                //Implementing adapter on From Type Spinner
                val arrayAdpFrom = ArrayAdapter(
                    this@MainActivity,
                    android.R.layout.simple_spinner_dropdown_item,
                    finalFromType
                )
                spinnerFrom.adapter = arrayAdpFrom
                spinnerFrom.setSelection(0, false) // Set the selection to the first item initially

                //Implementing adapter on To Type Spinner
                val arrayAdpTo = ArrayAdapter(
                    this@MainActivity,
                    android.R.layout.simple_spinner_dropdown_item,
                    finalToType
                )
                spinnerTo.adapter = arrayAdpTo
                spinnerTo.setSelection(
                    0,
                    false
                ) // set the list to start fromm position '0'
                // and 'false' set to not start with the by-default selected value
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
//Do nothing// Do nothing
            }
        }
        spinnerFrom.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                from = finalFromType[position]
                updateConvertedValue(etFrom.text.toString().toDoubleOrNull(), from, to, textTo)

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Do nothing
            }
        }

        spinnerTo.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                to = finalToType[position]
                updateConvertedValue(etFrom.text.toString().toDoubleOrNull(), from, to, textTo)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Do nothing
            }
        }


        etFrom.addTextChangedListener(object :
            TextWatcher {    // to continuously watch the changes in this editView
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Do nothing
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Calculate and update the result
                val inputValue = s.toString().toDoubleOrNull()
                updateConvertedValue(inputValue, from, to, textTo)
            }

            override fun afterTextChanged(s: Editable?) {
                // Do nothing
            }
        })
    }
}

private fun updateConvertedValue(
    inputValue: Double?,
    from: String,
    to: String,
    textTo: TextView
) {
    var convertedValue: Double? = null

    // Length conversions
    convertedValue = when (from) {
        "km" -> when (to) {
            "miles" -> inputValue?.times(0.621371)
            "yard" -> inputValue?.times(1093.61)
            "m" -> inputValue?.times(1000)
            "feets" -> inputValue?.times(3280.84)
            "inch" -> inputValue?.times(39370.1)
            "cm" -> inputValue?.times(100000)
            "mm" -> inputValue?.times(1e+6)
            else -> null
        }
        "miles" -> when (to) {
            "km" -> inputValue?.times(1.60934)
            "yard" -> inputValue?.times(1760)
            "m" -> inputValue?.times(1609.34)
            "feets" -> inputValue?.times(5280)
            "inch" -> inputValue?.times(63360)
            "cm" -> inputValue?.times(160934)
            "mm" -> inputValue?.times(1.609e+6)
            else -> null
        }
        "yard" -> when (to) {
            "km" -> inputValue?.div(1093.61)
            "miles" -> inputValue?.div(1760)
            "m" -> inputValue?.times(0.9144)
            "feets" -> inputValue?.times(3)
            "inch" -> inputValue?.times(36)
            "cm" -> inputValue?.times(91.44)
            "mm" -> inputValue?.times(914.4)
            else -> null
        }
        "m" -> when (to) {
            "km" -> inputValue?.div(1000)
            "miles" -> inputValue?.div(1609.34)
            "yard" -> inputValue?.times(1.09361)
            "feets" -> inputValue?.times(3.28084)
            "inch" -> inputValue?.times(39.3701)
            "cm" -> inputValue?.times(100)
            "mm" -> inputValue?.times(1000)
            else -> null
        }
        "feets" -> when (to) {
            "km" -> inputValue?.div(3280.84)
            "miles" -> inputValue?.div(5280)
            "yard" -> inputValue?.div(3)
            "m" -> inputValue?.div(3.28084)
            "inch" -> inputValue?.times(12)
            "cm" -> inputValue?.times(30.48)
            "mm" -> inputValue?.times(304.8)
            else -> null
        }
        "inch" -> when (to) {
            "km" -> inputValue?.div(39370.1)
            "miles" -> inputValue?.div(63360)
            "yard" -> inputValue?.div(36)
            "m" -> inputValue?.div(39.3701)
            "feets" -> inputValue?.div(12)
            "cm" -> inputValue?.times(2.54)
            "mm" -> inputValue?.times(25.4)
            else -> null
        }
        "cm" -> when (to) {
            "km" -> inputValue?.div(100000)
            "miles" -> inputValue?.div(160934)
            "yard" -> inputValue?.div(91.44)
            "m" -> inputValue?.div(100)
            "feets" -> inputValue?.div(30.48)
            "inch" -> inputValue?.div(2.54)
            "mm" -> inputValue?.times(10)
            else -> null
        }
        "mm" -> when (to) {
            "km" -> inputValue?.div(1e+6)
            "miles" -> inputValue?.div(1.609e+6)
            "yard" -> inputValue?.div(914.4)
            "m" -> inputValue?.div(1000)
            "feets" -> inputValue?.div(304.8)
            "inch" -> inputValue?.div(25.4)
            "cm" -> inputValue?.div(10)
            else -> null
        }


        // Weight conversions

        "tons" -> when (to) {
            "quintal" -> inputValue?.times(10)
            "kg" -> inputValue?.times(1000)
            "pounds" -> inputValue?.times(2204.62)
            "gm" -> inputValue?.times(1e+6)
            "mg" -> inputValue?.times(1e+9)
            else -> null
        }
        "quintal" -> when (to) {
            "tons" -> inputValue?.div(10)
            "kg" -> inputValue?.times(100)
            "pounds" -> inputValue?.times(220.462)
            "gm" -> inputValue?.times(100000)
            "mg" -> inputValue?.times(1e+8)
            else -> null
        }
        "kg" -> when (to) {
            "tons" -> inputValue?.div(1000)
            "quintal" -> inputValue?.div(100)
            "pounds" -> inputValue?.times(2.20462)
            "gm" -> inputValue?.times(1000)
            "mg" -> inputValue?.times(1e+6)
            else -> null
        }
        "pounds" -> when (to) {
            "tons" -> inputValue?.div(2204.62)
            "quintal" -> inputValue?.div(220.462)
            "kg" -> inputValue?.div(2.20462)
            "gm" -> inputValue?.times(453.592)
            "mg" -> inputValue?.times(453592)
            else -> null
        }
        "gm" -> when (to) {
            "tons" -> inputValue?.div(1e+6)
            "quintal" -> inputValue?.div(100000)
            "kg" -> inputValue?.div(1000)
            "pounds" -> inputValue?.div(453.592)
            "mg" -> inputValue?.times(1000)
            else -> null
        }
        "mg" -> when (to) {
            "tons" -> inputValue?.div(1e+9)
            "quintal" -> inputValue?.div(1e+8)
            "kg" -> inputValue?.div(1e+6)
            "pounds" -> inputValue?.div(453592)
            "gm" -> inputValue?.div(1000)
            else -> null
        }

        //Volumn conversions

        "L" -> when (to) {
            "gallons" -> inputValue?.times(0.264172)
            "ml" -> inputValue?.times(1000)
            else -> null
        }
        "gallons" -> when (to) {
            "L" -> inputValue?.times(3.78541)
            "ml" -> inputValue?.times(3785.41)
            else -> null
        }
        "ml" -> when (to) {
            "L" -> inputValue?.div(1000)
            "gallons" -> inputValue?.div(3785.41)
            else -> null
        }

// Speed Coversions

        "km/hr" -> when (to) {
            "knots" -> inputValue?.times(0.539957)
            "miles/hr" -> inputValue?.times(0.621371)
            "km/sec" -> inputValue?.div(3600)
            "m/sec" -> inputValue?.div(3.6)
            "m/hr" -> inputValue?.times(1000)
            else -> null
        }
        "knots" -> when (to) {
            "km/hr" -> inputValue?.times(1.852)
            "miles/hr" -> inputValue?.times(1.15078)
            "km/sec" -> inputValue?.div(1944)
            "m/sec" -> inputValue?.div(1.94384)
            "m/hr" -> inputValue?.times(1852)
            else -> null
        }
        "miles/hr" -> when (to) {
            "km/hr" -> inputValue?.times(1.60934)
            "knots" -> inputValue?.times(0.868976)
            "km/sec" -> inputValue?.div(2236.94)
            "m/sec" -> inputValue?.div(2.23694)
            "m/hr" -> inputValue?.times(1609.34)
            else -> null
        }
        "km/sec" -> when (to) {
            "km/hr" -> inputValue?.times(3600)
            "knots" -> inputValue?.times(1943.84)
            "miles/hr" -> inputValue?.times(2236.94)
            "m/sec" -> inputValue
            "m/hr" -> inputValue?.times(1000)
            else -> null
        }
        "m/sec" -> when (to) {
            "km/hr" -> inputValue?.times(3.6)
            "knots" -> inputValue?.times(1.94384)
            "miles/hr" -> inputValue?.times(2.23694)
            "km/sec" -> inputValue
            "m/hr" -> inputValue?.times(3600)
            else -> null
        }
        "m/hr" -> when (to) {
            "km/hr" -> inputValue?.div(1000)
            "knots" -> inputValue?.div(1852)
            "miles/hr" -> inputValue?.div(1609.34)
            "km/sec" -> inputValue?.div(1000)
            "m/sec" -> inputValue?.div(3600)
            else -> null
        }
        //Temperature Conversions
        "Celsius" -> when (to) {
            "Fahrenheit" -> (inputValue?.times(9)?.div(5))?.plus(32)
            "Kelvin" -> inputValue?.plus(273.15)
            else -> null
        }
        "Fahrenheit" -> when (to) {
            "Celsius" -> ((inputValue?.minus(32))?.times(5))?.div(9)
            "Kelvin" -> ((inputValue?.minus(32))?.times(5))?.div(9)?.plus(273.15)
            else -> null
        }
        "Kelvin" -> when (to) {
            "Celsius" -> inputValue?.minus(273.15)
            "Fahrenheit" -> ((inputValue?.minus(273.15))?.times(9))?.div(5)?.plus(32)
            else -> null
        }
        //Area Conversion
        "km sq." -> when (to) {
            "mile sq." -> inputValue?.times(0.386102)
            "yard sq." -> inputValue?.times(1_196_000)
            "feet sq." -> inputValue?.times(10_764_000)
            "m sq." -> inputValue?.times(1_000_000)
            "inch sq." -> inputValue?.times(1_550_000_000)
            "cm sq." -> inputValue?.times(100_000_000)
            "mm sq." -> inputValue?.times(1_000_000_000)
            else -> null
        }
        "mile sq." -> when (to) {
            "km sq." -> inputValue?.times(2.58999)
            "yard sq." -> inputValue?.times(3_097_600)
            "feet sq." -> inputValue?.times(27_878_400)
            "m sq." -> inputValue?.times(2_589_990)
            "inch sq." -> inputValue?.times(4_014_489_600)
            "cm sq." -> inputValue?.times(2_589_990_000)
            "mm sq." -> inputValue?.times(2_589_990_000_000)
            else -> null
        }
        "yard sq." -> when (to) {
            "km sq." -> inputValue?.div(1_196_000)
            "mile sq." -> inputValue?.div(3_097_600)
            "feet sq." -> inputValue?.times(9)
            "m sq." -> inputValue?.times(0.836127)
            "inch sq." -> inputValue?.times(1_296)
            "cm sq." -> inputValue?.times(8_361.27)
            "mm sq." -> inputValue?.times(8_361_270)
            else -> null
        }
        "feet sq." -> when (to) {
            "km sq." -> inputValue?.div(10_764_000)
            "mile sq." -> inputValue?.div(27_878_400)
            "yard sq." -> inputValue?.div(9)
            "m sq." -> inputValue?.times(0.092903)
            "inch sq." -> inputValue?.times(144)
            "cm sq." -> inputValue?.times(929.0304)
            "mm sq." -> inputValue?.times(929_030.4)
            else -> null
        }
        "m sq." -> when (to) {
            "km sq." -> inputValue?.div(1_000_000)
            "mile sq." -> inputValue?.div(2_589_990)
            "yard sq." -> inputValue?.div(0.836127)
            "feet sq." -> inputValue?.div(0.092903)
            "inch sq." -> inputValue?.times(1_550.0031)
            "cm sq." -> inputValue?.times(10_000)
            "mm sq." -> inputValue?.times(1_000_000)
            else -> null
        }
        "inch sq." -> when (to) {
            "km sq." -> inputValue?.div(1_550_000_000)
            "mile sq." -> inputValue?.div(4_014_489_600)
            "yard sq." -> inputValue?.div(1_296)
            "feet sq." -> inputValue?.div(144)
            "m sq." -> inputValue?.div(1_550.0031)
            "cm sq." -> inputValue?.times(6.4516)
            "mm sq." -> inputValue?.times(6_451.6)
            else -> null
        }
        "cm sq." -> when (to) {
            "km sq." -> inputValue?.div(100_000_000)
            "mile sq." -> inputValue?.div(2_589_990_000)
            "yard sq." -> inputValue?.div(8_361.27)
            "feet sq." -> inputValue?.div(929.0304)
            "m sq." -> inputValue?.div(10_000)
            "inch sq." -> inputValue?.div(6.4516)
            "mm sq." -> inputValue?.times(100)
            else -> null
        }
        "mm sq." -> when (to) {
            "km sq." -> inputValue?.div(1_000_000_000)
            "mile sq." -> inputValue?.div(2_589_990_000_000)
            "yard sq." -> inputValue?.div(8_361_270)
            "feet sq." -> inputValue?.div(929_030.4)
            "m sq." -> inputValue?.div(1_000_000)
            "inch sq." -> inputValue?.div(6_451.6)
            "cm sq." -> inputValue?.div(100)
            else -> null
        }
        else -> null
    }




    textTo.text = convertedValue?.toString() ?: ""

}


