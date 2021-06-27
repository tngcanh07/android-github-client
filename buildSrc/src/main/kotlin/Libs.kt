/**
 * Created by toannguyen
 * Jun 26, 2021 at 09:21
 */
object Libs {
    const val KOTLIN_STDLIB = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.KOTLIN}"
    const val KOTLINX_SERIALIZATION =
        "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.KOTLINX_SERIALIZATION}"
    const val COROUTINES_CORE =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.COROUTINES}"
    const val RETROFIT = "com.squareup.retrofit2:retrofit:${Versions.RETROFIT}"
    const val RETROFIT_GSON_CONVERTER = "com.squareup.retrofit2:converter-gson:${Versions.RETROFIT}"
    const val GSON = "com.google.code.gson:gson:${Versions.GSON}"
    const val OKHTTP = "com.squareup.okhttp3:okhttp:${Versions.OKHTTP}"
    const val OKHTTP_LOGGING = "com.squareup.okhttp3:logging-interceptor:${Versions.OKHTTP}"

    const val HILT_ANDROID = "com.google.dagger:hilt-android:${Versions.HILT}"
    const val HILT_COMPILER = "com.google.dagger:hilt-compiler:${Versions.HILT}"
    const val JAVAX_INJECT = "javax.inject:javax.inject:${Versions.JAVAX_INJECT}"

    const val PAGING_RUNTIME = "androidx.paging:paging-runtime:${Versions.PAGING}"
    const val SWIPE_REFRESH_LAYOUT =
        "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.SWIPE_REFRESH_LAYOUT}"

    const val GLIDE_RUNTIME = "com.github.bumptech.glide:glide:${Versions.GLIDE}"
    const val GLIDE_COMPILER = "com.github.bumptech.glide:compiler:${Versions.GLIDE}"

    // Unit Tests
    const val JUNIT = "junit:junit:4.13.2"
    const val MOCKITO = "org.mockito:mockito-core:3.11.2"

}


object devs {
    object Versions {
        // region environments: build, tool, plugin
        const val minSdkVersion = 23
        const val targetSdkVersion = 30
        const val compileSdkVersion = 30
        const val buildToolsVersion = "30.0.3"
        const val appVersion = "4.4.0"

        const val kotlin = "1.4.32"
        const val coroutines = "1.4.2"
        const val gradlePlugin = "4.1.3"
        const val firebaseCrashlyticsPlugin = "2.3.0"
        const val googleServicePlugin = "4.3.4"
        // endregion

        // region app dependencies
        const val javaxInject = "1"
        const val dagger = "2.27"

        const val playServicesBase = "17.1.0"
        const val playServicesAds = "18.2.0"
        const val playServicesMap = "17.0.0"
        const val playServicesLocation = "17.0.0"
        const val playServicesAnalytics = "17.0.0"
        const val playServicesTagManager = "17.0.0"

        const val firebaseBom = "28.0.1"
        const val firebaseCrashlytics = "17.2.2"
        const val firebaseAnalytics = "17.6.0"
        const val firebaseMessaging = "20.3.0"
        const val firebaseAppIndexing = "19.1.0"
        const val firebaseAppConfig = "19.2.0"
        const val firebaseFirestore = "21.7.1"

        const val androidPlayCore = "1.9.1"

        const val lifecycle = "2.2.0"
        const val arch = "1.1.1"

        const val androidxCore = "1.3.0"

        const val androidx = "1.0.0"
        const val supportAnnotation = "1.1.0"
        const val supportAppCompat = "1.2.0"
        const val supportMaterial = "1.2.1"
        const val supportCustomTab = "1.2.0"
        const val supportRecyclerView = "1.2.0"
        const val work = "2.0.1"
        const val constraintLayout = "1.1.3"
        const val swipeRefreshLayout = "1.1.0"
        const val androidArchCore = "2.1.0"
        const val androidLifecycle = "2.2.0"
        const val pagingVersion = "2.1.2"

        const val room = "2.2.3"

        const val retrofit = "2.8.1"
        const val okHttp = "3.14.9"
        const val gson = "2.8.5"

        const val rxJava1 = "1.3.8"
        const val rxAndroid1 = "1.2.1"
        const val rxJava2 = "2.2.7"
        const val rxAndroid2 = "2.1.1"
        const val rxJava3 = "3.0.0"

        const val azureNotificationHubs = "0.5"
        const val azureNotificationHandler = "1.0.1"

        const val glide = "4.11.0"
        const val glideOkHttp3 = "4.11.0"
        const val photoView = "2.3.0"
        const val photoViewOld = "1.2.4"
        const val betterLinkMovement = "2.2.0"
        const val flexBox = "2.0.1"

        const val tune = "4.12.0"
        const val branch = "3.1.1"
        const val aatKit = "2.29.11"
        const val facebook = "9.0.0"

        const val paperdb = "2.6"
        const val butterKnife = "10.1.0"

        const val loopingViewPager = "0.3.0"

        const val rxBinding = "4.0.0"

        const val timber = "4.7.1"
        const val mavenArtifact = "3.3.9"
        // endregion

        // region test dependencies
        const val leakCanary = "2.5"
        const val junit = "4.12"
        const val robolectric = "4.3.1"
        const val mockito = "2.23.0"
        const val mockitoV3 = "3.6.28"
        const val mockitoAndroidTest = "1.10.0"
        const val jraskaLiveData = "1.1.2"
        const val kotlinxCoroutineTesting = "1.4.2"
        const val androidTestRunner = "1.1.1"
        const val androidTestRules = "1.1.1"
        const val androidTestJUnitExt = "1.1.1"
        const val espresso = "3.1.1"
        const val uiautomator = "2.1.3"
        const val appCenter = "1.3"
        const val dexMaker = "1.4"
        const val jsonSimple = "1.1.1"
        const val biometric = "1.0.1"
        const val navigation = "2.2.2"
        const val easyPermission = "3.0.0"
        const val androidLint = "27.2.1"
        const val detekt = "1.16.0"
        const val sonaqube = "3.0"
        // endregion
    }

    object Repos {
        const val gradlePlugins = "https://plugins.gradle.org/m2/"
        const val jitPack = "https://jitPack.io"
        const val microsoftAzure = "http://dl.bintray.com/microsoftazuremobile/SDK"
        const val aatKit = "http://android-sdk.aatkit.com/maven/"
        const val smaato = "https://s3.amazonaws.com/smaato-sdk-releases/"
    }


    object Plugins {
        const val buildGradle = "com.android.tools.build:gradle:${Versions.gradlePlugin}"
        const val kotlinGradle = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
        const val googleService = "com.google.gms:google-services:${Versions.googleServicePlugin}"
        const val firebaseCrashlytics =
            "com.google.firebase:firebase-crashlytics-gradle:${Versions.firebaseCrashlyticsPlugin}"
        const val navigationSafeArgs =
            "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigation}"
    }

    object Deps {
        const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}"
        const val kotlinReflect = "org.jetbrains.kotlin:kotlin-reflect:${Versions.kotlin}"
        const val coroutinesCore =
            "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
        const val coroutinesAndroid =
            "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"

        const val firebaseBom = "com.google.firebase:firebase-bom:${Versions.firebaseBom}"
        const val firebaseCrashlytics =
            "com.google.firebase:firebase-crashlytics-ktx:${Versions.firebaseCrashlytics}"
        const val firebaseAnalytics =
            "com.google.firebase:firebase-analytics:${Versions.firebaseAnalytics}"
        const val firebaseMessaging =
            "com.google.firebase:firebase-messaging:${Versions.firebaseMessaging}"
        const val firebaseAppindexing =
            "com.google.firebase:firebase-appindexing:${Versions.firebaseAppIndexing}"
        const val firebaseConfig =
            "com.google.firebase:firebase-config:${Versions.firebaseAppConfig}"
        const val firebaseFirestore =
            "com.google.firebase:firebase-firestore-ktx:${Versions.firebaseFirestore}"

        const val playServicesBase =
            "com.google.android.gms:play-services-base:${Versions.playServicesBase}"
        const val playServicesMaps =
            "com.google.android.gms:play-services-maps:${Versions.playServicesMap}"
        const val playServicesLocation =
            "com.google.android.gms:play-services-location:${Versions.playServicesLocation}"
        const val playServicesAnalytics =
            "com.google.android.gms:play-services-analytics:${Versions.playServicesAnalytics}"
        const val playServicesTagManager =
            "com.google.android.gms:play-services-tagmanager:${Versions.playServicesTagManager}"
        const val playServicesAds =
            "com.google.android.gms:play-services-ads:${Versions.playServicesAds}"


        const val lifecycleViewModel =
            "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
        const val lifecycleLiveData =
            "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
        const val archExtensions = "android.arch.lifecycle:extensions:${Versions.arch}"
        const val archViewModel = "android.arch.lifecycle:viewmodel:${Versions.arch}"

        const val androidxCore = "androidx.core:core-ktx:${Versions.androidxCore}"
        const val androidxFragment = "androidx.fragment:fragment:${Versions.androidxCore}"
        const val androidxFragmentKtx = "androidx.fragment:fragment-ktx:${Versions.androidxCore}"

        const val androidPlayCore = "com.google.android.play:core:${Versions.androidPlayCore}"

        const val supportAnnotation = "androidx.annotation:annotation:${Versions.supportAnnotation}"
        const val supportCoreUtils =
            "androidx.legacy:legacy-support-core-utils:${Versions.androidx}"
        const val supportLegacyV4 = "androidx.legacy:legacy-support-v4:${Versions.androidx}"
        const val supportDesign = "com.google.android.material:material:${Versions.supportMaterial}"
        const val supportAppCompat = "androidx.appcompat:appcompat:${Versions.supportAppCompat}"
        const val supportCustomTabs = "androidx.browser:browser:${Versions.supportCustomTab}"
        const val supportCardView = "androidx.cardview:cardview:${Versions.androidx}"
        const val supportRecyclerView =
            "androidx.recyclerview:recyclerview:${Versions.supportRecyclerView}"
        const val workRuntime = "androidx.work:work-runtime-ktx:${Versions.work}"
        const val workTesting = "androidx.work:work-testing:${Versions.work}"
        const val constraintLayout =
            "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
        const val swipeRefreshLayout =
            "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.swipeRefreshLayout}"
        const val androidArchCoreCommon =
            "androidx.arch.core:core-common:${Versions.androidArchCore}"
        const val androidArchCoreRuntime =
            "androidx.arch.core:core-runtime:${Versions.androidArchCore}"
        const val androidArchCoreTesting =
            "androidx.arch.core:core-testing:${Versions.androidArchCore}"
        const val androidArchCoreExtensions =
            "android.arch.lifecycle:extensions:${Versions.androidArchCore}"
        const val androidArchCoreViewModel =
            "android.arch.lifecycle:viewmodel:${Versions.androidArchCore}"
        const val androidArchCoreViewModelKtx =
            "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.androidArchCore}"
        const val mockitoInline = "org.mockito:mockito-inline:${Versions.mockitoV3}"
        const val jraskaLiveDataTesting =
            "com.jraska.livedata:testing-ktx:${Versions.jraskaLiveData}"
        const val kotlinxCoroutinetesting =
            "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.kotlinxCoroutineTesting}"
        const val androidLifecycleReactivestreams =
            "androidx.lifecycle:lifecycle-reactivestreams:${Versions.androidLifecycle}"
        const val androidLifecycleReactivestreamsKtx =
            "androidx.lifecycle:lifecycle-reactivestreams-ktx:${Versions.androidLifecycle}"
        const val pagingLib = "androidx.paging:paging-runtime:${Versions.pagingVersion}"
        const val rxPaging = "androidx.paging:paging-rxjava2:${Versions.pagingVersion}"

        const val rxJava1 = "io.reactivex:rxjava:${Versions.rxJava1}"
        const val rxAndroid1 = "io.reactivex:rxandroid:${Versions.rxAndroid1}"
        const val rxJava2 = "io.reactivex.rxjava2:rxjava:${Versions.rxJava2}"
        const val rxAndroid2 = "io.reactivex.rxjava2:rxandroid:${Versions.rxAndroid2}"
        const val rxJava3 = "io.reactivex.rxjava3:rxjava:${Versions.rxJava3}"
        const val rxAndroid3 = "io.reactivex.rxjava3:rxandroid:${Versions.rxJava3}"
        const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
        const val retrofitGsonConverter =
            "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
        const val retrofitRxJavaAdapter =
            "com.squareup.retrofit2:adapter-rxjava:${Versions.retrofit}"
        const val retrofitRxJava2Adapter =
            "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofit}"
        const val okHttp = "com.squareup.okhttp3:okhttp:${Versions.okHttp}"
        const val mockWebServer = "com.squareup.okhttp3:mockwebserver:${Versions.okHttp}"
        const val okHttpUrlConnection =
            "com.squareup.okhttp3:okhttp-urlconnection:${Versions.okHttp}"
        const val okHttpLogging = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttp}"
        const val gson = "com.google.code.gson:gson:${Versions.gson}"

        const val room = "androidx.room:room-runtime:${Versions.room}"
        const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"
        const val rxRoom = "androidx.room:room-rxjava2:${Versions.room}"
        const val roomKtx = "androidx.room:room-ktx:${Versions.room}"

        const val azureNotificationsHubs =
            "com.microsoft.azure:notification-hubs-android-sdk:${Versions.azureNotificationHubs}@aar"
        const val azureNotificationsHandler =
            "com.microsoft.azure:azure-notifications-handler:${Versions.azureNotificationHandler}@aar"
        const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
        const val glideCompiler = "com.github.bumptech.glide:compiler:${Versions.glide}"
        const val glideOkHttpIntegration =
            "com.github.bumptech.glide:okhttp3-integration:${Versions.glideOkHttp3}"
        const val photoView = "com.github.chrisbanes:PhotoView:${Versions.photoView}"
        const val photoViewOld = "com.github.chrisbanes.photoview:library:${Versions.photoViewOld}"

        const val flexBox = "com.google.android:flexbox:${Versions.flexBox}"

        const val betterLinkMovement =
            "me.saket:better-link-movement-method:${Versions.betterLinkMovement}"

        const val tune = "com.tune:tune-marketing-console-sdk:${Versions.tune}"
        const val branch = "io.branch.sdk.android:library:${Versions.branch}"
        const val aatKit = "com.intentsoftware.addapptr:AATKit:${Versions.aatKit}"
        const val facebookCore = "com.facebook.android:facebook-core:${Versions.facebook}"

        const val dagger = "com.google.dagger:dagger:${Versions.dagger}"
        const val daggerSupportLib = "com.google.dagger:dagger-android-support:${Versions.dagger}"
        const val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"
        const val daggerAndroidProcessor =
            "com.google.dagger:dagger-android-processor:${Versions.dagger}"
        const val javaxInject = "javax.inject:javax.inject:${Versions.javaxInject}"

        const val paperDb = "io.paperdb:paperdb:${Versions.paperdb}"

        const val loopingViewPager =
            "com.kenilt.loopingviewpager:loopingviewpager:${Versions.loopingViewPager}"

        const val rxBinding = "com.jakewharton.rxbinding4:rxbinding:${Versions.rxBinding}"
        const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
        const val butterKnife = "com.jakewharton:butterknife:${Versions.butterKnife}"
        const val butterKnifeCompiler =
            "com.jakewharton:butterknife-compiler:${Versions.butterKnife}"
        const val leakCanary = "com.squareup.leakcanary:leakcanary-android:${Versions.leakCanary}"

        const val mavenArtifact = "org.apache.maven:maven-artifact:${Versions.mavenArtifact}"
        const val junit = "junit:junit:${Versions.junit}"
        const val robolectric = "org.robolectric:robolectric:${Versions.robolectric}"
        const val mockito = "org.mockito:mockito-core:${Versions.mockito}"
        const val mockitoV3 = "org.mockito:mockito-core:${Versions.mockitoV3}"
        const val mockitoAndroidTest = "org.mockito:mockito-core:${Versions.mockitoAndroidTest}"
        const val androidTestRunner = "androidx.test:runner:${Versions.androidTestRunner}"
        const val androidTestRules = "androidx.test:rules:${Versions.androidTestRules}"
        const val androidTestJUnitExt = "androidx.test.ext:junit:${Versions.androidTestJUnitExt}"
        const val dexMaker = "com.crittercism.dexmaker:dexmaker:${Versions.dexMaker}"
        const val dexMakerDx = "com.crittercism.dexmaker:dexmaker-dx:${Versions.dexMaker}"
        const val dexMakerMockito = "com.crittercism.dexmaker:dexmaker-mockito:${Versions.dexMaker}"

        const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
        const val espressoContrib = "androidx.test.espresso:espresso-contrib:${Versions.espresso}"
        const val espressoIntents = "androidx.test.espresso:espresso-intents:${Versions.espresso}"
        const val appCenterEspressoSupport =
            "com.xamarin.testcloud:espresso-support:${Versions.appCenter}"
        const val uiAutomator =
            "com.android.support.test.uiautomator:uiautomator-v18:${Versions.uiautomator}"
        const val jsonSimple = "com.googlecode.json-simple:json-simple:${Versions.jsonSimple}"
        const val biometric = "androidx.biometric:biometric:${Versions.biometric}"

        const val navFragment = "androidx.navigation:navigation-fragment:${Versions.navigation}"
        const val navFragmentKtx =
            "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
        const val navUI = "androidx.navigation:navigation-ui:${Versions.navigation}"
        const val navUIKtx = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
        const val easyPermission = "pub.devrel:easypermissions:${Versions.easyPermission}"

        // lint
        const val lintApi = "com.android.tools.lint:lint-api:${Versions.androidLint}"
        const val lintChecks = "com.android.tools.lint:lint-checks:${Versions.androidLint}"
        const val lintCore = "com.android.tools.lint:lint:${Versions.androidLint}"
        const val lintTests = "com.android.tools.lint:lint-tests:${Versions.androidLint}"

        const val featureVehicleDetail = ":feature_vehicle_detail"
        const val featureDealerReview = ":feature_dealer_review"
        const val core = ":core"
        const val coreConsumer = ":core_consumer"
        const val legacy = ":legacy"
        const val components = ":components"
        const val sharedComponent = ":shared_components"
        const val libYoutubeAndroidPlayer = ":lib_youtube_android_player"

        const val indefinitePagerIndicator = "com.ryanjeffreybrooks:indefinitepagerindicator:1.0.10"
    }

}