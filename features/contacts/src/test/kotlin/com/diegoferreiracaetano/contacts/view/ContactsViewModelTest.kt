import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.diegoferreiracaetano.Mock
import com.diegoferreiracaetano.contacts.view.ContactsViewModel
import com.diegoferreiracaetano.domain.user.ContactsInteractor
import com.diegoferreiracaetano.domain.user.User
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

internal class ContactsViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val interactor = mockk<ContactsInteractor>()
    private val observe = mockk<Observer<Result<List<User>>>>()
    private lateinit var viewModel: ContactsViewModel

    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = ContactsViewModel(interactor)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `Given interactor contacts When call fetchContacts Then verify result success`() {

        coEvery { interactor.execute() } returns Mock.users()

        viewModel.fetchContacts()
        viewModel.contacts.observeForever(observe)

        verify { observe.onChanged(Result.success(Mock.users())) }
    }

    @Test(expected = Throwable::class)
    fun `Given interactor contacts When call fetchContacts Then verify result error`() {

        val error = Throwable("error")

        coEvery { interactor.execute() } throws error

        viewModel.fetchContacts()
        viewModel.contacts.observeForever(observe)

        verify { observe.onChanged(Result.failure(error)) }
    }
}