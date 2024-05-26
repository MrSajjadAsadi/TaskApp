package ir.sajjadasadi.taskapp.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ir.sajjadasadi.taskapp.mvp.model.ModelMainActivity
import ir.sajjadasadi.taskapp.mvp.presenter.PresenterMainActivity
import ir.sajjadasadi.taskapp.mvp.view.ViewMainActivity

class MainActivity : AppCompatActivity() {
    private lateinit var presenter: PresenterMainActivity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = ViewMainActivity(this)

        setContentView(view.binding.root)
        presenter = PresenterMainActivity(view, ModelMainActivity(this))
        presenter.onCreate()
         view.binding.txtState.text = "کارهای انجام نشده"




    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }
}