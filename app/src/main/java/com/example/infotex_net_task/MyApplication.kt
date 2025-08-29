package com.example.infotex_net_task

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

//We need this to add @HiltAndroidApp to app. It need to be there to make hilt work

@HiltAndroidApp
class MyApplication : Application()