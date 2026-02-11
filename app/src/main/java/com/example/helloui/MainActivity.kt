package com.example.helloui // Sesuaikan package name kamu

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.google.android.material.switchmaterial.SwitchMaterial
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {

    private lateinit var tvHasil: TextView
    private lateinit var etNama: TextInputEditText // Tipe berubah jadi TextInputEditText
    private lateinit var switchMode: SwitchMaterial

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inisialisasi View
        etNama = findViewById(R.id.etNama)
        tvHasil = findViewById(R.id.tvHasil)
        switchMode = findViewById(R.id.switchMode)
        val btnSapa = findViewById<Button>(R.id.btnSapa)

        // Restore State (Agar tulisan tidak hilang saat ganti tema)
        if (savedInstanceState != null) {
            tvHasil.text = savedInstanceState.getString("STATE_HASIL")
        }

        // Logic Tombol Sapa
        btnSapa.setOnClickListener {
            val nama = etNama.text.toString().trim()
            if (nama.isNotEmpty()) {
                tvHasil.text = "Hello, $nama! 👋" // Ada emoji tangan
            } else {
                etNama.error = "Nama wajib diisi!"
            }
        }

        // Logic Switch Mode (KEMBALI KE TEKS)
        val currentMode = AppCompatDelegate.getDefaultNightMode()
        val isDarkMode = (currentMode == AppCompatDelegate.MODE_NIGHT_YES)

        // Set kondisi awal
        switchMode.isChecked = isDarkMode
        updateSwitchText(isDarkMode)

        switchMode.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
            // Teks akan update otomatis karena activity di-recreate
        }
    }

    // Fungsi helper untuk ubah teks switch
    private fun updateSwitchText(isDarkMode: Boolean) {
        if (isDarkMode) {
            switchMode.text = "Mode Gelap 🌙"
        } else {
            switchMode.text = "Mode Terang ☀️"
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("STATE_HASIL", tvHasil.text.toString())
    }
}