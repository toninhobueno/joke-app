package co.tiagoaguiar.tutorial.jokerappdev.presentation

import android.os.Handler
import android.os.Looper
import co.tiagoaguiar.tutorial.jokerappdev.model.Category
import co.tiagoaguiar.tutorial.jokerappdev.view.CategoryItem
import co.tiagoaguiar.tutorial.jokerappdev.view.HomeFragment

class HomePresenter (private val view : HomeFragment) {

    // VIEW <- PRESENTER
    // PRESENTER -> VIEW

    fun findAllCategories(){
        view.showProgress()
        fakeRequest()
    }

    fun onSuccess(response: List<String>){
        val categories = response.map{ Category(it, 0xFFFF0000) }

        view.showCategories(categories)
    }

    fun onError(message:String) {
        view.showFailure(message)
    }

    fun onComplete(){
        view.hideProgress()
    }

    // Simular requisição Http
    private fun fakeRequest(){
        Handler(Looper.getMainLooper()).postDelayed({
        val response = arrayListOf(
                   "Categoria 1",
                   "Categoria 2",
                   "Categoria 3",
                   "Categoria 4"
        )

            // lista está pronta (response)

            //Devolver falha ou sucesso
         onSuccess(response)

        // onError("FALHA NA CONEXÃO. TENTE NOVAMENTE MAIS TARDE")

        onComplete()
        },4000)
    }

}