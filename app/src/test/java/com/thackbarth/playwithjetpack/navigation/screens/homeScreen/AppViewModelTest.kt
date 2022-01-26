package com.thackbarth.playwithjetpack.navigation.screens.homeScreen

import com.thackbarth.playwithjetpack.model.Product
import com.thackbarth.playwithjetpack.repos.DatabaseRepo
import com.thackbarth.playwithjetpack.repos.StoreRepo
import junit.framework.TestCase.assertEquals
import junit.framework.TestSuite
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Test

class AppViewModelTest : TestSuite() {


    private val databaseRepo = DatabaseRepo(MockDatabaseDao())
    private val storeRepo = StoreRepo( MockStoreAPI() )
    private val appViewModel = AppViewModel(databaseRepo, storeRepo = storeRepo)

//    private fun buildProductList(): List<Product> {
//        val product = ArrayList<Product>()
//        product.add(Product(1,"soap",1.2f,"cleans", "Kitchen","http://dddd.com"))
//        product.add(Product(2,"soda",3.2f,"drink", "Food","http://dddd.com"))
//        product.add(Product(3,"iPhone",10.2f,"Apple phone", "Tech","http://dddd.com"))
//        product.add(Product(4,"Android",10.2f,"Google phone", "Tech","http://dddd.com"))
//
//        emit( product)
//    }

//    public override fun setUp() {
//        super.setUp()
//        appViewModel.productList = buildProductList()
//    }


    @Test
    fun testBuildCategoryList() {}



    @Test
    fun thisIsATest(){
        assertEquals(4, 2 + 2)
    }




}