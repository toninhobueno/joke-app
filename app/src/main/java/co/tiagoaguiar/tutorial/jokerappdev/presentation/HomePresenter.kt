package co.tiagoaguiar.tutorial.jokerappdev.presentation

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
        val categories = response.map{ Category(it, 0xFFFF0000) }

        view.showCategories(categories)
    }

    override fun onError(response:String) {
        view.showFailure(response)
    }

    override fun onComplete(){
        view.hideProgress()
    }

}