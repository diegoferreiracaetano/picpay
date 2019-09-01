import dependencies.Versions

object BuildPlugins {
    const val KOTLIN = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.KOTLIN}"
    const val GRADLE = "com.android.tools.build:gradle:${Versions.GRADLE}"
    const val NAVIGATION = "android.arch.navigation:navigation-safe-args-gradle-plugin:${Versions.NAVIGATION_SAFE_ARGS}"
}
