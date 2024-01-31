package co.tiagoaguiar.tutorial.jokerappdev.presentation

import android.os.Handler
import android.os.Looper
import co.tiagoaguiar.tutorial.jokerappdev.model.Category
import co.tiagoaguiar.tutorial.jokerappdev.view.HomeFragment

class HomePresenter(private val view: HomeFragment) {
    // INPUT
    fun findAllCategories() {
        view.showProgress()
        fakeRequest()
    }

    // OUTPUT (sucesso | falha | complete)
    fun onSuccess(response: List<String>) {
        val categories = response.map { Category(it, 0xFFFACE6E) }
        view.showCategories(categories)
    }

    fun onError(message: String) {
        view.showFailure(message)
    }

    fun onComplete() {
        view.hideProgress()
    }

    // Testing purpose
    private fun fakeRequest() {
        Handler(Looper.getMainLooper()).postDelayed({
            val response = arrayListOf(
                "Categoria 1",
                "Categoria 2",
                "Categoria 3",
                "Categoria 4"
            )

            onSuccess(response)
//            onError("falha de conexão")
            onComplete()
        }, 3000)
    }
}