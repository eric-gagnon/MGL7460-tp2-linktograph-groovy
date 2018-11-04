import eric_gagnon.com.github.linktograph.App

class AppTest extends GroovyTestCase {

    void testAppCreation() {
        println("test testAppCreation called.")
        def app = new App()
        // Dumb test.
        assertNotNull(app)
    }
}