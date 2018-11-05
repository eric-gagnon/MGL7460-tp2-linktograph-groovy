package test.scraper

import eric_gagnon.com.github.linktograph.Scraper

import java.nio.file.Paths

class ScraperTest extends GroovyTestCase {
    def downloadTestUrl

    def ScraperTest() {
        downloadTestUrl = "https://raw.githubusercontent.com/eric-gagnon/mgl7460-tp2-linktograph-groovy/master/src/test/scraper/testdata/downloadtest.txt"
    }

    void testCache() {
        def temp = getTempTestFolder()

        def links = [downloadTestUrl]
        Scraper.ScrapFilesToCache(links, temp.toString())

        def cacheFilePath = Scraper.getCacheFilePath(downloadTestUrl, temp.toString())
        def downloadedContent = new File(cacheFilePath).getText('UTF-8')

        assertEquals("I was downloaded successfully!", downloadedContent)
    }

    void testCacheWithBadParameter() {
        def temp = getTempTestFolder()

        Scraper.ScrapFilesToCache(downloadTestUrl /* Bad parameter, not in a list */, temp.toString())
        def cacheFilePath = Scraper.getCacheFilePath(downloadTestUrl, temp.toString())

        // File should not have been downloaded at all. Not empty created file either.
        assert !new File(cacheFilePath).exists()
    }

    def getTempTestFolder() {
        File temp = File.createTempDir("linktograph-", "-download-test")
        // todo_eg in ScrapFilesToCache if downloadTestUrl is not a list.
        println "Temporary test folder created in : $temp"

        return temp
    }
}
