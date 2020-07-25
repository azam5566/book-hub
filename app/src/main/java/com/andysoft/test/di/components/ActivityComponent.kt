package com.andysoft.test.di.components

import com.andysoft.test.BaseApp
import com.andysoft.test.di.annotations.PerActivity
import dagger.Subcomponent

@PerActivity
@Subcomponent
interface ActivityComponent {

    fun inject(BaseApp: BaseApp)

    @Subcomponent.Builder
    interface Builder {

        fun build(): ActivityComponent
    }

}