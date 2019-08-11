package dependencies

object Dependencies {

    const val KOTLIN = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.KOTLIN}"
    const val OKHTTP = "com.squareup.okhttp3:okhttp:${Versions.OKHTTP}"
    const val OKHTTP_LOGGER = "com.squareup.okhttp3:logging-interceptor:${Versions.OKHTTP}"
    const val RETROFIT = "com.squareup.retrofit2:retrofit:${Versions.RETROFIT}"
    const val RETROFIT_GSON = "com.squareup.retrofit2:converter-gson:${Versions.RETROFIT}"
    const val GSON = "com.google.code.gson:gson:${Versions.GSON}"
    const val MATERIAL_DESING = "com.google.android.material:material:${Versions.SUPPORT_LIBRARY}"
    const val PAGING = "android.arch.paging:runtime:${Versions.PAGING}"
    const val TIMBER = "com.jakewharton.timber:timber:${Versions.TIMBER}"
    const val CIRCLE_IMAGE = "de.hdodenhof:circleimageview:${Versions.CIRCLE_IMAGE}"

    object Android {
        const val EXIF_INTERFACE = "androidx.exifinterface:exifinterface:${Versions.SUPPORT_LIBRARY}"
        const val APP_COMPAT = "androidx.appcompat:appcompat:${Versions.APP_COMPAT}"
        const val FRAGMENT =  "androidx.fragment:fragment:${Versions.SUPPORT_LIBRARY}"
        const val CARD_VIEW = "androidx.cardview:cardview:${Versions.SUPPORT_LIBRARY}"
        const val RECYCLER_VIEW = "androidx.recyclerview:recyclerview:${Versions.SUPPORT_LIBRARY}"
        const val CONSTRAINT_LAYOUT = "androidx.constraintlayout:constraintlayout:${Versions.CONSTRAINT_LAYOUT}"
        const val DESIGN = "com.android.support:design:${Versions.SUPPORT_LIBRARY}"
        const val CORE_COMMON = "androidx.arch.core:core-common:${Versions.CORE_ARCH}"
        const val CORE_RUNTIME = "androidx.arch.core:core-runtime:${Versions.CORE_ARCH}"
    }

    object Lifecycle {
        const val VIEW_MODEL = "androidx.lifecycle:lifecycle-viewmodel:${Versions.LIFECYCLE}"
        const val LIVEDATA = "androidx.lifecycle:lifecycle-livedata:${Versions.LIFECYCLE}"
        const val RUNTIME = "androidx.lifecycle:lifecycle-runtime:${Versions.LIFECYCLE}"
        const val EXTENSIONS = "androidx.lifecycle:lifecycle-extensions:${Versions.LIFECYCLE}"
        const val COMPILER = "androidx.lifecycle:lifecycle-compiler:${Versions.LIFECYCLE}"
    }

    object Navigation {
        const val UI = "androidx.navigation:navigation-ui-ktx:${Versions.NAVIGATION}"
        const val FRAGMENT = "androidx.navigation:navigation-fragment-ktx:${Versions.NAVIGATION}"
    }

    object Room {
        const val COMPILER = "androidx.room:room-compiler:${Versions.ROOM}"
        const val RUNTIME = "androidx.room:room-runtime:${Versions.ROOM}"
    }

    object Test {
        const val JUNIT = "junit:junit:${Versions.JUNIT}"
        const val RUNNER = "androidx.test:runner:${Versions.TEST_RUNNER}"
        const val ESPRESSO = "androidx.test.espresso:espresso-core:${Versions.ESPRESSO}"
    }
}
