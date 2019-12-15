package com.diegoferreiracaetano.users

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.diegoferreiracaetano.commons.setImageUrl
import de.hdodenhof.circleimageview.CircleImageView
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ExtensionsKtTest {

    @Test
    fun `Given CircleImageView when call setImageUrl should verify not null`() {

        val context = ApplicationProvider.getApplicationContext<Context>()
        val image = CircleImageView(context)

        image.setImageUrl("https://randomuser.me/api/portraits/men/35.jpg")

        assertNotNull(image)
    }
}
