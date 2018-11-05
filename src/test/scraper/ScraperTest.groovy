package test

import eric_gagnon.com.github.linktograph.Scraper

class ScraperTest extends GroovyTestCase {
    def downloadTestUrl

    def ScraperTest() {
        downloadTestUrl = "https://raw.githubusercontent.com/eric-gagnon/mgl7460-tp2-linktograph-groovy/master/src/test/scraper/testdata/downloadtest.txt"
    }

    void testCache() {
        def temp = TestFolder.getTempTestFolder("scraper")

        def links = [downloadTestUrl]
        Scraper.ScrapeFilesToCache(links, temp.toString())

        def cacheFilePath = Scraper.getCacheFilePath(downloadTestUrl, temp.toString())
        def downloadedContent = new File(cacheFilePath).getText('UTF-8')

        assertEquals("I was downloaded successfully!", downloadedContent)
    }

    void testCacheWithBadParameter() {
        def temp = TestFolder.getTempTestFolder()

        Scraper.ScrapeFilesToCache(downloadTestUrl /* Bad parameter, not in a list */, temp.toString())
        def cacheFilePath = Scraper.getCacheFilePath(downloadTestUrl, temp.toString())

        // File should not have been downloaded at all. Not empty created file either.
        assert !new File(cacheFilePath).exists()
    }
}
