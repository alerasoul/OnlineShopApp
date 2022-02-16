package com.example.onlineshopapp.module

import com.example.onlineshopapp.api.customer.UserApi
import com.example.onlineshopapp.api.invoice.InvoiceApi
import com.example.onlineshopapp.api.invoice.TransactionApi
import com.example.onlineshopapp.api.product.ColorApi
import com.example.onlineshopapp.api.product.ProductApi
import com.example.onlineshopapp.api.product.ProductCategoryApi
import com.example.onlineshopapp.api.site.BlogApi
import com.example.onlineshopapp.api.site.ContentApi
import com.example.onlineshopapp.api.site.SliderApi
import com.example.onlineshopapp.repository.customer.UserRepository
import com.example.onlineshopapp.repository.invoice.InvoiceRepository
import com.example.onlineshopapp.repository.invoice.TransactionRepository
import com.example.onlineshopapp.repository.product.ColorRepository
import com.example.onlineshopapp.repository.product.ProductCategoryRepository
import com.example.onlineshopapp.repository.product.ProductRepository
import com.example.onlineshopapp.repository.site.BlogRepository
import com.example.onlineshopapp.repository.site.ContentRepository
import com.example.onlineshopapp.repository.site.SliderRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    @Singleton
    fun provideSliderRepository(api: SliderApi) = SliderRepository(api)

    @Provides
    @Singleton
    fun provideContent(api: ContentApi) = ContentRepository(api)

    @Provides
    @Singleton
    fun provideBlog(api: BlogApi) = BlogRepository(api)


    @Provides
    @Singleton
    fun provideColor(api: ColorApi) = ColorRepository(api)

    @Provides
    @Singleton
    fun provideProductCategory(api: ProductCategoryApi) = ProductCategoryRepository(api)

    @Provides
    @Singleton
    fun provideProduct(api: ProductApi) = ProductRepository(api)

    @Provides
    @Singleton
    fun provideInvoice(api: InvoiceApi) = InvoiceRepository(api)

    @Provides
    @Singleton
    fun provideTransaction(api: TransactionApi) = TransactionRepository(api)

    @Provides
    @Singleton
    fun provideUser(api: UserApi) = UserRepository(api)

}