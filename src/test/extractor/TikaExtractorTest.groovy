package test

import eric_gagnon.com.github.linktograph.Link
import eric_gagnon.com.github.linktograph.TikaExtractor

import java.nio.file.Paths

class TikaExtractorTest extends GroovyTestCase {

    /* Require running tika. */
    /* todo_eg : test should check if tika is available? */

    void testGetTikaRMetaAndTextResponseToCache() {

        def inputFileFolderPath = Paths.get("src", "test", "extractor", "testdata")
        def testFilename = "b9c2c0469b9101d66667b79166061f7e4d62c9ef"
        def fakeLink = "http://placeholder_for_test_not_a_real_url/"

        def cacheFolderPath = TestFolder.getTempTestFolder("tika")
        TikaExtractor.getTikaRMetaAndTextResponseToCache(fakeLink, testFilename, inputFileFolderPath.toString(), cacheFolderPath.toString())

        // 1. Check file exist.
        def cacheFilePath = Paths.get(cacheFolderPath.toString(), testFilename).toString()
        assert new File(cacheFilePath).exists()

        // 2. Check content can be parsed to json.
        def jsonText = new File(cacheFilePath).getText('UTF-8')

        def json = null

        try {
            json = new groovy.json.JsonSlurper().parseText(jsonText)
        } catch (e) {
            // Swallow the error.
        }
        assertNotNull json
    }

    void testGetAllTikaResultsToCache() {
        /* todo_eg : test to be revised, too dependant on previous steps. */
        /*
        def sourceCacheFolderPath = Paths.get("cache","web").toString()
        def tikaCacheFolderPath = Paths.get("cache","tika").toString()
        def linkPath = Paths.get("input","clean-links.txt").toString()
        def links = Link.getUniqueLinksFromFile(linkPath)
        TikaExtractor.getAllTikaResultsToCache(links, sourceCacheFolderPath, tikaCacheFolderPath)
        */
    }
}
