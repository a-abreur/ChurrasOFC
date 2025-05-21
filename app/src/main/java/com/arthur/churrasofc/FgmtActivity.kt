package com.arthur.churrasofc

import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment

class FgmtActivity : AppCompatActivity() {
    private lateinit var navCarnes: ImageView
    private lateinit var navGuarni: ImageView
    private lateinit var navBeb: ImageView
    private lateinit var navTemp: ImageView
    internal var qtdeAdultos: Int = 0
    internal var qtdeKids: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        window.statusBarColor = ContextCompat.getColor(this, R.color.white)



        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fgmt)

        qtdeAdultos = intent.getIntExtra("qtdeAdultos", 0)
        qtdeKids = intent.getIntExtra("qtdeKids", 0)


        // Inicializando os botões
        navCarnes = findViewById(R.id.btnCarnes)
        navGuarni = findViewById(R.id.btnGuarni)
        navBeb = findViewById(R.id.btnBeb)
        navTemp = findViewById(R.id.btnTemp)

        // Definir o primeiro fragmento
        replaceFragment(CarnesFragment())
        updateIcons(R.id.btnCarnes)

        // Configurar cliques nos botões
        navCarnes.setOnClickListener {
            replaceFragment(CarnesFragment())
            updateIcons(R.id.btnCarnes)
        }

        navGuarni.setOnClickListener {
            replaceFragment(GuarniFragment())
            updateIcons(R.id.btnGuarni)
        }

        navBeb.setOnClickListener {
            replaceFragment(BebFragment())
            updateIcons(R.id.btnBeb)
        }

        navTemp.setOnClickListener {
            replaceFragment(TempFragment())
            updateIcons(R.id.btnTemp)
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, fragment)
            .commit()
    }

    private fun updateIcons(selectedId: Int) {
        navCarnes.setImageResource(if (selectedId == R.id.btnCarnes) R.drawable.carne1 else R.drawable.carne2)
        navGuarni.setImageResource(if (selectedId == R.id.btnGuarni) R.drawable.gurani1 else R.drawable.guarni2)
        navBeb.setImageResource(if (selectedId == R.id.btnBeb) R.drawable.beb1 else R.drawable.beb2)
        navTemp.setImageResource(if (selectedId == R.id.btnTemp) R.drawable.temp1 else R.drawable.temp2)
    }
}
