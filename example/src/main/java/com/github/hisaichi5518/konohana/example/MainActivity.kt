package com.github.hisaichi5518.konohana.example

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView
import com.github.hisaichi5518.konohana.example.store.Konohana
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import java.util.Arrays

class MainActivity : AppCompatActivity() {

    private val disposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val konohana = Konohana(this)
        val userStore = konohana.storeOfUser()
        val itemStore = konohana.storeOfItem()

        val textView = findViewById<TextView>(R.id.user_name)
        val button = findViewById<Button>(R.id.name_changer)

        userStore.nameAsObservable().subscribe { textView.text = it }.addTo(disposable)
        itemStore.nameAsObservable().subscribe { button.text = it }.addTo(disposable)

        button.setOnClickListener {
            val names = Arrays.asList(
                    "yuriko",
                    "yoshitaka",
                    "yoshitaka-yuriko",
                    "yuriko-yoshitaka",
                    "吉高由里子",
                    "由里子",
                    "吉高")
            names.shuffle()

            val name = names.first()
            userStore.name = name
            itemStore.name = name
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }
}

private fun Disposable.addTo(disposable: CompositeDisposable) {
    disposable.add(this)
}
