package co.tiagoaguiar.tutorial.jokerappdev.presentation

import android.graphics.Color
import co.tiagoaguiar.tutorial.jokerappdev.data.CategoryRemoteDataSource
import co.tiagoaguiar.tutorial.jokerappdev.data.ListCategoryCallback
import co.tiagoaguiar.tutorial.jokerappdev.model.Category
import co.tiagoaguiar.tutorial.jokerappdev.view.HomeFragment

class HomePresenter (
    private val view : HomeFragment,
    private val dataSource: CategoryRemoteDataSource = CategoryRemoteDataSource()
) : ListCategoryCallback {

    // VIEW <- PRESENTER
    // PRESENTER -> VIEW


    //INPUT
    fun findAllCategories(){
        view.showProgress()
        dataSource.findAllCategory(this)
    }


    // output (SUCESSO | FALHA | COMPLETE)
    override fun onSuccess(response: List<String>){
        val start = 45 // H - matiz
        val end = 20 // H - matiz
        val diff  = (end - start) / response.size


        val categories = response.mapIndexed{ index, s->
            val hsv = floatArrayOf(


                start + (diff*index).toFloat(),        // H, matiz
                100.0f,                        // S, saturação
                100.0f                         // V, valor
            )

            Category(s, Color.HSVToColor(hsv).toLong()) }

        view.showCategories(categories)
    }

    override fun onError(response:String) {
        view.showFailure(response)
    }

    override fun onComplete(){
        view.hideProgress()
    }

}