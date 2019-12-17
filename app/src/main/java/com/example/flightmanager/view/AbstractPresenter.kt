package com.example.flightmanager.view

import org.koin.sampleapp.util.mvp.BasePresenter
import org.koin.sampleapp.util.mvp.BaseView

abstract class AbstractPresenter<V:BaseView<P>,out P :BasePresenter<V>>:BasePresenter<V> {
    override lateinit var view: V

}