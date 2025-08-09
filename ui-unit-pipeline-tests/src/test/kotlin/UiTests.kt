import com.project.playwright.pages.LogInPagePlaywright
import com.project.playwright.pages.MainPagePlaywright
import com.project.config.SaucedemoPlaywrightPipelineTestConfig
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(classes = [SaucedemoPlaywrightPipelineTestConfig::class])
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UiTests{

    companion object{
        private val log: Logger = LoggerFactory.getLogger(UiTests::class.java)
    }

    @Autowired
    private lateinit var logInPagePlaywright: LogInPagePlaywright
    @Autowired
    private lateinit var mainPagePlaywright: MainPagePlaywright

    @Value("\${test.user.password}")
    private lateinit var passwordVar: String
    @Value("\${test.user.userName}")
    private lateinit var userNameVar: String
    @Value("\${test.rest.baseUrl}")
    private lateinit var baseUrlVar: String

    @ParameterizedTest(name = "Test name")
    @MethodSource("com.project.testdata.TestData#testData1")
    fun uiTest(testValue1: String, testValue2: String) {
        logInPagePlaywright
            .openUrl(baseUrlVar)
            .putUserName(userNameVar)
            .putPassword(passwordVar)
            .clickLogInButton()

        log.info("Test data value1: {} , value2: {}", testValue1, testValue2)

        mainPagePlaywright
            .waitUntilProductTitleIsVisible()
            .clickOnHamburgerButton()
            .checkThatMenuWrapIsVisible()
    }
}