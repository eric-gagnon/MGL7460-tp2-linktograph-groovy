package eric_gagnon.com.github.linktograph

import eric_gagnon.com.github.linktograph.Link
import eric_gagnon.com.github.linktograph.TikaExtractor
import eric_gagnon.com.github.linktograph.Scraper

import java.nio.file.*

class App {

    def rootDir

    def App() {
        rootDir = ".."
    }

    void doProcess() {
        println "doProcess"

        def inputFilePath = Paths.get(rootDir, "input/clean-links.txt")

        def sourceLinks = subProcessGetUniqueLinks(inputFilePath.toString())

        subProcessScrapeFileToCache(sourceLinks)
        subProcessTikaExtract(sourceLinks)
        subProcessExtractWords()
        subProcessPrepareDataForGraph()
        subProcessExportGraph()
    }

    def subProcessGetUniqueLinks(filePath) {
        println "subProcessGetUniqueLinks"
        println "inputFilePath: $filePath"

        return Link.getUniqueLinksFromFile(filePath)
    }

    def subProcessScrapeFileToCache(sourceLinks) {
        println "subProcessScrapFileToCache"
        println "sourceLinks: $sourceLinks"

        def cacheFolder = Paths.get(rootDir, "cache", "web")

        Scraper.ScrapeFilesToCache(sourceLinks, cacheFolder.toString())
    }

    def subProcessTikaExtract(sourceLinks) {
        println "subProcessTikaTika"

        def sourceCacheFolderPath = Paths.get(rootDir, "cache", "web").toString()
        def tikaCacheFolderPath = Paths.get(rootDir, "cache", "tika").toString()

        TikaExtractor.getAllTikaResultsToCache(sourceLinks, sourceCacheFolderPath, tikaCacheFolderPath)
    }

    def subProcessExtractWords() {
        println "subProcessExtractWords"
        /* todo_eg : to be completed. */
    }

    def subProcessPrepareDataForGraph() {
        println "subProcessPrepareDataForGraph"

        // Read back all json. Create an intermediate graph mode?

        /* todo_eg : to be completed. */
        /* Node :
           Link
           downloaded_status
           tika_status
           tika_text
           tika_meta_person --> explode links
           links (missing)
           word (missing)
         */
    }

    def subProcessExportGraph() {
        println "subProcessExportGraph"
        /* todo_eg : to be completed. */
    }
}
